package Source;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Source.Classes.Filme;
import Source.Classes.Gravacao;
import Source.Classes.Software;

// AVISO IMPORTANTE: Eu estou mexendo nessa atividade as 2h20 então se tem algo estranho aqui é porque o sono
// está batendo forte.
//
// Adendo: A atividade estava incompleta na hora então eu estou terminando ela enquanto escreve os comentarios

public class Main extends JFrame {
    // Variaveis de inputs
    private JTextField fieldTitulo, fieldMidia, fieldDiretor, fieldDataLancamento, fieldDistribuidor, fieldVersion,
            fieldPlataforma;

    // Variveis dos botões de seleção
    private JRadioButton radioFilme, radioSoftware;

    // Variaveis dos botões
    private JButton buttonAdicionar, buttonLimpar, buttonVisualizar;

    // Vetor polimorfico que armazena mais de um tipo de classe
    // Ele funciona de maneira parecida com o template do C++, porém suporta somente
    // elementos
    // de uma classe pai e de seus filhos
    private ArrayList<Gravacao> itemArrayList;

    // Varivel de contador para indexação dos dados do CSV
    private int counter;

    // Contrutor da classe
    Main() {
        // Chamada do contrutor da classe pai (ela passa o titulo da janela)
        super("Livraria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());

        itemArrayList = new ArrayList<Gravacao>(); // Inicializa o vetor
        counter = 0; // Inicializa o contador

        initComponents(); // Função chamada para inicializar todos os componentes da janela
        eventListeners(); // Função que chama os escutadores de evento
    }

    // Main: função principal da classe
    public static void main(String[] args) {
        new Main();
    }

    // Função que é usada para inicializar todos os elementos da janela
    private void initComponents() {
        // AVISO: Vou explicar somente o que estou fazendo na primeira inicialização de
        // cada tipo de componente
        // Acho que não é necessario explicar 5 vezes como configurar o mesmo componente
        //
        //
        // Inicilização dos rotulos e inputs e de texto
        JLabel labelTitulo = new JLabel("Titulo:"); // Define e inicializa o que vai estar escrito no rotulo
        fieldTitulo = new JTextField(); // Inicializa o input de texto
        fieldTitulo.setColumns(20);
        fieldTitulo.setEnabled(false);

        JLabel labelMidia = new JLabel("Midia:");
        fieldMidia = new JTextField();
        fieldMidia.setColumns(20);
        fieldMidia.setEnabled(false);

        JLabel labelDiretor = new JLabel("Diretor:");
        fieldDiretor = new JTextField();
        fieldDiretor.setColumns(20);
        fieldDiretor.setEnabled(false);

        JLabel labelDataLancamento = new JLabel("Data Lancamento:");
        fieldDataLancamento = new JTextField();
        fieldDataLancamento.setColumns(20);
        fieldDataLancamento.setEnabled(false);

        JLabel labelDistribuidor = new JLabel("Distribuidor:");
        fieldDistribuidor = new JTextField();
        fieldDistribuidor.setColumns(20);
        fieldDistribuidor.setEnabled(false);

        JLabel labelVersion = new JLabel("Version:");
        fieldVersion = new JTextField();
        fieldVersion.setColumns(20);
        fieldVersion.setEnabled(false);

        JLabel labelPlataforma = new JLabel("Plataforma:");
        fieldPlataforma = new JTextField();
        fieldPlataforma.setColumns(20);
        fieldPlataforma.setEnabled(false);

        // Inicialização dos botões
        buttonAdicionar = new JButton("Adicionar"); // Inicializa e define o que vai ser mostrado no botão
        buttonLimpar = new JButton("Limpar");
        buttonVisualizar = new JButton("Visualizar");

        // Inicialização dos botões de seleção
        radioFilme = new JRadioButton("Filme"); // Inicializa e define o que vai ser mostrado ao lado do botão
        radioSoftware = new JRadioButton("Software");

        // Criação do grupo de botões
        // Ele é necessario para que os botões de seleção apareçam na tela
        ButtonGroup radioButtonGroup = new ButtonGroup(); // Inicialização do grupo de botões
        // Adicionando os botões ao grupo
        radioButtonGroup.add(radioFilme);
        radioButtonGroup.add(radioSoftware);

        // Adiciona todos os elementos na janela
        add(labelTitulo);
        add(fieldTitulo);
        add(labelMidia);
        add(fieldMidia);
        add(radioFilme);
        add(radioSoftware);
        add(labelDiretor);
        add(fieldDiretor);
        add(labelDataLancamento);
        add(fieldDataLancamento);
        add(labelDistribuidor);
        add(fieldDistribuidor);
        add(labelVersion);
        add(fieldVersion);
        add(labelPlataforma);
        add(fieldPlataforma);
        add(buttonAdicionar);
        add(buttonVisualizar);
        add(buttonLimpar);
    }

    // Função criada para tratar todos os event listeners
    // Event Listener ou escutador de eventos são as funções chamadas quando algum
    // evento acontece
    // na janela, como um clique em botão ou fechar a janela
    private void eventListeners() {

        // Adicionando um event listener ao programa
        addWindowListener(new WindowAdapter() {

            // Função que sera chamada caso o usuario clique para fechar o programa
            @Override // Ela tem o Override porque precisa ser impletada pelo programador, por padrão
                      // ela não tem nada
                      // Actions Listeners são classes abstratas, por isso precisam ter seus metodos
                      // implementados

            public void windowClosing(WindowEvent e) {

                // Abre o arquivo no modo de escrita
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Source/Data/dados.csv", true))) {

                    // Percorre cada item do vetor polimorfico
                    for (Gravacao item : itemArrayList) {
                        writer.write(item.toString() + System.lineSeparator()); // Escreve o item no arquivo CSV
                    }
                } catch (IOException exception) {
                    // Mostra o erro e encerra o programa se não for possivel abrir o CSV
                    exception.printStackTrace();
                }
            }
        });

        // Escutador de eventos do botão de adicionar
        buttonAdicionar.addActionListener(new ActionListener() {

            // Função que será chamada somente se o usuario clicar no botão
            @Override // Ela tem o Override porque precisa ser impletada pelo programador, por padrão
                      // ela não tem nada
            public void actionPerformed(ActionEvent e) {

                if (radioFilme.isSelected()) { // Testa se o botão de seleção de filme foi clicado

                    // Adiciona um filme ao vetor polimorfico
                    itemArrayList
                            .add(new Filme(counter, (int) (Math.random() * 10), fieldTitulo.getText(),
                                    fieldMidia.getText(),
                                    fieldDiretor.getText(), LocalDate.now(), fieldDistribuidor.getText()));
                }

                if (radioSoftware.isSelected()) { // Testa se o botão de seleção de software foi clicado

                    // Adiciona um software ao vetor polimorfico
                    itemArrayList
                            .add(new Software(counter, (int) (Math.random() * 10), fieldTitulo.getText(),
                                    fieldMidia.getText(), Integer.parseInt(fieldVersion.getText()),
                                    fieldPlataforma.getText()));
                }
            }
        });

        // Escutador de eventos do botão de limpar
        buttonLimpar.addActionListener(new ActionListener() {

            // Função que será chamada somente se o usuario clicar no botão
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpa todos inputs de texto, deixando eles em branco

                fieldTitulo.setText("");
                fieldMidia.setText("");
                fieldDiretor.setText("");
                fieldDataLancamento.setText("");
                fieldDistribuidor.setText("");
                fieldVersion.setText("");
                fieldPlataforma.setText("");
            }

        });

        // Escutador de eventos do botão de visualizar
        buttonVisualizar.addActionListener(new ActionListener() {

            // Função que será chamada somente se o usuario clicar no botão
            @Override
            public void actionPerformed(ActionEvent e) {
                // AVISO EXTREMAMENTE IMPORTANTE: Eu comecei a trabalhar nessa parte as 3h da
                // madrugada movido a café não garanto nada na qualidade dos códigos abaixo e
                // também não sei se vou conseguir explicar bem o que estou fazendo, mas vou
                // tentar dar o meu melhor para ajudar

                // Vetor de Strings para guardar as informações do CSV
                ArrayList<String> csvArrayList = new ArrayList<String>();
                String line;

                // Abrir o arquivo para pegar os dados que já foram inseridos no arquivo CSV
                //
                // Abre o arquivo CSV no modo de leitura
                try (BufferedReader reader = new BufferedReader(new FileReader("Source/Data/dados.csv"))) {
                    reader.readLine(); // Pula a linha de rotulo do arquivo CSV

                    // Percorre todas as linhas do CSV e adiciona as linhas ao vetor
                    while ((line = reader.readLine()) != null) {
                        csvArrayList.add(line); // Adiciona a linha temporaria ao vetor
                    }
                } catch (IOException exception) {
                    // Mensagem caso dê algum erro ao abrir o arquivo
                    exception.printStackTrace();
                }

                // Define o modelo da tabela que vai exibir os dados
                DefaultTableModel tableModel = new DefaultTableModel();
                // Adiciona o rotulo das colunas na tabelas
                tableModel.addColumn("N° do Catálogo");
                tableModel.addColumn("N° de Cópias");
                tableModel.addColumn("Título");
                tableModel.addColumn("Editor");
                tableModel.addColumn("Mídia");
                tableModel.addColumn("Diretor");
                tableModel.addColumn("Data de Lançamento");
                tableModel.addColumn("Distribuidor");
                tableModel.addColumn("Versão");
                tableModel.addColumn("Plataforma");

                // Percorre todas as linhas do CSV e adiciona elas na tabela
                for (String csvData : csvArrayList) {

                    // Tem que ser colocado um "-1" no final do split
                    // para que o Java não ignore os espaços sem dados
                    // do CSV
                    String[] csvDataSplit = csvData.split(";", -1);

                    // Adicionando os dados na tabela
                    tableModel.addRow(csvDataSplit);
                }

                for (Gravacao item : itemArrayList) {

                    // Tem que ser colocado um "-1" no final do split
                    // para que o Java não ignore os espaços sem dados
                    // do toString()
                    String[] dataSplit = item.toString().split(";", -1);

                    // Adicionando os dados na tabela
                    tableModel.addRow(dataSplit);
                }

                // Cria uma tabela usando o modelo criado acima
                JTable tabelaDeDados = new JTable(tableModel);

                // Cria um nova janela para mostrar a tabela criada acima
                JFrame janelaDaTabela = new JFrame("Dados do Programa");
                janelaDaTabela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                janelaDaTabela.setSize(1000, 400); // Ajuste o tamanho conforme necessário
                janelaDaTabela.getContentPane().add(new JScrollPane(tabelaDeDados));
                janelaDaTabela.setVisible(true);

                // Terminei essa função as 3h24 da madrugada e não sei se deu para entender o
                // que eu fiz
                // Novamente peço desculpas pelo código que talvez não tão simples de entender
            }
        });

        // Escutador de eventos do botão de seleção de filme
        radioFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define que somente os inputs necessarios possam ser usados
                //
                // Os inputs definidos como ativados são somente aquele que fornecem dados para
                // filme
                fieldTitulo.setEnabled(true);
                fieldMidia.setEnabled(true);
                fieldDiretor.setEnabled(true);
                fieldDataLancamento.setEnabled(true);
                fieldDistribuidor.setEnabled(true);

                // Desativa os inputs inuteis no momentos
                //
                // Abaixo ele desativa os inputs que não são necessario para a criação de um
                // filme
                fieldVersion.setEnabled(false);
                fieldPlataforma.setEnabled(false);
            }
        });

        radioSoftware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define que somente os inputs necessarios possam ser usados
                //
                // Os inputs definidos como ativados são somente aquele que fornecem dados para
                // software
                fieldTitulo.setEnabled(true);
                fieldMidia.setEnabled(true);
                fieldVersion.setEnabled(true);
                fieldPlataforma.setEnabled(true);

                // Desativa os inputs inuteis no momentos
                //
                // Abaixo ele desativa os inputs que não são necessario para a criação de um
                // filme
                fieldDiretor.setEnabled(false);
                fieldDataLancamento.setEnabled(false);
                fieldDistribuidor.setEnabled(false);

            }
        });

    }

}
