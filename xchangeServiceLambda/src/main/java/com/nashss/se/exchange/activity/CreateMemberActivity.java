package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.Models.MemberModel;
import com.nashss.se.exchange.activity.requests.CreateMemberRequest;
import com.nashss.se.exchange.activity.results.CreateMemberResult;
import com.nashss.se.exchange.converters.ModelConverter;
import com.nashss.se.exchange.Member;
import com.nashss.se.exchange.MemberDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateMemberActivity for the XchangeService's CreateMember API.
 * <p>
 * This API allows the customer to create an account to manage with listings later.
 */
public class CreateMemberActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao dao;

    @Inject
    public CreateMemberActivity(MemberDao dao) {
        this.dao = dao;
    }

    public CreateMemberResult handleRequest(final CreateMemberRequest createMemberRequest) {
        log.info("Received CreateMemberRequest, " + createMemberRequest);
        if (createMemberRequest == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        Member newMember = new Member();
        newMember.setMemberId(createMemberRequest.getMemberId());
        newMember.setZipCode(createMemberRequest.getZipCode());
        try {
            dao.saveMember(newMember);
        } catch (Exception e) {
            log.log(Level.INFO, e.getMessage(), e);
        }

        MemberModel memberModel = new ModelConverter().toMemberModel(newMember);
        return CreateMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}
