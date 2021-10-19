package com.ecomshop.deskplus.services;

import com.ecomshop.deskplus.models.RegistrationsEntity;
import com.ecomshop.deskplus.models.RolesEntity;
import com.ecomshop.deskplus.models.SubscriptionsEntity;
import com.ecomshop.deskplus.models.UsersEntity;
import com.ecomshop.deskplus.web.rest.CreateUserRequest;
import com.ecomshop.deskplus.web.rest.LoginRequest;
import com.ecomshop.deskplus.web.rest.Response;


import java.util.List;
import java.util.Map;

/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
public interface UserServices {

    Response authenticate(LoginRequest loginRequest);

    String authenticate(String userName, String password) throws Exception;

    Response createUser(CreateUserRequest createUserRequest);

    UsersEntity createUser(String firstName, String lastName, String email, String password, List<RolesEntity> roles, boolean isAccountOwner, RegistrationsEntity registrationsEntity, UsersEntity createdBy);

    Map<String, Object> getRegistrationDetails(SubscriptionsEntity subscriptionsEntity);
}
