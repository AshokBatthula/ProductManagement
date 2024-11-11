<%@page import="com.pace.library.bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.container{
    max-width: 600px;
    background-color: white;
    border-radius: 5px;
    width: 600px;
    text-align: center;
    padding-top:30px;
    padding-bottom:20px;
    border-style: solid;
    margin-top: 20%;
    }
     .button{
        margin-top:20px;
        height:30px;
        width:100px;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List Page</title>
</head>
<body style="background: linear-gradient(to top right,#fc2cc7 0%,#6c4079 100%)">
		<%
		//BookDao bookDao=new BookDao();
		session.getAttribute("proList");
		ArrayList<Product> prolist = new ArrayList<Product>();
		prolist =(ArrayList<Product>) session.getAttribute("proList");
		%>
		<center>
		<div class="container">
		<center>
		<table border="1">
		<%
		for(Product book:prolist){
			
		%>
		<tr>
			<td align="center"><%=book.getId() %></td>
			<td align="center"><%=book.getName() %></td>
			<td align="center"><%=book.getBrand() %></td>
			<td align="cenetr"><%=book.getPrice() %></td>
		</tr>
		<%
		}
		%>
		</table>
		</center>
		<a href="index.html" ><button class="button">Home</button></a>
		
		</div>
		</center>
</body>
</html>