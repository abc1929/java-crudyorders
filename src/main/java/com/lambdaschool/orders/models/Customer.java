package com.lambdaschool.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@JsonIgnoreProperties(value = {"custcodeset", "openingamtset", "receiveamtset", "paymentamtset", "outstandingamtset"}, allowSetters = true)
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long CUSTCODE;  // primary key, not null Long
   private String CUSTNAME;  //not null
   private String CUSTCITY;
   private String WORKINGAREA;
   private String CUSTCOUNTRY;
   private String GRADE;
   private double OPENINGAMT;

   public boolean isCustcodeset() {
      return custcodeset;
   }

   public void setCustcodeset(boolean custcodeset) {
      this.custcodeset = custcodeset;
   }

   @Transient
   private boolean custcodeset = false;

   @Transient
   private boolean openingamtset;

   @Transient
   private boolean receiveamtset;

   @Transient
   private boolean paymentamtset;

   @Transient
   private boolean outstandingamtset;


   public long getCUSTCODE() {
      return CUSTCODE;
   }

   public void setCUSTCODE(long CUSTCODE) {
      setCustcodeset(true);
      this.CUSTCODE = CUSTCODE;
   }

   public boolean isOpeningamtset() {
      return openingamtset;
   }

   public void setOpeningamtset(boolean OPENINGAMTset) {
      this.openingamtset = OPENINGAMTset;
   }

   public boolean isReceiveamtset() {
      return receiveamtset;
   }

   public void setReceiveamtset(boolean RECEIVEAMTset) {
      this.receiveamtset = RECEIVEAMTset;
   }

   public boolean isPaymentamtset() {
      return paymentamtset;
   }

   public void setPaymentamtset(boolean PAYMENTAMTset) {
      this.paymentamtset = PAYMENTAMTset;
   }

   public boolean isOutstandingamtset() {
      return outstandingamtset;
   }

   public void setOutstandingamtset(boolean outstandingamtset) {
      this.outstandingamtset = outstandingamtset;
   }

   public String getCUSTNAME() {
      return CUSTNAME;
   }

   public void setCUSTNAME(String CUSTNAME) {
      this.CUSTNAME = CUSTNAME;
   }

   public String getCUSTCITY() {
      return CUSTCITY;
   }

   public void setCUSTCITY(String CUSTCITY) {
      this.CUSTCITY = CUSTCITY;
   }

   public String getWORKINGAREA() {
      return WORKINGAREA;
   }

   public void setWORKINGAREA(String WORKINGAREA) {
      this.WORKINGAREA = WORKINGAREA;
   }

   public String getCUSTCOUNTRY() {
      return CUSTCOUNTRY;
   }

   public void setCUSTCOUNTRY(String CUSTCOUNTRY) {
      this.CUSTCOUNTRY = CUSTCOUNTRY;
   }

   public String getGRADE() {
      return GRADE;
   }

   public void setGRADE(String GRADE) {
      this.GRADE = GRADE;
   }

   public double getOPENINGAMT() {
      return OPENINGAMT;
   }

   public void setOPENINGAMT(double OPENINGAMT) {
      setOutstandingamtset(true);
      this.OPENINGAMT = OPENINGAMT;
   }

   public double getRECEIVEAMT() {
      return RECEIVEAMT;
   }

   public void setRECEIVEAMT(double RECEIVEAMT) {
      setOutstandingamtset(true);
      this.RECEIVEAMT = RECEIVEAMT;
   }

   public double getPAYMENTAMT() {
      return PAYMENTAMT;
   }

   public void setPAYMENTAMT(double PAYMENTAMT) {
      setOutstandingamtset(true);
      this.PAYMENTAMT = PAYMENTAMT;
   }

   public double getOUTSTANDINGAMT() {
      return OUTSTANDINGAMT;
   }

   public void setOUTSTANDINGAMT(double OUTSTANDINGAMT) {
      setOutstandingamtset(true);
      this.OUTSTANDINGAMT = OUTSTANDINGAMT;
   }

   public String getPHONE() {
      return PHONE;
   }

   public void setPHONE(String PHONE) {
      this.PHONE = PHONE;
   }


   private double RECEIVEAMT;
   private double PAYMENTAMT;
   private double OUTSTANDINGAMT;
   private String PHONE;


   @ManyToOne()
   @JoinColumn(name = "AGENTCODE", nullable = false)
   private Agent agent;


   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Order> orders = new ArrayList<>();

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   @JsonIgnoreProperties(value = "customer", allowSetters = true)
   public List<Order> getOrders() {
      return orders;
   }


   // AGENTCODE oreign key (one agent to many customers) not null

   @JsonIgnoreProperties(value = "customers", allowSetters = true)
   public Agent getAgent() {
      return agent;
   }

   public void setAgent(Agent agent) {
      this.agent = agent;
   }


   public Customer(String CUSTNAME, String CUSTCITY, String WORKINGAREA, String CUSTCOUNTRY, String GRADE, double OPENINGAMT, double RECEIVEAMT, double PAYMENTAMT, double OUTSTANDINGAMT, String PHONE, Agent agent) {
      this.CUSTNAME = CUSTNAME;
      this.CUSTCITY = CUSTCITY;
      this.WORKINGAREA = WORKINGAREA;
      this.CUSTCOUNTRY = CUSTCOUNTRY;
      this.GRADE = GRADE;
      this.OPENINGAMT = OPENINGAMT;
      this.RECEIVEAMT = RECEIVEAMT;
      this.PAYMENTAMT = PAYMENTAMT;
      this.OUTSTANDINGAMT = OUTSTANDINGAMT;
      this.PHONE = PHONE;
      this.agent = agent;
   }

   public Customer() {
   }


}
