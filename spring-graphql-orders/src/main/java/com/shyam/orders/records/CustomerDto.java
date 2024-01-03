package com.shyam.orders.records;

public record CustomerDto(String firstName, String lastName, String email, String phone, String line1, String line2,
                          String city, String state, String zip) {
}
