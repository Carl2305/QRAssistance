package com.qrassistance.serviceslayer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qrassistance.entitylayer.Login;
import com.qrassistance.interfaces.ILogin;

@Service
@Transactional
public class LoginService {
	@Autowired ILogin ilogin;
	
	public int Login(Login login) {
		int rpta= 0;
		try {
			Optional<Login> dataUser=ilogin.findById(login.getUsuario());
			Login data=dataUser.get();
			if(data.getUsuario().equals(login.getUsuario())) {
				if(data.getContrasenia().equals(login.getContrasenia())) {
					return data.getEmpleado().getCod_empleado();
				}else {
					return 0;
				}
			}else {
				rpta=0;
			}
		} catch (Exception e) {
			rpta=0;
		}		
		return rpta;
	}
	
	public int InsertLogin(Login login) {
		int rpta= 0;
		try {
			ilogin.save(login);
			rpta=1;
		} catch (Exception e) {
			rpta=0;
		}		
		return rpta;
	}
}
