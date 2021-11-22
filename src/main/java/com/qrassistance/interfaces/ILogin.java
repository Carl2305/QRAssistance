package com.qrassistance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrassistance.entitylayer.Login;

public interface ILogin extends JpaRepository<Login, String> {

}
