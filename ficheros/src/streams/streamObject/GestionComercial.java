package streams.streamObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class GestionComercial  implements Serializable{
	private String nomFich;
	
	public GestionComercial(String nombreFichero) {
		nomFich=nombreFichero;
	}
	
	public void guardaComerciales(ArrayList<Comercial> comerciales) throws IOException {
		
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomFich));

		for (Comercial comercial : comerciales) {
			oos.writeObject(comercial);
		}
		
		oos.writeObject(null);
		oos.close();
//		File f = new File(nomFich);
//		System.out.println(f.length());
//	
	}
	
	public void verComerciales() throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		File f = new File(nomFich);
		
		Comercial c=(Comercial) ois.readObject();
		
		while (c!=null) {
			System.out.println(c.toString());
			c=(Comercial) ois.readObject();
		}

		ois.close();
	
	}
	
	public Comercial buscarComercial(String nomComercial) throws ClassNotFoundException, IOException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		
		Comercial c=(Comercial) ois.readObject();
		
		while(c!=null) {
			if(c.getNombre().equals(nomComercial)){
				ois.close();
				return c;
			}
			c = (Comercial)ois.readObject();
		}
		
		ois.close();
		return null;
		
	}
	
	public void generarFichMoviles(String nomfich) throws ClassNotFoundException, IOException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomfich));
		
		Comercial c=(Comercial) ois.readObject();
		TelefonoMovil t;
		
		while(c!=null) {
			t=c.getTelefono();
			t.setSaldo(10);
			oos.writeObject(t);
			c=(Comercial)ois.readObject();
		}
		
		oos.writeObject(null);
		ois.close();
		oos.close();
	}
	
	public void trabajarTodos() throws ClassNotFoundException, IOException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		ArrayList<Comercial>comerciales=new ArrayList<Comercial>();
		Comercial c=(Comercial) ois.readObject();
		while(c!=null) {
			c.trabajar();
			comerciales.add(c);
			c=(Comercial)ois.readObject();
		}		
		ois.close();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));
		for (Comercial comercial : comerciales) {
			oos.writeObject(comercial);
		}
		oos.writeObject(null);
		
		oos.close();
		
	}
}
