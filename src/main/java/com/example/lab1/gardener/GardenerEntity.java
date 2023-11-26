package com.example.lab1.gardener;

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
@Table(name = "gardeners")
public class GardenerEntity implements Serializable {

    @Id
    private UUID id;

    private String login;

    @ToString.Exclude
    private String password;

    private String name;

    private int age;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "keeper", cascade = CascadeType.REMOVE)
    private List<PlantEntity> plants;

    @CollectionTable(name = "gardeners_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

}
