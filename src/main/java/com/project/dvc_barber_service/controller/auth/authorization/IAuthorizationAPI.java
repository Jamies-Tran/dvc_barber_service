package com.project.dvc_barber_service.controller.auth.authorization;

import com.project.dvc_barber_service.controller.auth.authorization.models.AuthorizeRequest;
import com.project.dvc_barber_service.controller.auth.authorization.models.identification.IdentificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/authorization")
public interface IAuthorizationAPI {

    @PostMapping("/login")
    ResponseEntity<?> verifyIdentification(@RequestBody IdentificationRequest identificationRequest);

    @GetMapping("/refresh/{refreshToken}")
    ResponseEntity<?> refreshToken(@PathVariable String refreshToken);
}
