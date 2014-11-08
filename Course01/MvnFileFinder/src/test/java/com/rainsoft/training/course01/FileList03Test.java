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
		testPath = "testfolder";
	}

	@Test
	public void testGetFiles() {
		final FileList03 f03 = new FileList03();
		final Collection<File> files = f03.getFiles(testPath);
		final boolean result = files.contains(new File("testFolder/testme.md"));
		Assert.assertEquals(true, result);
		Assert.assertEquals(2, files.size());
	}

}
