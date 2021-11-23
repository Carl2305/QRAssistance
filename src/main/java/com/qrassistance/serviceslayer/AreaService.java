package com.qrassistance.serviceslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qrassistance.entitylayer.Area;
import com.qrassistance.interfaces.IArea;

@Service
@Transactional
public class AreaService {

	@Autowired
	private IArea iarea;
	public List<Area> ListaAreas(){
		return iarea.findAll();
	}
}
