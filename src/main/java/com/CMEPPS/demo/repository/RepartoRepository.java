package com.CMEPPS.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CMEPPS.demo.model.Reparto;

public interface RepartoRepository extends JpaRepository < Reparto, Long > {
    List < Reparto > findByUserName(String user);
}