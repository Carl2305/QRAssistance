package com.qrassistance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrassistance.entitylayer.Marcacion;

public interface IAssistance extends JpaRepository<Marcacion, Integer> {

}
