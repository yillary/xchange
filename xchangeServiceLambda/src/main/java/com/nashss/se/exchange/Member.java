package com.nashss.se.exchange;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "members")
public class Member {
    private String memberId;
    private Set<String> listings;
    private String zipCode;

    @DynamoDBHashKey(attributeName = "memberId")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @DynamoDBAttribute(attributeName = "listings")
    public Set<String> getListings() {
        return listings;
    }

    public void setListings(Set<String> listings) {
        this.listings = listings;
    }

    @DynamoDBAttribute(attributeName = "zipCode")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) && Objects.equals(listings, member.listings) && Objects.equals(zipCode, member.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, listings, zipCode);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", listings=" + listings +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
