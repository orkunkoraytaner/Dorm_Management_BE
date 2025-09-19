package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.entity.Payment;
import com.devtiro.quickstart.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students/{studentId}/payments") // we need to identify the id of the student first
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPaymentsForStudent(@PathVariable Long studentId)
    {
        List<Payment> payments = paymentService.getAllPaymentForStudent(studentId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@PathVariable Long studentId, @RequestBody Payment payment) {
        return paymentService.createPayment(studentId, payment)
                .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long studentId, @PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

}
