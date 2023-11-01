<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Horarios Proxima Semana</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="40%">Fecha</th>
						<th width="30%">Disponible</th>
						<th width="10%">horas</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${horarios}" var="horario" varStatus="status">
						<tr>
							<td>${horario.fecha}</td>
							<td>${horario.disponible}</td>
							<td>${horario.horas}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-horario?id=${status.index}">Editar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>