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

        if(updateItemRequest.getItemId() == null || updateItemRequest.getMemberId() == null || updateItemRequest.getZipCode() == null){
            throw new IllegalArgumentException("itemId, memberId or zipCode is null");
        }

        if(XchangeServiceUtils.isValidZipCode(updateItemRequest.getZipCode()) == false) {
            throw new IllegalArgumentException("Zip Code is invalid. Must contain numbers only");
        }

        if (updateItemRequest.getTitle().length() > 20) {
            throw new IllegalArgumentException("Title cannot exceed 20 characters");
        }

        Item item = new Item();
        item.setTitle(updateItemRequest.getTitle());
        item.setDescription(updateItemRequest.getDescription());
        item.setZipCode(updateItemRequest.getZipCode());

        itemDao.saveItem(item);

        ItemModel itemModel = new ModelConverter().toItemModel(item);
        return UpdateItemResult.builder()
                .withItem(itemModel)
                .build();
    }
}
