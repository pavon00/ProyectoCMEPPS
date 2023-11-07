<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-todo">Añadir
			tarea</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Dias Proxima Semana</h3>
			<a type="button" class="btn btn-success"
        href="/list-horarios">Editar</a>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="14%">Lunes</th>
						<th width="14%">Martes</th>
						<th width="14%">Miercoles</th>
						<th width="14%">Jueves</th>
						<th width="14%">Viernes</th>
						<th width="14%">Sabado</th>
						<th width="14%">Domingo</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${fechasString}" var="fechaString">
							<td>${fechaString}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${horasString}" var="horaString">
							<td>${horaString}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${estatusString}" var="estatuString">
							<td>${estatuString}</td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Tareas pendientes</h3>
			<h5>Introducidas en horario</h5>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="30%">Descripción</th>
						<th width="30%">Fecha objetivo</th>
						<th width="10%">Horas estimadas</th>
						<th width="10%">Prioridad</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<c:if test="${todo.introducidaEnProximaSemana}">
							<tr>
								<td>${todo.description}</td>
								<td><fmt:formatDate value="${todo.targetDate}"
										pattern="dd/MM/yyyy" /></td>
								<td>${todo.horasEstimadas}</td>
								<td>${todo.prioridad}</td>
								<td><a type="button" class="btn btn-success"
									href="/update-todo-introducido-proximaSemana?id=${todo.id}">Deseleccionar</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Tareas pendientes</h3>
			<h5>Para introducir en horario</h5>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="30%">Descripción</th>
						<th width="30%">Fecha objetivo</th>
						<th width="10%">Horas estimadas</th>
						<th width="10%">Prioridad</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<c:if test="${not todo.introducidaEnProximaSemana}">
							<tr>
								<td>${todo.description}</td>
								<td><fmt:formatDate value="${todo.targetDate}"
										pattern="dd/MM/yyyy" /></td>
								<td>${todo.horasEstimadas}</td>
								<td>${todo.prioridad}</td>
								<td><a type="button" class="btn btn-success"
									href="/update-todo-introducido-proximaSemana?id=${todo.id}">Seleccionar</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>