<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<form class="form-horizontal" action="do?command=Login" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Login</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login">Login</label>
            <div class="col-md-4">
                <input id="login" name="login" type="text"
                       placeholder="" class="form-control input-md" required=""
                       value="TestLogin">
                <span class="help-block">min 5 char</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md"
                       required=""
                       value="TestPassword">
                <span class="help-block">min 8 sym</span>
            </div>
        </div>


        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submit">Single Button</label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-success">Submit</button>
            </div>
        </div>

    </fieldset>
</form>

<!-- Text input-->

<p>User=${user}</p>


<%@ include file="include/end-html.jsp" %>