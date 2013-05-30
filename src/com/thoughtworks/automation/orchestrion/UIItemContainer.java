/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Base class for all the containers
 *
 */
public abstract class UIItemContainer extends UIItem {
	
	public UIItemContainer(int refId) {
		super(refId);
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

	public ProgressBar getProgressBar(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getprogressbar",
				getRefId(), by, by.getValue());
		return new ProgressBar(id);
	}	
	
	public Slider getSlider(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getslider",
				getRefId(), by, by.getValue());
		return new Slider(id, getRefId());
	}
	
	public Hyperlink getHyperlink(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("gethyperlink",
				getRefId(), by, by.getValue());
		return new Hyperlink(id, getRefId());
	}
	
	public Panel getPanel(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getpanel",
				getRefId(), by, by.getValue());
		return new Panel(id);
	}
	
	public Spinner getSpinner(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getspinner",
				getRefId(), by, by.getValue());
		return new Spinner(id, getRefId());
	}
	
	public GroupBox getGroupBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getgroupbox",
				getRefId(), by, by.getValue());
		return new GroupBox(id);
	}

}