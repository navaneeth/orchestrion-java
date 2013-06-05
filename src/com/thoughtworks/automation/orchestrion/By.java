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
	 * @param automationId An ID for an element that is unique among siblings within its container.
	 * @return
	 */
	public static By automationId(String automationId) {
		return new ByAutomationId(automationId);
	}
	
	/**
	 * Locate UI items based on text. 
	 * <p>
	 * For eg: a button with text 'OK' can be located by 
	 * <pre>
	 * {@code window.getButton(By.text("OK"))}
	 * </pre>
	 * @param text
	 * @return
	 */
	public static By text(String text) {
		return new ByText(text);
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

}
