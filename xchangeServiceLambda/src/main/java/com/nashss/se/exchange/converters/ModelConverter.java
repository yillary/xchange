package com.nashss.se.exchange.converters;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.Models.MemberModel;
import com.nashss.se.exchange.Item;
import com.nashss.se.exchange.Member;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withItemId(item.getItemId())
                .withDescription(item.getDescription())
                .withEmail(item.getEmail())
                .withZipCode(item.getZipCode())
                .withTitle(item.getTitle())
                .withType(item.getItemType())
                .withExchanged(item.getExchanged())
                .build();
    }

    public MemberModel toMemberModel(Member model) {
        return MemberModel.builder()
                .withListings(model.getListings())
                .withMemberId(model.getMemberId())
                .withZipCode(model.getZipCode())
                .build();
    }
}
