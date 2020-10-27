package com.bookmanagement.dto;

import java.util.List;
import java.util.Objects;

public class CreateOrderRequest {

    private List<Integer> bookIds;

    private String phoneNumber;

    private String address;

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderRequest that = (CreateOrderRequest) o;
        return Objects.equals(bookIds, that.bookIds) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookIds, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "bookIds=" + bookIds +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
