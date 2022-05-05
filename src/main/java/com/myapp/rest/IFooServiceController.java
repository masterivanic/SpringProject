package com.myapp.rest;

import com.myapp.myapp.entities.Invoice;
import com.myapp.myapp.service.IInvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foos")
public class IFooServiceController {
    
    @Autowired
    private IInvoiceService service;

    @GetMapping(value = "/")
    public <List>Invoice findAll(){
        return (Invoice) service.getAllInvoices();
    }

    @GetMapping(value = "/{id}")
    public Invoice findById(@PathVariable("id") Long id){
        return service.getInvoiceById(id);
    }

    @GetMapping("/hello-rest")
    ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }


}
