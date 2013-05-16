package com.thoughtworks.automation.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RemoteServer {

	private final int port;
	private String serverURL = "http://localhost";

	public RemoteServer(int port) {
		this.port = port;
	}

	public RemoteServer() {
		this(8082);
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
		try {
			int id = Integer.parseInt(result);
			return id;
		}
		catch(NumberFormatException ex) {
			throw new RuntimeException("Failed to get an id from remote the server");
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
			connection.setConnectTimeout(100000);

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

}
