package com.hlq.lab3.dao;

import java.util.List;

public interface CustomerMapper {
	// 查找函数
	Customer findCustomerByPrimaryKey(Integer id);
	List<Customer> findCustomersByIds(List<Integer> idList);
	
	
	// 增
	Integer addOneCustomer(Customer customer);
	
	// 改 
	int updateByPrimaryKey(Customer customer);
	
	// 删
	int deleteCustomerByPrimaryKey(Integer id);
	int deleteCustomersByIds(List<Integer> ids);
	
	// 模糊查询
	List<Customer> findCustomersByNameLike(String nameLike);
	List<Customer> findCustomersByDim(Customer customer);
	Integer findAllCustomersCountByDim(Customer customerDim);
	
}
