
package com.expensetracking.models;

/**
 * Created by sandz on 02/27/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generated from http://www.jsonschema2pojo.org/ using GSON format
 */
public class Expense {

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("amount")
    @Expose
    private Double amount;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("added_on")
    @Expose
    private String addedOn;

    @SerializedName("description")
    @Expose
    private String description;

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The addedOn
     */
    public String getAddedOn() {
        return addedOn;
    }

    /**
     * @param addedOn The added_on
     */
    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}