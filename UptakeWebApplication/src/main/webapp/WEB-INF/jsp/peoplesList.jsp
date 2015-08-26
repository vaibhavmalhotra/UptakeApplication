<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>List Peoples</h1>
<br>
<h3><a href="index">Home</a></h3>
<h3><a href="add">Add Peoples</a></h3>
<form:form method="GET" action="/UptakeWebApplication/search">
<div class="row">
    <div class="small-3 columns">
        <input type="text" name="searchString">
        <button type="submit">Search Peoples</button>
    </div>     
</div>
</form:form>
<br>
<c:if test="${not empty peoples}">
 <table align="left" border="1">
  <tr>
   <th>FIRST NAME</th>
   <th>LAST NAME</th>
   <th>STREET ADDRESS</th>
   <th>CITY</th>
   <th>STATE</th>
   <th>COUNTRY</th>
   <th>PRIMARY PHONE</th>
   <th>PRIMARY EMAIL</th>
   <th>ACTIONS</th>
  </tr>

  <c:forEach items="${peoples}" var="people">
   <tr>
    <td><c:out value="${people.firstName}"/></td>
    <td><c:out value="${people.lastName}"/></td>
    <td><c:out value="${people.streetAddress}"/></td>
    <td><c:out value="${people.city}"/></td>
    <td><c:out value="${people.state}"/></td>
    <td><c:out value="${people.country}"/></td>
    <td><c:out value="${people.primaryPhone}"/></td>
    <td><c:out value="${people.primaryEmail}"/></td>
    <td align="center"><a href="add?id=${people.id}&firstName=${people.firstName}&lastName=${people.lastName}&streetAddress=${people.streetAddress}
    &city=${people.city}&state=${people.state}&country=${people.country}&primaryPhone=${people.primaryPhone}&primaryEmail=${people.primaryEmail}">Edit</a> 
    | <a href="delete?id=${people.id}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>