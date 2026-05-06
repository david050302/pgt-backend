package com.portable.microservices.ms_administration.iam.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.microservices.ms_administration.iam.domain.model.Account;
import com.portable.microservices.ms_administration.iam.domain.model.User;
import com.portable.microservices.ms_administration.iam.domain.ports.out.AccountPersistencePortOut;
import com.portable.microservices.ms_administration.iam.domain.ports.out.PasswordEncryptationPortOut;
import com.portable.microservices.ms_administration.iam.domain.ports.out.UserPersistencePortOut;
import com.portable.microservices.ms_administration.iam.presentation.dto.AccountResponse;
import com.portable.microservices.ms_administration.iam.presentation.dto.CreateAccountRequest;
import com.portable.shared.infrastructure.presentation.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({"/api/accounts","/api/v1/accounts"})
@RequiredArgsConstructor
public class AccountController {

    private final AccountPersistencePortOut accountPersistence;
    private final UserPersistencePortOut userPersistence;
    private final PasswordEncryptationPortOut passwordEncryptation;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> create(@Valid @RequestBody CreateAccountRequest request) {
        User user = userPersistence.findById(String.valueOf(request.userId()))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String hashed = passwordEncryptation.encrypt(request.password());

        Account account = new Account(
            null,
            UUID.randomUUID(),
            user,
            user.id(),
            request.headquarterId(),
            request.username(),
            hashed,
            request.active(),
            java.time.ZonedDateTime.now()
        );

        var saved = accountPersistence.save(account);
        var resp = new AccountResponse(saved.uuid(), saved.username(), saved.user()!=null?saved.user().firstName():null, saved.user()!=null?saved.user().lastName():null, saved.active(), saved.headquarterId(), saved.createdAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Cuenta creada", resp));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> list() {
        var accounts = accountPersistence.findAll();
        var responses = accounts.stream().map(a -> new AccountResponse(a.uuid(), a.username(), a.user()!=null?a.user().firstName():null, a.user()!=null?a.user().lastName():null, a.active(), a.headquarterId(), a.createdAt())).toList();
        return ResponseEntity.ok(ApiResponse.ok("Cuentas listadas", responses));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<AccountResponse>> get(@PathVariable UUID uuid) {
        var acc = accountPersistence.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        var resp = new AccountResponse(acc.uuid(), acc.username(), acc.user()!=null?acc.user().firstName():null, acc.user()!=null?acc.user().lastName():null, acc.active(), acc.headquarterId(), acc.createdAt());
        return ResponseEntity.ok(ApiResponse.ok("Cuenta encontrada", resp));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ApiResponse<AccountResponse>> update(@PathVariable UUID uuid, @Valid @RequestBody CreateAccountRequest request) {
        var acc = accountPersistence.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        Account updated = new Account(acc.id(), acc.uuid(), acc.user(), acc.userId(), request.headquarterId(), acc.username(), acc.password(), request.active(), acc.createdAt());
        var saved = accountPersistence.save(updated);
        var resp = new AccountResponse(saved.uuid(), saved.username(), saved.user()!=null?saved.user().firstName():null, saved.user()!=null?saved.user().lastName():null, saved.active(), saved.headquarterId(), saved.createdAt());
        return ResponseEntity.ok(ApiResponse.ok("Cuenta actualizada", resp));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        accountPersistence.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
