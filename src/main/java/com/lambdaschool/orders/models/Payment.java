package com.lambdaschool.orders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long PAYMENTID;
   private String TYPE;

   @ManyToMany(mappedBy = "payments")
   private Set<Order> orders = new HashSet<>();

   public Payment( String TYPE) {
      this.TYPE = TYPE;
   }


   public Payment() {
   }

   public long getPAYMENTID() {
      return PAYMENTID;
   }

   public void setPAYMENTID(long PAYMENTID) {
      this.PAYMENTID = PAYMENTID;
   }

   public String getTYPE() {
      return TYPE;
   }

   public void setTYPE(String TYPE) {
      this.TYPE = TYPE;
   }
}
