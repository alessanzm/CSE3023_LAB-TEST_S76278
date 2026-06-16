<%-- 
    Document   : session
    Created on : 16 Jun 2026, 3:41:10 PM
    Author     : MP2-4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Page</title>
    </head>
    <body>
        <h2>Book New Session</h2>
        <form action="schedule.jsp" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="action" value="register">
        Full Name: <input type="text" name="student_name" required><br><br>
        Branch Location<input type="text" name="branch_location" required><br><br>
        Lesson Type<input type="text" name="lesson_type" required><br><br>
        Status<input type="text" name="status" required><br><br>

        <br>
        <br>
        <input type="submit" value="Register">
</form>
    </body>
</html>
