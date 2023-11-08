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

public class Coordenador {

	private JFrame frmcoordenador;
	private JTextField textid_coordenador;
	private JTextField texttelefone;
	private JTextField textnome_coordenador;
	private JTextField textemail_coordenador;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Coordenador window = new Coordenador();
					window.frmcoordenador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Coordenador() {
		initialize();
	}

	private void initialize() {
		frmcoordenador = new JFrame();
		frmcoordenador.getContentPane().setBackground(new Color(255, 255, 255));
		frmcoordenador.setTitle("Cadastro de coordenador ");
		frmcoordenador.setBounds(100, 100, 700, 431);
		frmcoordenador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmcoordenador.getContentPane().setLayout(null);
		
		JLabel lblid_coordenador = new JLabel("id:");
		lblid_coordenador.setBounds(23, 21, 56, 14);
		frmcoordenador.getContentPane().add(lblid_coordenador);
		
		JLabel lbltelefone = new JLabel("telefone:");
		lbltelefone.setBounds(23, 55, 46, 14);
		frmcoordenador.getContentPane().add(lbltelefone);
		
		JLabel lblnome_coordenadoraluno = new JLabel("nome:");
		lblnome_coordenadoraluno.setBounds(23, 88, 46, 14);
		frmcoordenador.getContentPane().add(lblnome_coordenadoraluno);
		
		JLabel lblemail_coordenador = new JLabel("Email:");
		lblemail_coordenador.setBounds(23, 119, 56, 14);
		frmcoordenador.getContentPane().add(lblemail_coordenador);
		
		textid_coordenador = new JTextField();
		textid_coordenador.setBounds(89, 18, 124, 20);
		frmcoordenador.getContentPane().add(textid_coordenador);
		textid_coordenador.setColumns(10);
		
		texttelefone = new JTextField();
		texttelefone.setBounds(89, 52, 124, 20);
		frmcoordenador.getContentPane().add(texttelefone);
		texttelefone.setColumns(10);
		
		textnome_coordenador = new JTextField();
		textnome_coordenador.setBounds(89, 85, 124, 20);
		frmcoordenador.getContentPane().add(textnome_coordenador);
		textnome_coordenador.setColumns(10);
		
		textemail_coordenador = new JTextField();
		textemail_coordenador.setBounds(89, 116, 124, 20);
		frmcoordenador.getContentPane().add(textemail_coordenador);
		textemail_coordenador.setColumns(10);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(558, 191, 89, 23);
		btnincluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int id_coordenador = Integer.parseInt(textid_coordenador.getText());
		    	int telefone = Integer.parseInt(texttelefone.getText());
		        String nome_coordenador = textnome_coordenador.getText();
		        String email_coordenador = textemail_coordenador.getText();

		        inserirAluno(id_coordenador, telefone, nome_coordenador, email_coordenador);
		        
		        textid_coordenador.setText("");
		        texttelefone.setText("");
		        textnome_coordenador.setText("");
		        textemail_coordenador.setText("");
		    }
		});
		frmcoordenador.getContentPane().add(btnincluir);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(558, 225, 89, 23);
		btnexcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmcoordenador, "Selecione um registro para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int id_coordenador = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        
		        excluirAluno(id_coordenador);
		        
		        tableModel.removeRow(selectedRow);
		    }
		});
		frmcoordenador.getContentPane().add(btnexcluir);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(558, 259, 89, 23);
		btnalterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmcoordenador, "Nenhum coordenador selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int id_coordenador = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        int telefone = Integer.parseInt(texttelefone.getText());
		        String nome_coordenador = textnome_coordenador.getText();
		        String email_coordenador = textemail_coordenador.getText();

		        alterarAluno(id_coordenador, telefone, nome_coordenador, email_coordenador);
		        
		        Object telefoneObj = Integer.valueOf(telefone);
		        table.setValueAt(telefoneObj, selectedRow, 1);
		        table.setValueAt(nome_coordenador, selectedRow, 2);
		        table.setValueAt(email_coordenador, selectedRow, 3);
		        
		        JOptionPane.showMessageDialog(frmcoordenador, "Coordenador alterado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        
		        textid_coordenador.setText("");
		        texttelefone.setText("");
		        textnome_coordenador.setText("");
		        textemail_coordenador.setText("");
		    }
		});
		frmcoordenador.getContentPane().add(btnalterar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id_coordenador = textid_coordenador.getText();

		        if (id_coordenador != null && !id_coordenador.isEmpty()) {
		            Connection connection = null;
		            PreparedStatement statement = null;
		            ResultSet resultSet = null;

		            try {
		                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_escolar", "root", "");
		                String query = "SELECT * FROM coordenador WHERE id_coordenador = ?";
		                statement = connection.prepareStatement(query);
		                statement.setString(1, id_coordenador);
		                resultSet = statement.executeQuery();

		                if (resultSet.next()) {
		                    int telefone = resultSet.getInt("telefone");
		                    String nome_coordenador = resultSet.getString("nome_coordenador");
		                    String email_coordenador = resultSet.getString("email_coordenador");

		                    String resultMessage = "id_coordenador: " + id_coordenador + "\n";
		                    resultMessage += "telefone: " + telefone + "\n";
		                    resultMessage += "nome_coordenador: " + nome_coordenador + "\n";
		                    resultMessage += "email_coordenador: " + email_coordenador;

		                    JOptionPane.showMessageDialog(frmcoordenador, resultMessage, "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(frmcoordenador, "ID não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
		            JOptionPane.showMessageDialog(frmcoordenador, "Digite o id para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnconsultar.setBounds(558, 293, 89, 23);
		frmcoordenador.getContentPane().add(btnconsultar);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        textid_coordenador.setText("");
			        texttelefone.setText("");
			        textnome_coordenador.setText("");
			        textemail_coordenador.setText("");
			    }
			});
		
		btnlimpar.setBounds(558, 327, 89, 23);
		frmcoordenador.getContentPane().add(btnlimpar);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(558, 358, 89, 23);
		frmcoordenador.getContentPane().add(btnimprimir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(23, 187, 512, 194);
		frmcoordenador.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 492, 172);
		panel.add(scrollPane);
		
		table = new JTable();
	    tableModel = new DefaultTableModel(
	        new Object[][] { },
	        new String[] { "id_coordenador", "telefone", "nome_coordenador", "email_coordenador" }
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
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM coordenador");

	        tableModel.setRowCount(0);

	        while (resultSet.next()) {
	            int id_coordenador = resultSet.getInt("id_coordenador");
	            int telefone = resultSet.getInt("telefone");
	            String nome_coordenador = resultSet.getString("nome_coordenador");
	            String email_coordenador = resultSet.getString("email_coordenador");

	            Object telefoneObj = Integer.valueOf(telefone);
	            Object[] row = { Integer.valueOf(id_coordenador), telefoneObj, nome_coordenador, email_coordenador };
	            tableModel.addRow(row);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

		
		private void inserirAluno(int id_coordenador, int telefone, String nome_coordenador, String email_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO coordenador (id_coordenador, telefone, nome_coordenador, email_coordenador) VALUES (?, ?, ?, ?)");
		        statement.setInt(1, id_coordenador);
		        statement.setInt(2, telefone);
		        statement.setString(3, nome_coordenador);
		        statement.setString(4, email_coordenador);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();

		        tabela();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void excluirAluno(int id_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("DELETE FROM coordenador WHERE id_coordenador = ?");
		        statement.setInt(1, id_coordenador);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void alterarAluno(int id_coordenador, int telefone, String nome_coordenador, String email_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("UPDATE coordenador SET telefone = ?, nome_coordenador = ?, email_coordenador = ? WHERE id_coordenador = ?");

		        statement.setInt(1, telefone);
		        statement.setString(2, nome_coordenador);
		        statement.setString(3, email_coordenador);
		        statement.setInt(4, id_coordenador);

		        int rowsUpdated = statement.executeUpdate();
		        
		        statement.close();
		        connection.close();

		        if (rowsUpdated > 0) {
		            JOptionPane.showMessageDialog(frmcoordenador, "Aluno alterado com sucesso no banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(frmcoordenador, "Não foi possível alterar o aluno no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmcoordenador, "Erro ao alterar o aluno no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}

}