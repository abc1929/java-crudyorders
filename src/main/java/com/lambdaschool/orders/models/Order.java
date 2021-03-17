package com.lambdaschool.orders.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long ORDNUM; // primary key, not null Long
   private double ORDAMOUNT; // double
   private double ADVANCEAMOUNT; // double
//   CUSTCODE Long foreign key (one customer to many orders) not null
   private String ORDERDESCRIPTION; // String




   @ManyToMany()
   @JoinTable(name = "ORDERSPAYMENTS",
           joinColumns = @JoinColumn(name = "ORDNUM"),
           inverseJoinColumns = @JoinColumn(name = "PAYMENTID"))
   private Set<Payment> payments = new HashSet<>();


   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   @ManyToOne()
   @JoinColumn(name="CUSTCODE", nullable = false)
   @JsonIgnoreProperties(value = "orders",allowSetters = true)
   private Customer customer;

   public void setPayments(Set<Payment> payments) {
      this.payments = payments;
   }

   public Set<Payment> getPayments() {
      return payments;
   }

   public Order(double ORDAMOUNT, double ADVANCEAMOUNT, Customer cus, String ORDERDESCRIPTION) {
      this.ORDAMOUNT = ORDAMOUNT;
      this.ADVANCEAMOUNT = ADVANCEAMOUNT;
      this.customer = cus;
//      this.CUSTCODE = cus.getCUSTCODE();
      this.ORDERDESCRIPTION = ORDERDESCRIPTION;
   }

   public Order() {

   }

   public long getORDNUM() {
      return ORDNUM;
   }

   public void setORDNUM(long ORDNUM) {
      this.ORDNUM = ORDNUM;
   }

   public double getORDAMOUNT() {
      return ORDAMOUNT;
   }

   public void setORDAMOUNT(double ORDAMOUNT) {
      this.ORDAMOUNT = ORDAMOUNT;
   }

   public double getADVANCEAMOUNT() {
      return ADVANCEAMOUNT;
   }

   public void setADVANCEAMOUNT(double ADVANCEAMOUNT) {
      this.ADVANCEAMOUNT = ADVANCEAMOUNT;
   }


   public String getORDERDESCRIPTION() {
      return ORDERDESCRIPTION;
   }

   public void setORDERDESCRIPTION(String ORDERDESCRIPTION) {
      this.ORDERDESCRIPTION = ORDERDESCRIPTION;
   }
}
