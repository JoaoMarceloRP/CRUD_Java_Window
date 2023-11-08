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
import java.sql.*;
import javax.swing.JOptionPane;

public class Disciplina {

	private JFrame frmdisciplina;
	private JTextField textid_disciplina;
	private JTextField textnome_disciplina;
	private JTextField textid_coordenador;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Disciplina window = new Disciplina();
					window.frmdisciplina.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Disciplina() {
		initialize();
	}

	private void initialize() {
		frmdisciplina = new JFrame();
		frmdisciplina.getContentPane().setBackground(new Color(255, 255, 255));
		frmdisciplina.setTitle("Cadastro de Disciplina");
		frmdisciplina.setBounds(100, 100, 700, 431);
		frmdisciplina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmdisciplina.getContentPane().setLayout(null);
		
		JLabel lblid_disciplina = new JLabel("id_disciplina:");
		lblid_disciplina.setBounds(23, 21, 56, 14);
		frmdisciplina.getContentPane().add(lblid_disciplina);
		
		JLabel lblnome_disciplinaprofessor = new JLabel("nome:");
		lblnome_disciplinaprofessor.setBounds(23, 55, 46, 14);
		frmdisciplina.getContentPane().add(lblnome_disciplinaprofessor);
		
		JLabel lblid_coordenadorprofessor = new JLabel("id_coordenador:");
		lblid_coordenadorprofessor.setBounds(23, 88, 46, 14);
		frmdisciplina.getContentPane().add(lblid_coordenadorprofessor);
		
		textid_disciplina = new JTextField();
		textid_disciplina.setBounds(89, 18, 124, 20);
		frmdisciplina.getContentPane().add(textid_disciplina);
		textid_disciplina.setColumns(10);
		
		textnome_disciplina = new JTextField();
		textnome_disciplina.setBounds(89, 52, 124, 20);
		frmdisciplina.getContentPane().add(textnome_disciplina);
		textnome_disciplina.setColumns(10);
		
		textid_coordenador = new JTextField();
		textid_coordenador.setBounds(89, 85, 124, 20);
		frmdisciplina.getContentPane().add(textid_coordenador);
		textid_coordenador.setColumns(10);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(558, 191, 89, 23);
		btnincluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int id_disciplina = Integer.parseInt(textid_disciplina.getText());
		        String nome_disciplina = textnome_disciplina.getText();
		        int id_coordenador = Integer.parseInt(textid_coordenador.getText());

		        inserirdisciplina(id_disciplina, nome_disciplina, id_coordenador);
		        
		        textid_disciplina.setText("");
		        textnome_disciplina.setText("");
		        textid_coordenador.setText("");
		    }
		});
		frmdisciplina.getContentPane().add(btnincluir);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(558, 225, 89, 23);
		btnexcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmdisciplina, "Selecione um registro para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int id_disciplina = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        
		        excluirprofessor(id_disciplina);
		        
		        tableModel.removeRow(selectedRow);
		    }
		});
		frmdisciplina.getContentPane().add(btnexcluir);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(558, 259, 89, 23);
		btnalterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmdisciplina, "Nenhuma Disciplina selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int id_disciplina = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        String nome_disciplina = textnome_disciplina.getText();
		        int id_coordenador = Integer.parseInt(textid_coordenador.getText());

		        alterardisciplina(id_disciplina, nome_disciplina, id_coordenador);
		        
		        table.setValueAt(nome_disciplina, selectedRow, 1);
		        table.setValueAt(String.valueOf(id_coordenador), selectedRow, 2);
		        
		        JOptionPane.showMessageDialog(frmdisciplina, "Disciplina alterada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        
		        textid_disciplina.setText("");
		        textnome_disciplina.setText("");
		        textid_coordenador.setText("");
		    }
		});
		frmdisciplina.getContentPane().add(btnalterar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id_disciplina = textid_disciplina.getText();

		        if (id_disciplina != null && !id_disciplina.isEmpty()) {
		            Connection connection = null;
		            PreparedStatement statement = null;
		            ResultSet resultSet = null;

		            try {
		                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_escolar", "root", "");
		                String query = "SELECT * FROM professor WHERE id_disciplina = ?";
		                statement = connection.prepareStatement(query);
		                statement.setString(1, id_disciplina);
		                resultSet = statement.executeQuery();

		                if (resultSet.next()) {
		                    String nome_disciplina = resultSet.getString("nome_disciplina");
		                    String id_coordenador = resultSet.getString("id_coordenador");

		                    String resultMessage = "ID: " + id_disciplina + "\n";
		                    resultMessage += "nome_disciplina: " + nome_disciplina + "\n";
		                    resultMessage += "id_coordenador: " + id_coordenador + "\n";

		                    JOptionPane.showMessageDialog(frmdisciplina, resultMessage, "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(frmdisciplina, "ID não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
		            JOptionPane.showMessageDialog(frmdisciplina, "Digite o ID para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnconsultar.setBounds(558, 293, 89, 23);
		frmdisciplina.getContentPane().add(btnconsultar);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        textid_disciplina.setText("");
			        textnome_disciplina.setText("");
			        textid_coordenador.setText("");
			    }
			});
		
		btnlimpar.setBounds(558, 327, 89, 23);
		frmdisciplina.getContentPane().add(btnlimpar);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(558, 358, 89, 23);
		frmdisciplina.getContentPane().add(btnimprimir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(23, 187, 512, 194);
		frmdisciplina.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 492, 172);
		panel.add(scrollPane);
		
		table = new JTable();
	    tableModel = new DefaultTableModel(
	        new Object[][] { },
	        new String[] { "id_disciplina", "nome_disciplina", "id_coordenador" }
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
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM disciplina");

	        tableModel.setRowCount(0);

	        while (resultSet.next()) {
	            int id_disciplina = resultSet.getInt("id_disciplina");
	            String nome_disciplina = resultSet.getString("nome_disciplina");
	            int id_coordenador = resultSet.getInt("id_coordenador");

	            Object[] row = { Integer.valueOf(id_disciplina), nome_disciplina, Integer.valueOf(id_coordenador) };
	            tableModel.addRow(row);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

		
		private void inserirdisciplina(int id_disciplina, String nome_disciplina, int id_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO disciplina (id_disciplina, nome_disciplina, id_coordenador) VALUES (?, ?, ?)");
		        statement.setInt(1, id_disciplina);
		        statement.setString(2, nome_disciplina);
		        statement.setInt(3, id_coordenador);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();

		        tabela();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void excluirprofessor(int id_disciplina) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("DELETE FROM disciplina WHERE id_disciplina = ?");
		        statement.setInt(1, id_disciplina);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void alterardisciplina(int id_disciplina, String nome_disciplina, int id_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("UPDATE disciplina SET nome_disciplina = ?, id_coordenador = ? WHERE id_disciplina = ?");

		        statement.setString(1, nome_disciplina);
		        statement.setInt(2, id_coordenador);
		        statement.setInt(3, id_disciplina);

		        int rowsUpdated = statement.executeUpdate();

		        statement.close();
		        connection.close();

		        if (rowsUpdated > 0) {
		            JOptionPane.showMessageDialog(frmdisciplina, "Disciplina alterada com sucesso no banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(frmdisciplina, "Não foi possível alterar a disciplina no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmdisciplina, "Erro ao alterar a disciplina no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}


}