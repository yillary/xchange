package com.nashss.se.exchange.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

import static com.nashss.se.exchange.dynamodb.Item.ZIPCODE_TYPE_INDEX;

@Singleton
public class ItemDao {
    private final DynamoDBMapper mapper;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper) {
        this.mapper = dynamoDBMapper;
    }

    public Item getItem(String itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("ItemDao.getItem(): itemId cannot be null");
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
     * Retrieves a list of clothing items based on zipcode and type. It must have a zip code and a type, but criteria is
     * not necessary.
     * @param zipCode zipcode to search for HashKey
     * @param type type to search for RangeKey
     * @return list of items that match the zipCode and type
     */
    public List<Item> searchItems(String zipCode, String type, String[] criteria) {
        System.out.println("ItemDao.searchItems searching criteria: " + zipCode + " " + type + " " + Arrays.toString(criteria));

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":zipCode", new AttributeValue().withS(zipCode));
        valueMap.put(":itemType", new AttributeValue().withS(type));
        DynamoDBQueryExpression<Item> queryExpression = new DynamoDBQueryExpression<Item>()
                .withIndexName(ZIPCODE_TYPE_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("zipCode = :zipCode and itemType = :itemType")
                .withExpressionAttributeValues(valueMap);

        //searchResults will only have items that have zip and type, no other information.
        PaginatedQueryList<Item> searchResults = null;
        try {
            searchResults = mapper.query(Item.class, queryExpression);
        } catch(AmazonDynamoDBException e ) {
            System.out.println("Error is thrown when querying db: " + e.getMessage());
        }

        //fullyLoadedInfo gets all attributes of each item in searchResults
        List<Item> fullyLoadedInfo = new ArrayList<>();
        for(Item item : searchResults) {
            fullyLoadedInfo.add(mapper.load(item));
        }
        if (criteria == null) {
            return fullyLoadedInfo;
        }

        List<Item> criteriaResults = this.searchDescription(fullyLoadedInfo, criteria);

        return criteriaResults;
    }

    private List<Item> searchDescription(List<Item> preliminarySearchResults, String[] criteria) {
        System.out.println("ItemDao.searchDescription() with criteria: " + criteria.toString());
       List<Item> results = new ArrayList<>();

        for(Item item : preliminarySearchResults) {
            String description = item.getDescription().toLowerCase();
            boolean matchFound = false;
            for(String word : criteria){
                if (description.contains(word)) {
                    matchFound = true;
                    break;
                }
            }
            if(matchFound = true && !results.contains(item)) {
                results.add(item);
            }
        }
        return results;
    }
}
