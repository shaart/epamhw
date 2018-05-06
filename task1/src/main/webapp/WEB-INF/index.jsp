<%@ page language="java" contentType="text/html; chaartset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Values API</title>
    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/crud.js"></script>
</head>
<body>
<div>
    <h2>CREATE</h2>
    <input id="createValue"/>
    <div id="createMessage"></div>
    <input type="button" id="createButton" value="Create"/>
</div>
<div>
    <h2>PUT</h2>
    <input id="putValue"/>
    <input id="putNewValue"/>
    <input type="button" id="putButton" value="Put"/>
    <div id="putMessage"></div>
</div>
<div>
    <h2>DELETE</h2>
    <input id="deleteValue"/>
    <input type="button" id="deleteButton" value="Delete"/>
    <div id="deleteMessage"></div>
</div>
<div>
    <h2>GET ALL</h2>
    <input type="button" id="getButton" value="Get all"/>
    <div id="getMessage"></div>
    <h3>Results</h3>
    <ul id="results"></ul>
</div>
</body>
</html>