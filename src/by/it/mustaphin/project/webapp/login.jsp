<%@page import="by.it.mustaphin.project.java.controller.Messages"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<!--<p>Cmd Login: ${message}</p>-->
<% out.println(request.getAttribute(Messages.MESSAGE));    %>

<%@ include file="include/end-html.jsp" %>
