package com.hlq.lab3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hlq.lab3.dao.Customer;
import com.hlq.lab3.dao.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer findCustomerById(Integer id) {
		Customer customer = customerMapper.findCustomerByPrimaryKey(id);
		return customer;
	}

	@Override
	public int findAllCustomersCountByDim(Customer customerDim) {
		return customerMapper.findAllCustomersCountByDim(customerDim);
	}

	@Override
	public List<Customer> findCustomersByDimName(String dimName) {
		List<Customer> customers = customerMapper.findCustomersByNameLike(dimName);
		return customers;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public int addOneCustomer(Customer customer) {
		int num = customerMapper.addOneCustomer(customer);
		return num;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public int updateCustomer(Customer customer) {
		int num = customerMapper.updateByPrimaryKey(customer);
		return num;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public int deleteCustomerById(Integer id) {
		return customerMapper.deleteCustomerByPrimaryKey(id);
	}

	@Override
	public List<Customer> findCustoemrsByDim(Customer customer) {

		return customerMapper.findCustomersByDim(customer);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public int deleteCustomersByIds(List<Integer> ids) {
		return customerMapper.deleteCustomersByIds(ids);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public List<Customer> findCustomersByIds(List<Integer> idList) {
		return customerMapper.findCustomersByIds(idList);
	}

}
