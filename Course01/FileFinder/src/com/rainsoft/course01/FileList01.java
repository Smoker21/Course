package com.rainsoft.course01;

import java.io.File;

public class FileList01 {

	public static void main(final String[] args) {
		final File folder = new File("C:/");
		final File[] listOfFiles = folder.listFiles();

		for (File listOfFile : listOfFiles) {
			if (listOfFile.isFile()) {
				System.out.println("File " + listOfFile.getName());
			} else if (listOfFile.isDirectory()) {
				System.out.println("Directory " + listOfFile.getName());
			}
		}
	}

}
