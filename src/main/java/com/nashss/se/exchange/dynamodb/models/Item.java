package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "items")
public class Item {
    private String itemId;
    private String title;
    private String description;
    private String type;
    private Boolean exchanged;
    private Set<String> images;

    public Item(String itemId, String title, String description, String type, Boolean exchanged, Set<String> images) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.exchanged = exchanged;
        this.images = images;
    }

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

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "is_Exchanged")
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


    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", exchanged=" + exchanged +
                ", images=" + images +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(title, item.title) && Objects.equals(description, item.description) && Objects.equals(type, item.type) && Objects.equals(exchanged, item.exchanged) && Objects.equals(images, item.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, title, description, type, exchanged, images);
    }


}
