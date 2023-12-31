<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-todo">A�adir tarea</a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>Lista de tareas pendientes</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="30%">Descripci�n</th>
      <th width="30%">Fecha objetivo</th>
      <th width="10%">Horas estimadas</th>
      <th width="10%">Prioridad</th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${todos}" var="todo">
      <tr>
       <td>${todo.description}</td>
       <td><fmt:formatDate value="${todo.targetDate}"
         pattern="dd/MM/yyyy" /></td>
       <td>${todo.horasEstimadas}</td>
       <td>${todo.prioridad}</td>
       <td><a type="button" class="btn btn-success"
        href="/update-todo?id=${todo.id}">Editar</a>
       <a type="button" class="btn btn-warning"
        href="/delete-todo?id=${todo.id}">Borrar</a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>