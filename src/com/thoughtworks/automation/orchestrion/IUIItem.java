package com.thoughtworks.automation.orchestrion;

public interface IUIItem {
	
	public boolean isEnabled() throws Exception; 
	
	public boolean isVisible() throws Exception;
	
	public void click() throws Exception;
	
	public void doubleClick() throws Exception;
	
	public void rightClick() throws Exception;
	
	public String getName() throws Exception;

}
