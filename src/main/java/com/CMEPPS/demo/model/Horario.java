package com.CMEPPS.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horarios")
public class Horario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userName;
	private LocalDate fecha;
	private boolean disponible;
	private int horas;

	public Horario() {
		super();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Fecha: " + fecha + ", disponible: " + true + ", horas: " + horas;
	}

	public Horario(String userName, LocalDate fecha, boolean disponible, int horas, boolean isDone) {
		// TODO Auto-generated constructor stub
		super();
		this.userName = userName;
		this.fecha = fecha;
		this.horas = horas;
		this.disponible = disponible;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public void cambiarDisponibilidad() {
		if (this.disponible) {
			this.disponible = false;
		} else {
			this.disponible = true;
		}
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
