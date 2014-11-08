package com.rainsoft.course01;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class FileList03 {
	public Collection<File> getFiles(final String path) {
		return FileUtils.listFiles(new File(path), new String[] { "md" }, false);
	}
}
