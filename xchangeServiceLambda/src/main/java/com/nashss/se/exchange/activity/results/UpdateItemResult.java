package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;

public class UpdateItemResult {
    private final ItemModel itemModel;

    public UpdateItemResult(ItemModel itemModel) {
        this.itemModel = itemModel;
    }


    @Override
    public String toString() {
        return "UpdateItemResult{" +
                "itemModel=" + itemModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ItemModel itemModel;

        public Builder withItem(ItemModel itemModel) {
            this.itemModel = itemModel;
            return this;
        }

        public UpdateItemResult build() {
            return new UpdateItemResult(itemModel);
        }
    }
}
