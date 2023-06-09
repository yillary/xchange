package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.UpdateItemRequest;
import com.nashss.se.exchange.activity.results.UpdateItemResult;
import com.nashss.se.exchange.converters.ModelConverter;

import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.ItemDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.XchangeServiceUtils;

import javax.inject.Inject;

/**
 * Implementation of the UpdateItemActivity for the XchangeService's UpdateItem API.
 * <p>
 * This API allows the customer to update an item.
 */
public class UpdateItemActivity {
    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;

    @Inject
    public UpdateItemActivity(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public UpdateItemResult handleRequest(final UpdateItemRequest updateItemRequest) {
        log.info("Received updateItemRequest {}", updateItemRequest);

        if(updateItemRequest.getItemId() == null){
            throw new IllegalArgumentException("itemId is null");
        }

        if( updateItemRequest.getZipCode() == null) {
            throw new IllegalArgumentException("Zip code is null.");
        }

        if(XchangeServiceUtils.isValidZipCode(updateItemRequest.getZipCode()) == false) {
            throw new IllegalArgumentException("Zip Code is invalid. Must contain numbers only and only five numbers.");
        }

        if (updateItemRequest.getTitle().length() > 20) {
            throw new IllegalArgumentException("Title cannot exceed 20 characters");
        }

        if (updateItemRequest.getDescription().length() > 200) {
            throw new IllegalArgumentException("Your description is too long. It cannot exceed 200 characters.");
        }
        //load the original item from the Dao. With this item, assign the non-updated fields.
        String itemId = updateItemRequest.getItemId();
        Item itemRetrieved = itemDao.getItem(itemId);

        //create a new item that will be saved to the Dao. Populate updated fields with attributes from request.
        //Populate non-updatable fields with itemRetrieved aka original item from the Dao.
        log.info("Phase one");
        Item item = new Item();
        item.setItemId(itemRetrieved.getItemId());
        item.setEmail(itemRetrieved.getEmail());
        item.setItemType(itemRetrieved.getItemType());

        item.setTitle(updateItemRequest.getTitle());
        item.setDescription(updateItemRequest.getDescription());
        item.setZipCode(updateItemRequest.getZipCode());
        item.setExchanged(updateItemRequest.getExchanged());

        log.info("Phase two");
        itemDao.saveItem(item);

        ItemModel itemModel = new ModelConverter().toItemModel(item);
        log.info("ItemModel looks like this: {}", itemModel.toString());
        return UpdateItemResult.builder()
                .withItem(itemModel)
                .build();
    }
}
