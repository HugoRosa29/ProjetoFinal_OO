package servicos;

import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public interface MetodoPagamento {
    boolean processarPagamento(double valor) throws PagamentoRecusadoException, SaldoInsuficienteException;
}
