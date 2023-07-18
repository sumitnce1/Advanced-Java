<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>New User</title>
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
        <h1>New User</h1>
        <hr color='red' size='5'/>
       
        <form:form action="registerUser" modelAttribute="objUser">
            User Name  : <form:input path="userName"/>
            Password   : <form:password path="password"/>
            Name       : <form:input path="name"/>
            Email      : <form:input path="email"/>
            Phone      : <form:input path="phone"/>
            City       : <form:input path="city"/>
            <form:button value="submit">Register</form:button>
        </form:form>
        <a href='login'>Go to Login Page</a>
        </pre>
    </div>
</body>
</html>
