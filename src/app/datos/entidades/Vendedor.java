package app.datos.entidades;

import java.util.ArrayList;

/**
 * Created by fer on 07/10/16.
 */

public class Vendedor {
    Integer id;
    String Nombre;
    String Apellido;
    TipoDocumento tipoDocumento;
    Integer numeroDocumento;
    String password;
    String salt;
    ArrayList<Venta> ventas;
}
