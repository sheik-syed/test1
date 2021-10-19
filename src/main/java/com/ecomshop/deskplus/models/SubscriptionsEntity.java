package com.ecomshop.deskplus.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Entity
@Table(name = "subscriptions")
public class SubscriptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long subscription_id;

    private String reg_unique_id;

    private String stripe_subscription_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sub_start_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sub_end_date;

    private boolean is_in_trail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trail_start_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trail_end_date;

    private String sub_status;

    @Column(name = "product_key")
    private String product_key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_id", nullable = false)
    private RegistrationsEntity registration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlansEntity plan;


    public Long getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Long subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getReg_unique_id() {
        return reg_unique_id;
    }

    public void setReg_unique_id(String reg_unique_id) {
        this.reg_unique_id = reg_unique_id;
    }

    public String getStripe_subscription_id() {
        return stripe_subscription_id;
    }

    public void setStripe_subscription_id(String stripe_subscription_id) {
        this.stripe_subscription_id = stripe_subscription_id;
    }

    public Date getSub_start_date() {
        return sub_start_date;
    }

    public void setSub_start_date(Date sub_start_date) {
        this.sub_start_date = sub_start_date;
    }

    public Date getSub_end_date() {
        return sub_end_date;
    }

    public void setSub_end_date(Date sub_end_date) {
        this.sub_end_date = sub_end_date;
    }

    public boolean isIs_in_trail() {
        return is_in_trail;
    }

    public void setIs_in_trail(boolean is_in_trail) {
        this.is_in_trail = is_in_trail;
    }

    public Date getTrail_start_date() {
        return trail_start_date;
    }

    public void setTrail_start_date(Date trail_start_date) {
        this.trail_start_date = trail_start_date;
    }

    public Date getTrail_end_date() {
        return trail_end_date;
    }

    public void setTrail_end_date(Date trail_end_date) {
        this.trail_end_date = trail_end_date;
    }

    public String getSub_status() {
        return sub_status;
    }

    public void setSub_status(String sub_status) {
        this.sub_status = sub_status;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public RegistrationsEntity getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationsEntity registration) {
        this.registration = registration;
    }

    public PlansEntity getPlan() {
        return plan;
    }

    public void setPlan(PlansEntity plan) {
        this.plan = plan;
    }
}
