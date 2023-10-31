package com.example.lab1.gardener.models.old;

import com.example.lab1.gardener.GardenerEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SimpleGardenerModel {

    private UUID id;

    private String name;
}
