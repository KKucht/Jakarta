package com.example.lab1.authentication.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;

@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName = "Lab1")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/PlantCatalog",
        callerQuery = "select password from gardeners where login = ?",
        groupsQuery = "select role from gardeners_roles where id = (select id from gardeners where login = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
)
public class AuthenticationConfig {
}
