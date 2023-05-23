package com.nashss.se.exchange.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.dynamodb.models.Item;
import com.nashss.se.exchange.dynamodb.models.ItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.MockitoAnnotations.initMocks;

public class ItemDaoTest {
    private ItemDao itemDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private Item item;

    @BeforeEach
    public void setup() {
        initMocks(this);
        itemDao = new ItemDao(dynamoDBMapper);
        Set<String> images = new HashSet<>();
        images.add("img1");
        images.add("img2");
        item = new Item("ID001", "Awesome Shirt", "this isa super awesome shirt pink M", "Shirt", false, images, "98045");
    }

    @Test
    void getBeer_withExistentbeerIdAndPackageType_loadsAlbumTrackByPartitionAndSortKeys() {
        //GIVEN


        //WHEN

        //THEN
    }
}
