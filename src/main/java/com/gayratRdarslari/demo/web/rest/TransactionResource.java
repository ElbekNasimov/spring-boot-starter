package com.gayratRdarslari.demo.web.rest;

import com.gayratRdarslari.demo.model.Transaction;
import com.gayratRdarslari.demo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionResource {
    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
//    public ResponseEntity getMsg(){
    public ResponseEntity getAll(){
        return ResponseEntity.ok(transactionService.getMsg());
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction){
//        return ResponseEntity.ok(transactionService.save(transaction));
        return ResponseEntity.ok(transactionService.saveExchange(transaction));
    }

    @PutMapping("/transaction")
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.update(transaction));
    }
}
