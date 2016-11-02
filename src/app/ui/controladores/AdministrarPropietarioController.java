package app.ui.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.datos.entidades.Propietario;
import app.excepciones.PersistenciaException;
import app.logica.gestores.GestorPropietario;
import app.ui.componentes.VentanaError;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdministrarPropietarioController extends BaseController{

	@FXML
	private TableView<Propietario> tablaPropietarios;

	@FXML
	private TableColumn<Propietario, String> columnaNumeroDocumento;
	@FXML
	private TableColumn<Propietario, String> columnaNombre;
	@FXML
	private TableColumn<Propietario, String> columnaApellido;
	@FXML
	private TableColumn<Propietario, String> columnaTelefono;

	@FXML
	private Button botonVer;
	@FXML
	private Button botonAgregar;
	@FXML
	private Button botonModificar;
	@FXML
	private Button botonEliminar;

	private GestorPropietario gestorPropietario;

	private ArrayList<Propietario> listaPropietarios;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		try {
			listaPropietarios = gestorPropietario.obtenerPropietarios();
		} catch (PersistenciaException e) {
			new VentanaError("Error", "No se pudieron listar los propietarios", null); //falta el stage
		}

		columnaNumeroDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumeroDocumento()));
		columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		columnaApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
		columnaTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));

		tablaPropietarios.getItems().addAll(listaPropietarios);

		habilitarBotones(null);

		tablaPropietarios.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> habilitarBotones(newValue));
	}

	private void habilitarBotones(Propietario propietario) {
		if(propietario==null) {
			botonVer.setDisable(true);
    		botonModificar.setDisable(true);
    		botonEliminar.setDisable(true);
    	}
    	else {
    		botonVer.setDisable(false);
    		botonModificar.setDisable(false);
    		botonEliminar.setDisable(false);
    	}
	}
}