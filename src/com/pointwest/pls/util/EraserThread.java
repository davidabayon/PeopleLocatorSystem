package com.pointwest.pls.util;

public class EraserThread implements Runnable {
	private boolean stop;

	/**
	 * @param The
	 *            prompt displayed to the user
	 */
	public EraserThread(String prompt) {
		System.out.format("%91s", prompt);
	}

	/**
	 * Begin masking...display asterisks (*)
	 */
	public void run() {
		stop = true;
		while (stop) {
			System.out.print("\010*");
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Instruct the thread to stop masking
	 */
	public void stopMasking() {
		this.stop = false;
	}
}