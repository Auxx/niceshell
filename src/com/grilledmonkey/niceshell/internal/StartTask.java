package com.grilledmonkey.niceshell.internal;

import android.os.AsyncTask;

public class StartTask extends AsyncTask<Void, Void, Void> {
	private final String shell;

	public StartTask(String shell) {
		this.shell = shell;
	}

	@Override
	protected Void doInBackground(Void... arg) {
		ProcessBuilder builder = new ProcessBuilder(shell);
		builder.redirectErrorStream(true);

		return(null);
	}

}
