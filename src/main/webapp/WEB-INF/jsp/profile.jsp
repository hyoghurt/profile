<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="edu.school21.cinema.models.ImageDTO" %>
<%@ page import="edu.school21.cinema.models.SignInDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>

<%
    List<SignInDTO> list_sign_in = (List<SignInDTO>) request.getAttribute("list_sign_in");
    List<ImageDTO> list_images = (List<ImageDTO>) request.getAttribute("list_images");

    Collections.reverse(list_sign_in);
    Collections.reverse(list_images);

    String ava = "";
    try {
        ImageDTO imageDTO = list_images.get(0);
        ava = imageDTO.getId();
    } catch (Exception ignored) {
    }
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="parts/meta.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
</head>

<body>
    <jsp:include page="parts/header.jsp"/>

    <main class="p-0">

        <div class="row justify-content-center p-0 w-100 m-0">
            <div class="col p-0 m-0" style="max-width: 200px;">
                <div class="row rounded border border-primary align-items-end p-2 m-0 w-100" style="height: 300px;">
                    <div class="col m-0 p-0 text-center">
                        <img class="img-fluid m-0" style="max-height: 200px;"
                             src="${pageContext.request.contextPath}/images/<%=ava%>" alt="no image">
                        <form method="post" action="${pageContext.request.contextPath}/images" enctype="multipart/form-data">
                            <input class="my-2 form-control form-control-sm" type="file" id="file" name="file"
                                   accept="image/*">
                            <button class="btn btn-sm btn-primary w-100">Upload</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col p-0 m-0" style="max-width: 500px;">
                <div class="p-0 m-0 fw-bolder" style="height: 80px;">
                    <p class="m-2 my-0">${user.firstName} ${user.lastName}</p>
                    <p class="m-2 my-0">${user.email}</p>
                </div>
                <div class="ps-2 m-0">
                    <div class="p-0 m-0 border border-primary overflow-auto" style="height: 220px;">
                        <table class="table table-sm table-striped caption-top w-100">
                            <caption class="m-0 ms-2 p-0 fs-6">User sign in</caption>
                            <thead class="table-primary">
                            <tr>
                                <th scope="col">Date</th>
                                <th scope="col">Time</th>
                                <th scope="col">IP</th>
                            </tr>
                            </thead>
                            <tbody class="table-group-divider">
                            <c:forEach var="item" items="<%=list_sign_in%>">
                                <tr>
                                    <td>${item.date}</td>
                                    <td>${item.time}</td>
                                    <td>${item.ip}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="row justify-content-center p-0 w-100 m-0">
            <div class="col m-0 p-0" style="max-width: 700px">
                <table class="table table-sm table-striped w-100 mt-2">
                    <thead class="table-primary">
                    <tr>
                        <th>File name</th>
                        <th>Size</th>
                        <th>MIME</th>
                    </tr>
                    </thead>
                    <tbody class="table-group-divider">
                    <c:forEach var="item" items="<%=list_images%>">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/images/${item.id}"
                                   target="_blank">${item.name}</a></td>
                            <td>${item.size}</td>
                            <td>${item.mime}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>

</html>
