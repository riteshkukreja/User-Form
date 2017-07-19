<%-- 
    Document   : update
    Created on : Jun 20, 2017, 11:37:31 AM
    Author     : admin
--%>

<%@page import="com.mettl.learning.userform.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/display.css" rel="stylesheet" type="text/css" />
        <title>Update User</title>
    </head>
    <body>
        <a href="../"> Go back </a>
        <h1>Update User</h1>
        
        <form action="../user/${user.getID()}" method="post" class="bigass">
            <label> Name: 
                <input type="text" name="name" placeholder="Enter your name" value="${user.getName()}">
            </label>
            
            <label> Age: 
                <input type="number" min="18" max="120" name="age" placeholder="Enter your age" value="${user.getAge()}">
            </label>
           
            <label> Sex: 
                <select name="sex" selected="${user.getSex()}">
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </label>
           
            <label> Address: 
                <input type="text" name="address" placeholder="Enter your address" value="${user.getAddress()}">
            </label>
            
            <input type="submit">
        </form>
    </body>
</html>
