package com.example.rest.service;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;


@Service
public class CacheServiceImpl implements CacheService {

    private final Deque<String> cacheArrayDeque = new ArrayDeque<>();

    @Override
    public void saveMessage(String message) {
        cacheArrayDeque.add(message);
    }

    @Override
    public String pollMessage() {
        return cacheArrayDeque.poll();
    }

    @Override
    public boolean isEmpty() {
        return cacheArrayDeque.isEmpty();
    }

}
