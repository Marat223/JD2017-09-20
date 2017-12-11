<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<center>
    <table border="0">
        <form action="do?command=SignUp" method="post">
            <tr><td> <input name="login" type="text" placeholder="login min 5. symb"  required="" value="TestLogin"></td><td>Set login</td></tr>
            <tr> <td><input name="password" type="password" placeholder="password min. 8 symb" required="" value="TestPassword"></td><td>Set password</td></tr>
            <tr>  <td><input name="name" type="text" placeholder="name" required="" value="TestName"></td><td>Set your name</td></tr>
            <tr>  <td> <input  type="submit" value="SUBMIT"></td></tr>
        </form>
    </table>
</center>
<!-- Text input-->
<p>message=${message}</p>

<%@ include file="include/end-html.jsp" %>
