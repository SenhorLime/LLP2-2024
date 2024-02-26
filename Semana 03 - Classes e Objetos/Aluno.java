class Aluno {
  private int matricula, grupo, subTurma, senha;
  private String nome;

  public Aluno(int matricula, String nome, int grupo, int subTurma, int senha) {
    this.matricula = matricula;
    this.nome = nome;
    this.grupo = grupo;
    this.subTurma = subTurma;
    this.senha = senha;
  }

  private String hidePassword(int userPassword) {
    String passwordToInt = Integer.toString(userPassword);
    String hiddenPassword = "";

    for (int i = 0; i < passwordToInt.length(); i++) {
      hiddenPassword += "*";
    }

    return hiddenPassword;
  }

  @Override
  public String toString() {
    return "Aluno [Matricula = " + matricula + ", Nome = " + nome + ", Grupo = "
        + grupo + ", Sub-Turma = " + subTurma + ", Senha = " + hidePassword(this.senha) + "]";
  }
}