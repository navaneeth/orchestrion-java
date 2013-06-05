/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Manages orchestrion test environment
 * 
 */
public final class Orchestrion {

	private File workingDir;
	private Process orchestrionProcess;
	private int port = 8082;
	private int timeoutInMs = 10000;	

	/**
	 * Sets the port which orchestrion test environment will be hosted
	 * 
	 * @param port
	 *            Unused port number
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the port in which orchestrion test environment is hosted
	 * 
	 * @return port in use
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the connection timeout
	 * 
	 * @param timeoutInMs
	 *            timeout value in milliseconds
	 */
	public void setTimeout(int timeoutInMs) {
		this.timeoutInMs = timeoutInMs;
	}

	/**
	 * Gets the timeout value
	 * 
	 * @return current timeout value in milliseconds
	 */
	public int getTimeout() {
		return timeoutInMs;
	}

	/**
	 * Starts orchestrion test environment
	 * 
	 * @throws OrchestrionException
	 */
	public void start() throws OrchestrionException {
		final File tempDir;
		try {
			tempDir = createTempDirectory();
		} catch (IOException e) {
			throw new OrchestrionException(
					"Can't start orchestrion process. Error creating temporary working directory",
					e);
		}

		copyFileTo("Castle.Core.dll", tempDir);
		copyFileTo("orchestrion.exe", tempDir);
		copyFileTo("orchestrion.exe.config", tempDir);
		copyFileTo("TestStack.White.dll", tempDir);
		copyFileTo("log4net.dll", tempDir);

		start(tempDir.getAbsolutePath());
	}

	/**
	 * Starts orchestrion test environment in the specified working directory
	 * 
	 * @param workingDirectory
	 *            Directory where all orchestrion files are present
	 * @throws OrchestrionException
	 */
	public void start(String workingDirectory) throws OrchestrionException {
		if (workingDirectory == null)
			throw new NullPointerException("workingDirectory");

		workingDir = new File(workingDirectory);
		if (!workingDir.exists() || !workingDir.isDirectory())
			throw new OrchestrionException(String.format("%s is invalid.",
					workingDirectory), null);

		try {
			orchestrionProcess = Runtime.getRuntime().exec(
					workingDir.getAbsolutePath() + "/orchestrion.exe");
			// TODO : Handle exit values here

			RemoteServer.initialize(port, timeoutInMs);
		} catch (IOException e) {
			throw new OrchestrionException(
					"Can't start orchestrion test environment.", e);
		}
	}

	/**
	 * Stops the orchestrion test environment
	 */
	public void stop() {
		try {
			RemoteServer.instance().quit();
		} catch (Exception e) {
			// Graceful exit failed. Forcefully killing.
			orchestrionProcess.destroy();
		}
	}

	/**
	 * Gets the current working directory for orchestrion
	 * 
	 * @return
	 */
	public String getWorkingDirectory() {
		if (workingDir != null)
			return workingDir.getAbsolutePath();

		return null;
	}

	private void copyFileTo(String fileName, File outDir)
			throws OrchestrionException {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream("orchestrion/"
				+ fileName);
		if (stream == null)
			throw new OrchestrionException(String.format(
					"Can't copy %s from the bundle", fileName), null);

		try {
			File file = new File(outDir, fileName);
			file.createNewFile();
			FileOutputStream outFile = new FileOutputStream(file);
			byte[] buffer = new byte[10240];
			int len;
			while ((len = stream.read(buffer)) != -1) {
				outFile.write(buffer, 0, len);
			}
			outFile.close();
		} catch (IOException e) {
			throw new OrchestrionException(
					String.format(
							"Can't copy %s from the bundle. Failed to create destination file",
							fileName), e);
		}
	}

	private static File createTempDirectory() throws IOException {
		final File temp;

		temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

		if (!(temp.delete())) {
			throw new IOException("Could not delete temp file: "
					+ temp.getAbsolutePath());
		}

		if (!(temp.mkdir())) {
			throw new IOException("Could not create temp directory: "
					+ temp.getAbsolutePath());
		}

		return (temp);
	}

}
