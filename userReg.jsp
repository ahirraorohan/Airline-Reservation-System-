<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<center>
<body bgcolor="sky blue">
<form action="UserSignUp" method="post">
<label for="UserName">Enter User Name</label>
<input type="text" name="name" placeholder="Enter Name"><br>

<label for="UserPassword">Enter User Password</label>
<input type="text" name="password" placeholder="Enter Password"><br>

<label for="UserType">Enter User Type</label>
<select name="uType">
<option name="Admin">Admin</option>
<option name="Customer">Customer</option>
</select><br>

<label for="UserAddress">Enter Address</label>
<textarea rows="5" cols="5" name="address"></textarea><br>

<label for="UserPhone">Enter Phone</label>
<input type="text" name="phone"><br>

<label for="gender">Enter Gender</label>
<input type="radio" name="gen" value="male">MALE &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="gen" value="female">FEMALE <br>

<label for="UserDOB">Enetr DOB</label>
<input type="date" name="dob"><br>
<input type="submit" value="SignUp">

</form>
</body>
</center>
</html>
