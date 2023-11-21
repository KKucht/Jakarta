package com.example.lab1.gardener.models.rest;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGardenerResponse {

    private UUID id;

    private String name;

    private int age;
}
