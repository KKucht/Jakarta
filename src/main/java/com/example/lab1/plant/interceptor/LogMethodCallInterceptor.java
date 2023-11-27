package com.example.lab1.plant.interceptor;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.interceptor.binding.LogMethodCall;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;
import lombok.extern.java.Log;

import java.util.UUID;

@Interceptor
@LogMethodCall
@Priority(10)
@Log
public class LogMethodCallInterceptor {

    private final SecurityContext securityContext;

    @Inject
    public LogMethodCallInterceptor(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        UUID id = null;
        if ((context.getParameters()[0] instanceof PlantEntity)) {
            PlantEntity plant = (PlantEntity) context.getParameters()[0];
            id = plant.getId();
        } else if ((context.getParameters()[0] instanceof UUID)) {
            id = (UUID) context.getParameters()[0];
        }

        log.info(
                "User " + securityContext.getCallerPrincipal().getName() +
                        " called method " + context.getMethod().getName() +
                        " with parameter " + id
        );
        return context.proceed();
    }
}
