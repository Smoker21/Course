package com.rainty.sample.starter.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rainty.sample.starter.dao.AssetTypeRepo;
import com.rainty.sample.starter.entity.AssetType;

@RestController
public class AssetTypeRest {
	private static final Logger logger = LoggerFactory.getLogger(AssetTypeRest.class);
	@Autowired
	AssetTypeRepo assetTypeRepo;

	@RequestMapping(value = "api/assetType", produces = { "application/json; charset=utf-8" }, method = { RequestMethod.GET })
	public List<AssetType> getAssetTypes() {
		return assetTypeRepo.findAll();
	}

}
