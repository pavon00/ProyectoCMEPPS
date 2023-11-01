
package com.CMEPPS.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.CMEPPS.demo.model.Reparto;
import com.CMEPPS.demo.model.Todo;

public interface IRepartoService {

	List<Reparto> getRepartosByUser(String user);

	Optional<Reparto> getRepartoById(long id);

	void updateReparto(Reparto reparto);

	void addReparto(String name, LocalDate fecha, int horas, long idTodo, boolean isDone);

	void deleteReparto(long id);

	void saveReparto(Reparto reparto);
}