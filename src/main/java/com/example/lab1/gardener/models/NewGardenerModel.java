package com.example.lab1.gardener.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NewGardenerModel {

    private UUID id;

    private String name;

    private int age;
}
