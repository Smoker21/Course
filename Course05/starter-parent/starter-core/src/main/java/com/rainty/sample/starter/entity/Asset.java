package com.rainty.sample.starter.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ASSETS database table.
 *
 */
@Entity
@Table(name = "ASSETS")
@NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ASSET_ID")
	private long assetId;

	@Column(name = "ASSET_NAME")
	private String assetName;

	@Column(name = "ASSET_TYPE")
	private String assetType;

	private String description;

	private String location;

	@Column(name = "UPDATE_DT")
	private Timestamp updateDt;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	public Asset() {
	}

	public long getAssetId() {
		return this.assetId;
	}

	public void setAssetId(final long assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return this.assetName;
	}

	public void setAssetName(final String assetName) {
		this.assetName = assetName;
	}

	public String getAssetType() {
		return this.assetType;
	}

	public void setAssetType(final String assetType) {
		this.assetType = assetType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public Timestamp getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(final Timestamp updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(final String updateUser) {
		this.updateUser = updateUser;
	}

}