package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateItemRequest.Builder.class)
public class UpdateItemRequest {
    private final String title;
    private final String description;
    private final String zipCode;
    private final String itemId;
    private final String memberId;

    public UpdateItemRequest(String title, String description, String zipCode, String itemId, String memberId) {
        this.title = title;
        this.description = description;
        this.zipCode = zipCode;
        this.itemId = itemId;
        this.memberId = memberId;
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

    public String getMemberId() {
        return memberId;
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
                ", memberId='" + memberId + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        private String description;
        private String zipCode;
        private String itemId;
        private String memberId;

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

        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public UpdateItemRequest build() {
            return new UpdateItemRequest(title, description, zipCode, itemId, memberId);
        }
    }
}
