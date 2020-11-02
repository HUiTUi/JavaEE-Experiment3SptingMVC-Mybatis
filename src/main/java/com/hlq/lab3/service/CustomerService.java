package com.hlq.lab3.service;

import java.util.List;

import com.hlq.lab3.dao.Customer;

public interface CustomerService {
	// 查询
	Customer findCustomerById(Integer id);
	int findAllCustomersCountByDim(Customer customerDim);
	List<Customer> findCustomersByDimName(String dimName);
	List<Customer> findCustoemrsByDim(Customer customer);
	List<Customer> findCustomersByIds(List<Integer> idList);
	
	// 增加
	int addOneCustomer(Customer customer);
	
	// 动态更新
	int updateCustomer(Customer customer);
	// 删除
	int deleteCustomerById(Integer id);
	int deleteCustomersByIds(List<Integer> ids);

}
