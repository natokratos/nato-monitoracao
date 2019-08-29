package com.tivit.core.main;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.tivit.api.main.TivitMonMain;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TivitMonMain.class)
public class TivitMonMainTest {

	@Test
	public void contextLoads() {
	}
	 
	@Test
	public void start() {
		TivitMonMain.main(new String[] {});
	}

}
