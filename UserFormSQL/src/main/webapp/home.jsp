<%-- 
    Document   : index
    Created on : Jun 19, 2017, 5:43:56 PM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page import="com.mettl.learning.userform.models.User"%>
<%@page import="com.mettl.learning.userform.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/display.css" rel="stylesheet" type="text/css" />
        <title>Home</title>
    </head>
    <body>
        <header>
            <h1>User Form Spring MVC</h1>
            <a href="addNew">Add New User</a>
            <a href="#" onclick="showForm();">Upload File</a>
            <a href="user/json">JSON</a>
            <form action="upload" method="post" enctype="multipart/form-data" id="uploadForm" class="bigass shadow" style="display:none">
                <span class="close" onclick="hideForm();">X</span>
                <label> Select JSON File to Import
                    <input type="file" id="fileHandler" name="file" style="display:none" hidden/>
                </label>
                <input type="submit" value="upload" />
            </form>
            <h1 class="blue">Showing Users</h1>
        </header>
        <div class="data">
            <% List<User> ll = (List<User>)request.getAttribute("users"); %>
            <% if(ll == null || ll.size() == 0) { %>
                <tr>
                <h2>No User Available</h2>
                </tr>
            <% } else { %>
                <table class="container">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Sex</th>
                        <th>Address</th>
                        <th>View</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>            

                    <%  for(User user: ll) { %>

                    <tr>
                        <td><%= user.getID() %></td>
                        <td><%= user.getName() %></td>
                        <td><%= user.getAge() %></td>
                        <td><%= user.getSex() %></td>
                        <td><%= user.getAddress() %></td>
                        <td><a href="user/<%= user.getID() %>">View</a></td>
                        <td><a href="update/<%= user.getID() %>">Edit</a></td>
                        <td>
                            <form action="delete/<%= user.getID() %>" method="post">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>

                    <% } %>
                <% } %>
            </table>

        </div>
        
        <script>
            function showForm() {
                document.getElementById("uploadForm").style.display = "block";
            }
            function hideForm() {
                document.getElementById("uploadForm").style.display = "none";
            }
        </script>
    </body>
</html>
