package servicos;

import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PIX implements MetodoPagamento{

	@Override
	public boolean processarPagamento(double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
		// TODO Auto-generated method stub
		return false;
	}

}
