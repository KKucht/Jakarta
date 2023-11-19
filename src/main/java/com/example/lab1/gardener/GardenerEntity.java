package com.example.lab1.gardener;

import com.example.lab1.plant.PlantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "gardeners")
public class GardenerEntity implements Serializable {

    @Id
    private UUID id;

    private String name;

    private int age;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "keeper", cascade = CascadeType.REMOVE)
    private List<PlantEntity> plants;

}
