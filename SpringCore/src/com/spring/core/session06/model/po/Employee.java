package com.spring.core.session06.model.po;

import java.util.Date;

import lombok.Data;

// PO/Entity
// 對應 employee 資料表中的紀錄 
@Data
public class Employee {
	private Integer id;
	private String name;
	private Integer salary;
	private Date createtime;
}
