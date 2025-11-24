package servicos;

public class CartaoCredito implements MetodoPagamento{
	private String numeroCartao;
	
	public CartaoCredito(String numeroCartao) {
		this.setNumeroCartao(numeroCartao);
	}
	
	@Override
	public boolean processarPagamento(double valor) {
		return true;
	}

	public String getNumeroCartao() {return numeroCartao;}
	public void setNumeroCartao(String numeroCartao) {this.numeroCartao = numeroCartao;}
}
