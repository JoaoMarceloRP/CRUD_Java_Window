package model;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JOptionPane;

public class Aluno {

	private JFrame frmaluno;
	private JTextField textMatricula;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textEndereco;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Aluno window = new Aluno();
					window.frmaluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Aluno() {
		initialize();
	}

	private void initialize() {
		frmaluno = new JFrame();
		frmaluno.getContentPane().setBackground(new Color(255, 255, 255));
		frmaluno.setTitle("Cadastro de aluno ");
		frmaluno.setBounds(100, 100, 700, 431);
		frmaluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmaluno.getContentPane().setLayout(null);
		
		JLabel lblmatricula = new JLabel("Matricula:");
		lblmatricula.setBounds(23, 21, 56, 14);
		frmaluno.getContentPane().add(lblmatricula);
		
		JLabel lblnomealuno = new JLabel("Nome:");
		lblnomealuno.setBounds(23, 55, 46, 14);
		frmaluno.getContentPane().add(lblnomealuno);
		
		JLabel lblcpfaluno = new JLabel("CPF:");
		lblcpfaluno.setBounds(23, 88, 46, 14);
		frmaluno.getContentPane().add(lblcpfaluno);
		
		JLabel lblenderecoaluno = new JLabel("Endereço:");
		lblenderecoaluno.setBounds(23, 119, 56, 14);
		frmaluno.getContentPane().add(lblenderecoaluno);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(89, 18, 124, 20);
		frmaluno.getContentPane().add(textMatricula);
		textMatricula.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(89, 52, 124, 20);
		frmaluno.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		textCPF = new JTextField();
		textCPF.setBounds(89, 85, 124, 20);
		frmaluno.getContentPane().add(textCPF);
		textCPF.setColumns(10);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(89, 116, 124, 20);
		frmaluno.getContentPane().add(textEndereco);
		textEndereco.setColumns(10);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(558, 191, 89, 23);
		btnincluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = textMatricula.getText();
		        String nome = textNome.getText();
		        String cpf = textCPF.getText();
		        String endereco = textEndereco.getText();

		        inserirAluno(matricula, nome, cpf, endereco);
		        
		        textMatricula.setText("");
		        textNome.setText("");
		        textCPF.setText("");
		        textEndereco.setText("");
		    }
		});
		frmaluno.getContentPane().add(btnincluir);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(558, 225, 89, 23);
		btnexcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmaluno, "Selecione um registro para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int matricula = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        
		        excluirAluno(matricula);
		        
		        tableModel.removeRow(selectedRow);
		    }
		});
		frmaluno.getContentPane().add(btnexcluir);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(558, 259, 89, 23);
		btnalterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmaluno, "Nenhum aluno selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int matricula = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        String nome = textNome.getText();
		        String cpf = textCPF.getText();
		        String endereco = textEndereco.getText();

		        alterarAluno(matricula, nome, cpf, endereco);
		        
		        table.setValueAt(nome, selectedRow, 1);
		        table.setValueAt(cpf, selectedRow, 2);
		        table.setValueAt(endereco, selectedRow, 3);
		        
		        JOptionPane.showMessageDialog(frmaluno, "Aluno alterado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        
		        textMatricula.setText("");
		        textNome.setText("");
		        textCPF.setText("");
		        textEndereco.setText("");
		    }
		});
		frmaluno.getContentPane().add(btnalterar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = textMatricula.getText();

		        if (matricula != null && !matricula.isEmpty()) {
		            Connection connection = null;
		            PreparedStatement statement = null;
		            ResultSet resultSet = null;

		            try {
		                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_escolar", "root", "");
		                String query = "SELECT * FROM aluno WHERE nr_matricula = ?";
		                statement = connection.prepareStatement(query);
		                statement.setString(1, matricula);
		                resultSet = statement.executeQuery();

		                if (resultSet.next()) {
		                    String nome = resultSet.getString("nome_aluno");
		                    String cpf = resultSet.getString("cpf_aluno");
		                    String endereco = resultSet.getString("endereco_aluno");

		                    String resultMessage = "Matrícula: " + matricula + "\n";
		                    resultMessage += "Nome: " + nome + "\n";
		                    resultMessage += "CPF: " + cpf + "\n";
		                    resultMessage += "Endereço: " + endereco;

		                    JOptionPane.showMessageDialog(frmaluno, resultMessage, "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(frmaluno, "Matrícula não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
		                }

		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            } finally {
		                if (resultSet != null) {
		                    try {
		                        resultSet.close();
		                    } catch (SQLException ex) {
		                        ex.printStackTrace();
		                    }
		                }
		                if (statement != null) {
		                    try {
		                        statement.close();
		                    } catch (SQLException ex) {
		                        ex.printStackTrace();
		                    }
		                }
		                if (connection != null) {
		                    try {
		                        connection.close();
		                    } catch (SQLException ex) {
		                        ex.printStackTrace();
		                    }
		                }
		            }

		        } else {
		            JOptionPane.showMessageDialog(frmaluno, "Digite a matrícula para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnconsultar.setBounds(558, 293, 89, 23);
		frmaluno.getContentPane().add(btnconsultar);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        textMatricula.setText("");
			        textNome.setText("");
			        textCPF.setText("");
			        textEndereco.setText("");
			    }
			});
		
		btnlimpar.setBounds(558, 327, 89, 23);
		frmaluno.getContentPane().add(btnlimpar);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(558, 358, 89, 23);
		frmaluno.getContentPane().add(btnimprimir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(23, 187, 512, 194);
		frmaluno.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 492, 172);
		panel.add(scrollPane);
		
		table = new JTable();
	    tableModel = new DefaultTableModel(
	        new Object[][] { },
	        new String[] { "Matricula", "Nome", "CPF", "Endereço" }
	    );
	    table.setModel(tableModel);
	    table.setBackground(new Color(192, 192, 192));
	    scrollPane.setViewportView(table);

	    tabela();
	}

	private void tabela() {
	    try {
	        Connection connection = DatabaseConnection.getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM aluno");

	        tableModel.setRowCount(0);

	        while (resultSet.next()) {
	            int matricula = resultSet.getInt("nr_matricula");
	            String nome = resultSet.getString("nome_aluno");
	            String cpf = resultSet.getString("cpf_aluno");
	            String endereco = resultSet.getString("endereco_aluno");

	            Object[] row = { Integer.valueOf(matricula), nome, cpf, endereco };
	            tableModel.addRow(row);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

		
		private void inserirAluno(String matricula, String nome, String cpf, String endereco) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO aluno (nr_matricula, nome_aluno, cpf_aluno, endereco_aluno) VALUES (?, ?, ?, ?)");
		        statement.setString(1, matricula);
		        statement.setString(2, nome);
		        statement.setString(3, cpf);
		        statement.setString(4, endereco);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();

		        tabela();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void excluirAluno(int matricula) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("DELETE FROM aluno WHERE nr_matricula = ?");
		        statement.setInt(1, matricula);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void alterarAluno(int matricula, String nome, String cpf, String endereco) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("UPDATE aluno SET nome_aluno = ?, cpf_aluno = ?, endereco_aluno = ? WHERE nr_matricula = ?");

		        statement.setString(1, nome);
		        statement.setString(2, cpf);
		        statement.setString(3, endereco);
		        statement.setInt(4, matricula);

		        int rowsUpdated = statement.executeUpdate();
		        
		        statement.close();
		        connection.close();

		        if (rowsUpdated > 0) {
		            JOptionPane.showMessageDialog(frmaluno, "Aluno alterado com sucesso no banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(frmaluno, "Não foi possível alterar o aluno no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmaluno, "Erro ao alterar o aluno no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}

}