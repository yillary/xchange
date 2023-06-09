package com.nashss.se.exchange.activity.requests;

public class SearchTypeZipRequest {
    private final String zipCode;
    private final String type;
    private final String criteria;

    public SearchTypeZipRequest(String zipCode, String type, String criteria) {
        this.zipCode = zipCode;
        this.type = type;
        this.criteria = criteria;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getType() {
        return type;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchTypeZipRequest{" +
                "zipCode='" + zipCode + '\'' +
                ", type='" + type + '\'' +
                ", criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String zipCode;
        private String type;
        private String criteria;

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchTypeZipRequest build() {
            return new SearchTypeZipRequest(zipCode, type, criteria);
        }
    }
}
