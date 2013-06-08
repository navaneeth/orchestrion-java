/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages orchestrion test environment
 * 
 */
public final class Orchestrion {

	private final int NOT_SET = -1;

	private File workingDir;
	private Process orchestrionProcess;
	private int port = NOT_SET;
	private int timeoutInMs = 1000;
	private String host;
	private File logsDirectory;
	private final List<String> orchestrionFiles = new ArrayList<String>();
	private boolean isWorkingDirTemporary = false;

	public Orchestrion() {
		orchestrionFiles.add("Castle.Core.dll");
		orchestrionFiles.add("orchestrion.exe");
		orchestrionFiles.add("orchestrion.exe.config");
		orchestrionFiles.add("TestStack.White.dll");
		orchestrionFiles.add("log4net.dll");
	}

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
	 * @throws OrchestrionException
	 *             If it can't find a port
	 */
	public int getPort() throws OrchestrionException {
		if (this.port == NOT_SET) {
			this.port = findFreePort();
		}

		return this.port;
	}

	/**
	 * Sets the host name
	 * 
	 * @param host
	 *            host name to set
	 * @return
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the current host value
	 * 
	 * @return
	 */
	public String getHost() {
		if (this.host == null)
			return "localhost";

		return this.host;
	}

	/**
	 * Sets the logs directory
	 * <p>
	 * Orchestrion writes log files to this directory.
	 * 
	 * @param directoryPath
	 *            Directory path where log files to be written
	 * @throws OrchestrionException
	 *             If directoryPath is not writable
	 */
	public void setLogsDirectory(String directoryPath)
			throws OrchestrionException {
		File logsDirectory = new File(directoryPath);
		if (!isDirectoryAvailable(logsDirectory))
			throw new OrchestrionException(directoryPath + " is invalid", null);

		if (!canWriteToDirectory(logsDirectory))
			throw new OrchestrionException(directoryPath + " is not writable",
					null);

		this.logsDirectory = logsDirectory;
	}

	/**
	 * Gets the logs directory
	 * 
	 * @return
	 */
	public String getLogsDirectory() {
		if (logsDirectory != null)
			return logsDirectory.getAbsolutePath();

		return null;
	}

	/**
	 * Sets the working directory. A working directory is where all orchestrion
	 * executable files are placed. If this is not set, it will try to use the
	 * bundled orchestrion files.
	 * <p>
	 * If you are using orchestrion-java.jar, then you don't need to set this.
	 * Orchestrion-java takes care of setting working directory.
	 * 
	 * @param workingDirectory
	 *            a valid readable directory where orchestrion files are present
	 * @throws OrchestrionException
	 */
	public void setWorkingDirectory(String workingDirectory)
			throws OrchestrionException {
		if (workingDirectory == null)
			throw new NullPointerException("workingDirectory");

		workingDirectory = workingDirectory.trim();

		File dir = new File(workingDirectory);
		ensureWorkingDirectoryIsValid(dir);
		this.workingDir = dir;
	}

	private void ensureWorkingDirectoryIsValid(File dir)
			throws OrchestrionException {
		if (dir == null)
			throw new OrchestrionException("Working directory is not set", null);

		if (!dir.exists() || !dir.isDirectory())
			throw new OrchestrionException(String.format("%s is invalid.",
					dir.getAbsolutePath()), null);

		if (!dir.canRead())
			throw new OrchestrionException(String.format("%s is not readable",
					dir.getAbsolutePath()), null);

		for (String file : orchestrionFiles) {
			File f = new File(dir, file);
			if (!f.exists())
				throw new OrchestrionException(String.format(
						"%s is not a valid working directory. Can't find %s",
						dir.getAbsolutePath(), file), null);

			if (file.endsWith(".exe") && !f.canExecute())
				throw new OrchestrionException(
						String.format(
								"%s is not a valid working directory. %s is not executable",
								f.getAbsolutePath(), file), null);
		}
	}

	private boolean isDirectoryAvailable(File dir) {
		try {
			return dir.isDirectory();
		} catch (SecurityException e) {
			return false;
		}
	}

	private boolean canWriteToDirectory(File dir) {
		try {
			return dir.canWrite();
		} catch (SecurityException e) {
			return false;
		}
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
		if (getWorkingDirectory() == null) {
			// Creating a temporary working directory and copying orchestrion
			// files from the JAR
			final File tempDir;
			try {
				tempDir = createTempDirectory();
			} catch (IOException e) {
				throw new OrchestrionException(
						"Can't start orchestrion process. Error creating temporary working directory",
						e);
			}

			for (String file : orchestrionFiles) {
				copyFileFromBundleTo(file, tempDir);
			}

			this.workingDir = tempDir;
			isWorkingDirTemporary = true;
		}

		startTestEnvironment();
	}

	private void startTestEnvironment() throws OrchestrionException {
		ensureWorkingDirectoryIsValid(workingDir);

		try {
			String[] commandLineArgs = getCommandLineArgs();
			ProcessBuilder pb = new ProcessBuilder(commandLineArgs);
			orchestrionProcess = pb.start();
			RemoteServer.initialize(getPort(), timeoutInMs, getHost());

			int tries = 0;
			final int maxTries = 3;
			boolean pingSuccess = false;
			while (tries++ < maxTries) {
				try {
					RemoteServer.instance().ping();
					pingSuccess = true;
					break;
				} catch (Exception e) {
					// Looks like process is not yet ready
					Thread.sleep(50);
				}
			}

			if (!pingSuccess) {
				int exitValue = orchestrionProcess.exitValue();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								orchestrionProcess.getErrorStream()));
				String line = null;
				StringBuilder errorMessage = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					errorMessage.append(line);
				}
				reader.close();
				throw new OrchestrionException(
						"Can't start orchestrion test environment."
								+ errorMessage.toString() + ". Exit code "
								+ exitValue, null);
			}

		} catch (IOException e) {
			throw new OrchestrionException(
					"Can't start orchestrion test environment.", e);
		} catch (InterruptedException e) {
			throw new OrchestrionException(
					"Can't start orchestrion test environment.", e);
		} catch (IllegalThreadStateException e) {
			// process is still running, but ping failed.
			// It could be listening on a wrong host for eg
			orchestrionProcess.destroy();
			throw new OrchestrionException(
					"Can't start orchestrion test environment. Failed to establish connection.",
					null);
		}
	}

	private String[] getCommandLineArgs() throws OrchestrionException {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add(workingDir.getAbsolutePath() + File.separator
				+ "orchestrion.exe");

		commands.add("--port");
		commands.add(String.format("%d", getPort()));

		// Disabling console output as we are not reading it. When the buffer
		// gets full, process will hang
		commands.add("--console-output");
		commands.add("false");

		// Passing the current pid so that orchestrion process will quit
		// automatically
		// when this process dies.
		int pid = getPid();
		if (pid != NOT_SET) {
			commands.add("--parent");
			commands.add(String.format("%d", pid));
		}

		if (getHost() != null && !getHost().isEmpty()) {
			commands.add("--host");
			commands.add(getHost());
		}

		if (getLogsDirectory() != null && !getLogsDirectory().isEmpty()) {
			commands.add("--logs");
			commands.add(getLogsDirectory());
		}

		return commands.toArray(new String[commands.size()]);
	}

	private int getPid() {
		String name = null;
		try {
			name = ManagementFactory.getRuntimeMXBean().getName();
			if (name == null)
				return NOT_SET;

			int pid = Integer.parseInt(name);
			return pid;
		} catch (NumberFormatException e) {
			// JVM may returns something like 1234@localhost
			String[] parts = name.split("@");
			if (parts.length > 0) {
				int pid = Integer.parseInt(parts[0]);
				return pid;
			}
		} catch (Exception e) {
			return NOT_SET;
		}

		return NOT_SET;
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
		} finally {
			deleteTemporaryWorkingDirectory();
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

	private void deleteTemporaryWorkingDirectory() {
		if (isWorkingDirTemporary && workingDir.exists()) {
			try {
				File[] files = workingDir.listFiles();
				for (File file : files) {
					file.delete();
				}

				workingDir.delete();
			} catch (Exception e) {
				// ignore as it is a temporary directory
			}
		}
	}

	private void copyFileFromBundleTo(String fileName, File outDir)
			throws OrchestrionException {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream("orchestrion/"
				+ fileName);
		if (stream == null)
			throw new OrchestrionException(
					String.format(
							"Can't copy %s from the bundle. Make sure you are using the correct JAR file",
							fileName), null);

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

	public int findFreePort() throws OrchestrionException {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			return socket.getLocalPort();
		} catch (IOException e) {

		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}

		throw new OrchestrionException(
				"Can't find a free port. Try setting a port value using setPort()",
				null);
	}

}
