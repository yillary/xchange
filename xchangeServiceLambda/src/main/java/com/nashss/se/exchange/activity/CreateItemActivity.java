package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.CreateItemRequest;
import com.nashss.se.exchange.activity.results.CreateItemResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.dynamodb.Item;

import com.nashss.se.exchange.dynamodb.MemberDao;

import com.nashss.se.exchange.dynamodb.ItemDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.XchangeServiceUtils;

import javax.inject.Inject;

/**
 * Implementation of the CreateItemActivity for the XchangeService's CreateItem API.
 * <p>
 * This API allows the customer to create a new item.
 */
public class CreateItemActivity {
    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;
    private final MemberDao memberDao;

    @Inject
    public CreateItemActivity(ItemDao itemDao, MemberDao memberDao) {
        this.itemDao = itemDao;
        this.memberDao = memberDao;
    }

    public CreateItemResult handleRequest(final CreateItemRequest createItemRequest) {
        log.info("Received CreateItemRequest in the Activty: " + createItemRequest);

        if (createItemRequest.getType().equals(null)) {
            throw new IllegalArgumentException("Item doesn't have a category type. Types include Top, Bottom, Outerwear, Accessory etc.");
        }

        if (createItemRequest.getZipCode().equals(null) || XchangeServiceUtils
                .isValidZipCode(createItemRequest.getZipCode()) == false) {
            throw new IllegalArgumentException("Item does not have valid zip code.");
        }

        if (createItemRequest.getTitle().length() > 20) {
            throw new IllegalArgumentException("Title cannot exceed 20 characters");
        }

        if (createItemRequest.getDescription().length() > 200) {
            throw new IllegalArgumentException("Description cannot exceed 200 characters");
        }

        Item item = new Item();
        item.setItemId(XchangeServiceUtils.generateId());
        item.setTitle(createItemRequest.getTitle());
        item.setDescription(createItemRequest.getDescription());
        item.setItemType(createItemRequest.getType());
        item.setExchanged(false);
        item.setZipCode(createItemRequest.getZipCode());
        item.setEmail(createItemRequest.getEmail());

        itemDao.saveItem(item);
        memberDao.addItemToListings(item.getEmail(), item);
        //save to s3 bucket images, s3 image save, responds with the address that the image is stored at.
        // This becomes the url String that should
        //be stored in the String set for images. http library to make a call to
        //AWS S3 bucket configure it and it

        ItemModel itemModel = new ModelConverter().toItemModel(item);
        return CreateItemResult.builder()
                .withItem(itemModel)
                .build();
    }
}
