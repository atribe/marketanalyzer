package com.atomrockets.marketanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.atomrockets.marketanalyzer.services.IndexCalcsService;

public class FollowThruTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testRunIndexAnalysis() {
		IndexCalcsService a = new IndexCalcsService();
		
		//assertTrue("FollowThru Didn't Calc Correctly", a.runIndexAnalysis("^IXIC"));    // If checkOut fails, display message
	}

}
