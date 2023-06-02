package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;


public class CreateItemResult {
    private final ItemModel itemModel;

    private CreateItemResult(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public ItemModel getItem() {
        return itemModel;
    }


    @Override
    public String toString() {
        return "CreateItemResult{" +
                "itemModel=" + itemModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ItemModel itemModel;

        public Builder withPlaylist(ItemModel itemModel) {
            this.itemModel = itemModel;
            return this;
        }

        public CreateItemResult build() {
            return new CreateItemResult(itemModel);
        }
    }
}
