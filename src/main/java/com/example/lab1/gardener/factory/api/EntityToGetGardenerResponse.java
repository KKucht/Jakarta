package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.rest.GetGardenerResponse;

public interface EntityToGetGardenerResponse {

    GetGardenerResponse getResponse(GardenerEntity e);
}
