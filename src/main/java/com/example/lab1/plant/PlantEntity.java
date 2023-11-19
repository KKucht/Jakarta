package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.species.SpeciesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "plants")
public class PlantEntity implements Serializable {

    @Id
    private UUID id;

    private String name;

    private double height;

    @Column(name = "planting_date")
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "keeper")
    @EqualsAndHashCode.Exclude
    private GardenerEntity keeper;

    @ManyToOne
    @JoinColumn(name = "species")
    @EqualsAndHashCode.Exclude
    private SpeciesEntity species;

}
