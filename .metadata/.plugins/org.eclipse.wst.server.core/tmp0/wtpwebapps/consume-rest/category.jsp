<%@ page import="org.codehaus.jackson.JsonNode"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="org.springframework.http.ResponseEntity"%>
<%@ page import="org.springframework.web.client.RestTemplate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<nav class="navbar navbar-expand-sm navbar-dark bg-">
  <div class="container-fluid">
    <a class="navbar-brand" href="javascript:void(0)">Logo</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="text" placeholder="Search">
        <button class="btn btn-primary" type="button">Search</button>
      </form>
    </div>
  </div>
</nav>


    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Category</title>
</head>
<body>
<%
RestTemplate restTemplate = new RestTemplate();
String url = "http://localhost/category/all";
ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
String responseBody = responseEntity.getBody();
ObjectMapper objectMapper = new ObjectMapper();
JsonNode root = objectMapper.readTree(responseBody);
%>

<div class="container mt-3 table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Category ID:</th>
                <th>Category Name:</th>
                <th>Category Desc:</th>
                <th>Category Image:</th>
            </tr>
        </thead>
        <tbody>
        <% for (JsonNode category : root) { %>
            <tr>
                <td><%= category.path("categoryId").asInt() %></td>
                <td><%= category.path("categoryName").asText() %></td>
                <td><%= category.path("categoryDescription").asText() %></td>
                <%-- <td><%= category.path("categoryImageUrl").asText() %></td> --%>
                <td><img src="Images/<%= category.path("categoryImageUrl").asText() %>" alt="Category Image" style="height: 60px; width: 60px;"></td>
                
            </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
