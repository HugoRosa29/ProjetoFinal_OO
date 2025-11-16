package entidades;

public class Motorista extends Usuario {
	
	private int cnh;
	private StatusMotorista status;
	private Veiculo veiculo;
	
	public Motorista(String nome, String cpf, String senha, String email, int cnh, Veiculo veiculo, StatusMotorista status) {
		super(nome, cpf, senha, email);
		this.cnh = cnh;
		this.veiculo = veiculo;
		this.status = status;
	}
	
	public void ficarOnline() {
        this.status = StatusMotorista.ONLINE;
        System.out.println("Motorista " + getNome() + " está ONLINE.");
    }
	
	public void ficarOffline() {
        this.status = StatusMotorista.OFFLINE;
    }
	
	public StatusMotorista getStatus() {return status;}
    public Veiculo getVeiculo() {return veiculo;}
    public void setStatus(StatusMotorista status) {this.status = status;}
	
}
