package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {


   @Autowired
   private OrderServices orderServices;


   @GetMapping(value = "/order/{orderid}", produces = "application/json")
   public ResponseEntity<?> getCustomerById(@PathVariable long orderid) {

      return new ResponseEntity<>(orderServices.getOrderByID(orderid), HttpStatus.OK);

   }


   @GetMapping(value = "/order/advanceamount", produces = "application/json")
   public ResponseEntity<?> getAdvanceAmounts() {

      return new ResponseEntity<>(orderServices.getAdvanceAmounts(), HttpStatus.OK);


   }


   @PostMapping(value = "/order" , produces = "application/json", consumes = "application/json")
   public ResponseEntity<?> newOrderById ( @RequestBody @Valid Order order){

      order.setORDNUM(0);
      HttpHeaders resheader = new HttpHeaders();
      Order neword = orderServices.save(order);

      URI newOrdURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ordid}").buildAndExpand(neword.getORDNUM()).toUri();
      resheader.setLocation(newOrdURI);

      return new ResponseEntity<>(neword,resheader, HttpStatus.CREATED);

   }



   @PutMapping(value = "/order/{ordid}" , produces = "application/json", consumes = "application/json")
   public ResponseEntity<?> replaceOrderByID(@PathVariable long ordid , @RequestBody @Valid Order order){


      order.setORDNUM(ordid);
      Order neworder = orderServices.save(order);
      return new ResponseEntity<>(neworder, HttpStatus.OK);

   }

}
