package model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Notas {

	private JFrame frmnotas;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notas window = new Notas();
					window.frmnotas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Notas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmnotas = new JFrame();
		frmnotas.setTitle("Cadastro de notas");
		frmnotas.getContentPane().setBackground(new Color(255, 128, 64));
		frmnotas.setBounds(100, 100, 701, 467);
		frmnotas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmnotas.getContentPane().setLayout(null);
		
		JButton btnimprimir = new JButton("Imprimir ");
		btnimprimir.setBounds(586, 394, 89, 23);
		frmnotas.getContentPane().add(btnimprimir);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.setBounds(586, 360, 89, 23);
		frmnotas.getContentPane().add(btnlimpar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.setBounds(586, 326, 89, 23);
		frmnotas.getContentPane().add(btnconsultar);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(586, 292, 89, 23);
		frmnotas.getContentPane().add(btnalterar);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(586, 258, 89, 23);
		frmnotas.getContentPane().add(btnexcluir);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(586, 224, 89, 23);
		frmnotas.getContentPane().add(btnincluir);
		
		JLabel lblidprofessor = new JLabel("ID professor");
		lblidprofessor.setBounds(22, 36, 64, 14);
		frmnotas.getContentPane().add(lblidprofessor);
		
		JLabel lblidturma = new JLabel("ID Curso:");
		lblidturma.setBounds(22, 64, 46, 14);
		frmnotas.getContentPane().add(lblidturma);
		
		JLabel lblcurso = new JLabel("ID Turma:");
		lblcurso.setBounds(22, 100, 64, 14);
		frmnotas.getContentPane().add(lblcurso);
		
		JLabel lblmatricula = new JLabel("Matricula: ");
		lblmatricula.setBounds(22, 132, 64, 14);
		frmnotas.getContentPane().add(lblmatricula);
		
		JLabel lblnota = new JLabel("Nota disciplina:");
		lblnota.setBounds(22, 163, 83, 14);
		frmnotas.getContentPane().add(lblnota);
		
		JLabel lbldisciplina = new JLabel("ID disciplina: ");
		lbldisciplina.setBounds(22, 198, 64, 14);
		frmnotas.getContentPane().add(lbldisciplina);
		
		textField = new JTextField();
		textField.setBounds(96, 30, 104, 20);
		frmnotas.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 61, 104, 20);
		frmnotas.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 195, 104, 20);
		frmnotas.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(96, 97, 104, 20);
		frmnotas.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(96, 129, 105, 20);
		frmnotas.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(96, 160, 104, 20);
		frmnotas.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lbldatanota = new JLabel("Data da nota: ");
		lbldatanota.setBounds(22, 228, 70, 14);
		frmnotas.getContentPane().add(lbldatanota);
		
		textField_6 = new JTextField();
		textField_6.setBounds(96, 227, 104, 20);
		frmnotas.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(22, 258, 540, 159);
		frmnotas.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 520, 137);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID professor ", "ID Turma", "Matricula", "Nota Disciplina", "ID Disciplina ", "Data da nota", "ID curso"
			}
		));
		scrollPane.setViewportView(table);
	}

}
