package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.rest.GetGardenersResponse;

import java.util.List;

public interface EntityToGetGardenersResponse {
    GetGardenersResponse getResponse(List<GardenerEntity> e);

}
