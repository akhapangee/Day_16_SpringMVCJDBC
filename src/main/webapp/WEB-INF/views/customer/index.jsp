<%@include file="../shared/header.jsp" %>
<div class="page-header">
    <h1><a href="${SITE_URL}/">HOME</a></h1>
    <h1>Customers</h1>
</div>
<div class="pull-right">
    <a href="${SITE_URL}/customer/add" class="btn btn-primary btn_xs">
        <span class="glyphicon glyphicon-plus"/>
    </a>
</div>
<table class="table">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Contact</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.firstName} ${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>${customer.contactNo}</td>
            <td>
                <c:choose>
                    <c:when test="${customer.status}">
                        <label class="label label-success">Active</label>
                    </c:when>
                    <c:otherwise>
                        <label class="label label-danger">Inactive</label>
                    </c:otherwise>
                </c:choose>

            </td>
            <td>
                <a href="${SITE_URL}/customer/edit/${customer.id}" class="btn btn-success btn_xs">
                    <span class="glyphicon glyphicon-pencil"/>
                </a>
                <a href="${SITE_URL}/customer/delete/${customer.id}" class="btn btn-danger btn_xs"
                   onclick="return confirm('Are you sure to delete?')">
                    <span class="glyphicon glyphicon-trash"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../shared/footer.jsp" %>