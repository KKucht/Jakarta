package com.example.lab1.gardener.models.jsp;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GardenerModel {
    private UUID id;

    private String login;
}
