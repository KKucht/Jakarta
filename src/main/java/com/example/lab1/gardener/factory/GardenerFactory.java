package com.example.lab1.gardener.factory;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.factory.api.*;
import com.example.lab1.gardener.models.jsp.GardenerModel;
import com.example.lab1.gardener.models.jsp.GardenersModel;
import com.example.lab1.gardener.models.rest.GetGardenerResponse;
import com.example.lab1.gardener.models.rest.GetGardenersResponse;
import com.example.lab1.gardener.models.rest.PutGardenerRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GardenerFactory implements EntityToGetGardenerResponse,
        EntityToGetGardenersResponse,
        PutGardenerRequestToEntity, EntityToGardenerModel, EntityToGardenersModel {
    @Override
    public GetGardenerResponse getResponse(GardenerEntity e) {
        return GetGardenerResponse.builder()
                .age(e.getAge())
                .id(e.getId())
                .name(e.getName())
                .build();
    }

    @Override
    public GetGardenersResponse getResponse(List<GardenerEntity> e) {
        return GetGardenersResponse.builder()
                .gardeners(e.stream()
                        .map(gardener -> GetGardenersResponse.Gardener.builder()
                                .id(gardener.getId())
                                .name(gardener.getName())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public GardenerEntity getNewEntity(UUID uuid, PutGardenerRequest r) {
        return GardenerEntity.builder()
                .id(uuid)
                .login(r.getLogin())
                .password(r.getPassword())
                .name(r.getName())
                .age(r.getAge())
                .roles(null)
                .plants(new ArrayList<>())
                .build();
    }

    @Override
    public GardenerEntity getUpdatedEntity(GardenerEntity e, PutGardenerRequest r) {
        return GardenerEntity.builder()
                .id(e.getId())
                .age(r.getAge())
                .name(r.getName())
                .roles(e.getRoles())
                .login(e.getLogin())
                .password(e.getPassword())
                .plants(e.getPlants())
                .build();
    }

    @Override
    public GardenerModel getModel(GardenerEntity e) {
        return GardenerModel.builder()
                .id(e.getId())
                .login(e.getLogin())
                .build();
    }

    @Override
    public GardenersModel getModel(List<GardenerEntity> e) {
        return GardenersModel.builder()
                .gardeners(e.stream()
                        .map(gardener -> GardenersModel.Gardener.builder()
                                .id(gardener.getId())
                                .login(gardener.getLogin())
                                .build())
                        .toList())
                .build();
    }
}
