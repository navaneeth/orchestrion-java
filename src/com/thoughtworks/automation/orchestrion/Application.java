package com.thoughtworks.automation.orchestrion;

/**
 * 
 * Represents an Application. Provides methods to launch processes/attach to
 * running processes
 * 
 */
public final class Application {

	private final int refId;

	/**
	 * Launch the specified application
	 * 
	 * @param applicationPath
	 *            Full path to the application to launch. If the application is
	 *            in system path, specifying just executable name should work
	 * @return {@link Application} instance which represents the launched
	 *         application
	 * @throws Exception
	 */
	public static Application launch(String applicationPath) throws Exception {
		int refId = RemoteServer.instance().executeAndGetId("launch", 0,
				applicationPath);
		return new Application(refId);
	}

	/**
	 * Attaches to the specified process id
	 * 
	 * @param processId
	 *            Process id to attach to
	 * @return {@link Application} instance which represents the supplied
	 *         process id
	 * @throws Exception
	 */
	public static Application attach(int processId) throws Exception {
		int refId = RemoteServer.instance().executeAndGetId("attach", 0,
				String.format("%d", processId));
		return new Application(refId);
	}

	/**
	 * Initializes new Application instance. This is internal to orchestrion
	 * package
	 * 
	 * @param refId
	 */
	Application(int refId) {
		this.refId = refId;
	}

	/**
	 * Gets a Window matching the title
	 * <p>
	 * This method searches through all the available windows in the current
	 * application and returns the Window which has the specified title
	 * 
	 * @param windowTitle
	 *            Window title which will be used for matching
	 * @return {@link Window} instance
	 * @throws Exception
	 * @see Window
	 */
	public Window getWindow(String windowTitle) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getwindow", refId,
				windowTitle);
		return new Window(id);
	}

	/**
	 * Closes the application killing all open windows
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception {
		RemoteServer.instance().execute("close", refId);
	}

	/**
	 * Waits till the processing finishes
	 * 
	 * This is useful when some methods are asynchronous and you need to wait
	 * till the operation completes.
	 * 
	 * @throws Exception
	 */
	public void waitWhileBusy() throws Exception {
		RemoteServer.instance().execute("waitwhilebusy", refId);
	}

}
