package entidades;

import java.util.ArrayList;
import java.util.List;
import servicos.MetodoPagamento;

public class Passageiro extends Usuario{
	private List<MetodoPagamento> metodosPagamento = new ArrayList<>();

	public Passageiro(String nome, String cpf, String senha, String email, String telefone) {
		super(nome, cpf, senha, email, telefone);
	
	}
	
	public void adicionarMetodoPagamento(MetodoPagamento metodo) {
        this.metodosPagamento.add(metodo);
    }
	
	public MetodoPagamento getMetodoPagamento(int index) {
		return metodosPagamento.get(index);
		}
	
}
