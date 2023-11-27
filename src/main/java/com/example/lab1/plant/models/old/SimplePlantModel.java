package com.example.lab1.plant.models.old;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SimplePlantModel {

    private UUID id;

    private String name;

    private Long version;

    private LocalDateTime creationDateTime;

    private LocalDateTime lastUpdateDateTime;

}
