package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ItemDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Item getItem() {
        //TODO IMPLENT
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
