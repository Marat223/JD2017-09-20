<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<center>
    <form action="do?command=SignUp" method="post">
	<input name="login" type="text" placeholder=""  required="" value="">
	<input name="password" type="password" placeholder="" required="" value="">
	<input name="email" type="text" placeholder="email@domain" required="" value="">
	<button name="submit">Submit</button>
    </form>
</center>
<!-- Text input-->
<p>message=${message}</p>

<%@ include file="include/end-html.jsp" %>
