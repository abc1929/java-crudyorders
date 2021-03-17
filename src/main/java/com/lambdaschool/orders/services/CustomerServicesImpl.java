package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.CustomersRepository;
import com.lambdaschool.orders.views.CustomersOrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service(value = "customerservice")
public class CustomerServicesImpl implements CustomerServices {



   @Autowired
   private CustomersRepository customersRepository;

   @Autowired
   private AgentServices agentServices;

   @Autowired
   private OrderServices orderServices;

   @Override
   public List<Customer> getAllOrders(){
      return StreamSupport.stream(customersRepository.findAll().spliterator(), false).collect(Collectors.toList());
   }


   @Override
   public Customer getCustomerById(long cusid){

      return customersRepository.findById(cusid).orElseThrow(() -> new EntityNotFoundException("Customer "+cusid + " Not Found"));
   }
   @Override
   public List<Customer> getCustomersByName(String term){
      return StreamSupport.stream(customersRepository.findAll().spliterator(),false).filter(i -> i.getCUSTNAME().matches("(?i)^.*"+term+".*$")).collect(Collectors.toList());
   }


   @Override
   public List<CustomersOrderCounts> getCustomersOrderCounts(){
      System.out.println(customersRepository.getCustomersOrderCounts());
      return customersRepository.getCustomersOrderCounts();}

   @Transactional
   @Override
   public Customer save(Customer customer) {

      Customer cus =  new Customer();
      if(customer.isCustcodeset()){
         cus.setCUSTCODE(customer.getCUSTCODE());
      }
      cus.setCUSTNAME(customer.getCUSTNAME());
      cus.setCUSTCITY(customer.getCUSTCITY());
      cus.setWORKINGAREA(customer.getWORKINGAREA());
      cus.setCUSTCOUNTRY(customer.getCUSTCOUNTRY());
      cus.setGRADE(customer.getGRADE());
      cus.setOPENINGAMT(customer.getOPENINGAMT());
      cus.setRECEIVEAMT(customer.getRECEIVEAMT());
      cus.setPAYMENTAMT(customer.getPAYMENTAMT());
      cus.setOUTSTANDINGAMT(customer.getOUTSTANDINGAMT());
      cus.setPHONE(customer.getPHONE());

      // this refer a new customer to the existing agent
      if(agentServices.getAgentByID(customer.getAgent().getAGENTCODE())!=null){
         cus.setAgent(agentServices.getAgentByID(customer.getAgent().getAGENTCODE()));
      } else {
         // create a new agent? or just not accepting this post
         Agent newagent = new Agent();
         newagent.setAGENTNAME(customer.getAgent().getAGENTNAME());
         newagent.setWORKINGAREA(customer.getAgent().getWORKINGAREA());
         newagent.setCOMMISSION(customer.getAgent().getCOMMISSION());
         newagent.setPHONE(customer.getAgent().getPHONE());
         newagent.setCOUNTRY(customer.getAgent().getCOUNTRY());

         agentServices.save(newagent);

         cus.setAgent(newagent);
      }


      cus.getOrders().clear();
      for(Order o : customer.getOrders()){
         Order newo = new Order();
         newo.setORDAMOUNT(o.getORDAMOUNT());
         newo.setADVANCEAMOUNT(o.getADVANCEAMOUNT());
         newo.setCustomer(cus);
         newo.setORDERDESCRIPTION(o.getORDERDESCRIPTION());
         newo.setPayments(o.getPayments());
//         orderServices.save(newo);
         cus.getOrders().add(newo);
      }




      return customersRepository.save(cus);



   }


   @Transactional
   @Override
   public Customer update(long id, Customer customer){


      Customer cus =  new Customer();
      if (id != 0) {
         cus = customersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));

      }

      if(customer.getCUSTNAME()!=null){
         cus.setCUSTNAME(customer.getCUSTNAME());
      }
      if(customer.getCUSTCITY()!=null){
         cus.setCUSTCITY(customer.getCUSTCITY());
      }
      if(customer.getWORKINGAREA()!=null){
         cus.setWORKINGAREA(customer.getWORKINGAREA());
      }
      if(customer.getCUSTCOUNTRY()!=null){
         cus.setCUSTCOUNTRY(customer.getCUSTCOUNTRY());
      }
      if(customer.getGRADE()!=null){
         cus.setGRADE(customer.getGRADE());
      }
      if(customer.isOpeningamtset()){
         cus.setOPENINGAMT(customer.getOPENINGAMT());
      }
      if(customer.isReceiveamtset()){
         cus.setRECEIVEAMT(customer.getRECEIVEAMT());
      }
      if(customer.isPaymentamtset()){
         cus.setPAYMENTAMT(customer.getPAYMENTAMT());
      }
      if(customer.isOutstandingamtset()){
         cus.setOUTSTANDINGAMT(customer.getOUTSTANDINGAMT());
      }
      if(customer.getPHONE()!=null){
         cus.setPHONE(customer.getPHONE());
      }

      // this refer a new customer to the existing agent
      if(agentServices.getAgentByID(customer.getAgent().getAGENTCODE())!=null){
         cus.setAgent(agentServices.getAgentByID(customer.getAgent().getAGENTCODE()));
      } else {
         // create a new agent? or just not accepting this post
         Agent newagent = new Agent();
         newagent.setAGENTNAME(customer.getAgent().getAGENTNAME());
         newagent.setWORKINGAREA(customer.getAgent().getWORKINGAREA());
         newagent.setCOMMISSION(customer.getAgent().getCOMMISSION());
         newagent.setPHONE(customer.getAgent().getPHONE());
         newagent.setCOUNTRY(customer.getAgent().getCOUNTRY());

         agentServices.save(newagent);

         cus.setAgent(newagent);
      }


      if(customer.getOrders()!=null){
         cus.getOrders().clear();
         for(Order o : customer.getOrders()){
            Order newo = new Order();
            newo.setORDAMOUNT(o.getORDAMOUNT());
            newo.setADVANCEAMOUNT(o.getADVANCEAMOUNT());
            newo.setCustomer(cus);
            newo.setORDERDESCRIPTION(o.getORDERDESCRIPTION());
            newo.setPayments(o.getPayments());

//         orderServices.save(newo);
            cus.getOrders().add(newo);
         }
      }






      return customersRepository.save(cus);


   }

@Transactional
@Override
   public void delete(long id){
      if(customersRepository.findById(id).isPresent()){
         customersRepository.deleteById(id);

      } else {
         throw new EntityNotFoundException("No customer with this ID");
      }

}




}
