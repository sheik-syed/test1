package com.ecomshop.deskplus.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Sheik Syed Ali
 * Date: 09 Oct 2021
 */
@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long product_id;

    private String product_name;

    private String product_description;

    private String product_key;

    private String product_stripe_id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "product")
    private Set<PlansEntity> plans;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getProduct_stripe_id() {
        return product_stripe_id;
    }

    public void setProduct_stripe_id(String product_stripe_id) {
        this.product_stripe_id = product_stripe_id;
    }

    public Set<PlansEntity> getPlans() {
        return plans;
    }

    public void setPlans(Set<PlansEntity> plans) {
        this.plans = plans;
    }
}
