<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<!-- online -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- offline -->
<link href="${url}/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script src="${url}/resources/js/jquery.min.js"></script>
<script src="${url}/resources/js/bootstrap.min.js"></script>
<script src="${url}/resources/js/bootbox.min.js"></script>
<script src="${url}/resources/js/its.js"></script>

<title><spring:message code="user.search.page" /></title>
</head>
<body>	
<div class="container">
		
	<ext:messages bean="${bean}"></ext:messages>
	<c:url var="addAction" value="/users"></c:url>
		
	<form:form action="${addAction}" commandName="bean" modelAttribute="bean" id="search_form">		
		
		<div class="panel panel-default">
	    	<div class="panel-heading"><spring:message code="user.search.title" /></div>
	      	<div class="panel-body">
	      		<div class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2">Username</label>
						<div class="col-sm-6">
							<form:input id="usr" class="form-control" placeholder="Username" 
								maxlength="200" path="entity.username" />	  				
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"  for="fullname">Fullname</label>
						<div class="col-sm-6">
							<form:input id="fullname" class="form-control" placeholder="Fullname"
									maxlength="200" path="entity.fullname" />
						</div>
					</div>			
					<div class="form-group">		
						<div class="col-sm-offset-2 col-sm-6">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span> <spring:message text="Search User" />
							</button>						
							<a href="<c:url value='/users/edit'/>" class="btn btn-primary">
					          <span class="glyphicon glyphicon-plus"></span> New User 
					        </a>
						</div>	   
					</div>			
				</div>
	      	</div>
	    </div>
					
		<div class="panel panel-default">
			<div class="panel-heading">User List (${bean.total})</div>
	      	<div class="panel-body">
	      		<!-- <div class="table-responsive"> -->
	      		<section id="flip-scroll">
					<table class="table table-striped table-bordered out-tbl table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th>User ID</th>
							<th>UserName</th>
							<th>FullName</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${bean.listResult != null}"> 
						<c:forEach var="_user" items="${bean.listResult}" varStatus="i">
							<tr>
								<td>${(bean.page-1)*bean.limit+i.index+1}</td>
								<td>${_user.id}</td>
								<td>${_user.username}</td>
								<td>${_user.fullname}</td>
								<td><a href="<c:url value='/users/edit/${_user.id}' />">Edit</a></td>
								<td><a href="#" onclick='deleteItem(${_user})'>Delete</a></td>							
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
					</table>
				<!-- </div> -->
				</section>
				<div>
					<ext:pagination bean="${bean}" maxPage="10" formId="search_form"></ext:pagination>
				</div>
	      	</div>
	    </div>
	 
	</form:form>
</div>

<script>


function deleteItem(entity){
	//entity = JSON.parse(entity);
	var infor = 'User Name: ' +  entity.username;
		infor = infor + '<br/>' + 'Full Name: ' + entity.fullname;
		
	bootbox.dialog({
		  message: infor,
		  title: "Are you sure remove this user ?",
		  buttons: {
		    danger: {
		      label: "Cancel",
		      className: "btn-danger",
		      callback: function() {
		    	  console.log("cancel button");
		      }
		    },
		    main: {
		      label: "OK",
		      className: "btn-primary",
		      callback: function() {
		    	  document.location.href = "${addAction}/remove/"+entity.id;
		    	  console.log("Primary button");
		      }
		    }
		  }
		});
}
</script>
	
</body>
</html>