package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ItemDao {
    private final DynamoDBMapper mapper;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper) {
        this.mapper = dynamoDBMapper;
    }

    public Item getItem(String itemId) {
        //TODO IMPLEMENT
        Item item = mapper.load(Item.class, itemId);
        if(item == null) {
            throw new IllegalArgumentException("itemId cannot be null");
        }
        return null;
    }

    public Item deleteItem() {
        //TODO IMPLEMENT
        return null;
    }

    public Item saveItem() {
        //ToDo IMPLEMENT
        return null;
    }

    public List<Item> searchItems() {
        //ToDo IMPLEMENT
        return null;
    }
}
