package com.rainty.sample.starter.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rainty.sample.starter.config.ApplicationConfig;
import com.rainty.sample.starter.entity.AssetType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class AssetTypeRepoTest {

	@Autowired
	AssetTypeRepo assetTypeRepo;

	@Test
	public void test() {
		final long count = assetTypeRepo.count();
		assertEquals(3L, count);
		final AssetType type = new AssetType();
		type.setAssetType("New-Type");
		type.setTypeDescription("Test");
		type.setUpdateDt(new Timestamp(System.nanoTime()));
		type.setUpdateUser("TEST-USER");
		assetTypeRepo.save(type);
		final List<AssetType> assets = assetTypeRepo.findAll();
		for (final AssetType a : assets) {
			System.out.println("##### Asset :" + a);
		}
		assertEquals(4L, assetTypeRepo.count());
		assetTypeRepo.delete("New-Type");
		assertEquals(3L, assetTypeRepo.count());
	}

}
