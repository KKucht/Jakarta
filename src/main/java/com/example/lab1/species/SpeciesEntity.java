package com.example.lab1.species;


import com.example.lab1.plant.PlantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "species")
public class SpeciesEntity implements Serializable {

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "species", cascade = CascadeType.REMOVE)
    private List<PlantEntity> plants;

}
