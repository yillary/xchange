package com.nashss.se.exchange.activity.requests;

public class GetItemRequest {
    private final String itemId;

    private GetItemRequest(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return this.itemId;
    }


    @Override
    public String toString() {
        return "GetItemRequest{" +
                "itemId='" + itemId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String itemId;

        public Builder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public GetItemRequest build() {
            return new GetItemRequest(itemId);
        }
    }
}
