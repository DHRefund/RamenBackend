package com.sapporo.ramenbooking.controller;

import com.sapporo.ramenbooking.dto.response.BookingResponse;
import com.sapporo.ramenbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private static final String Token = "Bearer demo-token";

    private final BookingService bookingService;

    private boolean isAuthorized(String authHeader) {
        return Token.equals(authHeader);
    }

    @GetMapping("/reservations/{date}")
    public ResponseEntity<Object> getAllReservationsByDate(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (!isAuthorized(authHeader)) {
            System.out.println("Unauthorized access attempt to get reservations for date: " + date);
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return ResponseEntity.ok(bookingService.getAllReservationsByDate(date));
    }

    @GetMapping("/reservations")
    public ResponseEntity<Object> getAllReservations(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (!isAuthorized(authHeader)) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return ResponseEntity.ok(bookingService.getAllReservations());

    }

    @PatchMapping("/reservations/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelReservation(
            @RequestHeader(value = "Authorization", required = false) String authHeader, @PathVariable String id) {
        if (!isAuthorized(authHeader)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(bookingService.cancelReservation(id));
    }

    @PatchMapping("/reservations/{id}/complete")
    public ResponseEntity<BookingResponse> completeReservation(
            @RequestHeader(value = "Authorization", required = false) String authHeader, @PathVariable String id) {
        if (!isAuthorized(authHeader)) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(bookingService.completeReservation(id));
    }
}