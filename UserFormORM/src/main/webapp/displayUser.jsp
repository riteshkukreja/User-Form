<%-- 
    Document   : displayUser
    Created on : Jun 19, 2017, 5:43:00 PM
    Author     : admin
--%>

<%@page import="com.mettl.learning.userform.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/display.css" rel="stylesheet" type="text/css" />
        <title>${user.getName()}</title>
    </head>
    <body>
        
        <a href="../"> Go back </a>
        <a href="../update/${user.getID()}">Edit</a>
            
        <form action="../delete/${user.getID()}" method="post">
            <input type="submit" value="Delete">
        </form>
        <a href="${user.getID()}/json">JSON</a>
            
        <h1>Showing details for ${user.getName()}</h1>
            
         <form class="bigass">
            <label> Name: 
                <input type="text" name="name" value="${user.getName()}" disabled>
            </label>
            <label> Age: 
                <input type="number" min="18" max="120" name="age" value="${user.getAge()}"  disabled>
            </label>
            <label> Sex: 
                <select name="sex" selected="${user.getSex()}"  disabled>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </label>
            <label> Address: 
                <input type="text" name="address" value="${user.getAddress()}"  disabled>
            </label>
            
            
        </form>
            
        
    </body>
</html>
