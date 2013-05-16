package com.thoughtworks.automation.windows;

public class Window {

	private final int refId;
	private RemoteServer server;

	public Window(int refId) {
		this.refId = refId;
		this.server = new RemoteServer();
	}
	
	public void enterText(String text) throws Exception {	
		server.execute("entertext", refId, text, String.format("%d", refId));
	}
	
	public MenuBar getMenuBar() throws Exception {
		int id = server.executeAndGetId("getmenubar", refId);
		return new MenuBar(id, refId);
	}	
		
	public void close() throws Exception {
		server.execute("close", refId);
	}
	
	public Button getButton(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id = server.executeAndGetId("getbutton", refId, by, by.getValue());
		return new Button(id, refId);		
	}
	
	public Combobox getComboBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}
		
		int id = server.executeAndGetId("getcombobox", refId, by, by.getValue());
		return new Combobox(id, refId);
	}

}
