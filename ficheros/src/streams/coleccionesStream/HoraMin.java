package streams.coleccionesStream;

import java.io.Serializable;

public class HoraMin   implements Serializable{
	private int minuto,hora;
	
	public HoraMin(int hora,int min) {

		if(hora<24&&hora>=0) {
			this.hora=hora;	
		}else {
			this.hora=0;
		}
		
		if(min>0) {
			if(min<60) {
				minuto=min%60;
				hora+=(min/60);
			}else {
				minuto=min;
			}
		}else {
			minuto=0;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hora;
		result = prime * result + minuto;
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
		HoraMin other = (HoraMin) obj;
		if (hora != other.hora)
			return false;
		if (minuto != other.minuto)
			return false;
		return true;
	}
	
	public int getHora() {
		return hora;
	}
	public int getMinuto() {
		return minuto;
	}
	
	
}