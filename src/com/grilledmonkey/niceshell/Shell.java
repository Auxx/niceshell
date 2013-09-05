package com.grilledmonkey.niceshell;

public class Shell {
	private static final String DEFAULT_SHELL = "sh";
	protected String currentShell;

	public Shell() {
		this(null);
	}

	public Shell(String shell) {
		currentShell = shell != null ? shell : DEFAULT_SHELL;
	}

	public void start() {
		ProcessBuilder builder = new ProcessBuilder("");
	}

	public void stop() {

	}

	//	protected String getShell() {
	//		return(DEFAULT_SHELL);
	//	}
}
