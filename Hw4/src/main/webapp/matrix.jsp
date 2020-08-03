<%--
  Created by IntelliJ IDEA.
  User: stamacake
  Date: 02.08.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matrix</title>
</head>
<body>
<table border="1">
    <%
        int n = Integer.parseInt(request.getParameter("n"));
        for (int i = 1; i <= n; i++) {
            out.print("<tr>");
            for (int j = 1; j <=n ; j++) {
                out.print("<td>"+i+"-"+j+"</td>");
            }
            out.println("</tr>");
        }
    %>
</table>
</body>
</html>
