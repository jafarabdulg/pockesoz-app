package com.jafar.pockesoz.service;

import com.jafar.pockesoz.entity.Pocket;
import com.jafar.pockesoz.model.request.PocketRequest;
import com.jafar.pockesoz.model.response.PocketResponse;

import java.util.List;

public interface PocketService {

    PocketResponse create(PocketRequest request);
    PocketResponse getById(String id);
    List<Pocket> getAllByUserId(String userId);
    PocketResponse update(PocketRequest request);
    void deleteById(String id);
}
