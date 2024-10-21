package com.project.dvc_barber_service.controller.authorization;

import com.project.dvc_barber_service.controller.authorization.models.AuthorizeRequest;
import com.project.dvc_barber_service.controller.authorization.models.identification.IdentificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/authorization")
public interface IAuthorizationAPI {
    @PostMapping
    ResponseEntity<?> save(@RequestBody AuthorizeRequest authRequest);

    @PostMapping("/login")
    ResponseEntity<?> verifyIdentification(@RequestBody IdentificationRequest identificationRequest);

    @GetMapping("/refresh/{refreshToken}")
    ResponseEntity<?> refreshToken(@PathVariable String refreshToken);
}
