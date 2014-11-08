package com.rainsoft.training.course01;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileList02Test {

	@Before
	public void setupTest() {

	}

	@Test
	public void testGetFiles() {
		final FileList02 f02 = new FileList02();
		final List<String> list = f02.getFiles("testFolder/");
		Assert.assertEquals(1, list.size());
		final File f = new File("testFolder/testme.md");
		Assert.assertEquals(f.getAbsolutePath(), list.get(0));
	}

}
