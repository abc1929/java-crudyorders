package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.AgentsRepository;
import com.lambdaschool.orders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service(value = "agentservice")
public class AgentServicesImpl implements AgentServices {



   @Autowired
   private AgentsRepository agentsRepository;

   @Override
   public Agent getAgentByID(long agentid){
      return agentsRepository.findById(agentid).orElseThrow(() -> new EntityNotFoundException("Agent "+ agentid +" Not Found."));
   }

   @Transactional
   @Override
   public Agent save(Agent agent) {
      return agentsRepository.save(agent);
   }


}
