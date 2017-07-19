<%-- 
    Document   : addNew
    Created on : Jun 20, 2017, 7:59:36 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/display.css" rel="stylesheet" type="text/css" />
        <title>Add New User</title>
    </head>
    <body>
        <a href="./"> Go back </a>
        <h1>Add New User</h1>
        
        <form action="user" method="post" class="bigass">
            <label> Name: 
                <input type="text" name="name" placeholder="Enter your name" >
            </label>
            <label> Age: 
                <input type="number"  name="age" placeholder="Enter your age">
            </label>
            <label> Sex: 
                <select name="sex">
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </label>
            <label> Address: 
                <input type="text" name="address" placeholder="Enter your address" >
            </label>
            <input type="submit">
        </form>
    </body>
</html>
