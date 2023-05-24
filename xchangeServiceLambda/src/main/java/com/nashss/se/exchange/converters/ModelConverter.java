package com.nashss.se.exchange.converters;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.dynamodb.models.Item;

public class ModelConverter {

    public ItemModel toItemModel(Item item) {
        return ItemModel.builder()
                .withItemId(item.getItemId())
                .withDescription(item.getDescription())
                .withEmail(item.getEmail())
                .withImages(item.getImages())
                .withTitle(item.getTitle())
                .withType(item.getType())
                .withExchanged(item.getExchanged())
                .build();
    }
}
