package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.GetItemRequest;
import com.nashss.se.exchange.activity.requests.SearchTypeZipRequest;
import com.nashss.se.exchange.activity.results.GetItemResult;
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
    public SearchTypeZipActivity(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public SearchTypeZipResult handleRequest(final SearchTypeZipRequest searchTypeZipRequest) {
        log.info("Received SearchTypeZipRequest {}", searchTypeZipRequest);

        String[] formattedCriteria = this.formatSearchCriteria(searchTypeZipRequest.getCriteria());

        String type = searchTypeZipRequest.getType();
        String zipCode = searchTypeZipRequest.getZipCode();
        List<Item> searchResults = itemDao.searchItems(zipCode, type, formattedCriteria);

        List<ItemModel> modelResults = new ArrayList<>();
        for(Item item : searchResults){
            modelResults.add(new ModelConverter().toItemModel(item));
        }
        return SearchTypeZipResult.builder()
                .withItems(modelResults)
                .build();
    }

    private String[] formatSearchCriteria (String keywordCriteria) {
        String criteria = ifNull(keywordCriteria, "");
        String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.trim().toLowerCase().split("\\s");

        Set<String> fillerWords = new HashSet<>(Arrays.asList(
                "a", "an", "the", "in", "on", "at", "for", "to", "and", "or", "this", "these", "I", "can", "wear"
        ));
        String[] filteredInput = Arrays.stream(criteriaArray)
                .filter(word -> !fillerWords.contains(word))
                .toArray(String[]::new);

        return filteredInput;
    }
}
