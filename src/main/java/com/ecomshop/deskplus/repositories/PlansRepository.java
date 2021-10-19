package com.ecomshop.deskplus.repositories;

import com.ecomshop.deskplus.models.PlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Repository
public interface PlansRepository extends JpaRepository<PlansEntity, Long> {

    @Query("SELECT p FROM PlansEntity p WHERE p.plan_stripe_id = :planStripeId")
    PlansEntity findPlanByStripeId(@Param("planStripeId") String planStripeId);

}
