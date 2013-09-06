package com.grilledmonkey.niceshell.internal;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;

public class ProcessInfo {
	public Process process;
	public BufferedReader input;
	public OutputStreamWriter output;

	public ProcessInfo(Process process, BufferedReader input, OutputStreamWriter output) {
		this.process = process;
		this.input = input;
		this.output = output;
	}

}
