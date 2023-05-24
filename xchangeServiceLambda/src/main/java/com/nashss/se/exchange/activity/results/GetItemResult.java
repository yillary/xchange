package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;

public class GetItemResult {
    private final ItemModel item;

    private GetItemResult(ItemModel item) {
        this.item = item;
    }

    public ItemModel getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "GetItemResult{" +
                "item=" + item +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ItemModel item;

        public Builder withItem(ItemModel item){
            this.item = item;
            return this;
        }

        public GetItemResult build() {
            return new GetItemResult(item);
        }
    }

}
