
package com.CMEPPS.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CMEPPS.demo.model.Reparto;
import com.CMEPPS.demo.model.Todo;
import com.CMEPPS.demo.repository.RepartoRepository;

@Service
public class RepartoService implements IRepartoService {

	@Autowired
	private RepartoRepository repartoRepository;

	@Override
	public List<Reparto> getRepartosByUser(String user) {
		return repartoRepository.findByUserName(user);
	}

	@Override
	public Optional<Reparto> getRepartoById(long id) {
		return repartoRepository.findById(id);
	}

	@Override
	public void updateReparto(Reparto reparto) {
		repartoRepository.save(reparto);
	}

	@Override
	public void addReparto(String name, LocalDate fecha, int horas, long idTtodo, boolean isDone) {
		repartoRepository.save(new Reparto(name, fecha, horas, idTtodo, isDone));
	}

	@Override
	public void deleteReparto(long id) {
		Optional<Reparto> reparto = repartoRepository.findById(id);
		if (reparto.isPresent()) {
			repartoRepository.delete(reparto.get());
		}
	}

	@Override
	public void saveReparto(Reparto reparto) {
		repartoRepository.save(reparto);
	}
}
