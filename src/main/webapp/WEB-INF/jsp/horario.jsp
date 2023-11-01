<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Modificar Horario</div>
				<div class="panel-body">
					<form:form action="/update-horario?date=${horario.fecha}" method="post" modelAttribute="horario">
						<form:hidden path="id" />
						<fieldset class="form-group">
							<label>Fecha</label> <label> <c:out
									value="${horario.fecha}" />
							</label>
						</fieldset>
						<c:if test="${horario.disponible}">
							<fieldset class="form-group">
								<a type="button" class="btn btn-success"
									href="/update-horario-disponible?date=${horario.fecha}">No Disponible</a>
							</fieldset>

							<fieldset class="form-group">
								<form:label path="horas">Horas</form:label>
								<form:input path="horas" type="number" class="form-control"
									required="required" value="${horario.horas}" />
								<form:errors path="horas" cssClass="text-warning" />
							</fieldset>
						</c:if>
						<c:if test="${not horario.disponible}">
							<fieldset class="form-group">
								<a type="button" class="btn btn-success"
									href="/update-horario-disponible?date=${horario.fecha}">disponible</a>
							</fieldset>
						</c:if>
						<button type="submit" class="btn btn-success">Guardar</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>