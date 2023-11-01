package com.CMEPPS.demo.security;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.CMEPPS.demo.model.Horario;
import com.CMEPPS.demo.model.Reparto;
import com.CMEPPS.demo.model.Todo;

public class ProximaSemana {

	private List<Horario> horarios;
	private List<Todo> todos;
	private List<Todo> todosUsuario;
	public List<Todo> getTodosUsuario() {
		return todosUsuario;
	}

	public void setTodosUsuario(List<Todo> todosUsuario) {
		this.todosUsuario = todosUsuario;
	}

	private List<Reparto> reparto;

	private String userName;

	ProximaSemana(String userName) {
		this.userName = userName;
		this.todos = new ArrayList<Todo>();
		this.todosUsuario = new ArrayList<Todo>();
		this.reparto = new ArrayList<Reparto>();
		this.horarios = getHorariosProximaSemana();
	}
	
	private ArrayList<Horario> getHorariosProximaSemana() {
		LocalDate[] fechas = obtenerFechasProximaSemana();
		ArrayList<Horario> horariosAux = new ArrayList<Horario>();
		for (LocalDate f : fechas) {
			Horario h = new Horario(this.userName, f, true, 8, true);
			horariosAux.add(h);
		}
		return horariosAux;
	}
	
	public String getStringFechaHorarioEstado(LocalDate fecha) {
		int horasContador = 0;
		int horas = 0;
		for (Horario horario : horarios) {
			if (horario.getFecha().equals(fecha)) {
				horas = horario.getHoras();
				if (!horario.isDisponible()) {
					return "";
				}
			}
		}
		for (Reparto r : this.reparto) {
			if (r.getFecha().equals(fecha)) {
				horasContador = horasContador + r.getHoras();
			}
		}
		if (horasContador == 0) {
			return "Libre";
		}
		if (horas == horasContador) {
			return "Completo";
		}
		return "Semicompleto";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux = "";
		aux = aux + "\n----------\n\nHorario:\n";

		for (Horario h : horarios) {
			aux = aux + h.getFecha() + "\t\t\t";
		}
		aux = aux + "\n";

		for (Horario h : horarios) {
			if (h.isDisponible()) {
				aux = aux + h.getHoras() + "h\t\t\t\t";
			} else {
				aux = aux + "No disponible\t\t\t";
			}
		}

		aux = aux + "\n";

		for (Horario h : horarios) {
			aux = aux + getStringFechaHorarioEstado(h.getFecha()) + "\t\t\t\t";
		}

		aux = aux + "\n\n----------\n\nTareas:\n ";
		for (Todo t : todos) {
			aux = aux + "Tarea " + t.getDescription() + " con " + t.getHorasEstimadas() + " horas: \n";
			for (int i = 0; i < reparto.size(); i++) {
				if (obtenerTododeReparto(i).equals(t)) {
					aux = aux + "\t\t fecha: " + reparto.get(i).getFecha() + ", horas: " + reparto.get(i).getHoras()
							+ "\n";
				}
			}
		}
		return aux;
	}
	private void addReparto() {
		reparto = new ArrayList<Reparto>();
		int contador = 0;
		int horasContador = todos.get(contador).getHorasEstimadas();
		for (int i = 0; i < this.horarios.size(); i++) {
			if (this.horarios.get(i).isDisponible()) {
				int horasDisponibles = this.horarios.get(i).getHoras() - horasDiasRepartidos(i);
				if (horasDisponibles >= horasContador) {
					Reparto r = new Reparto(this.userName, this.horarios.get(i).getFecha(), horasContador, todos.get(contador).getId(), true);
					reparto.add(r);
					if (contador == todos.size() - 1) {
						return;
					}
					contador = contador + 1;
					horasContador = todos.get(contador).getHorasEstimadas();
					i--;
				} else {
					if (horasDisponibles != 0) {
						Reparto r = new Reparto(this.userName, this.horarios.get(i).getFecha(), horasDisponibles, todos.get(contador).getId(), true);
						reparto.add(r);
						horasContador = horasContador - horasDisponibles;
					}
				}
			}
		}
	}

	private int horasDiasRepartidos(int index) {
		int aux = 0;
		for (int i = 0; i < reparto.size(); i++) {
			if (reparto.get(i).getFecha().equals(this.horarios.get(index).getFecha())) {
				aux = aux + reparto.get(i).getHoras();
			}
		}
		return aux;
	}


	public boolean addTodo(Todo todo) {
		if (!todo.getUserName().equals(this.userName)) {
			return false;
		}
		if (todos.contains(todo)) {
			return false;
		}
		this.todos.add(todo);
		if (comprobarValidez()) {
			addReparto();
			return true;
		}
		eliminarUltimoTodo();
		return false;
	}

	public void deleteTodo(Todo todo) {
		while (this.todos.contains(todo)) {
			if (this.todos.contains(todo)) {
				this.todos.remove(this.todos.indexOf(todo));
				deleteReparto(todo);
				addReparto();
			}
		}
	}

	private void deleteReparto(Todo todo) {
		while (indexRepartoTodo(todo)!=-1) {
			int i = indexRepartoTodo(todo);
			reparto.remove(i);
		}
	}
	
	private int indexRepartoTodo(Todo todo) {
		for (int i = 0; i < reparto.size(); i++) {
			if (obtenerTododeReparto(i) == null) {
				return -1;
			}
			if (obtenerTododeReparto(i).equals(todo)) {
				return i;
			}
		}
		return -1;
	}
	
	private Todo obtenerTododeReparto(int index) {
		if (reparto.get(index).getTodo() != null) {
			return reparto.get(index).getTodo();
		}
		for (Todo todo : todos) {
			if (reparto.get(index).getIdTodo()==todo.getId()) {
				reparto.get(index).setTodo(todo);
				return todo;
			}
		}
		return null;
	}

	private LocalDate[] obtenerFechasProximaSemana() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate lunesProximaSemana = fechaActual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

		LocalDate[] fechas = new LocalDate[7];
		for (int i = 0; i < 7; i++) {
			fechas[i] = lunesProximaSemana.plusDays(i);
		}

		return fechas;
	}

	public void setDisponible(int index, boolean disponible) {
		if (index < 7 || index > 0) {
			this.horarios.get(index).setDisponible(disponible);
		}
	}

	public void setHorasDisponible(int index, int horasDisponible) {
		if (index < 7 || index > 0) {
			this.horarios.get(index).setHoras(horasDisponible);
		}
	}

	public boolean fechaEstaEnProximaSemana(LocalDate fecha) {
		LocalDate[] fechasProximaSemana = obtenerFechasProximaSemana();
		return fecha.isAfter(fechasProximaSemana[0]);
	}

	public boolean fechaEstaEnProximaSemana(Date fecha) {
		return fechaEstaEnProximaSemana(dateToLocalDate(fecha));
	}

	public LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public Date localDateToDate(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);
	}

	private boolean comprobarValidez() {
		if (getTotalHorasDisponible() < getTotalHorasTodo()) {
			return false;
		}
		return true;
	}

	private int getTotalHorasDisponible() {
		int aux = 0;
		for (int i = 0; i < 7; i++) {
			if (this.horarios.get(i).isDisponible()) {
				aux = aux + this.horarios.get(i).getHoras();
			}
		}
		return aux;
	}

	private int getTotalHorasTodo() {
		int aux = 0;
		for (Todo t : todos) {
			aux = aux + t.getHorasEstimadas();
		}
		return aux;
	}

	private void eliminarUltimoTodo() {
		if (this.todos.size() != 0) {
			this.todos.remove(this.todos.size() - 1);
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Reparto> getReparto() {
		return reparto;
	}

	public void setReparto(List<Reparto> reparto) {
		Collections.sort(reparto);
		this.todos = new ArrayList<Todo>();
		for (Reparto r : reparto) {
			for (Todo t : todosUsuario) {
				if (t.getId() == r.getIdTodo()) {
					if (!this.todos.contains(t)) {
						this.todos.add(t);
					}
				}
			}
		}
		this.reparto = reparto;
		while (!comprobarValidez()) {
			eliminarUltimoTodo();
		}
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = getHorariosProximaSemana();
		for (Horario h : horarios) {
			for (Horario horario : this.horarios) {
				if (horario.getFecha().equals(h.getFecha())) {
					horario.setDisponible(h.isDisponible());
					horario.setHoras(h.getHoras());
				}
			}
		}
	}

}
