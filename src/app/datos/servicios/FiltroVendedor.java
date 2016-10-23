package app.datos.servicios;

import app.datos.clases.TipoDocumentoStr;

public class FiltroVendedor {

	private TipoDocumentoStr tipoDocumento;
	private String documento;

	public FiltroVendedor(TipoDocumentoStr tipoDocumento, String documento) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}

	public TipoDocumentoStr getTipoDocumento() {
		return tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

}
