package com.example.lab1.configuration.singleton;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.GardenerRepository;
import com.example.lab1.gardener.GardenerRoles;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializeAdminService {
    private final GardenerRepository gardenerRepository;

    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public InitializeAdminService(
            GardenerRepository gardenerRepository,
            @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash
    ) {
        this.gardenerRepository = gardenerRepository;
        this.passwordHash = passwordHash;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {
        if (gardenerRepository.findByLogin("admin-service").isEmpty()) {

            GardenerEntity admin = GardenerEntity.builder()
                    .id(UUID.fromString("dd18512a-8bd6-11ee-b9d1-0242ac120002"))
                    .login("admin-service")
                    .name("Admin")
                    .age(0)
                    .password(passwordHash.generate("adminadmin".toCharArray()))
                    .roles(List.of(GardenerRoles.USER,GardenerRoles.ADMIN))
                    .build();

            gardenerRepository.create(admin);
        }
    }
}
