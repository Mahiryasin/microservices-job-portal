package com.project.AuditingAware;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;



@Component

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
       return Optional.of("mahir");
    }

}
