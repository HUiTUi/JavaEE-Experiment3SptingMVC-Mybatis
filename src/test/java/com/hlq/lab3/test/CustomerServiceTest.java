package com.hlq.lab3.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hlq.lab3.dao.Customer;
import com.hlq.lab3.dao.CustomerMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {

	@Autowired
	private CustomerMapper customerMapper;

	@Test
	public void findCustomerByPrimaryKey() {
		Customer customer = customerMapper.findCustomerByPrimaryKey(1);
		System.out.println(customer);
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	// 更新插入的时候使用事务管理
	public void addCustomerTest() {
		Customer customer = new Customer();
		customer.setUsername("Hanaaa");
		customer.setJobs("doctor");
		customer.setPhone("19861823000");
		int num = customerMapper.addOneCustomer(customer);
		if (num > 0) {
			System.out.println("插入的id为: " + customer.getId());
		} else {
			System.out.println("插入失败");
		}

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public void updateCustomerTest() {
		Customer customer = new Customer();
		customer.setUsername("Hanaaa");
		customer.setJobs("doctor");
		customer.setPhone("19861823000");

		customer.setId(5);
		int n = customerMapper.updateByPrimaryKey(customer);

		if (n > 0) {
			System.out.println("Ok");
		} else {
			throw new NullPointerException();
		}

	}

	@Test
	public void findCustomerByNameLike() {
		List<Customer> customers = customerMapper.findCustomersByNameLike("H");
		System.out.println(customers);
	}

	@Test
	public void findCustomerByDimTest() {
		Customer customer = new Customer();
		customer.setUsername("");
		customer.setJobs("");
		customer.setPhone("");
		List<Customer> customers = customerMapper.findCustomersByDim(customer);
		System.out.println(customers);
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, readOnly=false)
	public void deleteCustoemrsTest() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(14);
		ids.add(10);
		ids.add(13);
		int nums = customerMapper.deleteCustomersByIds(ids);
		if (nums > 0) {
			System.out.println(nums);
		} else {
			System.out.println("false");
		}
	}
	
}
