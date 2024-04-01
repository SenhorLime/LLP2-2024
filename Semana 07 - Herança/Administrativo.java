public class Administrativo extends Funcionario {
    private int setor;

    public Administrativo(String nome, String email, String senha, String cargo, int setor) {
        super(nome, email, senha, cargo);
        this.setor = setor;
    }

    public int getSetor() {
        return setor;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + ',' + getSetor();
    }
}
