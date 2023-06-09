package com.nashss.se.exchange.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.dynamodb.Item;
import com.nashss.se.exchange.dynamodb.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class MemberDao {
    private final DynamoDBMapper mapper;
    private final Logger log = LogManager.getLogger();

    @Inject
    public MemberDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Member getMember(String memberId) {
        log.info("hello from member dao memberId: {}", memberId);
        if (memberId == null) {
            throw new IllegalArgumentException("ItemDao.getItem(): itemId cannot be null");
        }
        Member member = mapper.load(Member.class, memberId);

        if(member == null) {
            throw new IllegalArgumentException("Could not find item");
        }
        return member;
    }

    /**
     * Use this to CREATE or UPDATE a new item in the table.
     * @param member to be created or updated in table.
     * @return boolean true if successful.
     */
    public Boolean saveMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        mapper.save(member);
        return true;
    }

    public void addItemToListings(String memberId, Item item) {

        //get the member's Set of Listing itemIds from Dao
        Member member = mapper.load(Member.class, memberId);
        Set<String> listingsSet;
        //if member doesn't have listings yet, make a new Set. Else populate list with existing listings.
        if(member.getListings() == null) {
        listingsSet = new HashSet<>();
        } else {
        listingsSet = member.getListings();
        }

        //add itemId to listings Set
        listingsSet.add(item.getItemId());
        //set member's listings to new set<String>
        member.setListings(listingsSet);
        //update member
        this.saveMember(member);
    }

}
