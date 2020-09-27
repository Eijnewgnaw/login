<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>


    <script>
        window.onload = function(){
            document.getElementById("img").onclick = function(){
                this.src="/login/checkCodeServlet?time="+new Date().getTime();
            }
        }


    </script>
    <style>
        div{
            color: #ff0000;
        }

    </style>
</head>
<body>

<form action="/login/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkCode"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/login/checkCodeServlet"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>


</form>


<div><%=request.getAttribute("check_code_error") == null ? "" : request.getAttribute("check_code_error")%></div>

</body>
</html>