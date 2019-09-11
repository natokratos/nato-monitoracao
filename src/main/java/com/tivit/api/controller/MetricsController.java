package com.tivit.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebParam;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeMBeanException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.tivit.api.domain.MemoryType;
import com.tivit.api.entity.Heading;
import com.tivit.api.entity.MemorySpace;
import com.tivit.api.entity.Metrics;
import com.tivit.api.repository.MetricsRepository;
import com.tivit.api.service.MetricsService;

@RestController
@RequestMapping("/")
public class MetricsController {

	@Autowired
	MetricsService metricsService;

	@GetMapping("transfer")
	public String transfer(@RequestParam("timeoutTwo") Integer timeoutTwo) {
		try {
			Thread.sleep(timeoutTwo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return metricsService.listMetrics().toString();
	}
	
	@GetMapping("metrics")
	public List<Metrics> doMetrics() {
		Set<ObjectName> beanSet = null;
		ArrayList<Metrics> lm = new ArrayList<Metrics>();
		
		//System.out.println("TESTE1");
		for (VirtualMachineDescriptor vmd : metricsService.getVmList()) {
			beanSet = metricsService.attachToVm(vmd);
			//System.out.println("TESTE2");
			if(beanSet != null) {
				Metrics metrics = new Metrics();
				try {
					metrics.setAppName(metricsService.getVmName());
					System.out.println("-------- VMNAME [" + metricsService.getVmName() + "]");
					for(ObjectName on : beanSet) {
			        	Heading heading = metricsService.getHeading(on.getCanonicalName());
			        	
			        	if(heading != null && !heading.isEmpty()) {
			        		if (heading.getType().matches("(R|r)equestProcessor")){
			        			metrics.setRequestProcessor(metricsService.parseRequestProcessor(on));
			        		} else if (heading.getType().matches("(T|t)hreadPool")){
			        			metrics.setThreadPool(metricsService.parseThreadPool(on));
			        		} else if (heading.getType().matches("(T|t)hreading")){
			        			metrics.setThreading(metricsService.parseThreading(on));
			        		} else if (heading.getType().matches("(M|m)emory")){
			        			List<MemorySpace> lms = metricsService.parseMemorySpace(on);
			        			
			        			for(MemorySpace ms : lms) {
				        			if(ms.getMemoryType() == MemoryType.HEAP) {
				        				metrics.setHeapMemorySpace(ms);
				        			} else {
				        				metrics.setNonHeapMemorySpace(ms);
				        			}
			        			}
			        		} else if (heading.getType().matches("(O|o)perating.*")){
			        			metrics.setOperatingSystem(metricsService.parseOperatingSystem(on));
			        		} else {
			        			System.out.println("[" + heading.getType() + "] Type not being tracked...");
			        		}	        		
			        	}
					}
				} catch(InstanceNotFoundException e) {
					System.out.println("InstanceNotFoundException!!!");
					e.printStackTrace();
				} catch(IntrospectionException e) {
					System.out.println("IntrospectionException!!!");
					e.printStackTrace();
				} catch(AttributeNotFoundException e) {
					System.out.println("AttributeNotFoundException!!!");
					e.printStackTrace();
				} catch(ReflectionException e) {
					System.out.println("ReflectionException!!!");
					e.printStackTrace();
				} catch(MBeanException e) {
					System.out.println("MBeanException!!!");
					e.printStackTrace();
				} catch(IOException e) {
					System.out.println("IOException!!!");
					e.printStackTrace();
				}
				
				metrics.setCreationDate(new Date());
				metrics.setMetricId(metricsService.getMetricsSeed());
				
				metricsService.save(metrics);
				lm.add(metrics);
			}
		}
		
		
		return lm;
	}
	
}
