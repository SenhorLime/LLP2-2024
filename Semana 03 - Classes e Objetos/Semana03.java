import java.util.List;

public class Semana03 {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno(20202629, "Ana Beatriz Costa Viana", 4, 1, 2629);
        Aluno aluno2 = new Aluno(20201532, "Ana Laura Gonçalves de Paula", 1, 1, 1532);
        Aluno aluno3 = new Aluno(20200778, "Daniel Martins de Abreu", 2, 1, 1778);

        Aluno alunos[] = { aluno1, aluno2, aluno3 };

        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        List<String> alunosList = FileManager.loadFile("CSVs/pessoal.csv");

        for (String aluno : alunosList) {
            System.out.println(aluno);
        }
    }
}