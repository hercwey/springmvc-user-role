<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="ext" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<!-- offline -->
<link href="${url}/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script src="${url}/resources/js/jquery.min.js"></script>
<script src="${url}/resources/js/its.js"></script>
<title>Edit User Page</title>
</head>
<body>
<div class="container">

	<ext:messages bean="${bean}"></ext:messages>
	<c:url var="addAction" value="/users/edit"></c:url>

	<form:form action="${addAction}" commandName="user">
	
		<div class="panel panel-default">
	    	<div class="panel-heading">
	    		<c:if test="${!empty user.entity.username}">
					<div class="label_title">Edit a User</div>
				</c:if>
				<c:if test="${empty user.entity.username}">
					<div class="label_title">Add a User</div>
				</c:if>
	    	</div>
	      	<div class="panel-body">
	      		<div class="row">
		      		<div class="form-horizontal">
						<c:if test="${!empty user.entity.username}">
							<form:hidden path="entity.id" />
						</c:if>
						<div class="form-group">
							<label class="control-label col-sm-2">Username</label>
							<div class="col-sm-6">
								<c:if test="${empty user.entity.username}">
									<form:input class="form-control" placeholder="Username"	maxlength="200" path="entity.username" required="required" />
								</c:if>	
								<c:if test="${!empty user.entity.username}">
									<form:input class="form-control" path="entity.username" readonly="true"	disabled="true" required="required"/>
									<form:hidden path="entity.username" />
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Fullname</label>
							<div class="col-sm-6">
								<form:input class="form-control" placeholder="Fullname"	maxlength="200" path="entity.fullname" />
							</div>
						</div>					
						<div class="form-group">
							<label class="control-label col-sm-2">Password</label>
							<div class="col-sm-6">
								<form:input class="form-control" placeholder="Password" maxlength="200" path="entity.password" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-6">
							<button type="submit" class="btn btn-primary" id="saveEdit">
								<span class="glyphicon glyphicon-floppy-disk"></span> <spring:message text="Save" />
							</button>					
							<a href="<c:url value='/users' />" class="btn btn-primary">
								<span class="glyphicon glyphicon-off"></span> <spring:message code="btn.close" />
							</a>
						</div>	
					</div>
				</div>
			</div>
		</div>	      	
	
		<div class="panel panel-default">
			<div class="panel-heading">Roles</div>
	      	<div class="panel-body">
	      		<div class="table-responsive">
	      		<table>
					<tr>
						<td style="width: 5%">&nbsp;</td>
						<td style="width: 40%; vertical-align: top;">
							<table class="table table-bordered table-hover out-tbl"	id="tblLeftRole">
								<thead>
									<tr>
										<td colspan="3" style="padding: 0px; margin: 0px;">
											<div class="">
												<div class=""
													style="text-align: right; padding-right: 10px;">
													<h4>																		
													Not Roles
													<button type="button" id="addRole" class="btn btn-default btn-xs">
														<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
													</button>
													</h4>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<th><input type="checkbox" name="chkAllRoleLeft"
											id="chkAllRoleLeft" value=""></th>
										<th>code</th>
										<th>name</th>
	
									</tr>
								</thead>
								<tbody>
									<c:forEach var="_roleleft" items="${left}" varStatus="i">
										<tr>
											<td style="text-align: center;"><input
												type="checkbox" name="chkRoleLeft" id="chkRoleLeft"
												value="${_roleleft.id}"> <input type="hidden"
												name="TeamLeft" id="TeamLeft" value="${_roleleft.id}"></td>
											<td><c:out value="${_roleleft.code}"></c:out></td>
											<td><c:out value="${_roleleft.name }"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
	
							</table>
	
						</td>
						<td style="width: 5%">&nbsp;</td>
						<td style="width: 40%; vertical-align: top;">
							<table class="table table-bordered table-hover out-tbl"	id="tblRightRole">
								<thead>
									<tr>
										<td colspan="3" style="padding: 0px; margin: 0px;">
											<div class="">
												<div class="span12 title">
													<h4>
													<button type="button" id="removeRole" class="btn btn-default btn-xs">
													 <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
													</button>
													Have Roles
													</h4>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<th><input type="checkbox" id="chkAllRoleRight"
											value=""></th>
										<th>code</th>
										<th>name</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="_roleright" items="${right}" varStatus="i">
										<tr>
											<td><input type="checkbox" name="chkRoleRight"
												id="chkRoleRight" value="${_roleright.id}"> <input
												type="hidden" name="RoleRight" id="RoleRight"
												value="${_roleright.id}"></td>
											<td><c:out value="${_roleright.code}"></c:out></td>
											<td><c:out value="${_roleright.name}"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
	
							</table>
	
						</td>
						<td style="width: 5%">&nbsp;</td>
					</tr>
				</table>
				</div>
	      	</div>
      	</div>

	</form:form>	

</div>

<script type="text/javascript">
	/*function backList() {
		document.location.href = "list";
	}*/
	$(document).ready(function() {
		$("#chkAllRoleLeft").click(function() {
			$("input[name=chkRoleLeft]").each(function() {
				this.checked = $("#chkAllRoleLeft").is(':checked');
			});
		});
		$("#chkAllRoleRight").click(function() {
			$("input[name=chkRoleRight]").each(function() {
				this.checked = $("#chkAllRoleRight").is(':checked');
			});
		});
		$("#saveEdit").click(function() {
			$("input[name=chkRoleRight]").each(function() {
				this.checked = true;
			});
		});
		$("#addRole").click(function() {
			$("input[name=chkRoleLeft]").each(function() {
				if (this.checked) {
					this.checked = false;
					var row = $(this).closest("tr");
					var table = $(this).closest("table");
					row.detach();
					if (table.is("#tblLeftRole")) {
						$("#tblRightRole").append(row);
					}
					//  row.fadeOut();
					row.fadeIn();
					this.name = "chkRoleRight";
					this.id = "chkRoleRight";
				}

			});
		});
		$("#removeRole").click(function() {
			$("input[name=chkRoleRight]").each(function() {
				if (this.checked) {
					this.checked = false;
					var row = $(this).closest("tr");
					var table = $(this).closest("table");
					row.detach();
					if (table.is("#tblRightRole")) {
						$("#tblLeftRole").append(row);
					}
					//row.fadeOut();
					row.fadeIn();
					this.name = "chkRoleLeft";
					this.id = "chkRoleLeft";
				}

			});
		});

	});
</script>
</body>
</html>