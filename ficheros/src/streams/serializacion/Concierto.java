package streams.serializacion;

import java.io.Serializable;

public class Concierto implements Serializable{
	private Artista artista;
	private int aforo;
	
	public Concierto(Artista ar,int af) {
		artista=ar;
		aforo=af;
	}
	
	public int getAforo() {
		return aforo;
	}
	
	public Artista getArtista() {
		return artista;
	}
	
}
