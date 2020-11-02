<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!-- 使用EL表达式 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Customer 增删改查</title>
	<style type="text/css">
		#box_query {
			margin: 10px auto;
			padding: 10px;
			width: 450px;
			height: 300px;
			border: 1px double black;
		}
		#msg_box{
			margin: 10px auto;
			padding: 10px;
			width: 400px;
			height: 50px;
			color: red;
			text-align: center;
		}
		#show_box {
			width: 650px;
			height: 600px;
			margin: 10px auto;
			padding: 5px;
		}
		
		.show_td {
			width: 80px;
			height: 30px;
			text-align: center;
		}
		
		.show_operator{
			width: 150px;
			height: 30px;
			text-align: center;
		}
		.input_button{
			dsiplay: block;
			width: 80px;
			height: 30px;
			margin: 5px;
			padding: 2px;
		}
		.input_text{
			width: 210px;
			height: 25px;
			line-height: 20px;
			font-size: 16px;
			
		}
		.a_button {
			display:block; 
			width: 90px; 
			height: 30px;
			line-height: 30px;
			font-size: 20px;
			text-decoration: none;
			border: 1px solid black;
			border-radius: 3px;
			margin: auto;
			background-color: rebeccapurple;
			color: white;
		}
		a{
			text-decoration: none;
		}
	</style>
	<script type="text/javascript">
		function confirmDelete() {
			return confirm("是否删除");
		}
		
		function deleteCustomers() {
			let customersForm = document.forms.customersForm;
			customersForm.action = "deleteCustomers";
			let r = confirm("是否删除选中项?");
			if (r) {
				customersForm.submit();
			}
		}
		
		function updateCustomers() {
			let customersForm = document.forms.customersForm;
			customersForm.action = "findCustomerByIds";
			customersForm.submit();
		}
	</script>
</head>
<body>

	<div id="box_query">
		<h2 style="width:450px; text-align: center;"><a href="index.jsp" style="text-decoration: none;">用户查询</a></h2>
		<div>
			<form action="findCustomerById" method="post">
				<table>
					<tr>
						<td style="width:150px;">要查询的ID: </td>
						<td><input type="text" id="id_input" class="input_text" name="id"></td>
						<td><input type="submit" class="input_button" value="查询单个"></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width:420px;height:50px;text-align: center;">
			<a class="a_button" href="addCustomerEdit">添加一个</a>
		</div>
		<div>
			<form action="findCustomerByDim" method="post">
				<table>
					<tr>
						<td style="width:150px;">要查询的Name: </td>
						<td><input type="text" id="id_input" class="input_text" name="username"  ></td>
						<td rowspan="3"><input type="submit" class="input_button" value="模糊查询"></td>
					</tr>
					<tr>
						<td style="width:150px;">要查询的Jobs: </td>
						<td><input type="text" id="id_input" class="input_text" name="jobs"  ></td>
						
					</tr>
					<tr>
						<td style="width:150px;">要查询的Phone: </td>
						<td><input type="text" id="id_input" class="input_text" name="phone"  ></td>
						
					</tr>
				</table>
			</form>
		</div>
		
	</div>
	
	<c:if test="${msg != null}">
		<div id="msg_box">
			${msg}
		</div>
	</c:if>
	
	
	<div id="show_box">
		<!-- 单个条件的判断 -->
		<c:if test="${customer != null}">
			<div>
				<table>
					<tr>
						<td class="show_td">用户ID</td>
						<td class="show_td">用户名</td>
						<td class="show_td">JOBS</td>
						<td class="show_td">PHONE</td>
						<td class="show_td">Operator</td>
					</tr>
					<tr>
						<td class="show_td">${customer.id}</td>
						<td class="show_td">${customer.username}</td>
						<td class="show_td">${customer.jobs}</td>
						<td class="show_td">${customer.phone}</td>
						<td class="show_operator">
							<a href="toUpdateCustomer?id=${customer.id}">UPDATE</a>&nbsp;&nbsp;
							<a href="deleteCustomer?id=${customer.id}" onclick="return confirmDelete()">DELETE</a>
						</td>
					</tr>
				</table>
			</div>
		</c:if>

		<!-- for循环的使用 -->
			<c:if test="${customers != null }">
			
				<div>
				<form  method="POST" name="customersForm">
					<table>
						<tr>
							<td class="show_td">选择</td>
							<td class="show_td">用户ID</td>
							<td class="show_td">用户名</td>
							<td class="show_td">JOBS</td>
							<td class="show_td">PHONE</td>
							<td class="show_td">Operator</td>
						</tr>
						
						<c:forEach items="${customers}" var="customer">
						
							<tr>
								<td class="show_td"><input name="ids" type="checkbox" value="${customer.id}"></td>
								<td class="show_td">${customer.id}</td>
								<td class="show_td">${customer.username}</td>
								<td class="show_td">${customer.jobs}</td>
								<td class="show_td">${customer.phone}</td>
								<td class="show_operator">
									<a href="toUpdateCustomer?id=${customer.id}">UPDATE</a>&nbsp;
									<a id="deleteCustomer" href="deleteCustomer?id=${customer.id}" onclick="return confirmDelete()">DELETE</a>
								</td>
							</tr>
						
						</c:forEach>
						
						<tr>
							<td colspan="3"  style="text-align: center;"><input type="button" id="deleteButton" value="删除选中项" onclick="deleteCustomers()" ></td>
							<td colspan="3"  style="text-align: center;"><input type="button" value="修改选中项" onclick="updateCustomers()" ></td>
						</tr>
						<tr>
							<td colspan="2"  style="text-align: center;"><a href="pageChange?changePage=-1">上一页</a></td>
							<td colspan="2" style="text-align: center;">${sessionScope.currentPage + 1}&nbsp;&nbsp;/&nbsp;&nbsp;${sessionScope.totalPage}</td>
							<td colspan="2"  style="text-align: center;"><a href="pageChange?changePage=1">下一页</a></td>
						</tr>
						
					</table>
					</form>
				</div>
			</c:if>
	</div>

</body>


</html>