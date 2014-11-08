package com.rainsoft.training.course01;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

/**
 * This is for course purpose only. We don't write duplicate code.
 * 
 * @author Lance
 *
 */
public class FileList03 {
	public Collection<File> getFiles(final String path) {
		return FileUtils.listFiles(new File(path), new String[] { "md" }, true);
	}
}
