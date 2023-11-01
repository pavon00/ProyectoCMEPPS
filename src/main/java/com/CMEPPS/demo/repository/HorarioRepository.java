package com.CMEPPS.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CMEPPS.demo.model.Horario;

public interface HorarioRepository extends JpaRepository < Horario, Long > {
    List < Horario > findByUserName(String user);
}