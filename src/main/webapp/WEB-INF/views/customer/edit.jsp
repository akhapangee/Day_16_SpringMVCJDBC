<%@include file="../shared/header.jsp" %>
<div class="page-header">
    <h1>Edit customer</h1>
</div>
<form  id="customer" action="${SITE_URL}/customer/save" method="post">
    <div class="form-group">
        <label>First Name</label>
        <input type="text" name="firstName" class="form-control" required="required" autofocus
               value="${customer.firstName}"/>
    </div>
    <div class="form-group">
        <label>Last Name</label>
        <input type="text" name="lastName" class="form-control" required="required"
               value="${customer.lastName}"/>
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" name="email" class="form-control" required="required"
               value="${customer.email}"/>
    </div>
    <div class="form-group">
        <label>Contact</label>
        <input type="text" name="contactNo" class="form-control" required="required"
               value="${customer.contactNo}"/>
    </div>
    <div class="form-inline">
        <label>Status </label>
        <input type="checkbox" name="status"
               <c:if test="${customer.status}">
                   checked 
               </c:if>>Active</input>
        </div>
        <input type="hidden" name="id" value="${customer.id}"/>
        <button type="submit" class="btn btn-success">Save</button>
        <a href="${SITE_URL}/customer" class="btn btn-danger">Back</a>
</form>
<%@include file="../shared/footer.jsp" %>