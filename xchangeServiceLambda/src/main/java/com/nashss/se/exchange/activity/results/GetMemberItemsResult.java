package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.ItemModel;

import java.util.Set;


public class GetMemberItemsResult {
    private final Set<ItemModel> memberItems;

    public GetMemberItemsResult(Set<ItemModel> memberItems) {
        this.memberItems = memberItems;
    }

    public Set<ItemModel> getMemberItems() {
        return memberItems;
    }

    @Override
    public String toString() {
        return "GetMemberItemsResult{" +
                "memberItems=" + memberItems +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Set<ItemModel> memberItems;

        public Builder withMemberItems(Set<ItemModel> items) {
            this.memberItems = items;
            return this;
        }

        public GetMemberItemsResult build() { return new GetMemberItemsResult(memberItems); }
    }
}
