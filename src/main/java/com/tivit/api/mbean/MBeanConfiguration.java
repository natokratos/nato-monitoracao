package com.tivit.api.mbean;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MBeanConfiguration {

    @Bean
    public void registerRequestStatsMBean() {
    	RequestStats mBean = null;
    	
    	String objectName = "com.tivit.api.mbean:type=requeststats";
    	
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

	    // Construct the ObjectName for the Hello MBean we will register
	    ObjectName mbeanName;
		try {
			mbeanName = new ObjectName(objectName);
		    
			mBean = new RequestStats();
			//mBean.status();
			
		    server.registerMBean(mBean, mbeanName);
		    
		    Set<ObjectInstance> instances = server.queryMBeans(new ObjectName(objectName), null);
		    
		    
		    ObjectInstance instance = (ObjectInstance) instances.toArray()[0];
		    
		    System.out.println("Class Name:t" + instance.getClassName());
		    System.out.println("Object Name:t" + instance.getObjectName());    	
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
