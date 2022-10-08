<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/errormsg.css">
</head>
<body>

<h1>Sign In</h1>
<a href="${pageContext.request.contextPath}/">Home page</a>
<form method="post">
    <div>
        <p>
            <label for="phone">Phone</label>
            <input type="text" maxlength="16" name="phone" id="phone" placeholder="phone number" required>
        </p>
    </div>
    <div>
        <p>
            <label for="password">Password</label>
            <input type="password" maxlength="32" name="password" id="password" placeholder="password" required>
        </p>
    </div>
    <div class="errormsg">
        <p>
            <%
                if (request.getAttribute("errorMsg") != null) {
                    out.println(request.getAttribute("errorMsg"));
                }
            %>
        </p>
    </div>
    <div>
        <button>Sign In</button>
    </div>
</form>

</body>
</html>
