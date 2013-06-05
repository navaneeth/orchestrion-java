package com.thoughtworks.automation.orchestrion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class RemoteServer {
	
	private static RemoteServer _instance = null; 
	public static void initialize(int port, int connectTimeout) {
		_instance = new RemoteServer(port, connectTimeout);		
	}
	
	public static RemoteServer instance() {
		if (_instance == null) {
			initialize(8082, 10000);
		}
		
		return _instance;
	}
	
	private final int port;
	private String serverURL = "http://localhost";
	private final int connectTimeout;

	public RemoteServer(int port, int connectTimeout) {
		this.port = port;
		this.connectTimeout = connectTimeout;
	}

	public int executeAndGetId(String command, int refId) throws Exception {
		return executeAndGetId(command, refId, null, (String[]) null);
	}
	
	public int executeAndGetId(String command, int refId, String... parameters) throws Exception {
		return executeAndGetId(command, refId, null, parameters);
	}

	public int executeAndGetId(String command, int refId, By by, String... parameters)
			throws Exception {
		String result = execute(command, refId, by, parameters);
		if (result.isEmpty()) {
			throw new RefIdNotAvailableException();
		}
		
		try {
			int id = Integer.parseInt(result);
			return id;
		}
		catch(NumberFormatException ex) {
			throw new RuntimeException("Failed to get an id from remote the server", ex);
		}
	}
	
	public String execute(String command, int refId) throws Exception {
		return execute(command, refId, null, (String[]) null);
	}
	
	public String execute(String command, int refId, String... parameters) throws Exception {
		return execute(command, refId, null, parameters);
	}

	public String execute(String command, int refId, By by, String... parameters)
			throws Exception {
		if (command == null) {
			throw new NullPointerException("command");
		}

		if (parameters == null) {
			parameters = new String[0];
		}

		StringBuilder requestURL = new StringBuilder();
		requestURL.append(String.format("%s:%d/?command=%s", serverURL, port,
				utf8URLEncode(command)));
		if (refId > 0) {
			requestURL.append(String.format("&ref=%d", refId));
		}
		
		if (by != null) {
			requestURL.append(String.format("&by=%s", utf8URLEncode(by.getKey())));
		}

		for (int i = 0; i < parameters.length; i++) {
			String parameter = parameters[i];
			requestURL.append(String.format("&%d=%s", (i + 1),
					utf8URLEncode(parameter)));
		}

		HttpURLConnection connection = null;
		try {
			URL url = new URL(requestURL.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setConnectTimeout(connectTimeout);

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				InputStream errorStream = connection.getErrorStream();
				String message = convertStreamToString(errorStream);
				errorStream.close();
				throw new RuntimeException(message);
			} else {
				InputStream stream = connection.getInputStream();
				String message = convertStreamToString(stream);
				stream.close();
				return message;				
			}
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private String utf8URLEncode(String text)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8");
	}

	private String convertStreamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		for (String line; (line = reader.readLine()) != null;) {
			builder.append(line);
		}

		return builder.toString();
	}

	public void quit() throws Exception {
		execute("quit", 0);
	}

}
