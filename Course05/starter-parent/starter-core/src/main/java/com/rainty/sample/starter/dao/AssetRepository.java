package com.rainty.sample.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rainty.sample.starter.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
