package app.logica;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import app.datos.clases.DatosLogin;
import app.excepciones.PersistenciaException;
import app.logica.gestores.GestorLogin;
import app.logica.resultados.ResultadoLogin;

@Service
public class CoordinadorJavaFX {

	@Resource
	GestorLogin gestorLogin;

	public ResultadoLogin loguearVendedor(DatosLogin login) throws PersistenciaException {
		return gestorLogin.loguearVendedor(login);
	}
}
