package com.rainsoft.training.course01;

import java.io.File;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileList03Test {

	String testPath;

	@Before
	public void setup() {
		testPath = ClassLoader.getSystemResource("testfolder").getPath();
	}

	@Test
	public void testGetFiles() {
		final FileList03 f03 = new FileList03();

		final Collection<File> files = f03.getFiles("testPath");
		Assert.assertEquals(2, files.size());
		final File f = new File("testFolder/testme.md");
		Assert.assertEquals(f.getAbsolutePath(), ((File) files.toArray()[0]).getAbsolutePath());
		System.out.println(((File) files.toArray()[0]).getAbsolutePath());
	}

}
