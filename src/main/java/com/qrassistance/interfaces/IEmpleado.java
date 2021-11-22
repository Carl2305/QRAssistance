package com.qrassistance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrassistance.entitylayer.Empleado;

public interface IEmpleado extends JpaRepository<Empleado, Integer> {

}
