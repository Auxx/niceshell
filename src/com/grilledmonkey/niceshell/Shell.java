package com.grilledmonkey.niceshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;

public class Shell {
	private static final String DEFAULT_SHELL = "sh";
	private static final String SEPARATOR = "\n";
	private static final String STARTER = "::NS ";
	private static final String TERMINATOR = " NS::";
	private static final String TERM_COMMAND = "echo \"" + STARTER + "$?" + TERMINATOR + "\"\n";

	private boolean isRunning = false;

	protected String currentShell;
	protected BufferedReader in;
	protected OutputStreamWriter out;

	public Shell() {
		this(null);
	}

	public Shell(String shell) {
		currentShell = shell != null ? shell : DEFAULT_SHELL;
	}

	public void start() {
		ProcessBuilder builder = new ProcessBuilder(DEFAULT_SHELL);
		builder.redirectErrorStream(true);
		try {
			Process process = builder.start();
			isRunning = true;

			in = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			out = new OutputStreamWriter(process.getOutputStream(), "UTF-8");
			//
			//			addCommand(new Command("id"));
			//			addCommand(new Command("pwd"));
			//			execute("exit" + SEPARATOR);

			stop();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if(isRunning) {
			try {
				execute("exit" + SEPARATOR);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			isRunning = false;
		}
	}

	public void addCommand(String command) throws IOException {
		addCommand(new Command(command));
	}

	public void addCommand(Command command) throws IOException {
		String result = command.getCommand() + SEPARATOR + TERM_COMMAND;
		execute(result);
	}

	protected void execute(String command) throws IOException {
		out.write(command);
		out.flush();
		String line;
		while((line = in.readLine()) != null) {
			Log.w("SHELL", "Line: " + line);
			if(line.endsWith(TERMINATOR)) {
				break;
			}
		}
	}

	public boolean isRunning() {
		return(isRunning);
	}

}
