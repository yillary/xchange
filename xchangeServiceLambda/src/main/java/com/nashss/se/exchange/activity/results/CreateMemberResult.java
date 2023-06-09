package com.nashss.se.exchange.activity.results;

import com.nashss.se.exchange.Models.MemberModel;

public class CreateMemberResult {
    private final MemberModel memberModel;

    public CreateMemberResult(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }


    @Override
    public String toString() {
        return "CreateMemberResult{" +
                "memberModel=" + memberModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MemberModel memberModel;

        public Builder withMember(MemberModel memberModel) {
            this.memberModel = memberModel;
            return this;
        }

        public CreateMemberResult build() {
            return new CreateMemberResult(memberModel);
        }
    }
}
