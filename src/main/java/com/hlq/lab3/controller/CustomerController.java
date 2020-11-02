package com.hlq.lab3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hlq.lab3.dao.Customer;
import com.hlq.lab3.dao.CustomerVO;
import com.hlq.lab3.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/findCustomerById")
	public String findCustomerById(Integer id, Model model) {
		Customer customer = customerService.findCustomerById(id);
		System.out.println(customer);
		model.addAttribute("customer", customer);
		return "index";
	}

	@RequestMapping(value = "/toUpdateCustomer", method = RequestMethod.GET)
	public String toUpdateCustomer(Integer id, Model model) {
		Customer customer = customerService.findCustomerById(id);
		model.addAttribute("customer", customer);
		return "WEB-INF/jsp/updateCustomer";
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(Customer customer, Model model) {
		int N = customerService.updateCustomer(customer);
		if (N > 0) {
			model.addAttribute("msg", "更新成功!");
		} else {
			model.addAttribute("msg", "更新失败!");
		}

		// 已经设置的对象置空
		model.addAttribute("customer", null);
		model.addAttribute("customers", null);
		return "index";
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public String deleteCustomer(Integer id, Model model) {
		int N = customerService.deleteCustomerById(id);
		// 已经设置的对象置空
		if (N > 0) {
			model.addAttribute("msg", "删除成功!");
		} else {
			model.addAttribute("msg", "删除失败!");
		}
		model.addAttribute("customer", null);
		model.addAttribute("customers", null);
		return "index";
	}

	@RequestMapping(value = "/addCustomerEdit", method = RequestMethod.GET)
	public String addCustomerEdit() {
		return "WEB-INF/jsp/addCustomerEdit";
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer(Customer customer, Model model) {
		int N = customerService.addOneCustomer(customer);
		if (N > 0) {
			model.addAttribute("msg", "添加成功!");
		} else {
			model.addAttribute("msg", "添加失败!");
		}
		// 已经设置的对象置空
		model.addAttribute("customer", null);
		model.addAttribute("customers", null);
		return "index";
	}

	@RequestMapping(value = "/findCustomerByDim", method = RequestMethod.POST)
	public String findCustomerByDim(Customer customerCondition, HttpServletRequest request, Model model) {
		// 获取Session
		HttpSession session = request.getSession();

		// 为空的时候设置为空串
		if (customerCondition.getUsername() == null) {
			customerCondition.setUsername("");
		}
		if (customerCondition.getJobs() == null) {
			customerCondition.setJobs("");
		}
		if (customerCondition.getPhone() == null) {
			customerCondition.setPhone("");
		}
		List<Customer> customers = customerService.findCustoemrsByDim(customerCondition);

		// 显示页数
		int customersCount = customerService.findAllCustomersCountByDim(customerCondition);
		// 对象改变内容时进行计算
		int totalPage = (int)((customersCount - 1) / customerCondition.getPageSize()) + 1;
		
		System.out.println("totalPage : " + totalPage);
		// 保存对象
		session.setAttribute("currentPage", customerCondition.getStartRow());
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("customerCondition", customerCondition);
		// model中的对象
		model.addAttribute("customers", customers);
		model.addAttribute("customer", null);
		return "index";
	}

	@RequestMapping(value = "/pageChange", method = RequestMethod.GET)
	public String pageChange(HttpServletRequest request, Integer changePage, Model model) {
		// 获取Session
		HttpSession session = request.getSession();
		// 获取当前保存的对象
		Customer customerCondition = (Customer) session.getAttribute("customerCondition");
		int currentPage = (Integer)session.getAttribute("currentPage");
		int totalPage = (Integer)session.getAttribute("totalPage");
		currentPage += changePage;
		
		// 限制条件
		if (currentPage < 0) {
			currentPage = 0;
		}
		if (currentPage >= totalPage) {
			currentPage = totalPage - 1;
		}
		
		
		customerCondition.setStartRow(currentPage * customerCondition.getPageSize());   // 设置要开始查询的页数
		// 查询
		List<Customer> customers = customerService.findCustoemrsByDim(customerCondition);
		model.addAttribute("customers", customers);
		
		// 保存对象
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("customerCondition", customerCondition);
		
		// model对象的操作
		model.addAttribute("customer", null);
		return "index";
		
	}
	
	@RequestMapping(value="/deleteCustomers", method=RequestMethod.POST)
	public String deleteCustomers(Integer[] ids, Model model) {
		List<Integer> idList = new ArrayList<Integer>();
		for (Integer id : ids) {
			idList.add(id);
		}
		int affectedRows = customerService.deleteCustomersByIds(idList);
		model.addAttribute("msg", "一共删除了" + affectedRows + "行");
		model.addAttribute("customers", null);
		model.addAttribute("customer", null);
		return "index";
	}
	
	@RequestMapping(value="/findCustomerByIds", method=RequestMethod.POST)
	public String findCustomerByIds(Integer[] ids, Model model) {
		List<Integer> idList = new ArrayList<Integer>();
		for (Integer id : ids) {
			idList.add(id);
		}
		
		List<Customer> customers = customerService.findCustomersByIds(idList);
		
		int customersSize = customers.size();
		System.out.println(customersSize);
		model.addAttribute("customers", customers);
		model.addAttribute("customersSize", customersSize);
		
		return "WEB-INF/jsp/updateCustomers";
		
	}
	
	@RequestMapping(value="/updateCustomers")
	public String updateCustomers(CustomerVO customerVO, Model model) {
		int affectRows = 0;
		List<Customer> customers = customerVO.getCustomers();
		for (Customer customer: customers) {
			int affectRow = customerService.updateCustomer(customer);
			affectRows += affectRow;
		}
		model.addAttribute("msg", "一共更新了" + affectRows + "行");
		return "index";
	}
	
	
	
}
