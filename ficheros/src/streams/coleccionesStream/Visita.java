package streams.coleccionesStream;

import java.io.Serializable;

public class Visita  implements Serializable{
	private String nombre;
	private int cantPer;
	private HoraMin horaVisita;
	
	
	public Visita(String n,int cantper,HoraMin h) {
		nombre=n;
		cantPer=cantper;
		horaVisita=h;
	}

	public HoraMin getHoraVisita() {
		return horaVisita;
	}
	public int getCantPer() {
		return cantPer;
	}
	public String getNombre() {
		return nombre;
	}
	public void setHoraVisita(HoraMin horaVisita) {
		this.horaVisita = horaVisita;
	}
	public void setCantPer(int cantPer) {
		this.cantPer = cantPer;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visita other = (Visita) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visita [nombre=" + nombre + ", cantPer=" + cantPer + ", horaVisita=" + horaVisita.getHora()+":"+horaVisita.getMinuto() + "]";
	}
	
	
	
	
}
