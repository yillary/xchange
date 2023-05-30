package com.nashss.se.exchange.activity;


import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.GetItemRequest;
import com.nashss.se.exchange.activity.results.GetItemResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.ItemDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class GetItemActivity {
    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;

    @Inject
    public GetItemActivity (ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public GetItemResult handleRequest(final GetItemRequest getItemRequest) {
        System.out.println("getItemRequest recieved in GetItemActivity" + getItemRequest.toString());
        log.info("Received GetItemRequest {}", getItemRequest);
        String itemId = getItemRequest.getItemId();
        Item item  = itemDao.getItem(itemId);
        ItemModel itemModel = new ModelConverter().toItemModel(item);
        return GetItemResult.builder()
                .withItem(itemModel)
                .build();

    }
}
