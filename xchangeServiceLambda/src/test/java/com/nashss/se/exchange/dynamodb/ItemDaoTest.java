package com.nashss.se.exchange.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.dynamodb.models.Item;
import com.nashss.se.exchange.dynamodb.models.ItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemDaoTest {

    @InjectMocks
    private ItemDao itemDao;

    @Mock
    private DynamoDBMapper dynamoDBMapper;


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
        item.setType("Top");
        item.setExchanged(false);
        item.setImages(images);
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
//        //WHEN
//        when(dynamoDBMapper.save(item)).thenReturn(true);
//        Boolean result = itemDao.saveItem(item);
//
//        //THEN
//        assertSame(true, result);
    }

}
