package com.lambdaschool.orders.repositories;


import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.views.CustomersOrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomersRepository extends CrudRepository<Customer, Long> {
   @Query(value= "select c.custcode, c.custname , count(o.custcode) as orderscount from " +
           "customers c left join orders o " +
           "on c.custcode = o.custcode " +
           "group by c.custcode", nativeQuery = true)
   List<CustomersOrderCounts> getCustomersOrderCounts();

}