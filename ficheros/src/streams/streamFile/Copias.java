package streams.streamFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copias {
	
	public static void copiar(String original) throws IOException {
		File f1 =new File(original);
		if (f1.exists()) {
			String copia=original.substring(0,original.lastIndexOf('.'));
			String extension=original.substring(original.lastIndexOf('.'));
			FileInputStream orig = new FileInputStream(original);
			copia+="_CPY"+extension;
			FileOutputStream  copi = new FileOutputStream (copia);
			
			int b;
			while(orig.available()>0) {
				b=orig.read();
				copi.write(b);
			}
			orig.close();
			copi.close();
		}else {
			System.out.println("El fichero introducido no existe.");
		}
	}
	
	public static void copiaGolpe(String original) throws IOException {
		File f1= new File(original);
		if(!f1.exists()) {
			System.out.println("no exixte");
		}else {
			
			System.out.println("existe");
			
			FileInputStream ori = new FileInputStream(original);
			int bites=ori.available();
			
			String copia=original.substring(0,original.lastIndexOf('.'));
			String extension=original.substring(original.lastIndexOf('.'));
			
			copia+="_CPY"+extension;
			
			FileOutputStream  cpy = new FileOutputStream (copia);
			
			byte [] b = new byte[bites];
			ori.read(b);
			cpy.write(b);
			ori.close();
			cpy.close();
		}
	}
	
	
	public static void copiaBloques(String original) throws IOException {
		final int N=512;
		String copia=original.substring(0,original.lastIndexOf('.'));
		String extension=original.substring(original.lastIndexOf('.'));
		FileInputStream ori = new FileInputStream(original);
		copia+="_CPY"+extension;
		FileOutputStream  cpy = new FileOutputStream (copia);
		
		
		byte [] b=new byte[N];
		int cuantos;
		while (ori.available()>0) {
			cuantos=ori.read(b);
			cpy.write(b, 0, cuantos);
		}
		ori.close();
		cpy.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		Copias.copiar(args[0]);
		Copias.copiaBloques(args[0]);
		Copias.copiaGolpe(args[0]);
	}
}
