package com.nashss.se.exchange.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.ItemDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemDaoTest {

    @InjectMocks
    private ItemDao itemDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private PaginatedQueryList<Item> queryResult;

    @BeforeEach
    public void setup() {
        initMocks(this);
        itemDao = new ItemDao(dynamoDBMapper);
    }

    @Test
    void getItem_existingItem_loadsItem() {
        //GIVEN
        Set<String> images = new HashSet<>();
        images.add("img1");
        images.add("img2");
        String itemId = "ID001";

        Item item = new Item();
        item.setItemId(itemId);
        item.setTitle("Awesome Shirt");
        item.setDescription("This is a super awesome shirt pink, M");
        item.setItemType("Top");
        item.setExchanged(false);
//        item.setImages(images);
        item.setZipCode("98045");
        item.setEmail("this@gmail.com");

        //GIVEN
        when(dynamoDBMapper.load(Item.class, itemId)).thenReturn(item);
        Item result =  itemDao.getItem(itemId);

        System.out.println("Item is: " + result.toString());
        //THEN
        assertEquals(itemId, result.getItemId());
        assertSame(result, item);
    }

    @Test
    void saveItem_givenValidItem_returnsTrue() {
        //GIVEN
        Set<String> images = new HashSet<>();
        images.add("img1");
        images.add("img2");
        String itemId = "ID001";

        Item item = new Item();
        item.setItemId(itemId);
        item.setTitle("Awesome Shirt");
        item.setDescription("This is a super awesome shirt pink, M");
        item.setItemType("Top");
        item.setExchanged(false);
//        item.setImages(images);
        item.setZipCode("98045");

        //WHEN
        Boolean result = itemDao.saveItem(item);

        //THEN
        assertSame(true, result);
    }

    @Test
    void searchItems_givenZipTypeAndCriteria_returnsListItemsInSameZipCodeAndType() {
//        //GIVEN
//        Set<String> images = new HashSet<>();
//        images.add("img1");
//        images.add("img2");
//        String itemId = "ID001";
//
//        Item item = new Item();
//        item.setItemId(itemId);
//        item.setTitle("Awesome Shirt");
//        item.setDescription("This is a super awesome shirt pink, M");
//        item.setType("Top");
//        item.setExchanged(false);
//        item.setImages(images);
//        item.setZipCode("98045");
//
//
//        Set<String> images2 = new HashSet<>();
//        images2.add("img1");
//        images2.add("img2");
//        String itemId2 = "ID002";
//
//        Item item2 = new Item();
//        item2.setItemId(itemId2);
//        item2.setTitle("Sweatshirt is cool");
//        item2.setDescription("So soft and I love it. You will too.");
//        item2.setType("Top");
//        item2.setExchanged(false);
//        item2.setImages(images2);
//        item2.setZipCode("98045");
//
//
//        Set<String> images3 = new HashSet<>();
//        images3.add("img1");
//        images3.add("img2");
//        String itemId3 = "ID003";
//
//        Item item3 = new Item();
//        item3.setItemId(itemId);
//        item3.setTitle("Another Pair of Pants");
//        item3.setDescription("This is a super awesome pant pink, M");
//        item3.setType("Bottoms");
//        item3.setExchanged(false);
//        item3.setImages(images);
//        item3.setZipCode("00000");
//
//        //Search Parameters
//        String zipCode = "98045";
//        String type = "";
//        String[] criteria = new String[0];
//
//        //List Items
//        List<Item> items = new ArrayList<>();
//        items.add(item);
//        items.add(item2);
//        items.add(item3);
//
//        //Excpected Result
//        List<Item> expectedResult = new ArrayList<>();
//        expectedResult.add(item);
//        expectedResult.add(item2);
//
//        //Mock Query Results
//
//        queryResult.add(item);
//        queryResult.add(item2);
//        System.out.println("size of mockQueryResults: " + queryResult.size());
//        System.out.println("toString() of mockQueryResults: " + queryResult.toString());
//        queryResult.stream().forEach(listing -> System.out.println("Listings: " + listing.toString()));
//
//        //Mockito
//        //when(dynamoDBMapper.query(Item.class, any(DynamoDBQueryExpression.class))).thenReturn((PaginatedQueryList<Item>) expectedResult);
//        when(dynamoDBMapper.query(eq(Item.class), any(DynamoDBQueryExpression.class)))
//                .thenReturn(queryResult);
//
//        //As suggested by ChatGPT on how to mock this part of the code:
//        when(dynamoDBMapper.load(eq(Item.class), anyString())).thenAnswer(invocation -> {
//            String itemIdNum = invocation.getArgument(1);
//            if(itemIdNum.equals("ID001")) {
//                return expectedResult.get(0);
//            } else if (itemId.equals("ID002")) {
//                return expectedResult.get(1);
//            }
//            return null;
//        });
//
//        List<Item> searchResults = Arrays.asList(item, item2);
//        List<Item> fullyLoadedInfo = new ArrayList<>();
//        for (Item item0 : searchResults) {
//            fullyLoadedInfo.add(dynamoDBMapper.load(Item.class, item0.getItemId()));
//        }
//
//        //WHEN
//        List<Item> results = itemDao.searchItems(zipCode, type, criteria);
//
//        //THEN
//
//        assertSame(expectedResult, results, "There should be two items that have both a Top " +
//                "type and a zip of 98045, " + results.toString());
    }

}
