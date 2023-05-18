package com.nashss.se.exchange.dynamodb.models;

import java.util.Objects;
import java.util.Set;

@DynamoDbTable(tableName = "items")
public class Item {
    private String itemId;
    private String title;
    private String description;
    private String type;
    private Boolean exchanged;
    private Set<String> images;
    private String email;

    public Item(String itemId, String title, String description, String type, Boolean exchanged, Set<String> images, String email) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.exchanged = exchanged;
        this.images = images;
        this.email = email;
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

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(title, item.title) && Objects.equals(description, item.description) && Objects.equals(type, item.type) && Objects.equals(exchanged, item.exchanged) && Objects.equals(images, item.images) && Objects.equals(email, item.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, title, description, type, exchanged, images, email);
    }


}
