package com.thoughtworks.automation.windows.controls;

import com.thoughtworks.automation.windows.By;
import com.thoughtworks.automation.windows.RemoteServer;
import com.thoughtworks.automation.windows.UIItem;

public class Window extends UIItem {

	public Window(int refId) {
		super(refId);
	}
	
	public void enterText(String text) throws Exception {	
		RemoteServer.instance().execute("entertext", getRefId(), text, String.format("%d", getRefId()));
	}
	
	public MenuBar getMenuBar() throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenubar", getRefId());
		return new MenuBar(id, getRefId());
	}
	
	public String getTitle() throws Exception {
		return RemoteServer.instance().execute("gettitle", getRefId());
	}
		
	public void close() throws Exception {
		RemoteServer.instance().execute("close", getRefId());
	}
	
	public Button getButton(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id = RemoteServer.instance().executeAndGetId("getbutton", getRefId(), by, by.getValue());
		return new Button(id, getRefId());		
	}
	
	public Combobox getComboBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id = RemoteServer.instance().executeAndGetId("getcombobox", getRefId(), by, by.getValue());
		return new Combobox(id, getRefId());
	}

}
