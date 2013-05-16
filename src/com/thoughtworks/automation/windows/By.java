package com.thoughtworks.automation.windows;

public abstract class By {
	
	public static By automationId(String automationId) {
		return new ByAutomationId(automationId);
	}
	
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
