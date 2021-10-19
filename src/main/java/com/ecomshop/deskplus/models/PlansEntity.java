package com.ecomshop.deskplus.models;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Entity
@Table(name = "plans")
public class PlansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long plan_id;

    private String plan_name;

    private String plan_interval;

    private String plan_currency;

    private String plan_amount;

    private String plan_stripe_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductsEntity product;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "plan")
    private List<SubscriptionsEntity> subscriptions;

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getPlan_interval() {
        return plan_interval;
    }

    public void setPlan_interval(String plan_interval) {
        this.plan_interval = plan_interval;
    }

    public String getPlan_currency() {
        return plan_currency;
    }

    public void setPlan_currency(String plan_currency) {
        this.plan_currency = plan_currency;
    }

    public String getPlan_amount() {
        return plan_amount;
    }

    public void setPlan_amount(String plan_amount) {
        this.plan_amount = plan_amount;
    }

    public String getPlan_stripe_id() {
        return plan_stripe_id;
    }

    public void setPlan_stripe_id(String plan_stripe_id) {
        this.plan_stripe_id = plan_stripe_id;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public List<SubscriptionsEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionsEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
