package com.CMEPPS.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "repartos")
public class Reparto implements Comparable<Reparto>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private String userName;
	private int horas;
	private long idTodo;
	private LocalDate fecha;
	
	@Transient
	private Todo todo;
	
	public Reparto() {
        super();
    }

	public Reparto(String userName, LocalDate fecha, int horas, long idTodo, boolean isDone) {
		// TODO Auto-generated constructor stub
        super();
		this.userName = userName;
		this.fecha = fecha;
		this.horas = horas;
		this.idTodo = idTodo;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getIdTodo() {
		return idTodo;
	}

	public void setIdTodo(long idTodo) {
		this.idTodo = idTodo;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
	public int compareTo(Reparto otra) {
		if (otra.getFecha().isAfter(this.fecha)) {
			return -1;
		}
		if (otra.getFecha().isBefore(this.fecha)) {
			return 1;
		}
		return 0;
	}


}
