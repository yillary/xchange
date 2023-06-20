package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.SearchTypeZipRequest;
import com.nashss.se.exchange.activity.results.SearchTypeZipResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.ItemDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.*;

import static utils.NullUtils.ifNull;

public class SearchTypeZipActivity {
    private final Logger log = LogManager.getLogger();
    private final ItemDao itemDao;

    @Inject
    public SearchTypeZipActivity(com.nashss.se.exchange.dynamodb.ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public SearchTypeZipResult handleRequest(final SearchTypeZipRequest searchTypeZipRequest) {
        System.out.println("SearchTypeZipActivity.handleRequest() with searchTypeRequest: "
                + searchTypeZipRequest.toString());
        log.info("Received SearchTypeZipRequest {}", searchTypeZipRequest);

        String[] formattedCriteria = this.formatSearchCriteria(searchTypeZipRequest.getCriteria());

        String type = searchTypeZipRequest.getType().toLowerCase();
        String zipCode = searchTypeZipRequest.getZipCode();
        List<Item> searchResults = itemDao.searchItems(zipCode, type, formattedCriteria);

        //filtering search results so if the item is exchanged it won't be included in the results
        for(Item item: searchResults) {
            if(item.getExchanged() == true) {
                searchResults.remove(item);
            }
        }

        List<ItemModel> modelResults = new ArrayList<>();
        for(Item item : searchResults){
            modelResults.add(new ModelConverter().toItemModel(item));
        }
        return SearchTypeZipResult.builder()
                .withItems(modelResults)
                .build();
    }

    private String[] formatSearchCriteria (String keywordCriteria) {
        System.out.println("SearchTypeZipActivity.formatSearchCriteria()");
        String criteria = ifNull(keywordCriteria, "");
        String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.trim().toLowerCase().split("\\s");

        Set<String> fillerWords = new HashSet<>(Arrays.asList(
                "a", "an", "the", "in", "on", "at", "for", "to", "and", "or", "this", "these", "i", "can", "wear"
        ));
        String[] filteredInput = Arrays.stream(criteriaArray)
                .filter(word -> !fillerWords.contains(word))
                .toArray(String[]::new);

        return filteredInput;
    }
}
