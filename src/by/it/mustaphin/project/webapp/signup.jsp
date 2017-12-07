<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<form action="do?command=SignUp" method="post">

    <input id="login" name="login" type="text"
	   placeholder="" class="form-control input-md" required=""
	   value="TestLogin">
    <input id="password" name="password" type="password" placeholder="" class="form-control input-md"
	   required=""
	   value="TestPassword">
    <input id="email" name="email" type="text" placeholder="email@domain" class="form-control input-md"
	   required=""
	   value="email@email.com">
    <button name="submit">Submit</button>
</form>
<!-- Text input-->
<p>message=${message}</p>

<%@ include file="include/end-html.jsp" %>
