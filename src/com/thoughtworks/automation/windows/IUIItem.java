package com.thoughtworks.automation.windows;

public interface IUIItem {
	
	public boolean isEnabled() throws Exception; 
	
	public void click() throws Exception;
	
	public void doubleClick() throws Exception;

}
