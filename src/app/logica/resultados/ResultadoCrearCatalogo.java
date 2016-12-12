/**
 * Copyright (C) 2016 Fernando Berti - Daniel Campodonico - Emiliano Gioria - Lucas Moretti - Esteban Rebechi - Andres Leonel Rico
 * This file is part of Olimpo.
 *
 * Olimpo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Olimpo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Olimpo. If not, see <http://www.gnu.org/licenses/>.
 */
package app.logica.resultados;

import app.logica.resultados.ResultadoCrearCatalogo.ErrorCrearCatalogo;

public class ResultadoCrearCatalogo extends Resultado<ErrorCrearCatalogo> {

	public ResultadoCrearCatalogo(ErrorCrearCatalogo... errores) {
		super(errores);
	}

	public enum ErrorCrearCatalogo {
		Cliente_inexistente,
		Codigo_Inmueble_Inexistente,
		Tipo_Inmueble_Inexistente,
		Localidad_Inmueble_Inexistente,
		Direccion_Inmueble_Inexistente,
		Barrio_Inmueble_Inexistente,
		Precio_Inmueble_Inexistente
	}
}