package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.AgentsRepository;
import com.lambdaschool.orders.repositories.CustomersRepository;
import com.lambdaschool.orders.repositories.OrdersRepository;
import com.lambdaschool.orders.views.CustomersOrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service(value = "orderservice")
public class OrderServicesImpl implements OrderServices {


   @Autowired
   private OrdersRepository ordersRepository;

   @Override
   public Order getOrderByID(long orderid) {
      return ordersRepository.findById(orderid).orElseThrow(() -> new EntityNotFoundException("Order " + orderid + " Not Found"));
   }

   @Override
   public List<Order> getAdvanceAmounts() {
      return StreamSupport.stream(ordersRepository.findAll().spliterator(), false).filter(i -> i.getADVANCEAMOUNT() > 0).collect(Collectors.toList());
   }


   @Transactional
   @Override
   public Order save(Order order) {

      Order neworder = new Order();
      if(order.getORDNUM()>0){
         neworder.setORDNUM(order.getORDNUM());
      }

      neworder.setORDAMOUNT(order.getORDAMOUNT());
      neworder.setADVANCEAMOUNT(order.getADVANCEAMOUNT());
      neworder.setCustomer(order.getCustomer());
      neworder.setORDERDESCRIPTION(order.getORDERDESCRIPTION());



      return ordersRepository.save(neworder);


   }

   @Transactional
   @Override
   public void delete(long id) {

      if (ordersRepository.findById(id).isPresent()) {
         ordersRepository.deleteById(id);
      } else {
         throw new EntityNotFoundException("No order with such ID");
      }

   }


}
