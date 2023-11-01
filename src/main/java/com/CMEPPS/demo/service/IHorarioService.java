
package com.CMEPPS.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.CMEPPS.demo.model.Horario;

public interface IHorarioService {

	List<Horario> getHorariosByUser(String user);

	Optional<Horario> getHorarioById(long id);

	void updateHorario(Horario horario);

	void addHorario(String name, LocalDate fecha, boolean disponible, int horas, boolean isDone);

	void deleteHorario(long id);

	void saveHorario(Horario horario);
}