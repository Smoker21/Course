package com.rainsoft.training.course01;

import java.io.File;

public class FileList01 {

	public static void main(final String[] args) {
		final File folder = new File("C:/");
		final File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}

}
