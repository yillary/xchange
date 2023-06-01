package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = CreateItemRequest.Builder.class)
public class CreateItemRequest {
    private final String title;
    private final String description;
    private final String type;
    private final Boolean exchanged;
    private final Set<String> images;
    private final String zipCode;

    public CreateItemRequest(String title, String description, String type, Boolean exchanged, Set<String> images, String zipCode) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.exchanged = exchanged;
        this.images = images;
        this.zipCode = zipCode;
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

    public Set<String> getImages() {
        return images;
    }

    public String getZipCode() {
        return zipCode;
    }


    @Override
    public String toString() {
        return "CreateItemRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", exchanged=" + exchanged +
                ", images=" + images +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateItemRequest.Builder builder() {
        return new CreateItemRequest.Builder();
    }

    public static class Builder {
        private String title;
        private String description;
        private String type;
        private Boolean exchanged;
        private Set<String> images;
        private String zipCode;

        public CreateItemRequest.Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        public CreateItemRequest.Builder withDescription(String description) {
            this.description = description;
            return this;
        }
        public CreateItemRequest.Builder withType(String type) {
            this.type = type;
            return this;
        }
        public CreateItemRequest.Builder withExchanged(Boolean exchanged) {
            this.exchanged = exchanged;
            return this;
        }
        public CreateItemRequest.Builder withImages(Set<String> images) {
            this.images = images;
            return this;
        }
        public CreateItemRequest.Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CreateItemRequest build() {
            return new CreateItemRequest(title, description, type, exchanged, images, zipCode);
        }
    }
}
