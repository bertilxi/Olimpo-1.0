package app.comun;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import app.datos.entidades.Barrio;
import app.datos.entidades.Calle;
import app.datos.entidades.Direccion;
import app.datos.entidades.Localidad;
import app.datos.entidades.Pais;
import app.datos.entidades.Provincia;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ValidadorFormatoTest {

	@Test
	@Parameters({ "Juan, true",
			"Juan Pablo, true",
			", false",
			"Nombre Incorrecto por mas de treinta caracteres, false",
			"Nombre Incorrect0 por numer0s, false",
			"ácéntós íú y ñ, true" })
	public void validarNombreTest(String nombre, boolean esperado) {
		assertEquals(esperado, new ValidadorFormato().validarNombre(nombre));
	}

	@Test
	@Parameters({ "juan@juan.com, true",
			"Juan.Pablo@a.com, true",
			", false",
			"email_a_a@juan.com, true",
			"emailmayoramuchoscaracteres@juan.com, false",
			"sinarroba, false",
			"caracte^r~@juan.com, true",
			"ácéntós@juan.com, true" })
	public void validarEmailTest(String email, boolean esperado) {
		assertEquals(esperado, new ValidadorFormato().validarEmail(email));
	}

	@Test
	@Parameters({ "3424686868, true",
			"54342686868, true",
			"abcd, false",
			"123456789012345678901234567890, false" })
	public void validarTelefonoTest(String telefono, boolean esperado) {
		assertEquals(esperado, new ValidadorFormato().validarTelefono(telefono));
	}

	protected Object[] parametersForValidarDireccionTest() {
		Localidad localidad1 = new Localidad()
				.setProvincia(new Provincia()
						.setPais(new Pais()
								.setNombre("Argentina"))
						.setNombre("Santa Fe"))
				.setNombre("Santa Fe");
		Localidad localidadVacia = new Localidad();
		Calle calle1 = new Calle("abc", localidad1);
		Barrio barrio1 = new Barrio("abc", localidad1);
		return new Object[] {
				new Object[] { new Direccion("123", "1", "A", calle1, barrio1, localidad1), true },
				new Object[] { new Direccion("123", "1", "A", calle1, barrio1, localidadVacia), false },
				new Object[] { new Direccion("abc", "1", "A", calle1, barrio1, localidad1), false },
				new Object[] { new Direccion("123", "1", "A", null, barrio1, localidad1), false },
				new Object[] { new Direccion("123", "1", "A", calle1, null, localidad1), true },
				new Object[] { new Direccion("123", "1", "A", calle1, barrio1, null), false }
		};
	}

	@Test
	@Parameters
	public void validarDireccionTest(Direccion direccion, boolean esperado) {
		assertEquals(esperado, new ValidadorFormato().validarDireccion(direccion));
	}
}
