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

public class Professor {

	private JFrame frmprofessor;
	private JTextField textid_professor;
	private JTextField textcpf;
	private JTextField textnome;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Professor window = new Professor();
					window.frmprofessor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Professor() {
		initialize();
	}

	private void initialize() {
		frmprofessor = new JFrame();
		frmprofessor.getContentPane().setBackground(new Color(255, 255, 255));
		frmprofessor.setTitle("Cadastro de professor ");
		frmprofessor.setBounds(100, 100, 700, 431);
		frmprofessor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmprofessor.getContentPane().setLayout(null);
		
		JLabel lblid_professor = new JLabel("id_professor:");
		lblid_professor.setBounds(23, 21, 56, 14);
		frmprofessor.getContentPane().add(lblid_professor);
		
		JLabel lblcpfprofessor = new JLabel("cpf:");
		lblcpfprofessor.setBounds(23, 55, 46, 14);
		frmprofessor.getContentPane().add(lblcpfprofessor);
		
		JLabel lblnomeprofessor = new JLabel("nome:");
		lblnomeprofessor.setBounds(23, 88, 46, 14);
		frmprofessor.getContentPane().add(lblnomeprofessor);
		
		textid_professor = new JTextField();
		textid_professor.setBounds(89, 18, 124, 20);
		frmprofessor.getContentPane().add(textid_professor);
		textid_professor.setColumns(10);
		
		textcpf = new JTextField();
		textcpf.setBounds(89, 52, 124, 20);
		frmprofessor.getContentPane().add(textcpf);
		textcpf.setColumns(10);
		
		textnome = new JTextField();
		textnome.setBounds(89, 85, 124, 20);
		frmprofessor.getContentPane().add(textnome);
		textnome.setColumns(10);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(558, 191, 89, 23);
		btnincluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id_professor = textid_professor.getText();
		        String cpf = textcpf.getText();
		        String nome = textnome.getText();

		        inserirprofessor(id_professor, cpf, nome);
		        
		        textid_professor.setText("");
		        textcpf.setText("");
		        textnome.setText("");
		    }
		});
		frmprofessor.getContentPane().add(btnincluir);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(558, 225, 89, 23);
		btnexcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmprofessor, "Selecione um registro para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int id_professor = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        
		        excluirprofessor(id_professor);
		        
		        tableModel.removeRow(selectedRow);
		    }
		});
		frmprofessor.getContentPane().add(btnexcluir);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(558, 259, 89, 23);
		btnalterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmprofessor, "Nenhum professor selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int id_professor = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        String cpf = textcpf.getText();
		        String nome = textnome.getText();

		        alterarprofessor(id_professor, cpf, nome);
		        
		        table.setValueAt(cpf, selectedRow, 1);
		        table.setValueAt(nome, selectedRow, 2);
		        
		        JOptionPane.showMessageDialog(frmprofessor, "professor alterado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        
		        textid_professor.setText("");
		        textcpf.setText("");
		        textnome.setText("");
		    }
		});
		frmprofessor.getContentPane().add(btnalterar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id_professor = textid_professor.getText();

		        if (id_professor != null && !id_professor.isEmpty()) {
		            Connection connection = null;
		            PreparedStatement statement = null;
		            ResultSet resultSet = null;

		            try {
		                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_escolar", "root", "");
		                String query = "SELECT * FROM professor WHERE id_professor = ?";
		                statement = connection.prepareStatement(query);
		                statement.setString(1, id_professor);
		                resultSet = statement.executeQuery();

		                if (resultSet.next()) {
		                    String cpf = resultSet.getString("cpf_professor");
		                    String nome = resultSet.getString("nome");

		                    String resultMessage = "ID: " + id_professor + "\n";
		                    resultMessage += "cpf: " + cpf + "\n";
		                    resultMessage += "nome: " + nome + "\n";

		                    JOptionPane.showMessageDialog(frmprofessor, resultMessage, "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(frmprofessor, "ID não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
		            JOptionPane.showMessageDialog(frmprofessor, "Digite o ID para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnconsultar.setBounds(558, 293, 89, 23);
		frmprofessor.getContentPane().add(btnconsultar);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        textid_professor.setText("");
			        textcpf.setText("");
			        textnome.setText("");
			    }
			});
		
		btnlimpar.setBounds(558, 327, 89, 23);
		frmprofessor.getContentPane().add(btnlimpar);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(558, 358, 89, 23);
		frmprofessor.getContentPane().add(btnimprimir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(23, 187, 512, 194);
		frmprofessor.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 492, 172);
		panel.add(scrollPane);
		
		table = new JTable();
	    tableModel = new DefaultTableModel(
	        new Object[][] { },
	        new String[] { "id_professor", "cpf", "nome" }
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
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM professor");

	        tableModel.setRowCount(0);

	        while (resultSet.next()) {
	            int id_professor = resultSet.getInt("id_professor");
	            String cpf = resultSet.getString("cpf_professor");
	            String nome = resultSet.getString("nome");

	            Object[] row = { Integer.valueOf(id_professor), cpf, nome};
	            tableModel.addRow(row);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

		
		private void inserirprofessor(String id_professor, String cpf, String nome) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO professor (id_professor, cpf_professor, nome) VALUES (?, ?, ?)");
		        statement.setString(1, id_professor);
		        statement.setString(2, cpf);
		        statement.setString(3, nome);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();

		        tabela();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void excluirprofessor(int id_professor) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("DELETE FROM professor WHERE id_professor = ?");
		        statement.setInt(1, id_professor);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void alterarprofessor(int id_professor, String cpf, String nome) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("UPDATE professor SET cpf_professor = ?, nome = ? WHERE id_professor = ?");

		        if (!cpf.isEmpty()) {
		            long cpfLong = Long.parseLong(cpf);

		            statement.setLong(1, cpfLong);
		        } else {
		            statement.setNull(1, Types.INTEGER);
		        }

		        statement.setString(2, nome);
		        statement.setInt(3, id_professor);

		        int rowsUpdated = statement.executeUpdate();

		        statement.close();
		        connection.close();

		        if (rowsUpdated > 0) {
		            JOptionPane.showMessageDialog(frmprofessor, "Professor alterado com sucesso no banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(frmprofessor, "Não foi possível alterar o professor no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmprofessor, "Erro ao alterar o professor no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmprofessor, "CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}


}