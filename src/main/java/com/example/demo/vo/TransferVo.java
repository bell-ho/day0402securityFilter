package com.example.demo.vo;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferVo {
	private String from;	//돈보내는 사람
	private String to; 		//돈받는사람
	private int amount;		//송금액
	
}
