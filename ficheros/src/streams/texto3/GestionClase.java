package streams.texto3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionClase {
	private String nomFich;
	
	
	public GestionClase(String rutaFichero) {
		nomFich=rutaFichero;
	}
	
	public void ver() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		
		String linea = br.readLine();
		while(linea!=null) {
			System.out.println(linea);
			linea=br.readLine();
		}
		br.close();
	}
	
	public void aniadePersona(Persona p) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(nomFich,true));
		bw.newLine();
		bw.write(p.toString());
		bw.close();
	}
	
	public boolean nombreEnLinea(String n,String nombre) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		int m = Integer.parseInt(n);
		for (int i = 1; i < m; i++) {
			br.readLine();
		}
		String str=br.readLine();
		str=str.substring(str.indexOf("\t")+1);
		str=str.substring(str.indexOf("\t")+1);
		str=str.substring(0, str.indexOf("\t"));
		br.close();
		if(str.equals(nombre)) {
			return true;
		}
		return false;
		
	}
	
	public Persona transforma(String n) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		int m = Integer.parseInt(n);
		for (int i = 1; i < m; i++) {
			br.readLine();
		}
		String str=br.readLine(), nom, tel, lug;
		int ed;
		if (str== null) {
			return null;
		}
		tel=str.substring(0, str.indexOf("\t"));
		str=str.substring(str.indexOf("\t")+1);
		ed=Integer.parseInt(str.substring(0, str.indexOf("\t")));
		str=str.substring(str.indexOf("\t")+1);
		nom=str.substring(0, str.indexOf("\t"));
		str=str.substring(str.indexOf("\t")+1);
		lug=str.substring(0);
		Persona p = new Persona(nom,tel,lug,ed);
		br.close();
		return p;
		
	}

	public Persona buscaPersona(String nombre) throws IOException {
		Persona p=null;
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		String linea=br.readLine();
		int cont=1;
		while(linea!=null) {
			if(nombreEnLinea(cont+"", nombre)) {
				p=transforma(cont+"");
				br.close();
				return p;
			}else {
				linea=br.readLine();
				cont++;
			}
		}
		
		br.close();
		return p;
	}
	
	public void eliminaPersona(String nombre) throws IOException {
		Persona p=null;
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		String linea=br.readLine();
		int cont=1;
		
		while(linea!=null) {
			if(!nombreEnLinea(cont+"", nombre)) {
				bw.write(linea);
				bw.newLine();
				cont++;
			}
			linea=br.readLine();
		}
		br.close();
		bw.close();
		
		BufferedReader br1 = new BufferedReader(new FileReader("temp.txt"));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(nomFich));
		
		String oLinea=br1.readLine();
		bw1.write(oLinea);

		while(oLinea!=null) {
			bw1.newLine();
			oLinea=br1.readLine();
			bw1.write(oLinea);
		}
		bw1.close();
		br1.close(); 
	}


	
	public static void main(String[] args) throws IOException {
		
		GestionClase gc = new GestionClase("src/streams/texto3/archivo.txt");
		
		gc.ver();
		gc.aniadePersona(new Persona("Angelo", "944123456","Bilbao",41));
		System.out.println();
		gc.ver();
		System.out.println();
		Persona p=gc.buscaPersona("Angelo");
		System.out.println(p+"\n");
		
		gc.eliminaPersona("Angelo");
		System.out.println();
		gc.ver();
	}
	
}
