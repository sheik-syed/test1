package com.ecomshop.deskplus.repositories;

import com.ecomshop.deskplus.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: Sheik Syed Ali
 * Date: 24 Sep 2021
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByEmail(String email);

    @Query("SELECT u FROM UsersEntity u " +
            "INNER JOIN RegistrationsEntity r ON r.reg_id = u.registration.reg_id " +
            "WHERE u.user_id = :userId AND r.reg_unique_id = :regId")
    UsersEntity getUser(@Param("userId") Long userId, @Param("regId") String regId);

//    @Query("SELECT u FROM UsersEntity u WHERE u.email = :email")
//    UsersEntity fetchByEmail(@Param("email") String email);
}
