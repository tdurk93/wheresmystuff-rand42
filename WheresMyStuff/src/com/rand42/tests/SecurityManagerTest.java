package com.rand42.tests;

import com.rand42.model.SecurityManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SecurityManagerTest {

	SecurityManager manager = SecurityManager.getSecurityManager();

	@Test(timeout=200)
	public void testLogin(){
		assertTrue( manager.check("test") );
	}
	
	@Test(timeout=200)
	public void testLockout(){
		assertTrue(manager.check("lock"));
		assertTrue(manager.check("lock"));
		//assertTrue(manager.check("lock"));
		assertTrue(!manager.check("lock"));
	}
	
	@Test(timeout=200)
	public void testReset(){
		assertTrue(manager.check("reset"));
		assertTrue(manager.check("reset"));
		//assertTrue(manager.check("reset"));
		assertTrue(!manager.check("reset"));
		manager.reset("reset");
		assertTrue(manager.check("reset"));
	}
}
