package com.qrassistance.serviceslayer;

import java.sql.SQLException;
import java.util.List;
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
	
	public List<Empleado> ListaEmpleados(){
		return iempleado.findAll();
	}
	
	public int DeleteEmployee(int codigo) {
		int resultado = 0;
		
		try 
		{
			iempleado.deleteById(codigo);
			resultado = 1;
		}
		catch(Exception ex)
		{
			resultado = 0;
			throw ex;
		}
		
		
		return resultado;
	}
	public int UpdateEmployee(Empleado emple) {
		int resultado = 0;
		try 
		{
			iempleado.save(emple);
			resultado = 1;
		}
		catch(Exception ex)
		{
			resultado = 0;
			throw ex;
		}
		return resultado;
	}
}
