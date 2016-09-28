/**
 *
 */
package com.rainty.sample.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rainty.sample.starter.entity.AssetType;

@Repository
public interface AssetTypeRepo extends JpaRepository<AssetType, String> {

}
