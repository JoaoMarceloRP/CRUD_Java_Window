package model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;

public class Controle {

	private JFrame frmcoordenador;
	private JTextField textFieldID;
	private JTextField textFieldnome;
	private JTextField textFieldemail;
	private JLabel lblnomecompleto;
	private JLabel lblemail;
	private JTextField textFieldTelefone;
	private JLabel lblIDl_2;
	private JLabel lbltelefone;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controle window = new Controle();
					window.frmcoordenador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Controle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmcoordenador = new JFrame();
		frmcoordenador.setTitle("Cadastro de coordenador ");
		frmcoordenador.getContentPane().setBackground(new Color(0, 128, 192));
		frmcoordenador.setBounds(100, 100, 697, 454);
		frmcoordenador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(255, 255, 0));
		frmcoordenador.getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(101, 27, 192, 20);
		layeredPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldnome = new JTextField();
		textFieldnome.setBounds(101, 69, 192, 20);
		layeredPane.add(textFieldnome);
		textFieldnome.setColumns(10);
		
		textFieldemail = new JTextField();
		textFieldemail.setBounds(101, 109, 192, 20);
		layeredPane.add(textFieldemail);
		textFieldemail.setColumns(10);
		
		lblnomecompleto = new JLabel("Nome completo:");
		lblnomecompleto.setBounds(10, 72, 81, 14);
		layeredPane.add(lblnomecompleto);
		
		lblemail = new JLabel("Email");
		lblemail.setBounds(10, 112, 46, 14);
		layeredPane.add(lblemail);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(101, 149, 192, 20);
		layeredPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		lblIDl_2 = new JLabel("ID: ");
		lblIDl_2.setBounds(10, 30, 46, 14);
		layeredPane.add(lblIDl_2);
		
		lbltelefone = new JLabel("Telefone:");
		lbltelefone.setBounds(10, 152, 46, 14);
		layeredPane.add(lbltelefone);
		
		JButton btnNewButtonimprimir = new JButton("Imprimir");
		btnNewButtonimprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonimprimir.setBounds(582, 375, 89, 23);
		layeredPane.add(btnNewButtonimprimir);
		
		JButton btnNewButtonlimpar = new JButton("Limpar");
		btnNewButtonlimpar.setBounds(582, 341, 89, 23);
		layeredPane.add(btnNewButtonlimpar);
		
		JButton btnNewButtonexcluir = new JButton("Excluir");
		btnNewButtonexcluir.setBounds(582, 307, 89, 23);
		layeredPane.add(btnNewButtonexcluir);
		
		JButton btnNewButtonconsulta = new JButton("Consultar");
		btnNewButtonconsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonconsulta.setBounds(582, 273, 89, 23);
		layeredPane.add(btnNewButtonconsulta);
		
		JButton btnNewButtonalterar = new JButton("Alterar");
		btnNewButtonalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonalterar.setBounds(582, 239, 89, 23);
		layeredPane.add(btnNewButtonalterar);
		
		JButton btnNewButtonincluir = new JButton("Incluir");
		btnNewButtonincluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonincluir.setBounds(582, 205, 89, 23);
		layeredPane.add(btnNewButtonincluir);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(30, 205, 514, 182);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 494, 160);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Email", "Telefone"
			}
		));
		scrollPane_1.setViewportView(table);
	}
}