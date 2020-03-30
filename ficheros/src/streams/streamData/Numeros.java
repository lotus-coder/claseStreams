package streams.streamData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import streams.Consola;

public class Numeros {
	
	
	public static int generar1Num(int inf,int sup) {
		int num=(int)(inf+(Math.random()*((sup+1)-(inf))));		
		return num;
	}

	public static ArrayList<Integer> generarNums(int inf,int sup,int cant){
		ArrayList<Integer> listaNum = new ArrayList<Integer>();
		int cont=0,num;
		if(cant>sup-inf) {
			System.out.println("la cantidad de numeros supera el tamaño del rango.");
			return null;
		}
		while(cont<cant) {
			num=generar1Num(inf,sup);
			if(!listaNum.contains(num)) {
				listaNum.add(num);
				cont++;
			}
		}
		return listaNum;
	}
	
	public static void aniade1Num(String ruta,int num) throws IOException {
		File f1 = new File(ruta);
		if(!f1.exists()) {
			System.out.println("No existe el archivo");
		}else {
			FileOutputStream fi = new FileOutputStream(ruta,true);
			DataOutputStream fich = new DataOutputStream(fi);
			fich.writeInt(num);
			fich.close();
		}
	}
	
	public static void aniadeNums(String ruta,ArrayList<Integer> lista) throws IOException {
		
		File f = new File(ruta);
		if(!f.exists()) {
			System.out.println("No existe el fichero.");
		}else {
			FileOutputStream fi = new FileOutputStream(f,true);
			DataOutputStream fich = new DataOutputStream(fi);
			
			Iterator<Integer> it = lista.iterator();
			
			while (it.hasNext()) {
				Integer in = it.next();
				fich.writeInt(in);
			}
			fich.close();
		}
	}
	
	public static Object buscarEnFichero(String ruta, int p) throws IOException {
		File f1 = new File(ruta);
		Object cosa;
		p--;
		if(!f1.exists()) {
			cosa=null;
			System.out.println("No existe el Fichero.");
		}else {
			
			FileInputStream fi =new FileInputStream(f1);
			DataInputStream fich = new DataInputStream(fi);


			if(fich.available()>=p) {
				fich.skip(Integer.BYTES*p);
			}
			cosa=fich.readInt();	
			fich.close();
		}
		return cosa;
	}
	
	public static void ver(String ruta) throws IOException{
		FileInputStream fi =new FileInputStream(ruta);
		DataInputStream fich = new DataInputStream(fi);
		int num;


		while(fich.available()>0) {
			num=fich.readInt();
			System.out.println(num);
		}
		fi.close();
		fich.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		String ruta2="fich2.bin",ruta = "fich1.bin";
		FileOutputStream f1 = new FileOutputStream(ruta);
		
		FileOutputStream f2 = new FileOutputStream(ruta2);
		int num;
		
		System.out.println("¿Cuantos numeros quiere generar?");
		num = Consola.leeInt();
		ArrayList<Integer> lista=generarNums(55, 500, num);
		aniadeNums(ruta, lista);
		
		aniade1Num(ruta, generar1Num(1, 5));
		
		
		aniadeNums(ruta,generarNums(1, 50, 5));
		
		ver(ruta);
		
		System.out.println("\n"+buscarEnFichero(ruta, 6));
		f1.close();
		f2.close();
		FileInputStream fi = new FileInputStream(ruta);
		DataInputStream fich = new DataInputStream(fi);
		num=fich.available()-Integer.BYTES;
		fich.skip(num);
		System.out.println(fich.readInt());

		aniadeNums(ruta2, generarNums(50, 70, 10));

		File f00 = new File(ruta);
		num=(int) (f00.length()/Integer.BYTES);
		
		for (int i = 1; i <= num; i+=2) {
			aniade1Num(ruta2, (Integer)buscarEnFichero(ruta, i));
		}
		System.out.println();
		ver(ruta);
		System.out.println();
		ver(ruta2);		
		fi.close();
		fich.close();
	}
}