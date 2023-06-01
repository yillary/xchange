package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.CreateItemRequest;
import com.nashss.se.exchange.activity.results.CreateItemResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.dynamodb.Item;
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

    @Inject
    public CreateItemActivity(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public CreateItemResult handleRequest(final CreateItemRequest createItemRequest) {
        log.info("Received CreateItemRequest in the Activty: " + createItemRequest);

        if(createItemRequest.getType().equals(null)) {
            throw new IllegalArgumentException("Item doesn't have a category type. Types include Top, Bottom, Outerwear, Accessory etc.");
        }

        if(createItemRequest.getZipCode().equals(null) || XchangeServiceUtils.isValidZipCode(createItemRequest.getZipCode()) == false) {
            throw new IllegalArgumentException("Item does not have valid zip code.");
        }

        if(createItemRequest.getTitle().length() > 20) {
            throw new IllegalArgumentException("Title cannot exceed 20 characters");
        }

        Item item = new Item();
        item.setItemId(XchangeServiceUtils.generateId());
        item.setTitle(createItemRequest.getTitle());
        item.setDescription(createItemRequest.getDescription());
        item.setItemType(createItemRequest.getType());
        item.setExchanged(false);
        item.setZipCode(createItemRequest.getZipCode());
        item.setImages(XchangeServiceUtils.formatImages(createItemRequest.getImages()));

        itemDao.saveItem(item);

        ItemModel playlistModel = new ModelConverter().toItemModel(item);
        return CreateItemResult.builder()
                .withPlaylist(playlistModel)
                .build();
    }


}



//    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest) {
//        log.info("Received CreatePlaylistRequest {}", createPlaylistRequest);
//
//        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName())) {
//            throw new InvalidAttributeValueException("Playlist name [" + createPlaylistRequest.getName() +
//                    "] contains illegal characters");
//        }
//
//        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
//            throw new InvalidAttributeValueException("Playlist customer ID [" + createPlaylistRequest.getCustomerId() +
//                    "] contains illegal characters");
//        }
//
//        Set<String> playlistTags = null;
//        if (createPlaylistRequest.getTags() != null) {
//            playlistTags = new HashSet<>(createPlaylistRequest.getTags());
//        }
//
//        Playlist newPlaylist = new Playlist();
//        newPlaylist.setId(MusicPlaylistServiceUtils.generatePlaylistId());
//        newPlaylist.setName(createPlaylistRequest.getName());
//        newPlaylist.setCustomerId(createPlaylistRequest.getCustomerId());
//        newPlaylist.setCustomerName(createPlaylistRequest.getCustomerName());
//        newPlaylist.setSongCount(0);
//        newPlaylist.setTags(playlistTags);
//        newPlaylist.setSongList(new ArrayList<>());
//
//        playlistDao.savePlaylist(newPlaylist);
//
//        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(newPlaylist);
//        return CreatePlaylistResult.builder()
//                .withPlaylist(playlistModel)
//                .build();
//    }
//}
