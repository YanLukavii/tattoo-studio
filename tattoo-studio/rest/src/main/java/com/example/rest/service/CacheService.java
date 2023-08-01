package com.example.rest.service;

public interface CacheService {

    void saveMessage(String message);

    String pollMessage();

    boolean isEmpty();

}
