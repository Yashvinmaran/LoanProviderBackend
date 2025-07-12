package com.loan.controllers;

import com.loan.entities.DocumentEntity;
import com.loan.entities.Loan;
import com.loan.entities.User;
import com.loan.models.JwtRequest;
import com.loan.models.JwtResponse;
import com.loan.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ================= AUTH =================

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        return userService.loginUser(request);
    }

    // ================= PROFILE =================

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Fallback method to handle /api/v1/user/{email} requests
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmailFallback(@PathVariable String email) {
        return getUserByEmail(email);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        return userService.updateUser(email, user);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

    // ================= DOCUMENTS =================

    @PostMapping("/upload-documents/{userId}")
    public ResponseEntity<DocumentEntity> uploadDocuments(
            @PathVariable String userId,
            @RequestParam("aadharFile") MultipartFile aadharFile,
            @RequestParam("panFile") MultipartFile panFile) {

        return userService.uploadDocuments(userId, aadharFile, panFile);
    }

    @GetMapping("/documents/{userId}")
    public ResponseEntity<DocumentEntity> getDocuments(@PathVariable String userId) {
        return userService.getDocuments(userId);
    }

    // ================= LOANS =================

    @PostMapping("/loan/apply/{email}")
    public ResponseEntity<String> applyLoan(@PathVariable String email, @RequestBody Loan loan) {
        return userService.applyLoan(email, loan);
    }

    @GetMapping("/loans/{userId}")
    public ResponseEntity<List<Loan>> getUserLoans(@PathVariable String userId) {
        return userService.getUserLoans(userId);
    }

    @GetMapping("/loan/status/{loanId}")
    public ResponseEntity<String> getLoanStatus(@PathVariable String loanId) {
        // Added logging for debugging
        System.out.println("Fetching loan status for loanId: " + loanId);
        return userService.getLoanStatus(loanId);
    }
}
