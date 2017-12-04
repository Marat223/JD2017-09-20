<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<center>
    <br><br><br><br><br><br>
    <form class="form-horizontal" action="do?command=ResetDB" method="post">
        <fieldset>

            <!-- Form Name -->
            <legend>Reset or Init Database</legend>


            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit">DEBUG FUNCTION: Click for reset (full cleaning) the database</label>
                <div class="col-md-4">
                    <button id="submit" name="submit" class="btn btn-danger">Reset DB</button>
                </div>
            </div>

        </fieldset>
    </form>
</center>


<%@ include file="include/end-html.jsp" %>