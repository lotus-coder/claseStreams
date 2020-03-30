package streams.texto3;

public class Persona {
	private String nombre, telefono, lugar;
	private int edad;
	
	public Persona(String n,String t,String l,int e) {
		nombre=n;
		telefono=t;
		lugar=l;
		edad=e;
	}

	@Override
	public String toString() {
		return telefono+ "\t" + edad  + "\t" + nombre + "\t" + lugar;
	}
	
	
	
	
}
