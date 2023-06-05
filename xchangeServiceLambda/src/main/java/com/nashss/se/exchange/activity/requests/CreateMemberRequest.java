package com.nashss.se.exchange.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateMemberRequest.class)
public class CreateMemberRequest {
    private final String zipCode;
    private final String memberId;


    public CreateMemberRequest(String zipCode, String memberId) {
        this.zipCode = zipCode;
        this.memberId = memberId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getMemberId() {
        return memberId;
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateMemberRequest.Builder builder() {
        return new CreateMemberRequest.Builder();
    }

    @Override
    public String toString() {
        return "CreateMemberRequest{" +
                "zipCode='" + zipCode + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }

    public static class Builder {
        private String zipCode;
        private String memberId;

        public CreateMemberRequest.Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CreateMemberRequest.Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        public CreateMemberRequest build() {
            return new CreateMemberRequest(zipCode, memberId);
        }
    }
}
