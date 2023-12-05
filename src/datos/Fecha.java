package datos;

public class Fecha {
	private int dia;
	private int mes;
	private int anio;
	public Fecha(String s) {
		//buscamos la primera ocurrencia
		int pos1 = s.indexOf('/');
		//buscamos la ultima ocurrencia
		int pos2 = s.lastIndexOf('/');
		//Procesamos el dia
		String sDia = s.substring(0,pos1);
		dia = Integer.parseInt(sDia);
		//Procesamos el mes
		String sMes = s.substring(pos1+1,pos2);
		mes = Integer.parseInt(sMes);
		//Procesamos el anio
		String sAnio=s.substring(pos2+1);
		anio = Integer.parseInt(sAnio);
	}
	public Fecha() {
		this.dia = 1;
		this.mes = 1;
		this.anio = 2000;
		
	}
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	@Override
	public String toString() {
		//return "Fecha [dia=" + dia + ", mes=" + mes + ", anio=" + anio + "]";
		return dia+"/"+mes+"/"+anio;
	}
	
	
	
	
	

}
