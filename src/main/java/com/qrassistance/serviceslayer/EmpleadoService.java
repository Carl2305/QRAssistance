package com.qrassistance.serviceslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qrassistance.entitylayer.Empleado;
import com.qrassistance.interfaces.IEmpleado;

@Service
@Transactional
public class EmpleadoService {

	@Autowired
	private IEmpleado iempleado;
	
	public Empleado getEmpleado(int id) {
		Empleado emple= iempleado.findById(id).get();
		return emple;
	}
	
	public int InsertEmployee(Empleado e) {
		int rpta=0;
		try {
			iempleado.save(e);
			rpta=1;
		} catch (Exception e2) {
			rpta=0;
		}
		return rpta;
	}
	
}
