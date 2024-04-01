public class Funcionario {
    protected String nome;
    protected String email;
    protected String senha;
    protected String cargo;

    public Funcionario (String[] dados) {
        this.nome = dados[1];
        this.email = dados[2];
        this.senha = dados[3];
        this.cargo = dados[4];
    }

    public Funcionario(String nome, String email, String senha, String cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nome + ',' + email + ',' + this.escondeSenha() + ',' + cargo;
    }

    private String escondeSenha() {
        String senhaEscondida = "";

        for (char caracter : senha.toCharArray()) {
            senhaEscondida += caracter;
        }

        return senhaEscondida;
    }
}
