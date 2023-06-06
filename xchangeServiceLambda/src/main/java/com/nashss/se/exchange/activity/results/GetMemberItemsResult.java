package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.dynamodb.Item;

import java.util.Set;


public class GetMemberItemsResult {
    private final Set<Item> memberItems;

    public GetMemberItemsResult(Set<Item> memberItems) {
        this.memberItems = memberItems;
    }

    public Set<Item> getMemberItems() {
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
        private Set<Item> memberItems;

        public Builder withMemberItems(Set<Item> items) {
            this.memberItems = items;
            return this;
        }

        public GetMemberItemsResult build() { return new GetMemberItemsResult(memberItems); }
    }
}
