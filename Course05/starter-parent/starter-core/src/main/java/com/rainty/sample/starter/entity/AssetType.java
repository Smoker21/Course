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
 * The persistent class for the ASSET_TYPE database table.
 *
 */
@Entity
@Table(name = "ASSET_TYPE")
@NamedQuery(name = "AssetType.findAll", query = "SELECT a FROM AssetType a")
public class AssetType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ASSET_TYPE")
	private String assetType;

	@Column(name = "TYPE_DESCRIPTION")
	private String typeDescription;

	@Column(name = "UPDATE_DT")
	private Timestamp updateDt;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	public AssetType() {
	}

	public String getAssetType() {
		return this.assetType;
	}

	public void setAssetType(final String assetType) {
		this.assetType = assetType;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(final String typeDescription) {
		this.typeDescription = typeDescription;
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

	@Override
	public String toString() {
		return "AssetType [assetType=" + assetType + ", typeDescription=" + typeDescription + ", updateDt=" + updateDt
				+ ", updateUser=" + updateUser + "]";
	}

}