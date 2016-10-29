package app.datos.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "archivo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Archivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //ID

	@Column(name = "archivo")
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] archivo; //Puede ir String ruta si al final no lo ponemos en la base de datos

	public Archivo() {
		super();
	}

	public Archivo(byte[] archivo) {
		this();
		this.archivo = archivo;
	}

	public Integer getId() {
		return id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public Archivo setArchivo(byte[] archivo) {
		this.archivo = archivo;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((archivo == null) ? 0 : archivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(getClass() != obj.getClass()){
			return false;
		}
		Archivo other = (Archivo) obj;
		if(id == null){
			if(other.id != null){
				return false;
			}
		}
		else if(!id.equals(other.id)){
			return false;
		}
		else{
			return true;
		}
		if(archivo == null){
			if(other.archivo != null){
				return false;
			}
		}
		else if(!archivo.equals(other.archivo)){
			return false;
		}
		return true;
	}

}
