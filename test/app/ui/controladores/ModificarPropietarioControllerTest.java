package app.ui.controladores;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import app.datos.clases.TipoDocumentoStr;
import app.datos.entidades.Barrio;
import app.datos.entidades.Calle;
import app.datos.entidades.Direccion;
import app.datos.entidades.Localidad;
import app.datos.entidades.Pais;
import app.datos.entidades.Propietario;
import app.datos.entidades.Provincia;
import app.datos.entidades.TipoDocumento;
import app.excepciones.SaveUpdateException;
import app.logica.CoordinadorJavaFX;
import app.logica.resultados.ResultadoModificarPropietario;
import app.logica.resultados.ResultadoModificarPropietario.ErrorModificarPropietario;
import app.ui.ScenographyChanger;
import app.ui.componentes.ventanas.PresentadorVentanas;
import app.ui.componentes.ventanas.VentanaConfirmacion;
import app.ui.componentes.ventanas.VentanaError;
import app.ui.componentes.ventanas.VentanaErrorExcepcion;
import app.ui.componentes.ventanas.VentanaErrorExcepcionInesperada;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ModificarPropietarioControllerTest {

	private static Propietario propietario;

	@Test
	@Parameters
	public void testModificarPropietario(String nombre,
			String apellido,
			TipoDocumento tipoDocumento,
			String numeroDocumento,
			String telefono,
			String email,
			Direccion direccion,
			ResultadoModificarPropietario resultadoModificarPropietarioEsperado,
			Integer llamaAPresentadorVentanasPresentarError,
			Integer llamaAPresentadorVentanasPresentarExcepcion,
			Exception excepcion) throws Throwable {

		CoordinadorJavaFX coordinadorMock = Mockito.mock(CoordinadorJavaFX.class);
		PresentadorVentanas presentadorVentanasMock = mock(PresentadorVentanas.class);
		VentanaError ventanaErrorMock = mock(VentanaError.class);
		VentanaErrorExcepcion ventanaErrorExcepcionMock = mock(VentanaErrorExcepcion.class);
		VentanaErrorExcepcionInesperada ventanaErrorExcepcionInesperadaMock = mock(VentanaErrorExcepcionInesperada.class);
		VentanaConfirmacion ventanaConfirmacionMock = mock(VentanaConfirmacion.class);
		ScenographyChanger scenographyChangerMock = mock(ScenographyChanger.class);
		ModificarPropietarioController modificarPropietarioControllerMock = mock(ModificarPropietarioController.class);

		when(presentadorVentanasMock.presentarError(any(), any(), any())).thenReturn(ventanaErrorMock);
		when(presentadorVentanasMock.presentarExcepcion(any(), any())).thenReturn(ventanaErrorExcepcionMock);
		when(presentadorVentanasMock.presentarExcepcionInesperada(any(), any())).thenReturn(ventanaErrorExcepcionInesperadaMock);
		when(presentadorVentanasMock.presentarConfirmacion(any(), any(), any())).thenReturn(ventanaConfirmacionMock);
		when(scenographyChangerMock.cambiarScenography(any(), any())).thenReturn(modificarPropietarioControllerMock);
		doNothing().when(modificarPropietarioControllerMock).setPropietarioEnModificacion(any());
		doNothing().when(presentadorVentanasMock).presentarToast(any(), any());

		propietario = new Propietario()
				.setNombre(nombre)
				.setApellido(apellido)
				.setTipoDocumento(tipoDocumento)
				.setNumeroDocumento(numeroDocumento)
				.setTelefono(telefono)
				.setEmail(email)
				.setDireccion(direccion);


		when(coordinadorMock.modificarPropietario(propietario)).thenReturn(resultadoModificarPropietarioEsperado);
		if(excepcion != null){
			when(coordinadorMock.modificarPropietario(propietario)).thenThrow(excepcion);
		}

		ArrayList<TipoDocumento> tipos = new ArrayList<>();
		tipos.add(tipoDocumento);
		Mockito.when(coordinadorMock.obtenerTiposDeDocumento()).thenReturn(tipos);

		ModificarPropietarioController modificarPropietarioController = new ModificarPropietarioController() {
			@Override
			public void inicializar(URL location, ResourceBundle resources) {
				this.coordinador = coordinadorMock;
				this.presentador = presentadorVentanasMock;
				setScenographyChanger(scenographyChangerMock);
				super.inicializar(location, resources);
			}

			@Override
			public void acceptAction() {
				this.textFieldNombre.setText(nombre);
				this.textFieldApellido.setText(apellido);
				this.comboBoxTipoDocumento.getSelectionModel().select(tipoDocumento);
				this.textFieldNumeroDocumento.setText(numeroDocumento);
				this.textFieldTelefono.setText(telefono);
				this.textFieldCorreoElectronico.setText(email);
				this.comboBoxPais.getSelectionModel().select(direccion.getLocalidad().getProvincia().getPais());
				this.comboBoxProvincia.getSelectionModel().select(direccion.getLocalidad().getProvincia());
				this.comboBoxLocalidad.getSelectionModel().select(direccion.getLocalidad());
				this.comboBoxBarrio.getSelectionModel().select(direccion.getBarrio());
				this.comboBoxCalle.getSelectionModel().select(direccion.getCalle());
				this.textFieldAlturaCalle.setText(direccion.getNumero());
				this.textFieldDepartamento.setText(direccion.getDepartamento());
				this.textFieldPiso.setText(direccion.getPiso());
				this.textFieldOtros.setText(direccion.getOtros());
				super.acceptAction();
			}

			@Override
			protected void setTitulo(String titulo) {

			}

			@Override
			public void setPropietarioEnModificacion(Propietario propietarioEnModificacion) {
				this.propietarioEnModificacion = propietarioEnModificacion;
			}
		};

		modificarPropietarioController.setPropietarioEnModificacion(propietario);

		ControladorTest corredorTestEnJavaFXThread =
				new ControladorTest(ModificarPropietarioController.URLVista, modificarPropietarioController);

		Statement test = new Statement() {
			@Override
			public void evaluate() throws Throwable {
				modificarPropietarioController.acceptAction();

				Mockito.verify(coordinadorMock).obtenerTiposDeDocumento();
				Mockito.verify(presentadorVentanasMock, times(llamaAPresentadorVentanasPresentarError)).presentarError(eq("Revise sus campos"), any(), any());
				Mockito.verify(presentadorVentanasMock, times(llamaAPresentadorVentanasPresentarExcepcion)).presentarExcepcion(eq(excepcion), any());
			}
		};

		corredorTestEnJavaFXThread.apply(test, null).evaluate();


	}

	protected Object[] parametersForTestModificarPropietario() {
		TipoDocumento doc = new TipoDocumento(TipoDocumentoStr.DNI);
		Pais pais = new Pais("Argentina");
		Provincia provincia = new Provincia("Santa fé", pais);
		Localidad localidad = new Localidad("Capital", provincia);
		Direccion dir = new Direccion("1865", "1", "B", new Calle("San martín", localidad), new Barrio("Centro", localidad), localidad, "");
		return new Object[] {
				//prueba correcta
				new Object[] { "Juan", "Perez",doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoCorrecto, 0, 0, null},
				//prueba nombre incorrecto
				new Object[] { "Juan", "Perez",doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarNombreIncorrecto, 1, 0, null},
				//prueba apellido incorrecto
				new Object[] { "Juan", "Perez",doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarApellidoIncorrecto, 1, 0, null},
				//prueba documento incorrecto
				new Object[] { "Juan", "Perez", doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarDocumentoIncorrecto, 1, 0, null},
				//prueba teléfono incorrecto
				new Object[] { "Juan", "Perez", doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarTelefonoIncorrecto, 1, 0, null},
				//prueba email incorrecto
				new Object[] { "Juan", "Perez", doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarEmailIncorrecto, 1, 0, null},
				//prueba direccion incorrecta
				new Object[] { "Juan", "Perez", doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarDireccionIncorrecta, 1, 0, null},
				//prueba ya existe propietario
				new Object[] { "Juan", "Perez", doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoModificarYaExiste, 1, 0, null},
				//prueba ya existe propietario
				new Object[] { "Juan", "Perez",doc,"12345678", "123-123", "juanperez@hotmail.com", dir,new ResultadoModificarPropietario(ErrorModificarPropietario.Formato_Nombre_Incorrecto,ErrorModificarPropietario.Formato_Apellido_Incorrecto),1, 0, null},
				//prueba nombre vacio
				new Object[] { "", "Perez", doc, "12345678", "123-123", "juanperez@hotmail.com", dir, null, 1, 0, null},
				//prueba PersistenciaException
				new Object[] { "Juan", "Perez",doc,"12345678", "123-123", "juanperez@hotmail.com", dir, resultadoCorrecto, 0, 1, new SaveUpdateException(new Throwable())}

		};
	}

	//Para modificarPropietario
	private static final ResultadoModificarPropietario resultadoCorrecto = new ResultadoModificarPropietario();
	private static final ResultadoModificarPropietario resultadoModificarNombreIncorrecto =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Nombre_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarApellidoIncorrecto =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Apellido_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarDocumentoIncorrecto =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Documento_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarTelefonoIncorrecto =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Telefono_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarEmailIncorrecto =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Email_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarDireccionIncorrecta =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Formato_Direccion_Incorrecto);
	private static final ResultadoModificarPropietario resultadoModificarYaExiste =
			new ResultadoModificarPropietario(ResultadoModificarPropietario.ErrorModificarPropietario.Ya_Existe_Propietario);

}