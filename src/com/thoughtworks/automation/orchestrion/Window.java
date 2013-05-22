package com.thoughtworks.automation.orchestrion;


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
	
	public ListBox getListBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id = RemoteServer.instance().executeAndGetId("getlistbox", getRefId(), by, by.getValue());
		return new ListBox(id, getRefId());
	}
	
	public TextBox getTextBox(By by, boolean multiline) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id;
		if (multiline) {
			id = RemoteServer.instance().executeAndGetId("getmultilinetextbox", getRefId(), by, by.getValue());
		}
		else {
			id = RemoteServer.instance().executeAndGetId("gettextbox", getRefId(), by, by.getValue());
		}
		
		return new TextBox(id, getRefId());
	}
	
	public MessageBox getMessageBox(String title) throws Exception {
		if (title == null) {
			throw new IllegalArgumentException("title");
		}
				
		try {
			int id = RemoteServer.instance().executeAndGetId("getmessagebox", getRefId(), title);
			return new MessageBox(id);			
		}
		catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
