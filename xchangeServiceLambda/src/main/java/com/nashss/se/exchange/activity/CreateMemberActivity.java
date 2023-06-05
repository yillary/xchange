package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.MemberModel;
import com.nashss.se.exchange.activity.requests.CreateItemRequest;
import com.nashss.se.exchange.activity.requests.CreateMemberRequest;
import com.nashss.se.exchange.activity.results.CreateItemResult;
import com.nashss.se.exchange.activity.results.CreateMemberResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.dynamodb.Member;
import com.nashss.se.exchange.dynamodb.MemberDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;

public class CreateMemberActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao dao;


    public CreateMemberActivity(MemberDao dao) {
        this.dao = dao;
    }

    public CreateMemberResult handleRequest(final CreateMemberRequest createMemberRequest) {
        log.info("Recieved CreateMemberRequest, " + createMemberRequest);
        if(createMemberRequest == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        Member newMember = new Member();
        newMember.setMemberId(createMemberRequest.getMemberId());
        newMember.setZipCode(createMemberRequest.getZipCode());
        newMember.setListings(new HashSet<>());

        dao.saveMember(newMember);

        MemberModel memberModel = new ModelConverter().

        }
}
