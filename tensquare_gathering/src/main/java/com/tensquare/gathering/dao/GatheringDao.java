package com.tensquare.gathering.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.gathering.pojo.Gathering;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/30
 */
public interface GatheringDao extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {
}
