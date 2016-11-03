package app.ui.componentes.ventanas;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * Representa una ventana que muestra un mensaje de error
 *
 * @author Acosta - Gioria - Moretti - Rebechi
 */
public class VentanaInformacion extends Alert {

	/**
	 * Constructor. Genera la ventana
	 *
	 * @param mensaje
	 *            mensaje a mostrar en la ventana
	 */
	protected VentanaInformacion(String titulo, String mensaje) {
		this(titulo, mensaje, null);
	}

	/**
	 * Constructor. Genera la ventana
	 *
	 * @param mensaje
	 *            mensaje a mostrar en la ventana
	 * @param padre
	 *            ventana en la que se mostrar� este di�logo
	 */
	protected VentanaInformacion(String titulo, String mensaje, Window padre) {
		super(AlertType.INFORMATION);
		if(padre != null){
			this.initOwner(padre);
		}
		this.setContentText(mensaje);
		this.setHeaderText(null);
		this.setTitle(titulo);
		this.showAndWait();
	}
}
