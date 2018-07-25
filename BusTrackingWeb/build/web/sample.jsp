<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="java.util.List"%>
<%@page import="com.st.bean.LoginBean"%>
<html>
    <title>hai</title>
    <body>
        <a href="staff.jsp">staff</a>
        <a href="student_delete.do?dby=name=biju&page=index.jsp">delete</a>
        <a href="student_get.do?gby=LoginBean:id=2&page=index.jsp">get list</a>
        <a href="student_get.do?gby=LoginBean&page=index.jsp">get All list</a>
        <form action="student_update.do" >
            <input type="text" name="name">
            <input type="text" name="pass">
            <input type="hidden" name="good">
            <input type="text" name="eby" placeholder="Delecte by param ex name=sujith">
            <input type="text" name="page" value="index.jsp">
            <input type="submit" value="Submit"> 
            <% if (request.getAttribute("list0") != null) {
                    List<LoginBean> list = (List) request.getAttribute("list0");
                    for (LoginBean b : list) {
            %>
            <h5><%=b.getName()%></h5>
            <%
                    }

                }%>
    </body>  
</html>