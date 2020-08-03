<%--
  Created by IntelliJ IDEA.
  User: stamacake
  Date: 02.08.2020
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ABOUT</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>
                <p>ФИО</p>
            </td>
            <td>
                <b><%= request.getParameter("surname") %></b>
                <b><%= request.getParameter("name") %></b>
                <b><%= request.getParameter("middle") %></b>
            </td>
        </tr>
        <tr>
            <td>
                <p>Дата рождения</p>
            </td>
            <td>
                <b><%= request.getParameter("date") %></b>
            </td>
        </tr>
        <tr>
            <td>
                <p>Город проживания</p>
            </td>
            <td>
                <b><%= request.getParameter("town") %></b>
            </td>
        </tr>
    </table>
</body>
</html>
