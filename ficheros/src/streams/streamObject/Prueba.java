package streams.streamObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Prueba implements Serializable{
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		GestionComercial gc1 = new GestionComercial("mifichero.obj");
		ArrayList<Comercial> comerciales= new ArrayList<Comercial>();
	
		comerciales.add(new Comercial("Ermenigildo Antunez",2500,new TelefonoMovil("945212121", 60)));
		comerciales.add(new Comercial("Coco Drilo",1900,new TelefonoMovil("945216958", 15)));
		comerciales.add(new Comercial("Jessica Fernandez",1500,new TelefonoMovil("945458121", 31)));
		comerciales.add(new Comercial("Jose Moe",1100,new TelefonoMovil("945992121", 20)));
		comerciales.add(new Comercial("Michael Roe",3100,new TelefonoMovil("945762121", 8)));
		
		gc1.guardaComerciales(comerciales);

		
		gc1.verComerciales();
		
		System.out.println();
		System.out.println(gc1.buscarComercial("Coco Drilo"));
//		System.out.println(gc1.buscarComercial(" Drilo"));
		
		
		gc1.generarFichMoviles("moviles.obj");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("moviles.obj"));
		TelefonoMovil t = (TelefonoMovil) ois.readObject();
		while(t!=null) {
			System.out.println(t);
			t=(TelefonoMovil) ois.readObject();
		}
		
		gc1.trabajarTodos();
		
		gc1.verComerciales();
		
	}
}
