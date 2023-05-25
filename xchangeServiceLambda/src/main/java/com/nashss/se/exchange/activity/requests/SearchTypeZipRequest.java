package com.nashss.se.exchange.activity.requests;

public class SearchTypeZipRequest {
    private final String zipCode;
    private final String type;

    public SearchTypeZipRequest(String zipCode, String type) {
        this.zipCode = zipCode;
        this.type = type;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SearchTypeZipRequest{" +
                "zipCode='" + zipCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String zipCode;
        private String type;

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public SearchTypeZipRequest build() {
            return new SearchTypeZipRequest(zipCode, type);
        }
    }
}