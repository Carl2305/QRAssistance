package com.qrassistance.entitylayer;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPA_test_QRAssistance {

	private EntityManager manager;
	
	public JPA_test_QRAssistance(EntityManager manager) {
		this.manager = manager;
	}
	
public static void main(String[] args)
	
	{
		//comenzamos  a testear  los respectivos  metodos.....
				EntityManagerFactory factory=Persistence.createEntityManagerFactory("QRAssistance");
				EntityManager manager=factory.createEntityManager();
				//instanciamos la misma clase
				JPA_test_QRAssistance jpatest=new JPA_test_QRAssistance(manager);
				EntityTransaction tx=manager.getTransaction();
				//iniciamos la transaccion....
				tx.begin();
				//llamamos al metodo creear  empleado....
				jpatest.registrarMarcacion();
				
				tx.commit();
		
	}

	public void registrarMarcacion()
	{
		Area area1 = new Area("A01","Contabilidad");
		try {
			manager.persist(area1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error area: "+e.getMessage());
		}
				
		Cargo cargo1= new Cargo("C01","Responsable Junior");
		try {
			manager.persist(cargo1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Cargo: "+e.getMessage());
		}
		
		
		Estado estado1 = new Estado(0,"Conectado");
		try {
			manager.persist(estado1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error estado: "+e.getMessage());
		}
		
		
		Empleado empleado1 = new Empleado();
		empleado1.setNom_empleado("Guillermo");
		empleado1.setApe1_empleado("Rios");
		empleado1.setApe2_empleado("Balbuena");
		empleado1.setArea(area1);
		empleado1.setCargo(cargo1);
		empleado1.setCorreo_empleado("revivedx.47@gmail.com");
		empleado1.setTlf_empleado(917585200);
		empleado1.setEmpleado(null);
		
		Login login1 = new Login();
		login1.setUsuario("admin");
		login1.setContrasenia("123456");
		
		empleado1.setLogin(login1);
		login1.setEmpleado(empleado1);
		
		
		try {
			manager.persist(empleado1);
			manager.persist(login1);
		} catch (Exception e) {
			System.out.println("Error estado: "+e.getMessage());
		}
		
		Marcacion marcacion1 = new Marcacion(1,empleado1,LocalDateTime.now(),null,estado1);
		
		try {
			manager.persist(marcacion1);
		} catch (Exception e) {
			System.out.println("Error estado: "+e.getMessage());
		}
		
		
		manager.flush();
	}
}
