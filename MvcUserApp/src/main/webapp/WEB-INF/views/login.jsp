<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-size: 20px;
        }
        h1{
         text-align:center;
        }
    </style>
</head>
<body>
    <div class="center">
    
     <pre>
        <h1>Login</h1>
        <hr color='red' size='5'/>
       
        <form:form action="authenticate" modelAttribute="objUser">
            User Name  : <form:input path="userName"/>
            Password   : <form:password path="password"/>
            <form:button type="submit" value="Login">Login</form:button>
        </form:form>
        <a href='newUser'>new User</a> <br>
        <a href='newCategory'>Add New Category</a>
         <br>
        <a href='newProduct'>Add New Product</a>
        </pre>
    </div>
</body>
</html>
