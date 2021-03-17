package com.lambdaschool.orders.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agents")
public class Agent {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long AGENTCODE; //primary key not null long
   private String AGENTNAME;
   private String WORKINGAREA;
   private double COMMISSION;
   private String PHONE;
   private String COUNTRY;

   public List<Customer> getCustomers() {
      return customers;
   }

   public void setCustomers(List<Customer> customers) {
      this.customers = customers;
   }

   @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
      @JsonIgnoreProperties(value = "agent", allowSetters = true)
   private List<Customer> customers = new ArrayList<>();


   public String getAGENTNAME() {
      return AGENTNAME;
   }

   public void setAGENTNAME(String AGENTNAME) {
      this.AGENTNAME = AGENTNAME;
   }

   public long getAGENTCODE() {
      return AGENTCODE;
   }

   public void setAGENTCODE(long AGENTCODE) {
      this.AGENTCODE = AGENTCODE;
   }

   public String getWORKINGAREA() {
      return WORKINGAREA;
   }

   public void setWORKINGAREA(String WORKINGAREA) {
      this.WORKINGAREA = WORKINGAREA;
   }

   public double getCOMMISSION() {
      return COMMISSION;
   }

   public void setCOMMISSION(double COMMISSION) {
      this.COMMISSION = COMMISSION;
   }

   public String getPHONE() {
      return PHONE;
   }

   public void setPHONE(String PHONE) {
      this.PHONE = PHONE;
   }

   public String getCOUNTRY() {
      return COUNTRY;
   }

   public void setCOUNTRY(String COUNTRY) {
      this.COUNTRY = COUNTRY;
   }

   public Agent(String AGENTNAME, String WORKINGAREA, double COMMISSION, String PHONE, String COUNTRY) {
      this.AGENTNAME = AGENTNAME;
      this.WORKINGAREA = WORKINGAREA;
      this.COMMISSION = COMMISSION;
      this.PHONE = PHONE;
      this.COUNTRY = COUNTRY;
   }

   public Agent() {

   }


}
