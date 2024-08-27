package Source;

import java.awt.GridLayout;

import javax.swing.JFrame;

import Source.Components.ButtonRow;
import Source.Pages.AlunoForm;

public class Main extends JFrame {
  public AlunoForm alunoForm;
  
  public Main() {
    super("Java Swing CRUD");
    setLayout(new GridLayout(2, 1));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    alunoForm = new AlunoForm();

    ButtonRow buttonRow = new ButtonRow();
    buttonRow.handleEvents(this);
    
    add(alunoForm);
    add(buttonRow);
  }
  
  public static void main(String[] args) {
    Main main = new Main();
    main.setVisible(true);
    main.setSize(600, 250);
  }
}