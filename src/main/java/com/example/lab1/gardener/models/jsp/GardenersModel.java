package com.example.lab1.gardener.models.jsp;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GardenersModel {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Gardener {

        /**
         * Unique id identifying character.
         */
        private UUID id;

        /**
         * Name of the character.
         */
        private String login;

    }

    @Singular
    private List<Gardener> gardeners;
}
