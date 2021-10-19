package com.ecomshop.deskplus.repositories;

import com.ecomshop.deskplus.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Sheik Syed Ali
 * Date: 24 Sep 2021
 */
@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    RolesEntity findByRole(String role);

}
