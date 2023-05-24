package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "items")
public class Item {
//    public static final String ZIPCODE_TYPE_INDEX = "SearchByTypeZip";

    private String itemId;
    private String title;
    private String description;
    private String type;
    private Boolean exchanged;
    private Set<String> images;
    private String zipCode;
    private String email;

    @DynamoDBHashKey(attributeName = "item_Id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @DynamoDBIndexRangeKey(globalSecondaryIndexName = ZIPCODE_TYPE_INDEX, attributeName = "type")
    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "exchanged")
    public Boolean getExchanged() {
        return exchanged;
    }

    public void setExchanged(Boolean exchanged) {
        this.exchanged = exchanged;
    }

    @DynamoDBAttribute(attributeName = "images")
    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    //@DynamoDBIndexHashKey(globalSecondaryIndexName = ZIPCODE_TYPE_INDEX, attributeName = "zip_Code")
    @DynamoDBAttribute(attributeName = "zip_Code")
    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(title, item.title) && Objects.equals(description, item.description) && Objects.equals(type, item.type) && Objects.equals(exchanged, item.exchanged) && Objects.equals(images, item.images) && Objects.equals(zipCode, item.zipCode) && Objects.equals(email, item.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, title, description, type, exchanged, images, zipCode, email);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", exchanged=" + exchanged +
                ", images=" + images +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
