package com.ecomshop.deskplus.repositories;

import com.ecomshop.deskplus.models.SubscriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Repository
public interface SubscriptionsRepository extends JpaRepository<SubscriptionsEntity, Long> {
}
