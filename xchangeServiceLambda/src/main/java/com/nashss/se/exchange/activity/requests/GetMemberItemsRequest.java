package com.nashss.se.exchange.activity.requests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetMemberItemsRequest {
    private final String memberId;

    public GetMemberItemsRequest(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "GetMemberItemsRequest{" +
                "memberId='" + memberId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String memberId;

        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public GetMemberItemsRequest build() {
            return new GetMemberItemsRequest(memberId);
        }
    }

}
