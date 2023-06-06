//package com.nashss.se.exchange.activity.results;
//
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.nashss.se.exchange.activity.requests.GetItemRequest;
//import com.nashss.se.exchange.activity.requests.GetMemberItemsRequest;
//
//@JsonDeserialize(builder = GetMemberItemsRequest.class)
//public class GetMemberItemsResult {
//    private final String memberId;
//
//    public GetMemberItemsResult(String memberId) {
//        this.memberId = memberId;
//    }
//
//    public String getMemberId() {
//        return memberId;
//    }
//
//
//    @Override
//    public String toString() {
//        return "GetMemberItemsResult{" +
//                "memberId='" + memberId + '\'' +
//                '}';
//    }
//
//    //CHECKSTYLE:OFF:Builder
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder {
//        private String memberId;
//
//        public Builder withMemberId(String memberId) {
//            this.memberId = memberId;
//            return this;
//        }
//
//        public GetMemberItemsRequest build() { return new GetMemberItemsResult(memberId); }
//    }
//}
