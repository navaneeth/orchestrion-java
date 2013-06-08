package com.thoughtworks.automation.orchestrion;

/**
 * 
 * Represents search criteria for finding UI items
 * 
 */
public abstract class By {

	/**
	 * Locate UI item based on automation id
	 * 
	 * @param automationId
	 *            An ID for an element that is unique among siblings within its
	 *            container.
	 * @return
	 */
	public static By automationId(String automationId) {
		return new ByAutomationId(automationId);
	}

	/**
	 * Locate UI item based on text.
	 * <p>
	 * For eg: a button with text 'OK' can be located by
	 * 
	 * <pre>
	 * {@code window.getButton(By.text("OK"))}
	 * </pre>
	 * 
	 * @param text
	 * @return
	 */
	public static By text(String text) {
		return new ByText(text);
	}

	/**
	 * Locate UI item based on class name
	 * <p>
	 * Class name can be located from UISpy or inspect tool
	 * 
	 * @param className
	 * @return
	 */
	public static By className(String className) {
		return new ByClassName(className);
	}

	/**
	 * Locate UI item based on framework
	 * 
	 * @param framework
	 * @return
	 */
	public static By framework(String framework) {
		return new ByFramework(framework);
	}

	private final String value;

	protected By(String value) {
		this.value = value;

	}

	public abstract String getKey();

	public String getValue() {
		return value;
	}

	private static class ByAutomationId extends By {

		public ByAutomationId(String automationId) {
			super(automationId);
		}

		@Override
		public String getKey() {
			return "automationid";
		}

	}

	private static class ByText extends By {

		public ByText(String text) {
			super(text);
		}

		@Override
		public String getKey() {
			return "text";
		}

	}

	private static class ByClassName extends By {

		public ByClassName(String className) {
			super(className);
		}

		@Override
		public String getKey() {
			return "classname";
		}

	}

	private static class ByFramework extends By {

		public ByFramework(String framework) {
			super(framework);
		}

		@Override
		public String getKey() {
			return "framework";
		}

	}

}
