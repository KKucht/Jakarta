package com.example.lab1.gardener;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.EJBException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class GardenerService {

    private final GardenerRepository gardenerRepository;

    private final ImageService imageService;

    private final SecurityContext securityContext;

    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public GardenerService(GardenerRepository gardenerRepository,
                           ImageService imageService,
                           @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash,
                           @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.gardenerRepository = gardenerRepository;
        this.imageService = imageService;
        this.securityContext = securityContext;
        this.passwordHash = passwordHash;
    }

    @PermitAll
    public void createGardener(GardenerEntity entity) throws IOException {
        if (gardenerRepository.find(entity.getId()).isPresent()) {
            throw new IOException("Gardener with the specified UUID exits");
        }
        if (entity.getRoles() == null || entity.getRoles().isEmpty()) {
            entity.setRoles(List.of(GardenerRoles.USER));
        }
        entity.setPassword(passwordHash.generate(entity.getPassword().toCharArray()));
        gardenerRepository.create(entity);
    }

    @RolesAllowed(GardenerRoles.USER)
    public GardenerEntity getGardener(UUID uuid) throws IOException {
        checkAdminRoleOrOwner(gardenerRepository.find(uuid));
        return gardenerRepository.find(uuid).get();
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public GardenerEntity getGardenerByLogin(String login) throws IOException {
        if (gardenerRepository.findByLogin(login).isEmpty())
            return gardenerRepository.findByLogin(login).get();
        throw new IOException("Gardener with the specified UUID exits");
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public List<GardenerEntity> getGardeners() {
        return gardenerRepository.findAll();
    }

    @RolesAllowed(GardenerRoles.USER)
    public byte[] getGardenerImage(UUID uuid) throws IOException {
        checkAdminRoleOrOwner(gardenerRepository.find(uuid));
        byte[] image = imageService.getImage(uuid);
        if (image == null) {
            throw new IOException("Image not found");
        }
        return image;
    }

    @RolesAllowed(GardenerRoles.USER)
    public void createGardenerImage(UUID uuid, InputStream is) throws IOException {
        checkAdminRoleOrOwner(gardenerRepository.find(uuid));
        try {
            imageService.createImage(uuid, is);
        } catch (EJBException | IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @RolesAllowed(GardenerRoles.USER)
    public void updateGardenerImage(UUID uuid, InputStream is) throws IOException {
        checkAdminRoleOrOwner(gardenerRepository.find(uuid));
        try {
            imageService.updateImage(uuid, is);
        } catch (EJBException | IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @RolesAllowed(GardenerRoles.USER)
    public void removeGardenerImage(UUID uuid) throws IOException {
        checkAdminRoleOrOwner(gardenerRepository.find(uuid));
        imageService.removeImage(uuid);
    }

    private void checkAdminRoleOrOwner(Optional<GardenerEntity> gardener) throws EJBAccessException {
        if (securityContext.isCallerInRole(GardenerRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(GardenerRoles.USER)
                && gardener.isPresent()
                && gardener.get().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }

    @PermitAll
    public boolean verify(String login, String password) throws IOException {
        GardenerEntity gardener = getGardenerByLogin(login);
        return gardener.getPassword().equals(password);
    }

}
