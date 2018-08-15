package com.spring.salesapi.models;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
public class Sales {
    @Id
    @ApiModelProperty(notes = "The database generated sales ID")
    String id;
    @ApiModelProperty(notes = "The person ID from the PersonAPI that is a Guid")
    String personId;
    @ApiModelProperty(notes = "The client ID from the ClientAPI that is an integer")
    long clientId;
    @ApiModelProperty(notes = "The inventory ID from the InventoryAPI that is an integer")
    long inventoryId;
    @ApiModelProperty(notes = "The price of the item bought, coming from the InventoryAPI")
    BigDecimal price;
    @ApiModelProperty(notes = "Any discounted price different from the main price, coming from the InventoryAPI")
    BigDecimal discount;
    @ApiModelProperty(notes = "Any tax on the sale")
    float tax;
    @ApiModelProperty(notes = "The quantity bought of the item")
    int quantity;
    @ApiModelProperty(notes = "Auto-generated date for the sales transaction")
    String created;
    @ApiModelProperty(notes = "Auto-generated date if the record is updated, otherwise null")
    String updated;

    public Sales() {
    }

    public Sales(String personId, long clientId, long inventoryId, BigDecimal price, BigDecimal discount, float tax, int quantity, String created) {
        this.personId = personId;
        this.clientId = clientId;
        this.inventoryId = inventoryId;
        this.price = price;
        this.discount = discount;
        this.tax = tax;
        this.quantity = quantity;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    
    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    
    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
