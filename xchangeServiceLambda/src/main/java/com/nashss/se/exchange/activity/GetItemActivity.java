package com.nashss.se.exchange.activity;


import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.GetItemRequest;
import com.nashss.se.exchange.activity.results.GetItemResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.Item;
import com.nashss.se.exchange.ItemDao;

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

    /**
     * This method handles the request to get an item.
     * <p>
     * It returns an ItemModel
     * <p>
     *
     * @param getItemRequest request object containing the item id
     * @return GetItemResult result object containing the API defined {@link ItemModel}
     */
    public GetItemResult handleRequest(final GetItemRequest getItemRequest) {
        log.info("Received GetItemRequest {}", getItemRequest);
        String itemId = getItemRequest.getItemId();
        Item item  = itemDao.getItem(itemId);
        ItemModel itemModel = new ModelConverter().toItemModel(item);
        return GetItemResult.builder()
                .withItem(itemModel)
                .build();

    }
}
