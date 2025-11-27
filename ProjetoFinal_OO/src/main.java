
import entidades.CategoriaComum;
import entidades.Corrida;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.StatusMotorista;
import entidades.Veiculo;
import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.PagamentoRecusadoException;
import excecoes.PassageiroPendenteException;
import excecoes.SaldoInsuficienteException;
import servicos.CartaoCredito;
import servicos.MetodoPagamento;
import servicos.Pix;


public class main {
    public static void main(String[] args) {
        System.out.println("Projeto Final de Orientação a Objetos");
         // criar passageiros
        Passageiro passageiro = new Passageiro("Ana Silva", "11122233344", "ana@ex.com", "8199999", "senha");
        // adicionar uma forma de pagamento (cartão que termina com 1 -> normalmente aprovado)
        passageiro.adicionarMetodoPagamento(new CartaoCredito("1234567890123451", "VISA", "12/27", "123"));
        // adicionar uma forma de pagamento PIX
        passageiro.adicionarMetodoPagamento(new Pix("ana@pix"));


        
        Veiculo veiculoAtivo = new Veiculo("ABC1D23", "Fiat Uno", "Prata", 2018, new CategoriaComum());
        Motorista motorista = new Motorista("Carlos", "22233344455", "senha2", "car@ex.com", "8198888", "CNH-123", veiculoAtivo, StatusMotorista.ONLINE);
        motorista.setStatus(StatusMotorista.ONLINE);

        try {
            passageiro.checarPodeSolicitarCorrida();;
            Corrida corrida = new Corrida(passageiro, "Avenida X", "Rua Y", 10.0, new CategoriaComum());
            System.out.println("Corrida criada: " + corrida);

            // atribuir motorista
            corrida.atribuirMotorista(motorista);
            System.out.println("Motorista atribuído: " + motorista.getNome());

            // inicía viagem
            corrida.iniciarCorrida();
            System.out.println("Viagem iniciada: " + corrida);

            // finaliza viagem
            corrida.finalizarCorrida();
            System.out.println("Viagem finalizada: " + corrida);

            // escolha de pagamento pelo passageiro: vamos usar a primeira forma cadastrada
            MetodoPagamento formaEscolhida = passageiro.getMetodoPagamento(0);
            corrida.setMetodoPagamento(formaEscolhida);

            try {
                corrida.processarPagamento();
                System.out.println("Pagamento efetuado com sucesso. Status: " + corrida.getStatus());
            } catch (SaldoInsuficienteException e) {
                System.err.println("Saldo insuficiente: " + e.getMessage());
            } catch (PagamentoRecusadoException e) {
                System.err.println("Pagamento recusado: " + e.getMessage());
            } catch (EstadoInvalidoDaCorridaException e) {
                System.err.println("Estado inválido para pagamento: " + e.getMessage());
            }

            // avaliações (exemplo)
            passageiro.avaliarMotorista(motorista, 5);
            motorista.avaliarPassageiro(passageiro, 5);

            System.out.println("Média motorista: " + motorista.getMediaAvaliacao());
            System.out.println("Média passageiro: " + passageiro.getMediaAvaliacao());

        } catch (PassageiroPendenteException e) {
            System.err.println("Não pode solicitar corrida: " + e.getMessage());
        } catch (EstadoInvalidoDaCorridaException e) {
            System.err.println("Estado inválido: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
