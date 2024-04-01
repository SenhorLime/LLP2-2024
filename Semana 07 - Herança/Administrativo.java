public class Administrativo extends Funcionario {
    private String setor;

    public Administrativo(String[] dados) {
        super(dados);
        this.setor = dados[6];
    }

    public Administrativo(String nome, String email, String senha, String cargo, String setor) {
        super(nome, email, senha, cargo);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public String toString() {
        return super.toString() + ',' + getSetor();
    }
}
