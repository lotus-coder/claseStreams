package streams.serializacion;

import java.io.Serializable;

public class Artista implements Serializable{
	private String nombre,categoria;
	
	public Artista(String n, String c){
		nombre=n;
		categoria=c;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getNombre() {
		return nombre;
	}
}
