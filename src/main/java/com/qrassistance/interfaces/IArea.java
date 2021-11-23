package com.qrassistance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrassistance.entitylayer.Area;

public interface IArea extends JpaRepository<Area, String> {

}
