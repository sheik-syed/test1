package com.ecomshop.deskplus.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */

@Entity
@Table(name = "registrations")
public class RegistrationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long reg_id;

    private String reg_unique_id;

    private String cust_stripe_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registration_time;

    private String activation_key;

    @Temporal(TemporalType.TIMESTAMP)
    private Date activation_time;

    private boolean activated;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "registration")
    private Set<UsersEntity> users;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "registration")
    private List<SubscriptionsEntity> subscriptions;


    public Long getReg_id() {
        return reg_id;
    }

    public void setReg_id(Long reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_unique_id() {
        return reg_unique_id;
    }

    public void setReg_unique_id(String reg_unique_id) {
        this.reg_unique_id = reg_unique_id;
    }

    public String getCust_stripe_id() {
        return cust_stripe_id;
    }

    public void setCust_stripe_id(String cust_stripe_id) {
        this.cust_stripe_id = cust_stripe_id;
    }

    public Date getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(Date registration_time) {
        this.registration_time = registration_time;
    }

    public String getActivation_key() {
        return activation_key;
    }

    public void setActivation_key(String activation_key) {
        this.activation_key = activation_key;
    }

    public Date getActivation_time() {
        return activation_time;
    }

    public void setActivation_time(Date activation_time) {
        this.activation_time = activation_time;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersEntity> users) {
        this.users = users;
    }

    public List<SubscriptionsEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionsEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
