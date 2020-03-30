package streams.compresion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Comprime {
	
	public static void comprimir (String nomFichOriginal) throws IOException {
		FileInputStream dis = new FileInputStream(nomFichOriginal);
		nomFichOriginal=nomFichOriginal.substring(0, nomFichOriginal.lastIndexOf('.'));
		nomFichOriginal=nomFichOriginal+"_comprimido.bin";
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(nomFichOriginal));
		
		int b,anterior,cont=1;
		
		anterior=dis.read();
		
		
		while(dis.available()>0) {
			b=dis.read();
			if(b==anterior) {
				cont++;
			}else {
				dos.write(anterior);
				dos.writeInt(cont);
				cont=1;
				anterior=b;
			}
		}

		dos.write(anterior);
		dos.writeInt(cont);
		
		dos.close();		
		dis.close();
	}
	
	
	public static void descomprimir (String nomFichComprimido) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(nomFichComprimido));
		nomFichComprimido=nomFichComprimido.substring(0, nomFichComprimido.lastIndexOf('.'));
		nomFichComprimido=nomFichComprimido+"_descomprimido.pdf";
		FileOutputStream fos = new FileOutputStream(nomFichComprimido);
		
		byte b;
		int i;
		while(dis.available()>0) {
			b=(byte)dis.read();
			i=dis.readInt();
			for (int j = 0; j < i; j++) {
				fos.write(b);
			}
		}
		dis.close();
		fos.close();		
	}
	
	public static int numAleatorio(int inf,int sup) {
		int num=(int)((inf)+(Math.random()*((sup+1)-(inf))));
		return num;
	}
	
	public static void crearBinarioAleatorio(String nomFich) throws IOException {
		FileOutputStream fos = new FileOutputStream(nomFich);
		for (int i=0;i<100;i++) {
			
			byte b = (byte) numAleatorio(1, 127);
			int num = numAleatorio(1, 500);			
			
			for (int j = 0; j < num; j++)				
				fos.write(b);
		}
		fos.close();
		
	}
	
	public static void verBinario(String nomFich) throws IOException {
		
		FileInputStream fis = new FileInputStream(nomFich);
		int b;
		while (fis.available()>0) {
			b=fis.read();
			System.out.println(b);
			
			
		}
		
	}
	
	public static void verComprimido(String nomFich) throws IOException {
		
		DataInputStream fis = new DataInputStream(new FileInputStream(nomFich));
		int b;
		while (fis.available()>0) {
			b=fis.read();
			System.out.println(b);
			b=fis.readInt();
			System.out.println(b);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		String nom="curriculum-Angelo_Buono.pdf",nomc="curriculum-Angelo_Buono_comprimido.bin",nomd="curriculum-Angelo_Buono_comprimido_descomprimido.pdf";
		
	//	crearBinarioAleatorio(nom);
		
	//	verBinario(nom);
		
	File f = new File(nom);
		
		
		comprimir(nom);
		File fC = new File(nomc);
	//	verComprimido(nomc);
		System.out.println();
		descomprimir(nomc);
		
		File fD = new File(nomd);
		
		System.out.println(f.length()+"----------------- ARCHIVO SIN COMPRIMIR");
		System.out.println(fC.length()+"----------------- ARCHIVO COMPRIMIDO");
		System.out.println(fD.length()+"----------------- ARCHIVO DESCOMPRESO");
		
		
		
		
		
		
	}
}
