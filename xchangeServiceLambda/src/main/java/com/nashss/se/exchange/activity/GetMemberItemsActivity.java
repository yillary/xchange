package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.ItemModel;
import com.nashss.se.exchange.activity.requests.GetMemberItemsRequest;
import com.nashss.se.exchange.activity.results.GetMemberItemsResult;
import com.nashss.se.exchange.converters.ModelConverter;

import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.ItemDao;
import com.nashss.se.exchange.dynamodb.Member;
import com.nashss.se.exchange.dynamodb.MemberDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GetMemberItemsActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;
    private final ItemDao itemDao;

    @Inject
    public GetMemberItemsActivity(MemberDao memberDao, ItemDao itemDao) {
        this.memberDao = memberDao;
        this.itemDao = itemDao;
    }

    public GetMemberItemsResult handleRequest(final GetMemberItemsRequest getMemberItemsRequest) {
        log.info("getMemberItemRequest received {}", getMemberItemsRequest);
        if (getMemberItemsRequest.getMemberId() == null) {
            throw new IllegalArgumentException("memberId cannot be null.");
        }
        log.info("Phase one");
        Member member = memberDao.getMember(getMemberItemsRequest.getMemberId());
        log.info("Phase two");
        log.info("Member's itemIds: " + member.getListings());
        List<Item> items = itemDao.getMemberItems(member);
        Set<ItemModel> result = new HashSet<>();
        for (Item item : items) {
            ItemModel itemModel = new ModelConverter().toItemModel(item);
            result.add(itemModel);
        }
        log.info("Phase three");
        return GetMemberItemsResult.builder()
                .withMemberItems(result)
                .build();
    }
}
