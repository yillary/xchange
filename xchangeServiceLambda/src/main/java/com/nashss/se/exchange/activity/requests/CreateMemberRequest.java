package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateMemberRequest.class)
public class CreateMemberRequest {
    private final String zipCode;


    public CreateMemberRequest(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "CreateMemberRequest{" +
                "zipCode='" + zipCode + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateMemberRequest.Builder builder() {
        return new CreateMemberRequest.Builder();
    }

    public static class Builder {
        private String zipCode;

        public CreateMemberRequest.Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CreateMemberRequest build() {
            return new CreateMemberRequest(zipCode);
        }
    }
}
