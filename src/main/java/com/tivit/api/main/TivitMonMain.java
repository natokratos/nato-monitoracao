package com.tivit.api.main;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sun.xml.ws.transport.http.servlet.WSSpringServlet;
import com.tivit.api.mbean.TivitMon;

@SpringBootApplication(scanBasePackages = {"com.tivit.api.service, com.tivit.api.controller, com.tivit.api.mbean, com.tivit.api.main"})
@EntityScan(basePackages={"com.tivit.api.entity"})
@EnableJpaRepositories(basePackages = {"com.tivit.api.repository"})
public class TivitMonMain {
	
	public static void main(String[] args) {
		SpringApplication.run(TivitMonMain.class, args);
	}

    @Bean
    public void registerMBean() {
    	TivitMon mBean = null;
    	
    	String objectName = "com.tivit.api.mbean:type=tivitmon";
    	
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

	    // Construct the ObjectName for the Hello MBean we will register
	    ObjectName mbeanName;
		try {
			mbeanName = new ObjectName(objectName);
		    
			mBean = new TivitMon();
			mBean.status();
			
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