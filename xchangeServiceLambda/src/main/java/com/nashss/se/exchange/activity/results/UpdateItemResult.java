package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;

public class UpdateItemResult {
    private final ItemModel item;

    public UpdateItemResult(ItemModel item) {
        this.item = item;
    }

    public ItemModel getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "UpdateItemResult{" +
                "item=" + item +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ItemModel item;

        public Builder withItem(ItemModel item) {
            this.item = item;
            return this;
        }

        public UpdateItemResult build() {
            return new UpdateItemResult(item);
        }
    }
}
