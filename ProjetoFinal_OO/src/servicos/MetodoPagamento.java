package servicos;

public interface MetodoPagamento {
	public default boolean processarPagamento(double saldo) {
		return false;
	}
}
