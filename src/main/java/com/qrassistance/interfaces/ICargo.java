package com.qrassistance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrassistance.entitylayer.Cargo;

public interface ICargo extends JpaRepository<Cargo, String> {

}
