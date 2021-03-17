package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {


   @Autowired
   private CustomerServices customerServices;


   @GetMapping(value = "/{all}", produces = "application/json")
   public ResponseEntity<?> getAllOrders(@PathVariable String all){
      String [] a = {"orders", "customers", "all"};
//      System.out.println(all);
      if (Arrays.stream(a).anyMatch(Predicate.isEqual(all))) {
         return new ResponseEntity<>( customerServices.getAllOrders(), HttpStatus.OK);
      } else {
         return new ResponseEntity<>( "nothing", HttpStatus.OK);
      }
   }


   @GetMapping(value = "/customer/{cusid}", produces = "application/json")
   public ResponseEntity<?> getCustomerById(@PathVariable long cusid){

      return new ResponseEntity<>(customerServices.getCustomerById(cusid), HttpStatus.OK);

   }

   @GetMapping(value = "/namelike/{term}", produces = "application/json")
   public ResponseEntity<?> getCustomersByName(@PathVariable String term){

      return new ResponseEntity<>(customerServices.getCustomersByName(term), HttpStatus.OK);

   }


   @GetMapping(value =  "/orders/count", produces = "application/json")
   public ResponseEntity<?> getOrderCountsByCustomers(){
      return new ResponseEntity<>(customerServices.getCustomersOrderCounts(),HttpStatus.OK);
   }


   @PostMapping(value = "/customer", produces = "application/json", consumes = "application/json")
   public ResponseEntity<?> createNewCustomer( @RequestBody @Valid Customer cus){
      cus.setCUSTCODE(0);

      HttpHeaders cushead = new HttpHeaders();
      Customer newcus  = customerServices.save(cus);
      URI newcusuri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cusid}").buildAndExpand(newcus.getCUSTCODE()).toUri();
      cushead.setLocation(newcusuri);

      return new ResponseEntity<>(newcus,cushead, HttpStatus.CREATED);

   }


   @PutMapping(value = "/customer/{cusid}", produces = "application/json", consumes = "application/json")
   public ResponseEntity<?> replaceCustomerByID(@PathVariable long cusid , @RequestBody @Valid Customer cus){

      cus.setCUSTCODE(cusid);
      Customer newcus = customerServices.save(cus);
      return new ResponseEntity<>(newcus, HttpStatus.OK);


   }


   @PatchMapping(value = "/customer/{cusid}", produces = "application/json", consumes = "application/json")
   public ResponseEntity<?> updateCustomerByID(@PathVariable long cusid , @RequestBody @Valid Customer cus){

      Customer newcus = customerServices.update(cusid,cus);
      return new ResponseEntity<>(newcus, HttpStatus.OK);

   }


   @DeleteMapping(value = "/customer/{cusid}", produces = "application/json")
   public ResponseEntity<?> deleteCustomerByID(@PathVariable long cusid){

      customerServices.delete(cusid);

      return new ResponseEntity<>(HttpStatus.OK);
   }





}
