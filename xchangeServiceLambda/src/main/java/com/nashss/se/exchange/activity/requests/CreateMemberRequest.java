package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateMemberRequest.Builder.class)
public class CreateMemberRequest {
    private final String memberId;
    private final String zipCode;


    public CreateMemberRequest(String memberId, String zipCode) {
        this.memberId = memberId;
        this.zipCode = zipCode;
    }


    public String getMemberId() {
        return memberId;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "CreateMemberRequest{" +
                "memberId='" + memberId + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String memberId;
        private String zipCode;


        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CreateMemberRequest build() {
            return new CreateMemberRequest(memberId, zipCode);
        }
    }
}
