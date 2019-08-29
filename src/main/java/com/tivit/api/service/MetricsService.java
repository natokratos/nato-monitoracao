package com.tivit.api.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeMBeanException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.tivit.api.domain.MemoryType;
import com.tivit.api.domain.SeedType;
import com.tivit.api.entity.Heading;
import com.tivit.api.entity.MemorySpace;
import com.tivit.api.entity.Metrics;
import com.tivit.api.entity.OperatingSystem;
import com.tivit.api.entity.RequestProcessor;
import com.tivit.api.entity.Seed;
import com.tivit.api.entity.ThreadPool;
import com.tivit.api.entity.Threading;
import com.tivit.api.repository.MetricsRepository;
import com.tivit.api.repository.SeedRepository;

@Service
public class MetricsService {
	
	@Autowired
	SeedRepository seedRepository;
	
	@Autowired
	MetricsRepository metricsRepository;
	
	private String vmName = "";
	private MBeanServerConnection mbeanConn = null;
	
	public List<VirtualMachineDescriptor> getVmList() {
		return VirtualMachine.list();
	}
	
	public Heading getHeading(String canonicalName) {
		Heading heading = new Heading();
		String [] hs = canonicalName.split(":");
	
		if(hs.length > 1) {
	    	String [] line = hs[1].split(",");

	    	heading.setHeader(hs[0]);
	    	for(String l : line) {
	        	String [] attr = l.split("=");
	        	if(attr[0].equals("name")) {
	        		heading.setName(attr[1]);
	        	}
	        	if(attr[0].equals("type")) {
	        		heading.setType(attr[1]);
	        	}
	    	}
		}
		
		return heading;
	}
	
	public Set<ObjectName> attachToVm(VirtualMachineDescriptor vmd) {
		Set<ObjectName> beanSet = null;
		
	    try {
			VirtualMachine vm = VirtualMachine.attach(vmd);
			
			//System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDD " + vm.id());
		    Properties props = vm.getAgentProperties();
		    //System.out.println(props.toString());
		    //Properties props1 = vm.getSystemProperties();
		    //System.out.println(props1.toString());
		    
		    Pattern pattern = Pattern.compile("sun.java.command=(.*?)( |}|$)");
		    Matcher matcher = pattern.matcher(props.toString());
		    vmName = "";
		    while (matcher.find()) {
		        vmName += matcher.group(1);
		    }
		    
		    Thread.sleep(100);
		    //System.out.println(props.toString());
		    String connectorAddress = props	.getProperty("com.sun.management.jmxremote.localConnectorAddress");
		    
		    JMXServiceURL url = null;
		    if (connectorAddress == null) { // && !vmName.contains("JConsole")) {
		    	System.out.println("No Connector Address!!!");
		    	
		    	loadManagementAgent(vm);
			    props = vm.getAgentProperties();
			    connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
		    	//url = new JMXServiceURL("service:jmx:rmi://localhost:1099/localhost/myjmxconnector");

		    	//JMXConnector connector = JMXConnectorFactory.newJMXConnector(url,null);
		    	//connector.connect();

		    	//mbeanConn = connector.getMBeanServerConnection();
		        //beanSet = mbeanConn.queryNames(null, null);
		    } //else {
		    url = new JMXServiceURL(connectorAddress);
		    JMXConnector connector = JMXConnectorFactory.connect(url);

	    	mbeanConn = connector.getMBeanServerConnection();
	        beanSet = mbeanConn.queryNames(null, null);
	        
	        return beanSet;
		    //}
	    } catch (AttachNotSupportedException e) {
	    	System.out.println("AttachNotSupportedException!!!");
			e.printStackTrace();
	    } catch (IOException e) {
	    	System.out.println("IOException!!!");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException!!!");
			e.printStackTrace();
		} catch (AgentLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AgentInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return beanSet;
	}

    // load the management agent into the target VM
    private void loadManagementAgent(VirtualMachine vm) throws IOException, AgentLoadException, AgentInitializationException {
        String home = vm.getSystemProperties().getProperty("java.home");

        // Normally in ${java.home}/jre/lib/management-agent.jar but might
        // be in ${java.home}/lib in build environments.

        String agent = home + File.separator + "jre" + File.separator +
                           "lib" + File.separator + "management-agent.jar";
        File f = new File(agent);
        if (!f.exists()) {
            agent = home + File.separator +  "lib" + File.separator +
                        "management-agent.jar";
            f = new File(agent);
            if (!f.exists()) {
                throw new IOException("Management agent not found");
            }
        }

        agent = f.getCanonicalPath();
        vm.loadAgent(agent, "com.sun.management.jmxremote");

        // get the connector address
        //Properties agentProps = vm.getAgentProperties();
        //address = (String) agentProps.get(LOCAL_CONNECTOR_ADDRESS_PROP);

        //vm.detach();
    }
    
	public Threading parseThreading(ObjectName on) throws InstanceNotFoundException, IntrospectionException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
		Threading t = new Threading();
		
    	for(MBeanAttributeInfo mb : mbeanConn.getMBeanInfo(on).getAttributes()) {
    		try {
    			if(mb.getName().equals("CurrentThreadCpuTime")) {
    				t.setCurrentThreadCPUTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("CurrentThreadUserTime")) {
    				t.setCurrentThreadUserTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("DaemonThreadCount")) {
    				t.setDaemonThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("PeakThreadCount")) {
    				t.setPeakThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("ThreadCount")) {
    				t.setThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("TotalStartedThreadCount")) {
    				t.setTotalStartedThreadCount((long)mbeanConn.getAttribute(on, mb.getName()));
    			} 
    		} catch(RuntimeMBeanException e) {
    			System.out.println("Erro " + e);
    		}
    	}
    	
    	t.setThreadingId(getThreadingSeed());
    	
    	return t;
	}
	
	public ThreadPool parseThreadPool(ObjectName on) throws InstanceNotFoundException, IntrospectionException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
		ThreadPool t = new ThreadPool();

    	for(MBeanAttributeInfo mb : mbeanConn.getMBeanInfo(on).getAttributes()) {
    		try {
    			if(mb.getName().equals("acceptCount")) {
    				t.setAcceptCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("acceptorThreadCount")) {
    				t.setAcceptorThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("acceptorThreadPriority")) {
    				t.setAcceptorThreadPriority((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("connectionCount")) {
    				t.setConnectionCount((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("connectionLinger")) {
    				t.setConnectionLinger((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("currentThreadCount")) {
    				t.setCurrentThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
				} else if(mb.getName().equals("currentThreadBusy")) {
					t.setCurrentThreadBusy((int)mbeanConn.getAttribute(on, mb.getName()));
				} else if(mb.getName().equals("keepAliveCount")) {
					t.setKeepAliveCount((int)mbeanConn.getAttribute(on, mb.getName()));
				} else if(mb.getName().equals("pollerThreadCount")) {
					t.setPollerThreadCount((int)mbeanConn.getAttribute(on, mb.getName()));
				} else if(mb.getName().equals("pollerThreadPriority")) {
					t.setPollerThreadPriority((int)mbeanConn.getAttribute(on, mb.getName()));
				} else if(mb.getName().equals("running")) {
					t.setRunning((boolean)mbeanConn.getAttribute(on, mb.getName()));
				} 
    		} catch(RuntimeMBeanException e) {
    			System.out.println("Erro " + e);
    		}
    	}
    	
    	t.setThreadPoolId(getThreadPoolSeed());
    	
    	return t;
	}	
		
	public OperatingSystem parseOperatingSystem(ObjectName on) throws InstanceNotFoundException, IntrospectionException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
		OperatingSystem os = new OperatingSystem();
		
    	for(MBeanAttributeInfo mb : mbeanConn.getMBeanInfo(on).getAttributes()) {
    		try {
    			if(mb.getName().equals("Name")) {
    				os.setName((String)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("Arch")) {
    				os.setArch((String)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("Version")) {
    				os.setVersion((String)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("ProcessCpuTime")) {
    				os.setProcessCPUTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("SystemCpuLoad")) {
    				os.setSystemCPULoad((double)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("ProcessCpuLoad")) {
    				os.setProcessCPULoad((double)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("SystemLoadAverage")) {
    				os.setSystemLoadAverage((double)mbeanConn.getAttribute(on, mb.getName()));
    			} 
    		} catch(RuntimeMBeanException e) {
    			System.out.println("Erro " + e);
    		}
    	}
    	
    	os.setOperatingSystemId(getOperatingSystemSeed());
    	
    	return os;
	}

	public List<MemorySpace> parseMemorySpace(ObjectName on) throws InstanceNotFoundException, IntrospectionException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
		ArrayList<MemorySpace> lms = new ArrayList<MemorySpace>();
		CompositeDataSupport mu = null;
		MemorySpace ms = null;
	
    	for(MBeanAttributeInfo mb : mbeanConn.getMBeanInfo(on).getAttributes()) {
    		try {
    			if(mb.getName().equals("HeapMemoryUsage")) {
        			mu = (CompositeDataSupport)mbeanConn.getAttribute(on, mb.getName());
    				
        			ms = new MemorySpace();
        			ms.setInitial((long)mu.get("init"));
    				ms.setMax((long)mu.get("max"));
    				ms.setUsed((long)mu.get("used"));
    				ms.setMemoryType(MemoryType.HEAP);

    		    	ms.setMemorySpaceId(getMemorySpaceSeed());
    		    	
    				lms.add(ms);
    			} else if(mb.getName().equals("NonHeapMemoryUsage")) {
        			mu = (CompositeDataSupport)mbeanConn.getAttribute(on, mb.getName());

        			ms = new MemorySpace();
    				ms.setInitial((long)mu.get("init"));
    				ms.setMax((long)mu.get("max"));
    				ms.setUsed((long)mu.get("used"));
    				ms.setMemoryType(MemoryType.NON_HEAP);

    		    	ms.setMemorySpaceId(getMemorySpaceSeed());
    		    	
    				lms.add(ms);
    			}
    		} catch(RuntimeMBeanException e) {
    			System.out.println("Erro " + e);
    		}
    	}
    	
    	return lms;
	}

	public RequestProcessor parseRequestProcessor(ObjectName on) throws InstanceNotFoundException, IntrospectionException, AttributeNotFoundException, ReflectionException, MBeanException, IOException {
		RequestProcessor os = new RequestProcessor();
		
    	for(MBeanAttributeInfo mb : mbeanConn.getMBeanInfo(on).getAttributes()) {
    		try {
    			if(mb.getName().equals("rpName")) {
    				os.setRpName(mbeanConn.getAttribute(on, mb.getName()).toString());
    			} else if(mb.getName().equals("maxRequestUri")) {
    				os.setMaxRequestUri((String)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("errorCount")) {
    				os.setErrorCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("lastRequestProcessingTime")) {
    				os.setLastRequestProcessingTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("maxTime")) {
    				os.setMaxTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("processingTime")) {
    				os.setProcessingTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("requestCount")) {
    				os.setRequestCount((int)mbeanConn.getAttribute(on, mb.getName()));
    			} else if(mb.getName().equals("requestProcessingTime")) {
    				os.setRequestProcessingTime((long)mbeanConn.getAttribute(on, mb.getName()));
    			} 
    		} catch(RuntimeMBeanException e) {
    			System.out.println("Erro " + e);
    		}
    	}
    	
    	os.setRequestProcessorId(getRequestProcessorSeed());
    	
    	return os;
	}	
	@Transactional
	public int getThreadingSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.THREADING);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	@Transactional
	public int getThreadPoolSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.THREAD_POOL);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	@Transactional
	public int getOperatingSystemSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.OPERATING_SYSTEM);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	@Transactional
	public int getMemorySpaceSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.MEMORY_SPACE);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	@Transactional
	public int getMetricsSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.METRICS);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}

	@Transactional
	public int getRequestProcessorSeed() {
		Seed seed = null;
		int nSeed = 0;

		seed = seedRepository.findBySeedId(SeedType.REQUEST_PROCESSOR);
		//logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		//logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	@Transactional
	public void save(Metrics metrics) {
		metricsRepository.save(metrics);
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	
}
