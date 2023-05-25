package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.dynamodb.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchTypeZipResult {
    private final List<Item> items;


    public SearchTypeZipResult(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
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
        private List<Item> items ;

        public Builder withItems(List<Item> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public SearchTypeZipResult build() {
            return new SearchTypeZipResult(items);
        }

    }

}