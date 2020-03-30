package streams.serializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class GestorDeConciertos implements Serializable{
	
	private HashMap<Concierto, Integer> mapaCiertos;
	
	public GestorDeConciertos(HashMap<Concierto, Integer> hs){
		mapaCiertos = hs;
	}
	
	public void grabarConciertosLlenos(String ruta) throws IOException{
		
		Iterator<Concierto> it =mapaCiertos.keySet().iterator();
		
		FileOutputStream fich = new FileOutputStream(ruta);
		ObjectOutputStream miFich = new ObjectOutputStream(fich);
		while (it.hasNext()) {
			Concierto concierto = it.next();
			if(concierto.getAforo()==mapaCiertos.get(concierto)) {
				miFich.writeObject(concierto);
			}
		}
		miFich.writeObject(null);
		miFich.close();
	}
	
	public void verFicheroConciertos(String ruta) throws IOException, ClassNotFoundException {

			System.out.println("existe");
			FileInputStream fich =new FileInputStream(ruta);
			ObjectInputStream miFich = new ObjectInputStream(fich);
			Concierto con =  (Concierto) miFich.readObject();
			while (con!=null) {
				System.out.println(con.getArtista().getNombre()+" tiene un concierto con aforo de "+con.getAforo()+" y ha vendido todas las entradas.");
				con =  (Concierto) miFich.readObject();
			}
			miFich.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
		HashMap<Concierto, Integer> mapa = new HashMap<Concierto, Integer>();
		mapa.put(new Concierto(new Artista ("Killer Mike","rap"),5550), 5550);
		mapa.put(new Concierto(new Artista ("Xamala","rap"),555), 5550);
		mapa.put(new Concierto(new Artista ("Tupac","rap"),55500), 55500);
		
		GestorDeConciertos gesti = new GestorDeConciertos(mapa);
		gesti.grabarConciertosLlenos("img/conciertos.txt");
		
		gesti.verFicheroConciertos("img/conciertos.txt");
		
		
	}
}
