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

	/**
	 * Gets the keyboard instance
	 * 
	 * @return keyboard
	 * @throws Exception
	 * @see Keyboard
	 */
	public Keyboard getKeyBoard() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getkeyboard",
					getRefId());
			return new Keyboard(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets the mouse instance
	 * 
	 * @return
	 * @throws Exception
	 */
	public Mouse getMouse() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getmouse",
					getRefId());
			return new Mouse(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets a Button by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 */
	public Button getButton(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getbutton",
				getRefId(), by, by.getValue());
		return new Button(id, getRefId());
	}

	/**
	 * Gets a CheckBox by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 */
	public CheckBox getCheckBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getcheckbox",
				getRefId(), by, by.getValue());
		return new CheckBox(id, getRefId());
	}

	/**
	 * Gets a RadioButton by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see RadioButton
	 */
	public RadioButton getRadioButton(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getradiobutton",
				getRefId(), by, by.getValue());
		return new RadioButton(id, getRefId());
	}

	/**
	 * Gets a ComboBox by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see ComboBox
	 */
	public Combobox getComboBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getcombobox",
				getRefId(), by, by.getValue());
		return new Combobox(id, getRefId());
	}

	/**
	 * Gets a ListBox by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see ListBox
	 */
	public ListBox getListBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getlistbox",
				getRefId(), by, by.getValue());
		return new ListBox(id, getRefId());
	}

	/**
	 * Gets a TextBox by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @param multiline
	 *            set this to true when looking for multiline textbox
	 * @return
	 * @throws Exception
	 * @see TextBox
	 */
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

	/**
	 * Gets a Label by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Label
	 */
	public Label getLabel(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getlabel",
				getRefId(), by, by.getValue());
		return new Label(id, getRefId());
	}

	/**
	 * Gets a Tree by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Tree
	 */
	public Tree getTree(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("gettree", getRefId(),
				by, by.getValue());
		return new Tree(id, getRefId());
	}

	/**
	 * Gets a ProgressBar by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see ProgressBar
	 */
	public ProgressBar getProgressBar(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getprogressbar",
				getRefId(), by, by.getValue());
		return new ProgressBar(id);
	}

	/**
	 * Gets a Slider by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Slider
	 */
	public Slider getSlider(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getslider",
				getRefId(), by, by.getValue());
		return new Slider(id, getRefId());
	}

	/**
	 * Gets a Hyperlink by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Hyperlink
	 */
	public Hyperlink getHyperlink(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("gethyperlink",
				getRefId(), by, by.getValue());
		return new Hyperlink(id, getRefId());
	}

	/**
	 * Gets a Panel by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Panel
	 */
	public Panel getPanel(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getpanel",
				getRefId(), by, by.getValue());
		return new Panel(id);
	}

	/**
	 * Gets a Spinner by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see Spinner
	 */
	public Spinner getSpinner(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getspinner",
				getRefId(), by, by.getValue());
		return new Spinner(id, getRefId());
	}

	/**
	 * Gets a GroupBox by the search criteria
	 * 
	 * @param by
	 *            condition to locate element
	 * @return
	 * @throws Exception
	 * @see GroupBox
	 */
	public GroupBox getGroupBox(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getgroupbox",
				getRefId(), by, by.getValue());
		return new GroupBox(id);
	}

}
