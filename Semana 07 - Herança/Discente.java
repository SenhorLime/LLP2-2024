public class Discente extends Funcionario {
    private String turma;

    public Discente(String[] dados) {
        super(dados);
        this.turma = dados[5];
    }

    public Discente(String nome, String email, String senha, String cargo, String turma) {
        super(nome, email, senha, cargo);
        this.turma = turma;
    }

    public String getTurma() {
        return turma;
    }

    @Override
    public String toString() {
        return super.toString() + ',' + getTurma();
    }
}
