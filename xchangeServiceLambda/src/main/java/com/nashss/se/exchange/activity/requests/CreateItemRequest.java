package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateItemRequest.Builder.class)
public class CreateItemRequest {
    private final String title;
    private final String description;
    private final String type;
    private final String zipCode;
    private final String email;

    public CreateItemRequest(String title, String description, String type, String zipCode, String email) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.zipCode = zipCode;
        this.email = email;
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

    public String getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CreateItemRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        private String description;
        private String type;
        private String zipCode;
        private String email;

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

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public CreateItemRequest build() {
            return new CreateItemRequest(title, description, type, zipCode, email);
        }
    }
}
