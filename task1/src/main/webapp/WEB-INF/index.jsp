<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; chaartset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageVisits" value="${cookie['pageVisits'].value}"/>
<c:choose>
    <c:when test="${empty pageVisits}">
        <%
            Cookie pageVisitsCookie = new Cookie("pageVisits", "1");
            response.addCookie(pageVisitsCookie);
        %>
        <c:set var="pageVisits" value="1"/>
    </c:when>
    <c:otherwise>
        <%
            Cookie[] cookies = request.getCookies();
            Cookie pageVisitsCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("pageVisits"))
                    .findFirst().orElse(null);
            if (pageVisitsCookie != null) {
                pageVisitsCookie.setValue(Integer.valueOf(pageVisitsCookie.getValue()) + 1 + "");
                response.addCookie(pageVisitsCookie);
            }
        %>
        <c:set var="pageVisits" value="${cookie['pageVisits'].value}"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
    <title>Values API</title>
    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/js/crud.js"></script>
</head>
<body>
<div>
    <h2>Page Visits: ${pageVisits}</h2>
    <h2>CREATE</h2>
    <input id="createValue"/>
    <div id="createStatus"></div>
    <input type="button" id="createButton" value="Create"/>
</div>
<div>
    <h2>PUT</h2>
    <input id="putValueId"/>
    <input id="putValueName"/>
    <input type="button" id="putButton" value="Put"/>
    <div id="putStatus"></div>
</div>
<div>
    <h2>DELETE</h2>
    <input id="deleteValue"/>
    <input type="button" id="deleteButton" value="Delete"/>
    <div id="deleteStatus"></div>
</div>
<div>
    <h2>GET ALL</h2>
    <input type="button" id="getButton" value="Get all"/>
    <div id="getStatus"></div>
    <h3>Results</h3>
    <ul id="results"></ul>
</div>
</body>
</html>