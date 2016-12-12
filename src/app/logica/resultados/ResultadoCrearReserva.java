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

import app.logica.resultados.ResultadoCrearReserva.ErrorCrearReserva;

public class ResultadoCrearReserva extends Resultado<ErrorCrearReserva> {

	public ResultadoCrearReserva(ErrorCrearReserva... errores) {
		super(errores);
	}

	public enum ErrorCrearReserva {

		Formato_Importe_Incorrecto,
		Ya_Existe_Reserva_Entre_Las_Fechas,
		Cliente_Vacío,
		Nombre_Cliente_Vacío,
		Apellido_Cliente_Vacío,
		TipoDocumento_Cliente_Vacío,
		NúmeroDocumento_Cliente_Vacío,
		Propietario_Vacío,
		Nombre_Propietario_Vacío,
		Apellido_Propietario_Vacío,
		Inmueble_Vacío,
		Tipo_Inmueble_Vacío,
		Dirección_Inmueble_Vacía,
		Localidad_Inmueble_Vacía,
		Barrio_Inmueble_Vacío,
		Calle_Inmueble_Vacía,
		Altura_Inmueble_Vacía,
		FechaInicio_vacía,
		FechaFin_vacía,
		Importe_vacío
	}
}
