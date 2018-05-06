### Task 1 (30 points)
1. (10 points) Write simple Java Web App with one servlet and one JSP page. JSP should
has AJAX call buttons (or forms if you feel better with forms) with names GET, POST,
PUT, DELETE. Servlet should handle these requests and change its own state (inner
state can be presented as a large collection of String objects).
2. (6 points) JSP should display inner state of servlet (optional).
3. (2 points)  Don’t forget README (with deploy instructions for Tomcat) and web.xml
4. (2 points) Use Maven to build that project
5. (3 points) Use cookies to track amount of JSP views
6. (3 points) Add JSTL support and use fmt functions to present data
7. (4 points) Use Rest-Assured framework to write a few tests for servlet