package streams.coleccionesStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import streams.Consola;


public class VisitasDias implements Serializable{
	private int[] diaVisita;
	private ArrayList<Visita> visitas;
	private HashMap<HoraMin, Integer> horaVisita;
	
	public VisitasDias(int[] dia) {
		if(dia.length==2) {
			diaVisita = dia;
		}else {
			diaVisita[0]=dia[0];
			diaVisita[1]=dia[1];
		}
		visitas=new ArrayList<Visita>();
		horaVisita = new HashMap<HoraMin, Integer>();
	}

	public boolean aniadeVisita(Visita v) throws IOException {
		if(!comprobador(v)) {
			return false;
		}
		visitas.add(v);
		return true;
	}
	
	public void guardarAFichero(String fichero) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
		oos.writeObject(visitas);
		oos.close();
	}

	public boolean comprobador(Visita v) throws IOException{
		boolean esta=false;
		
		BufferedReader br = new BufferedReader(new FileReader("streams.coleccionesStream/tiempos_de_visita.txt"));
		int lHora,lMinuto, hora = v.getHoraVisita().getMinuto(),minuto = v.getHoraVisita().getMinuto();
		String [] cosa;
		String linea = br.readLine();
		if(visitas.contains(v)) {
			int cont=0;
			for (Visita vis : visitas) {
				if(vis.equals(v)) {
					cont+=vis.getCantPer();
				}
			}
			if(cont+v.getCantPer()>50) {
				return false;
			}
		}
		while(linea!=null) {
			cosa=linea.split("\t");

			if(hora==Integer.parseInt(cosa[0])&&minuto==Integer.parseInt(cosa[1])) {
				return true;	
			}
			
			linea=br.readLine();
		}
		br.close();
		return esta;
	}
	
	public int cargarVisitas(String fichero) throws ClassNotFoundException, IOException {
		int n=-1;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
		
		ArrayList<Visita> v = (ArrayList<Visita>) ois.readObject();
		for (Visita visita : v) {
			aniadeVisita(visita);
		}
		ois.close();		
		return n;
	}
	
	public static void verFichero(String fichero) throws ClassNotFoundException, IOException {
		
		ObjectInputStream dis = new ObjectInputStream(new FileInputStream(fichero));
		ArrayList<Visita> v = (ArrayList<Visita>) dis.readObject();
		
		for (Visita visita : v) {
			System.out.println(visita);
		}
		dis.close();
	}
	
	public boolean actualizaVisita(String nombre) throws IOException {
		for (Visita v : visitas) {
			
			if(v.getNombre().equals(nombre)) {
				System.out.println("¿que quiere cambiar la hora o la cantidad de personas?(H/C)");
				char resp = Consola.leeChar();
				
				if (resp=='H'||resp=='h') {
					
					boolean esta =false;
					while(!esta) {
						System.out.println("Pon primero la hora y despues los minutos\nHora: ");
						int hora=Consola.leeInt(),min;
						System.out.println("Minutos: ");
						min=Consola.leeInt();
						Visita vis = new Visita("",0,(new HoraMin(hora,min)));
						esta=comprobador(vis);
						if(esta) {
							v.setHoraVisita(vis.getHoraVisita());
						}else {
							System.out.println("Esa hora no es valida.");
						}
					}
					
				}else if(resp=='C'||resp=='c') {
					
					boolean esta =false;
					System.out.println("Cuantas personas son?: ");
					int cuantos=Consola.leeInt();
					while(horaVisita.get(v.getHoraVisita())+cuantos>50) {
						System.out.println("Son demasiadas prueba otra vez : ");
						cuantos=Consola.leeInt();

					}
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void crearInforme() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("report"+diaVisita[0]+"_"+diaVisita[1]+".txt"));
		for (Visita v : visitas) {
			bw.write(v.getNombre()+"\t"+v.getHoraVisita()+"\t"+v.getCantPer()+"\n");
		}
	}
	
	public HashMap<HoraMin,Integer> mapaLibres() throws IOException{
		HashMap<HoraMin,Integer> hm = new HashMap<HoraMin, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("streams.coleccionesStream/tiempos_de_visita.txt"));
		String linea = br.readLine();
		int lHora,lMinuto;
		HoraMin hM;
		while(linea!=null) {
			lHora=Integer.parseInt(linea.substring(0,linea.indexOf("\t")));
			linea=linea.substring(0,linea.indexOf("\t")+1);
			lMinuto=Integer.parseInt(linea.substring(0,linea.indexOf("\t")));
			hM = new HoraMin(lHora, lMinuto);
			hm.put(hM, 0);
			linea = br.readLine();
		}
		br.close();
		for (Visita v : visitas) {
			hm.put(v.getHoraVisita(),hm.get(v.getHoraVisita())+v.getCantPer());
		}
		
		return hm;
	}
	
	public HoraMin VisitaMasCercano(int hora, int minutos) throws IOException {		
		BufferedReader br = new BufferedReader(new FileReader("streams.coleccionesStream/tiempos_de_visita.txt"));
		String linea = br.readLine();
		int lHora,lMinuto,gminuto,ghora,difHoras=Integer.MAX_VALUE,difMinutos=Integer.MAX_VALUE;
		HoraMin hM=null;
		while(linea!=null) {
			lHora=Integer.parseInt(linea.substring(0,linea.indexOf("\t")));
			linea=linea.substring(0,linea.indexOf("\t")+1);
			lMinuto=Integer.parseInt(linea.substring(0,linea.indexOf("\t")));
			hM = new HoraMin(lHora, lMinuto);
			if(horaVisita.get(hM)<50) {
				if(lHora-hora<difHoras) {
					difHoras=lHora-hora;
					ghora=lHora;
					if(lMinuto-minutos<difMinutos) {
						difMinutos=lMinuto-minutos;
						gminuto=lMinuto;
					}
				}
			}
			linea = br.readLine();
		}
		br.close();
		return hM;
	}
	
	
	public int borrarVisitasPasadas() throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("visitas_pasadas_"+Calendar.HOUR_OF_DAY+"_"+Calendar.MINUTE+".bin",true));
		Visita v =null;
		int cont=0,lHora,lMinuto;
		int hora,minutos;
		HoraMin hM=null;
		hora=Calendar.HOUR_OF_DAY;
		minutos=Calendar.MINUTE;
		Iterator<Visita> it = visitas.iterator();
		while(it.hasNext()) {
			v=it.next();
			if(hora>v.getHoraVisita().getHora()) {
				visitas.remove(v);
				cont++;
				dos.writeShort(horaVisita.get(v.getHoraVisita()));
			}
			if(hora==v.getHoraVisita().getHora()&&minutos>v.getHoraVisita().getMinuto()) {
				visitas.remove(v);
				cont++;
				dos.writeShort(horaVisita.get(v.getHoraVisita()));
			}
		}
		
		return cont;
	}
	
}