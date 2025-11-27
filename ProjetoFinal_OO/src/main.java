
import entidades.*;
import excecoes.*;
import servicos.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("=== SISTEMA DE TESTES DO RIDE SHARING ===\n");

            /* ------------------------------------------------------
               1. Criar lista de motoristas cadastrados no sistema
            -------------------------------------------------------- */

            List<Motorista> motoristas = new ArrayList<>();
            Veiculo v1 = new Veiculo("ABC-1234","Honda Civic", "Branco", 2020, 
                    new CategoriaComum());
            Motorista m1 = new Motorista("Carlos Silva", "111.222.333-44", "senha123",
                    "carlos@email.com", "61999990000", "5421345", v1);
            m1.setStatus(StatusMotorista.ONLINE);

            motoristas.add(m1);

            Veiculo v2 = new Veiculo("XYZ-0000","Corolla", "Preto", 2023, 
                    new CategoriaLuxo());
            Motorista m2 = new Motorista("Marcos Sena", "999.333.111-22", "senha456",
                    "marcos@email.com", "61999995555", "1234567", v2);
            m2.setStatus(StatusMotorista.ONLINE);

            motoristas.add(m2);


            System.out.println("[OK] Motoristas cadastrados com sucesso!\n");


            /* ------------------------------------------------------
               2. Criar passageiro
            -------------------------------------------------------- */

            Passageiro passageiro = new Passageiro("Ana Costa", "555.444.333-22",
                    "ana@email.com", "61988887777", "senha123");

            System.out.println("[OK] Passageiro cadastrado: " + passageiro.getNome() + "\n");


            /* ------------------------------------------------------
               3. Adicionar formas de pagamento
            -------------------------------------------------------- */

            MetodoPagamento pix = new Pix("ana@pix.com");
            MetodoPagamento credito = new CartaoCredito("5555 4444 3333 2222", "Ana Costa", "08/29", "123");
            MetodoPagamento dinheiro = new Dinheiro();  

            passageiro.adicionarMetodoPagamento(pix);
            passageiro.adicionarMetodoPagamento(credito);
            passageiro.adicionarMetodoPagamento(dinheiro);

            System.out.println("[OK] Passageiro adicionou métodos de pagamento.\n");


            /* ------------------------------------------------------
               4. Solicitar corrida
            -------------------------------------------------------- */

            Corrida corrida = new Corrida(
                    passageiro,
                    "UnB ICC Sul",
                    "Shopping Iguatemi",
                    12.5,
                    new CategoriaLuxo()
            );

            System.out.println("Corrida solicitada: " + corrida);


            /* ------------------------------------------------------
               5. Atribuir motorista AUTOMATICAMENTE dentro da classe Corrida
            -------------------------------------------------------- */

            corrida.atribuirMotorista(motoristas);

            System.out.println("[OK] Motorista atribuído automaticamente: "
                    + corrida.getMotorista().getNome()
                    + " | Veículo: " + corrida.getMotorista().getVeiculo().getModelo()
                    + " | Placa: "
                    + corrida.getMotorista().getVeiculo().getPlaca()
                    + "\n");


            /* ------------------------------------------------------
               6. Iniciar a viagem
            -------------------------------------------------------- */

            corrida.iniciarCorrida();
            System.out.println("[OK] Viagem iniciada!\n");


            /* ------------------------------------------------------
               7. Finalizar a viagem
            -------------------------------------------------------- */

            corrida.finalizarCorrida();
            System.out.println("[OK] Viagem finalizada!\n");


            /* ------------------------------------------------------
               8. Processar pagamento
            -------------------------------------------------------- */

            corrida.setMetodoPagamento(dinheiro);

            try {
                corrida.processarPagamento();
                System.out.println("[OK] Pagamento efetuado com sucesso!\n");
            }
            catch (SaldoInsuficienteException | PagamentoRecusadoException e) {
                System.out.println("[ERRO] Falha no pagamento: " + e.getMessage());
                System.out.println("Status da corrida: " + corrida.getStatus());
            }


            /* ------------------------------------------------------
               9. Avaliações
            -------------------------------------------------------- */

            passageiro.avaliarMotorista(corrida.getMotorista(), 5);
            corrida.getMotorista().avaliarPassageiro(passageiro, 5);

            System.out.println("[OK] Avaliações registradas.\n");


            /* ------------------------------------------------------
               10. Exibir estados finais
            -------------------------------------------------------- */

            System.out.println("=== ESTADO FINAL ===");
            System.out.println("Status da Corrida: " + corrida.getStatus());
            System.out.println("Média do Motorista: " + corrida.getMotorista().getMediaAvaliacao());
            System.out.println("Média do Passageiro: " + passageiro.getMediaAvaliacao());

            System.out.println("\n=== FIM DO TESTE ===");


        } catch (Exception e) {
            System.out.println("Erro no fluxo principal: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
