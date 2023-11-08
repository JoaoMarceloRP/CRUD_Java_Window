package model;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Disciplinacurso {

	private JFrame frmCronograma;
	private JTextField txtFldidprofessor;
	private JTextField txtFldidturma;
	private JTable table;
	protected Object frmTurma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Disciplinacurso window = new Disciplinacurso();
					((Window) window.frmTurma).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Disciplinacurso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCronograma = new JFrame();
		frmCronograma.setTitle("Disciplinacurso");
		frmCronograma.getContentPane().setBackground(new Color(255, 64, 128));
		frmCronograma.setBounds(100, 100, 700, 462);
		frmCronograma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCronograma.getContentPane().setLayout(null);
		
		JLabel lblIDturma = new JLabel("ID discplina: ");
		lblIDturma.setBounds(29, 60, 71, 14);
		frmCronograma.getContentPane().add(lblIDturma);
		
		JLabel lblidprofessor = new JLabel("ID curso:");
		lblidprofessor.setBounds(29, 24, 71, 14);
		frmCronograma.getContentPane().add(lblidprofessor);
		
		txtFldidprofessor = new JTextField();
		txtFldidprofessor.setBounds(110, 21, 105, 20);
		frmCronograma.getContentPane().add(txtFldidprofessor);
		txtFldidprofessor.setColumns(10);
		
		txtFldidturma = new JTextField();
		txtFldidturma.setBounds(110, 57, 105, 20);
		frmCronograma.getContentPane().add(txtFldidturma);
		txtFldidturma.setColumns(10);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(565, 376, 89, 23);
		frmCronograma.getContentPane().add(btnimprimir);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.setBounds(565, 343, 89, 23);
		frmCronograma.getContentPane().add(btnlimpar);
		
		JButton btnconsulta = new JButton("Consultar");
		btnconsulta.setBounds(565, 308, 89, 23);
		frmCronograma.getContentPane().add(btnconsulta);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(565, 274, 89, 23);
		frmCronograma.getContentPane().add(btnalterar);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(565, 240, 89, 23);
		frmCronograma.getContentPane().add(btnexcluir);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnincluir.setBounds(565, 206, 89, 23);
		frmCronograma.getContentPane().add(btnincluir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(29, 203, 494, 196);
		frmCronograma.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 474, 174);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID discplina", "ID curso"
			}
		));
		scrollPane.setViewportView(table);
	}
}