package com.rainsoft.training.course01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Get File Version 2.
 *
 * @author Lance
 *
 */
public class FileList02 {
	public List<String> getFiles(final String path) {
		final File folder = new File(path);
		final File[] listOfFiles = folder.listFiles();
		final List<String> fileNameList = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileNameList.add(listOfFiles[i].getAbsolutePath());
			} else if (listOfFiles[i].isDirectory()) {
				fileNameList.add(listOfFiles[i].getAbsolutePath());
			}
		}
		return fileNameList;
	}

	public final static void main(final String... args) {
		final FileList02 fileList = new FileList02();
		final List<String> fileNameList = fileList.getFiles("c:/");
		for (final String name : fileNameList) {
			System.out.println(name);
		}

	}
}
