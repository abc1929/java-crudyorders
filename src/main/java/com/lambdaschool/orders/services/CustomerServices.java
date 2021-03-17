package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.views.CustomersOrderCounts;

import java.util.List;

public interface CustomerServices {
//   http://localhost:2019/customers/orders
    List<Customer> getAllOrders();
//   http://localhost:2019/customers/customer/7
//   http://localhost:2019/customers/customer/77
   Customer getCustomerById(long cusid);
//   http://localhost:2019/customers/namelike/mes
//   http://localhost:2019/customers/namelike/cin
   List<Customer> getCustomersByName(String term);

//   http://localhost:2019/agents/agent/9
//   http://localhost:2019/orders/order/7
//   http://localhost:2019/orders/advanceamount

   List<CustomersOrderCounts> getCustomersOrderCounts();


//   POST http://localhost:2019/customers/customer
   Customer save(Customer customer);

//   PUT http://localhost:2019/customers/customer/19

//   PATCH http://localhost:2019/customers/customer/19
   Customer update(long id, Customer customer);
//   DELETE http://localhost:2019/customers/customer/54
   void delete(long id);




}

