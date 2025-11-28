import entidades.*;
import excecoes.*;
import servicos.CartaoCredito;
import servicos.MetodoPagamento;

import java.util.*;

public class MainInterativa {

    private static Scanner sc = new Scanner(System.in);

    private static List<Passageiro> passageiros = new ArrayList<>();
    private static List<Motorista> motoristas = new ArrayList<>();
    private static List<Corrida> corridas = new ArrayList<>();

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {

            mostrarMenu();

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }

            try {
                switch (opcao) {
                    case 1 -> cadastrarPassageiro();
                    case 2 -> cadastrarMotorista();
                    case 3 -> cadastrarVeiculoMotorista();
                    case 4 -> cadastrarMetodoPagamentoPassageiro();
                    case 5 -> alterarStatusMotorista();
                    case 6 -> solicitarCorrida();
                    case 7 -> iniciarCorrida();
                    case 8 -> finalizarCorrida();
                    case 9 -> cancelarCorrida();
                    case 10 -> processarPagamento();
                    case 11 -> listarPassageiros();
                    case 12 -> listarMotoristas();
                    case 13 -> listarCorridas();
                    case 14 -> avaliarMotorista();
                    case 15 -> avaliarPassageiro();
                    case 0 -> System.out.println("Encerrando programa...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n❌ ERRO: " + e.getMessage());
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }

    /* =======================================================
                       MENU PRINCIPAL
    ========================================================== */

    private static void mostrarMenu() {
        System.out.println("\n====== SISTEMA DE RIDE SHARING ======");
        System.out.println("1. Cadastrar Passageiro");
        System.out.println("2. Cadastrar Motorista");
        System.out.println("3. Cadastrar Veículo ao Motorista");
        System.out.println("4. Adicionar Método de Pagamento ao Passageiro");
        System.out.println("5. Alterar status do Motorista");
        System.out.println("6. Solicitar Corrida");
        System.out.println("7. Iniciar Corrida");
        System.out.println("8. Finalizar Corrida");
        System.out.println("9. Cancelar Corrida");
        System.out.println("10. Processar Pagamento");
        System.out.println("11. Listar Passageiros");
        System.out.println("12. Listar Motoristas");
        System.out.println("13. Listar Corridas");
        System.out.println("14. Avaliar Motorista");
        System.out.println("15. Avaliar Passageiro");
        System.out.println("0. Sair");
        System.out.print("Escolha a opção: ");
    }


    /* =======================================================
                       CADASTROS BÁSICOS
    ========================================================== */

    private static void cadastrarPassageiro() {
        System.out.println("\n=== Cadastro de Passageiro ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        

        Passageiro p = new Passageiro(nome, cpf, email, telefone, senha);
        passageiros.add(p);

        System.out.println("Passageiro cadastrado com sucesso!");
    }

    private static void cadastrarMotorista() {
        System.out.println("\n=== Cadastro de Motorista ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("CNH: ");
        String cnh = sc.nextLine();
        

        Motorista m = new Motorista(nome, cpf, email, telefone, senha, cnh, null);

        motoristas.add(m);
        System.out.println("Motorista cadastrado com sucesso!");
    }

    private static void cadastrarMetodoPagamentoPassageiro() {
        System.out.println("\n=== Cadastro de Método de Pagamento para Passageiro ===");

        Passageiro p = escolherPassageiro();
        if (p == null) return;

        System.out.println("Escolha o método de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Pix");
        System.out.println("3. Dinheiro");
        int escolha = Integer.parseInt(sc.nextLine());
        
        switch (escolha) {
            case 1:
                System.out.println("Digite o número do cartão de crédito:");
                String numeroCartao = sc.nextLine();
                System.out.println("Digite a bandeira do cartão:");
                String bandeira = sc.nextLine();
                System.out.println("Digite a validade do cartão (MM/AA):");
                String validade = sc.nextLine();
                System.out.println("Digite o CVV do cartão:");
                String cvv = sc.nextLine();
                MetodoPagamento cartaoCredito = new CartaoCredito(numeroCartao, bandeira, cvv, validade);
                p.adicionarMetodoPagamento(cartaoCredito);
                break;
            case 2:
                System.out.println("Digite a chave Pix:");
                String chavePix = sc.nextLine();
                MetodoPagamento pix = new servicos.Pix(chavePix);
                p.adicionarMetodoPagamento(pix);
                break;
            case 3:
                MetodoPagamento dinheiro = new servicos.Dinheiro();
                p.adicionarMetodoPagamento(dinheiro);
                break;
            default:
                break;
        }

        System.out.println("Método de pagamento adicionado ao passageiro!");
    }

    private static void cadastrarVeiculoMotorista() {
        System.out.println("\n=== Cadastro de Veículo a Motorista ===");

        Motorista m = escolherMotorista();
        if (m == null) return;

        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Cor: ");
        String cor = sc.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(sc.nextLine());

        System.out.println("Categoria do veículo:");
        System.out.println("1. Comum");
        System.out.println("2. Luxo");
        System.out.print("Escolha: ");
        int cat = Integer.parseInt(sc.nextLine());

        Categoria categoria = (cat == 1) ? new CategoriaComum() : new CategoriaLuxo();

        Veiculo v = new Veiculo(placa, modelo, cor, ano, categoria);
        m.setVeiculo(v);

        System.out.println("Veículo cadastrado e atribuído ao motorista!");
    }

    private static void alterarStatusMotorista() {
        System.out.println("\n=== Alterar Status do Motorista ===");

        Motorista m = escolherMotorista();
        if (m == null) return;

        System.out.println("1. ONLINE");
        System.out.println("2. OFFLINE");
        System.out.println("3. EM_CORRIDA");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());
        StatusMotorista s =
                (op == 1 ? StatusMotorista.ONLINE
                        : op == 2 ? StatusMotorista.OFFLINE
                        : StatusMotorista.EM_CORRIDA);

        m.setStatus(s);
        System.out.println("Status alterado!");
    }


    /* =======================================================
                      SOLICITAR / GERENCIAR CORRIDAS
    ========================================================== */

    private static void solicitarCorrida() throws Exception {
        System.out.println("\n=== Solicitar Corrida ===");

        Passageiro p = escolherPassageiro();
        if (p == null) return;

        System.out.print("Origem: ");
        String origem = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Distância (km): ");
        double km = Double.parseDouble(sc.nextLine());

        System.out.println("Categoria:");
        System.out.println("1. Comum");
        System.out.println("2. Luxo");
        int c = Integer.parseInt(sc.nextLine());
        Categoria categoria = (c == 1 ? new CategoriaComum() : new CategoriaLuxo());

        Corrida corrida = new Corrida(p, origem, destino, km, categoria);

        corrida.atribuirMotorista(motoristas);

        corridas.add(corrida);

        System.out.println("\n✔ Corrida solicitada com sucesso!");
        System.out.println("Motorista atribuído: " + corrida.getMotorista().getNome());
        System.out.println("Valor estimado: R$ " + corrida.getValorEstimado());
    }

    private static void iniciarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.iniciarCorrida();
        System.out.println("✔ Corrida agora está EM ANDAMENTO!");
    }

    private static void finalizarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.finalizarCorrida();
        System.out.println("✔ Corrida finalizada!");
    }

    private static void cancelarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.cancelar();
        System.out.println("✔ Corrida cancelada com sucesso!");
    }

    private static void processarPagamento() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        Passageiro p = c.getPassageiro();

        System.out.println("\nSelecione método de pagamento:");
        for (int i = 0; i < p.getMetodosPagamento().size(); i++) {
            System.out.println(i + " - " + p.getMetodosPagamento().get(i));
        }

        int mp = Integer.parseInt(sc.nextLine());
        MetodoPagamento forma = p.getMetodosPagamento().get(mp);

        c.setMetodoPagamento(forma);

        try {
            c.processarPagamento();
            System.out.println("✔ Pagamento realizado com sucesso!");
        } catch (SaldoInsuficienteException | PagamentoRecusadoException e) {
            System.out.println("❌ Falha no pagamento: " + e.getMessage());
        }
    }


    /* =======================================================
                      AVALIAÇÕES
    ========================================================== */

    private static void avaliarMotorista() {
        Corrida c = escolherCorrida();
        if (c == null) return;

        Motorista m = c.getMotorista();
        Passageiro p = c.getPassageiro();

        System.out.print("Nota (1 a 5): ");
        int nota = Integer.parseInt(sc.nextLine());

        p.avaliarMotorista(m, nota);
        System.out.println("✔ Motorista avaliado!");
    }

    private static void avaliarPassageiro() {
        Corrida c = escolherCorrida();
        if (c == null) return;

        Motorista m = c.getMotorista();
        Passageiro p = c.getPassageiro();

        System.out.print("Nota (1 a 5): ");
        int nota = Integer.parseInt(sc.nextLine());

        m.avaliarPassageiro(p, nota);
        System.out.println("✔ Passageiro avaliado!");
    }


    /* =======================================================
                      MÉTODOS DE APOIO
    ========================================================== */

    private static Passageiro escolherPassageiro() {
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado!");
            return null;
        }

        System.out.println("\nPassageiros:");
        for (int i = 0; i < passageiros.size(); i++) {
            System.out.println(i + " - " + passageiros.get(i).getNome());
        }

        System.out.print("Escolha: ");
        return passageiros.get(Integer.parseInt(sc.nextLine()));
    }

    private static Motorista escolherMotorista() {
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista cadastrado!");
            return null;
        }

        System.out.println("\nMotoristas:");
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println(i + " - " + motoristas.get(i).getNome() + " (" + motoristas.get(i).getStatus() + ")");
        }

        System.out.print("Escolha: ");
        return motoristas.get(Integer.parseInt(sc.nextLine()));
    }

    private static Corrida escolherCorrida() {
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida cadastrada!");
            return null;
        }

        System.out.println("\nCorridas:");
        for (int i = 0; i < corridas.size(); i++) {
            Corrida c = corridas.get(i);
            System.out.println(i + " - " + c);
        }

        System.out.print("Escolha: ");
        return corridas.get(Integer.parseInt(sc.nextLine()));
    }

    private static void listarPassageiros() {
        System.out.println("\n=== Passageiros ===");
        for (Passageiro p : passageiros) {
            System.out.println(p.getNome());
        }
    }

    private static void listarMotoristas() {
        System.out.println("\n=== Motoristas ===");
        for (Motorista m : motoristas) {
            System.out.println(m.getNome() + " | Status: " + m.getStatus());
        }
    }

    private static void listarCorridas() {
        System.out.println("\n=== Corridas ===");
        corridas.forEach(System.out::println);
    }
}
    
