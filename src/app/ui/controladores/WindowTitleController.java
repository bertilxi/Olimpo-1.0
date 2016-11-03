package app.ui.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import app.logica.CoordinadorJavaFX;
import app.ui.componentes.ventanas.PresentadorVentanas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WindowTitleController extends OlimpoController {

	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	private HBox titlebar;
	protected Stage stage;
	protected CoordinadorJavaFX coordinador;
	protected PresentadorVentanas presentador;

	@FXML
	private void exitPlatform() {
		Platform.exit();
	}

	@FXML
	private void minimizePlatform() {
		stage.setIconified(true);
	}

	@FXML
	private void maximizePlatform() {

		if(stage.isMaximized()){
			stage.setMaximized(false);
		}
		else{
			stage.setMaximized(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// como se pierden las propiedades del sistema por no tener barra de titulo
		// se implementan dos handlers que manejan el movimiento de arrastre de la ventana
		// se pierden mas propiedades que por el momento ignoro
		titlebar.setOnMousePressed(event -> {
			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		});

		titlebar.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		});
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void setCoordinador(CoordinadorJavaFX coordinador) {
		this.coordinador = coordinador;
	}

	@Override
	public void setPresentador(PresentadorVentanas presentador) {
		this.presentador = presentador;
	}
}
