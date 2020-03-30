package streams.streamObject2;

import java.io.Serializable;

public class TelefonoMovil  implements Serializable {
	
	private String numero;
	private double saldo;
	
	public TelefonoMovil(String n,double s) {
		saldo=s;
		numero=n;
	}
	
	public void ver() {
		System.out.println("El saldo es de:"+saldo+" y el numero de telefono es: "+numero);
	}
	
	public void cargar(int s) {
		saldo+=s;
	}
	
	public void llamar(int min) {
		saldo=(saldo-(min*2));
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "TelefonoMovil [numero=" + numero + ", saldo=" + saldo + "]";
	}
	
}
