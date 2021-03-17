package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.services.AgentServices;
import com.lambdaschool.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/agents")
public class AgentController {


   @Autowired
   private AgentServices agentServices;


   @GetMapping(value = "/agent/{agentid}", produces = "application/json")
   public ResponseEntity<?> getCustomerById(@PathVariable long agentid){

      return new ResponseEntity<>(agentServices.getAgentByID(agentid), HttpStatus.OK);

   }



}
