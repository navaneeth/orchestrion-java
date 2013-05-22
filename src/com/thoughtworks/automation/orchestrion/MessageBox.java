package com.thoughtworks.automation.orchestrion;

public class MessageBox extends Window {

	public MessageBox(int refId) {
		super(refId);
	}
	
	public void clickButton(String text) throws Exception {
		getButton(By.text(text)).click();
	}

}
