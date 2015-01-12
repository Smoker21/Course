package com.rainty.sample.starter.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-13T07:43:21.107+0800")
@StaticMetamodel(Asset.class)
public class Asset_ {
	public static volatile SingularAttribute<Asset, Long> assetId;
	public static volatile SingularAttribute<Asset, String> assetName;
	public static volatile SingularAttribute<Asset, String> assetType;
	public static volatile SingularAttribute<Asset, String> description;
	public static volatile SingularAttribute<Asset, String> location;
	public static volatile SingularAttribute<Asset, Timestamp> updateDt;
	public static volatile SingularAttribute<Asset, String> updateUser;
}
