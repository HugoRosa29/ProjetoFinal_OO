package entidades;
import servicos.MetodoPagamento;

public class Corrida {
	private String id;
    private Passageiro passageiro;
    private Motorista motorista;
    private StatusCorrida status;
    private double precoFinal;
    private MetodoPagamento metodoPagamento;
    private Categoria categoria;
}
