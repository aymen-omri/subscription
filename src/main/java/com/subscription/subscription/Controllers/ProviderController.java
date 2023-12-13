package com.subscription.subscription.Controllers;

import java.util.List;

import com.subscription.subscription.Models.Provider;
import com.subscription.subscription.Services.ProviderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<String> addProvider(@RequestBody Provider provider) {
        try {
            providerService.addProvider(provider);
            return ResponseEntity.status(HttpStatus.CREATED).body("Provider added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add provider");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProvider(@RequestBody Provider provider, @PathVariable long id) {
        try {
            providerService.updateProvider(provider, id);
            return ResponseEntity.status(HttpStatus.OK).body("Provider updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update provider");
        }
    }

    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        try {
            List<Provider> providers = providerService.getAllProviders();
            return ResponseEntity.ok(providers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable long id) {
        try {
            Provider provider = providerService.getProviderById(id);
            if (provider != null) {
                return ResponseEntity.ok(provider);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable long id) {
        try {
            providerService.deleteProvider(id);
            return ResponseEntity.status(HttpStatus.OK).body("Provider deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete provider");
        }
    }
}
