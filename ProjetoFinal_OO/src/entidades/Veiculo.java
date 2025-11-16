package entidades;

public class Veiculo {
	private String placa, 
					modelo,
					cor;
	private int ano;
	
	public Veiculo(String placa, String modelo, String cor, int ano) {
		this.placa = placa;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
	}
}
