
package com.CMEPPS.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CMEPPS.demo.model.Horario;
import com.CMEPPS.demo.repository.HorarioRepository;

@Service
public class HorarioService implements IHorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	@Override
	public List<Horario> getHorariosByUser(String user) {
		return horarioRepository.findByUserName(user);
	}

	@Override
	public Optional<Horario> getHorarioById(long id) {
		return horarioRepository.findById(id);
	}

	@Override
	public void updateHorario(Horario horario) {
		horarioRepository.save(horario);
	}

	@Override
	public void addHorario(String name, LocalDate fecha, boolean disponible, int horas, boolean isDone) {
		horarioRepository.save(new Horario(name, fecha, disponible, horas, isDone));
	}

	@Override
	public void deleteHorario(long id) {
		Optional<Horario> horario = horarioRepository.findById(id);
		if (horario.isPresent()) {
			horarioRepository.delete(horario.get());
		}
	}

	@Override
	public void saveHorario(Horario horario) {
		horarioRepository.save(horario);
	}
}
