package com.subscription.subscription.Controllers;

import java.util.List;

import com.subscription.subscription.Models.Subscription;
import com.subscription.subscription.Services.SubscriptionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<String> addSubscription(@RequestBody Subscription subscription) {
        try {
            subscriptionService.addSubscription(subscription);
            return ResponseEntity.status(HttpStatus.CREATED).body("Subscription added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add subscription");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubscription(@RequestBody Subscription subscription, @PathVariable long id) {
        try {
            subscriptionService.updateSubscription(subscription, id);
            return ResponseEntity.status(HttpStatus.OK).body("Subscription updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update subscription");
        }
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAll();
            return ResponseEntity.ok(subscriptions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<Subscription>> getAllSubscriptionsByName(@RequestParam("name") String name) {
        try {
            List<Subscription> subscriptions = subscriptionService.getAllByName(name);
            return ResponseEntity.ok(subscriptions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable long id) {
        try {
            Subscription subscription = subscriptionService.getById(id);
            if (subscription != null) {
                return ResponseEntity.ok(subscription);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable long id) {
        try {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.status(HttpStatus.OK).body("Subscription deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete subscription");
        }
    }
}
