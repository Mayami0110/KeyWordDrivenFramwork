package com.keywordframework.qa.tests;

import org.testng.annotations.Test;

import com.keywordframework.qa.engine.KeyWordEngine;

public class LoginTest {
	
	public KeyWordEngine keywordEngine;
	
	@Test
	public void loginTest()
	{
		keywordEngine = new KeyWordEngine();
		keywordEngine.startExecution("login");
	}

}
