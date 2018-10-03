package com.donghd.notend.repository;

import com.donghd.notend.domain.ConfigInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConfigInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfigInfoRepository extends JpaRepository<ConfigInfo, Long> {

}
