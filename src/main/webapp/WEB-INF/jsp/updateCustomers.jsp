<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!-- 使用EL表达式 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		#box {
			margin: 50px auto;
			padding: 10px;
			width: 450px;
			height: 260px;
		}
		
		.show_td {
			width: 80px;
			height: 30px;
			text-align: center;
		}
		
		.input_box {
			width: 300px;
			height: 30px;
			text-align: center;
			line-height: 36px;
			font-size: 20px;
		}
		
		.input_button {
			width: 80px;
			height: 30px;
			margin: 5px auto;
			padding: 2px;
		}
	</style>
</head>

<body>

	<div id="box">
		<h2 style="width: 450px; text-align: center;">
			<a href="index.jsp" style="text-decoration: none;">用户查询</a>
		</h2>
		<c:if test="${customers != null}">

			<form action="updateCustomers" method="post">
				<table>
					<c:forEach begin="0" end="${customersSize - 1}" step="1" var="i">
						<tr>
							<td class="show_td">ID:</td>
							<td class="show_td"><input class="input_box" type="text"
								name="customers[${i}].id" value="${customers[i].id}" readonly></td>
						</tr>
						<tr>
							<td class="show_td">USERNAME:</td>
							<td class="show_td"><input class="input_box" type="text"
								name="customers[${i}].username" value="${customers[i].username}" required></td>
						</tr>
						<tr>
							<td class="show_td">JOBS:</td>
							<td class="show_td"><input class="input_box" type="text"
								name="customers[${i}].jobs" value="${customers[i].jobs}" required></td>
						</tr>
						<tr>
							<td class="show_td">PHONE:</td>
							<td class="show_td"><input class="input_box" type="text"
								name="customers[${i}].phone" value="${customers[i].phone}" required></td>
						</tr>
						<tr>
							<td colspan="2"><hr></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2" style="text-align: center"  ><input type="submit" class="input_button" value="Confirm"></td>
					</tr>
				</table>
			</form>
		</c:if>

	</div>

</body>
</html>