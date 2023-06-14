package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateItemRequest.Builder.class)
public class UpdateItemRequest {
    private final String title;
    private final String description;
    private final String zipCode;
    private final String itemId;
    private final String email;
    private final Boolean exchanged;

    public UpdateItemRequest(String title, String description, String zipCode, String itemId, String email, Boolean exchanged) {
        this.title = title;
        this.description = description;
        this.zipCode = zipCode;
        this.itemId = itemId;
        this.email = email;
        this.exchanged = exchanged;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getItemId() {
        return itemId;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getExchanged() {
        return exchanged;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UpdateItemRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemId='" + itemId + '\'' +
                ", email='" + email + '\'' +
                ", exchanged=" + exchanged +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        private String description;
        private String zipCode;
        private String itemId;
        private String email;
        private Boolean exchanged;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withExchanged(Boolean exchanged) {
            this.exchanged = exchanged;
            return this;
        }

        public UpdateItemRequest build() {
            return new UpdateItemRequest(title, description, zipCode, itemId, email, exchanged);
        }
    }
}
