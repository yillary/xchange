package com.nashss.se.exchange.Models;

import java.util.Objects;
import java.util.Set;

/**
 * Creates an ItemModel
 */
public class ItemModel {
    private final String itemId;
    private final String title;
    private final String description;
    private final String type;
    private final Boolean exchanged;
    private final String email;
    private final String zipCode;


    private ItemModel(String itemId, String title, String description, String type, Boolean exchanged, String email, String zipCode) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.exchanged = exchanged;
        this.email = email;
        this.zipCode = zipCode;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Boolean getExchanged() {
        return exchanged;
    }

    public String getEmail() {
        return email;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", exchanged=" + exchanged +
                ", email='" + email + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemModel itemModel = (ItemModel) o;
        return Objects.equals(itemId, itemModel.itemId) && Objects.equals(title, itemModel.title)
                && Objects.equals(description, itemModel.description) && Objects.equals(type, itemModel.type)
                && Objects.equals(exchanged, itemModel.exchanged) && Objects.equals(email, itemModel.email)
                && Objects.equals(zipCode, itemModel.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, title, description, type, exchanged, email, zipCode);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String itemId;
        private String title;
        private String description;
        private String type;
        private Boolean exchanged;
        private String email;
        private String zipCode;

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withExchanged(Boolean exchanged) {
            this.exchanged = exchanged;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ItemModel build() {
            return new ItemModel(itemId, title, description, type, exchanged, email, zipCode);
        }
    }
}
