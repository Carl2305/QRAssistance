package com.qrassistance.serviceslayer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qrassistance.entitylayer.Marcacion;
import com.qrassistance.interfaces.IAssistance;

@Service
@Transactional
public class AssistanceService {
	@Autowired
	private IAssistance iassistance;
	
	public boolean RegistroIngresoAsistencia(Marcacion marcaion) {
		boolean rpta=false;
		try {
			iassistance.save(marcaion);
			rpta=true;
		} catch (Exception e) {	throw e; }
		return rpta;
	}
	public boolean RegistroSalidaAsistencia(Marcacion marcaion) {
		boolean rpta=false;
		try {
			Optional<Marcacion> dataBD=iassistance.findById(marcaion.getMarcacion());
			Marcacion s=dataBD.get();
			s.setFin(marcaion.getFin());
			iassistance.save(s);
			rpta=true;
		} catch (Exception e) { System.out.println("Error --> "+e);	throw e; }
		return rpta;
	}
	public List<Marcacion> ListaAsistencia(){
		return iassistance.findAll();
	}
}
