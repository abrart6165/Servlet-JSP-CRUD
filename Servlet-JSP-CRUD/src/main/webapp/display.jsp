<%@page import="com.c2k.dao.UserDao"%>
<%@page import="com.c2k.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>    
     <%
     List<User> listUsers =UserDao.allUsers();
     pageContext.setAttribute("listUsers", listUsers);
     %>
    
     <form action="delete" method="POST">
    <div align="center">
        <table border="1" cellpadding="5">
            
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Select for Delete</th>
            </tr>
            <c:forEach  items="${pageScope.listUsers}" var="user">
                <tr>
                    <td><c:out value="${user.uid}" /></td>
                    <td><c:out value="${user.fname}" /></td>
                    <td><c:out value="${user.lname}" /></td>
                    <td><c:out value="${user.uname}" /></td>
                     <td><c:out value="${user.pass}" /></td>
                    <td><c:out   value="${user.role}" /></td> 
                     <td width="25%"><input type="radio" name="uid" 
                        value="${user.uid}"></td>                  
                </tr>
            </c:forEach>
        </table>
        <input type="submit"  value="delete"> 
    </div>
    </form>
<!-- <form name="main1"  method="get" action="index.jsp">
 <input type="submit" name="ter" value="Homepage" >
</form>
<form name="main1"  method="get" action="updating.jsp">
 <input type="submit" name="ter" value="Update" >
</form> -->
</body>
</html>