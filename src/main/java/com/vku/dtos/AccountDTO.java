package com.vku.dtos;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountDTO {
//	private Long Id;

	private String email;

	private String password;

	private String fullName;

	private String phoneNumber;


	private int role;

    private boolean status = true;
	
	private Timestamp createTime;

	private Timestamp updateTime;

}
