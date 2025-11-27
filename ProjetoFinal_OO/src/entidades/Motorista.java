package entidades;

import excecoes.MotoristaInvalidoException;

public class Motorista extends Usuario {

    private String cnh;
    private StatusMotorista status;
    private Veiculo veiculo;

    public Motorista(String nome, String cpf, String senha, String email, String telefone, String cnh, Veiculo veiculo) {
        super(nome, cpf, senha, email, telefone);
        this.cnh = cnh;
        this.veiculo = veiculo;
    }

    public void ficarOnline() throws MotoristaInvalidoException {
        if (veiculo == null) {
            throw new MotoristaInvalidoException("Motorista precisa cadastrar um veículo antes de ficar online.");
        } else {
            this.status = StatusMotorista.ONLINE;
            System.out.println("Motorista " + getNome() + " está ONLINE.");
        }
    }

    public void ficarOffline() {
        this.status = StatusMotorista.OFFLINE;
    }

    public void avaliarPassageiro(Passageiro passageiro, int estrelas) {
        passageiro.adicionarAvaliacao(estrelas);
    }

    public StatusMotorista getStatus() {return status;}
    public Veiculo getVeiculo() {return veiculo;}
    public void setStatus(StatusMotorista status) {this.status = status;}
    public String getCnh() {return cnh;}
    public void setCnh(String cnh) {this.cnh = cnh;}

}
