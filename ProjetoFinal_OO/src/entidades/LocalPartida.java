package entidades;

import java.util.Scanner;

public class LocalPartida extends Local{
Scanner sc = new Scanner(System.in);

	public LocalPartida(Double latitude, Double longitude, String endereco) {
		super(latitude, longitude, endereco);

	}
public void setLatitude() {}

public void setLongitude() {}
public void setEndereco() {}

public Double getLatitude() {return latitude;}

public Double getLongitude() {return longitude;}
public String getEndereco() {return endereco;}

		
	}
	
	
