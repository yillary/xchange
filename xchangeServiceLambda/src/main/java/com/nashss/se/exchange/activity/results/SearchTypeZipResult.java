package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTypeZipResult {
    private final List<ItemModel> items;


    public SearchTypeZipResult(List<ItemModel> items) {
        this.items = items;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SearchTypeZipResult{" +
                "items=" + items +
                '}';
    }

        //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ItemModel> items ;

        public Builder withItems(List<ItemModel> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public SearchTypeZipResult build() {
            return new SearchTypeZipResult(items);
        }

    }
}
