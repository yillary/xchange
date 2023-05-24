package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.exchange.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.nashss.se.exchange.dynamodb.models.Item.ZIPCODE_TYPE_INDEX;

@Singleton
public class ItemDao {
    private final DynamoDBMapper mapper;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper) {
        this.mapper = dynamoDBMapper;
    }

    public Item getItem(String itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId cannot be null");
        }
        Item item = mapper.load(Item.class, itemId);

        if(item == null) {
            throw new IllegalArgumentException("Could not find item");
        }
        return item;
    }


//    public List<Item> getMemberItems(String memberId) {
//        //TODO IMPLEMENT
//        //make a call to the member dao to get the Listings list
//        //use this list to make a for loop and retrieve from the items table.
//        return null;
//    }



    public Boolean deleteItem(Item item) {
        if (item == null ){
            throw new IllegalArgumentException("Item cannot be null");
        }
        mapper.delete(item);
        return true;
    }

    /**
     * Use this to CREATE or UPDATE a new item in the table.
     * @param item to be created or updated in table.
     * @return boolean true if successful.
     */
    public Boolean saveItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        mapper.save(item);
        return true;
    }

    /**
     * Retrieves a list of clothing items based on zipcode and type. You can adjust the number of results as needed.
     * @param zipCode zipcode to search for HashKey
     * @param type type to search for RangeKey
     * @return list of items that match the zipCode and type
     */
//    public List<Item> searchItems(String zipCode, String type) {
//        Map<String, AttributeValue> valueMap = new HashMap<>();
//        valueMap.put(":type", new AttributeValue().withS(type));
//        valueMap.put(":zip_Code", new AttributeValue().withS(zipCode));
//        DynamoDBQueryExpression<Item> queryExpression = new DynamoDBQueryExpression<Item>()
//                .withIndexName(ZIPCODE_TYPE_INDEX)
//                .withConsistentRead(false)
//                .withKeyConditionExpression("type = :type and zipCode = :zip_Code")
////                .withLimit()
//                .withExpressionAttributeValues(valueMap);
//
//        //searchResults will only have items that have zip and type, no other information.
//        PaginatedQueryList<Item> searchResults = mapper.query(Item.class, queryExpression);
//        //fullyLoadedInfo gets all attributes of each item in searchResults
//        List<Item> fullyLoadedInfo = new ArrayList<>();
//        for(Item item : searchResults) {
//            fullyLoadedInfo.add(mapper.load(item));
//        }
//        return fullyLoadedInfo;
//    }
}
