package com.nashss.se.exchange.Models;

import java.util.Objects;
import java.util.Set;

public class MemberModel {
    private final String memberId;
    private final Set<String> listings;
    private final String zipCode;

    public MemberModel(String memberId, Set<String> listings, String zipCode) {
        this.memberId = memberId;
        this.listings = listings;
        this.zipCode = zipCode;
    }


    public String getMemberId() {
        return memberId;
    }

    public Set<String> getListings() {
        return listings;
    }

    public String getZipCode() {
        return zipCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberModel that = (MemberModel) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(listings, that.listings) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, listings, zipCode);
    }


    @Override
    public String toString() {
        return "MemberModel{" +
                "memberId='" + memberId + '\'' +
                ", listings=" + listings +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
