package com.thoughtworks.automation.orchestrion;

public class Window extends UIItem {

	public Window(int refId) {
		super(refId);
	}	

	public MenuBar getMenuBar() throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenubar",
				getRefId());
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

		int id = RemoteServer.instance().executeAndGetId("getbutton",
				getRefId(), by, by.getValue());
		return new Button(id, getRefId());
	}
	
	public CheckBox getCheckBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getcheckbox",
				getRefId(), by, by.getValue());
		return new CheckBox(id, getRefId());
	}
	
	public RadioButton getRadioButton(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getradiobutton",
				getRefId(), by, by.getValue());
		return new RadioButton(id, getRefId());
	}

	public Combobox getComboBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getcombobox",
				getRefId(), by, by.getValue());
		return new Combobox(id, getRefId());
	}

	public ListBox getListBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getlistbox",
				getRefId(), by, by.getValue());
		return new ListBox(id, getRefId());
	}

	public TextBox getTextBox(By by, boolean multiline) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id;
		if (multiline) {
			id = RemoteServer.instance().executeAndGetId("getmultilinetextbox",
					getRefId(), by, by.getValue());
		} else {
			id = RemoteServer.instance().executeAndGetId("gettextbox",
					getRefId(), by, by.getValue());
		}

		return new TextBox(id, getRefId());
	}

	public Label getLabel(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getlabel",
				getRefId(), by, by.getValue());
		return new Label(id, getRefId());
	}

	public Tree getTree(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("gettree", getRefId(),
				by, by.getValue());
		return new Tree(id, getRefId());
	}

	public MessageBox getMessageBox(String title) throws Exception {
		if (title == null) {
			throw new IllegalArgumentException("title");
		}

		try {
			int id = RemoteServer.instance().executeAndGetId("getmessagebox",
					getRefId(), title);
			return new MessageBox(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	public ProgressBar getProgressBar(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getprogressbar",
				getRefId(), by, by.getValue());
		return new ProgressBar(id);
	}

	public Window getModalWindow(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getmodalwindow",
				getRefId(), by, by.getValue());
		return new Window(id);
	}

	public Windows getModalWindows() throws Exception {		
		try {
			int id = RemoteServer.instance().executeAndGetId("getmodalwindows", getRefId());
			return new Windows(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}
	
	/**
	 * Waits till the processing finishes
	 * 
	 *  This is useful when some methods are asynchronous and you need to wait till the operation 
	 *  completes.
	 * 
	 * @throws Exception
	 */
	public void waitWhileBusy() throws Exception {
		RemoteServer.instance().execute("waitwhilebusy", getRefId());
	}

}
