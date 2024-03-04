//package com.vku.models;
//
//import java.sql.Timestamp;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "account")
//@AllArgsConstructor
//@Data
//@NoArgsConstructor
//public class InforShpping {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "shipping_id")
//	private Long shippingId;
//
//	@ManyToOne
//	@JoinColumn(name = "account_id")
//	private Account account;
//
//	@Column(name = "fullname")
//	private String fullname;
//
//	@Column(name = "phone_number")
//	private String phoneNumber;
//
//	@Column(name = "address")
//	private String address;
//
//  private boolean status = true;

//
//	@Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false)
//	private Timestamp createTime;
//
//	@Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", updatable = true, nullable = false)
//	private Timestamp updateTime;
//
//}