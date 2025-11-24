package servicos;

import java.util.Date;

public class CartaoCredito implements MetodoPagamento{
	private String numeroCartao, nome, titular, cvv;
	Date validade;
	
	
	
	
	public CartaoCredito(String numeroCartao, String nome, String titular, String cvv, Date validade) {
		this.setNumeroCartao(numeroCartao);
		this.nome = nome;
		this.titular = titular;
		this.cvv = cvv;
		this.validade = validade;
		}
	
	
	public String getNome() {return nome;}
	
	public String getTitular() {return titular;}
	
	public String getCvv() {return cvv;}
	
	public Date getValidade() {return validade;}
	
	
	public void setNome() {}
	public void setTitular() {}
	public void setCvv() {}
	public void setValidade() {}
	
	
	
	@Override
	public boolean processarPagamento(double valor) {
		return true;
	}

	public String getNumeroCartao() {return numeroCartao;}
	public void setNumeroCartao(String numeroCartao) {this.numeroCartao = numeroCartao;}
}
