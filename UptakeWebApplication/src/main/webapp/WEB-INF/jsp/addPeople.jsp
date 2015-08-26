<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>  
  <h1>Add/Edit People</h1>
  <br>
  <h3><a href="index">Home</a></h3>
  <p>${error}</p>
  <form:form method="POST" action="/UptakeWebApplication/save">
      <table>
       <tr>
           <td><form:label path="firstName">FIRST NAME:</form:label></td>
           <td><form:input path="firstName" value="${people.firstName}"/></td>
       </tr>
       <tr>
           <td><form:label path="lastName">LAST NAME:</form:label></td>
           <td><form:input path="lastName" value="${people.lastName}"/></td>
       </tr>
       <tr>
           <td><form:label path="streetAddress">STREET ADDRESS:</form:label></td>
           <td><form:input path="streetAddress" value="${people.streetAddress}"/></td>
       </tr>
       <tr>
           <td><form:label path="city">CITY:</form:label></td>
           <td><form:input path="city" value="${people.city}"/></td>
       </tr>
       <tr>
           <td><form:label path="state">STATE:</form:label></td>
           <td><form:input path="state" value="${people.state}"/></td>
       </tr>
       <tr>
           <td><form:label path="country">COUNTRY:</form:label></td>
           <td><form:input path="country" value="${people.country}"/></td>
       </tr>
       <tr>
           <td><form:label path="primaryPhone">PRIMARY PHONE:</form:label></td>
           <td><form:input path="primaryPhone" value="${people.primaryPhone}"/></td>
       </tr>
       <tr>
           <td><form:label path="primaryEmail">PRIMARY EMAIL:</form:label></td>
           <td><form:input path="primaryEmail" value="${people.primaryEmail}"/></td>
       </tr>
       <tr>
         <td colspan="2">
         <br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input type="submit" value="Submit"/>
         </td>
       </tr>
   </table> 
  </form:form>
</body>
</html>