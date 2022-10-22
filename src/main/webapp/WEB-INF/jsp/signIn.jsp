<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="parts/meta.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sign.css">
    </head>
    <body>
<%--    header--%>
        <jsp:include page="parts/header.jsp"/>

        <main class="d-fixed align-items-center">
            <div class="container-fluid border border-primary rounded bg-primary bg-opacity-25 my-form">
                <form method="post" action="${pageContext.request.contextPath}/signin">
                    <div class="form-floating">
                        <input type="email" name="email" id="email"
                               class="form-control input-t" placeholder="test@example.com" required>
                        <label for="email">Email</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" name="password" id="password"
                               class="form-control input-b mb-3" maxlength="32" placeholder="password" required>
                        <label for="password">Password</label>
                    </div>
                    <div class="errormsg">
                        <%
                            if (request.getAttribute("errorMsg") != null) {
                                out.print("<p style=\"margin-top: 15px\">");
                                out.print(request.getAttribute("errorMsg"));
                                out.print("</p>");
                            }
                        %>
                    </div>
                    <div>
                        <button type="submit" class="w-100 btn btn-primary">Sign In</button>
                    </div>
                </form>
            </div>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
                crossorigin="anonymous"></script>
    </body>
</html>
