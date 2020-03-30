package streams.streamObject2;

import java.io.Serializable;

public class Comercial  implements Serializable{
	private String nombre;
	private double salario;
	private TelefonoMovil telefono;
	
	public Comercial(String n, double s,TelefonoMovil t) {
		nombre=n;
		salario=s;
		telefono=t;
	}
	
	public void ver() {
		System.out.println(nombre+" ");
	}
	
	public void trabajar() {
		salario+=10;
		telefono.llamar(15);
	}
	
	public void trabajar(int s,int min) {
		salario+=s;
		telefono.llamar(min);
	}
	
	public String getNombre() {
		return nombre;
	}
	public TelefonoMovil getTelefono() {
		return telefono;
	}
	
	@Override
	public String toString() {
		return "Comercial [nombre=" + nombre + ", salario=" + salario + ", telefono=" + telefono + "]";
	}
	
}
