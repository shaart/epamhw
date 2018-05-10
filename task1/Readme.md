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

### Installing
1. Install Apache Tomcat 8 or later
2. Clone repository `git clone https://github.com/shaart/epamhw.git`
3. Go to `task1` directory
4. Build package ```task1-webapp:package```
5. Start Tomcat by running `bin\startup.bat` (or `bin\startaup.sh` for Linux)
6. Tomcat will automatically deploy the war
7. Open [http://localhost:8080/](http://localhost:8080/) in your browser