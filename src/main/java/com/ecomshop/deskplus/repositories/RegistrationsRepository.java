package com.ecomshop.deskplus.repositories;

import com.ecomshop.deskplus.models.PlansEntity;
import com.ecomshop.deskplus.models.RegistrationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Repository
public interface RegistrationsRepository extends JpaRepository<RegistrationsEntity, Long> {

    @Query("SELECT r FROM RegistrationsEntity r WHERE r.activation_key = :activationKey and r.activated = false")
    RegistrationsEntity findRegistrationByActivationKey(@Param("activationKey") String activationKey);

    @Query("SELECT r FROM RegistrationsEntity r WHERE r.reg_unique_id = :regUniqueId")
    RegistrationsEntity getRegistrationByRegUniqueId(@Param("regUniqueId") String regUniqueId);
}
