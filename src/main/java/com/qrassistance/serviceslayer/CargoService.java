package com.qrassistance.serviceslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qrassistance.entitylayer.Cargo;
import com.qrassistance.interfaces.ICargo;

@Service
@Transactional
public class CargoService {
	@Autowired
	private ICargo icargo;
	
	public List<Cargo> ListaCargos(){
		return icargo.findAll();
	}
}
