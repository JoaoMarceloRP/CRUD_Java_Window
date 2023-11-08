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

public class Curso {

	private JFrame frmcurso;
	private JTextField textid_curso;
	private JTextField textnome_curso;
	private JTextField textid_coordenador;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Curso window = new Curso();
					window.frmcurso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Curso() {
		initialize();
	}

	private void initialize() {
		frmcurso = new JFrame();
		frmcurso.getContentPane().setBackground(new Color(255, 255, 255));
		frmcurso.setTitle("Cadastro de curso ");
		frmcurso.setBounds(100, 100, 700, 431);
		frmcurso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmcurso.getContentPane().setLayout(null);
		
		JLabel lblid_curso = new JLabel("id_curso:");
		lblid_curso.setBounds(23, 21, 56, 14);
		frmcurso.getContentPane().add(lblid_curso);
		
		JLabel lblnome_curso = new JLabel("nome:");
		lblnome_curso.setBounds(23, 55, 46, 14);
		frmcurso.getContentPane().add(lblnome_curso);
		
		JLabel lblid_coordenador = new JLabel("id_coordenador:");
		lblid_coordenador.setBounds(23, 88, 46, 14);
		frmcurso.getContentPane().add(lblid_coordenador);
		
		textid_curso = new JTextField();
		textid_curso.setBounds(89, 18, 124, 20);
		frmcurso.getContentPane().add(textid_curso);
		textid_curso.setColumns(10);
		
		textnome_curso = new JTextField();
		textnome_curso.setBounds(89, 52, 124, 20);
		frmcurso.getContentPane().add(textnome_curso);
		textnome_curso.setColumns(10);
		
		textid_coordenador = new JTextField();
		textid_coordenador.setBounds(89, 85, 124, 20);
		frmcurso.getContentPane().add(textid_coordenador);
		textid_coordenador.setColumns(10);
		
		JButton btnincluir = new JButton("Incluir");
		btnincluir.setBounds(558, 191, 89, 23);
		btnincluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int id_curso = Integer.parseInt(textid_curso.getText());
		        String nome_curso = textnome_curso.getText();
		        int id_coordenador = Integer.parseInt(textid_coordenador.getText());

		        inserircurso(id_curso, nome_curso, id_coordenador);
		        
		        textid_curso.setText("");
		        textnome_curso.setText("");
		        textid_coordenador.setText("");
		    }
		});
		frmcurso.getContentPane().add(btnincluir);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setBounds(558, 225, 89, 23);
		btnexcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmcurso, "Selecione um registro para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        int id_curso = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        
		        excluircurso(id_curso);
		        
		        tableModel.removeRow(selectedRow);
		    }
		});
		frmcurso.getContentPane().add(btnexcluir);
		
		JButton btnalterar = new JButton("Alterar");
		btnalterar.setBounds(558, 259, 89, 23);
		btnalterar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(frmcurso, "Nenhum curso selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int id_curso = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		        String nome_curso = textnome_curso.getText();
		        int id_coordenador = Integer.parseInt(textid_coordenador.getText());

		        alterarcurso(id_curso, nome_curso, id_coordenador);
		        
		        table.setValueAt(nome_curso, selectedRow, 1);
		        table.setValueAt(Integer.valueOf(id_coordenador), selectedRow, 2);

		        
		        JOptionPane.showMessageDialog(frmcurso, "curso alterado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        
		        textid_curso.setText("");
		        textnome_curso.setText("");
		        textid_coordenador.setText("");
		    }
		});
		frmcurso.getContentPane().add(btnalterar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id_curso = textid_curso.getText();

		        if (id_curso != null && !id_curso.isEmpty()) {
		            Connection connection = null;
		            PreparedStatement statement = null;
		            ResultSet resultSet = null;

		            try {
		                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_escolar", "root", "");
		                String query = "SELECT * FROM curso WHERE id_curso = ?";
		                statement = connection.prepareStatement(query);
		                statement.setString(1, id_curso);
		                resultSet = statement.executeQuery();

		                if (resultSet.next()) {
		                    String nome_curso = resultSet.getString("nome_curso");
		                    String id_coordenador = resultSet.getString("id_coordenador");

		                    String resultMessage = "ID: " + id_curso + "\n";
		                    resultMessage += "nome_curso: " + nome_curso + "\n";
		                    resultMessage += "id_coordenador: " + id_coordenador + "\n";

		                    JOptionPane.showMessageDialog(frmcurso, resultMessage, "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(frmcurso, "ID não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
		            JOptionPane.showMessageDialog(frmcurso, "Digite o ID para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		btnconsultar.setBounds(558, 293, 89, 23);
		frmcurso.getContentPane().add(btnconsultar);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        textid_curso.setText("");
			        textnome_curso.setText("");
			        textid_coordenador.setText("");
			    }
			});
		
		btnlimpar.setBounds(558, 327, 89, 23);
		frmcurso.getContentPane().add(btnlimpar);
		
		JButton btnimprimir = new JButton("Imprimir");
		btnimprimir.setBounds(558, 358, 89, 23);
		frmcurso.getContentPane().add(btnimprimir);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(23, 187, 512, 194);
		frmcurso.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 492, 172);
		panel.add(scrollPane);
		
		table = new JTable();
	    tableModel = new DefaultTableModel(
	        new Object[][] { },
	        new String[] { "id_curso", "nome_curso", "id_coordenador" }
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
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM curso");

	        tableModel.setRowCount(0);

	        while (resultSet.next()) {
	            int id_curso = resultSet.getInt("id_curso");
	            String nome_curso = resultSet.getString("nome_curso");
	            String id_coordenador = resultSet.getString("id_coordenador");

	            Object[] row = { Integer.valueOf(id_curso), nome_curso, id_coordenador};
	            tableModel.addRow(row);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

		
		private void inserircurso(int id_curso, String nome_curso, int id_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("INSERT INTO curso (id_curso, nome_curso, id_coordenador) VALUES (?, ?, ?)");
		        statement.setInt(1, id_curso);
		        statement.setString(2, nome_curso);
		        statement.setInt(3, id_coordenador);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();

		        tabela();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void excluircurso(int id_curso) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("DELETE FROM curso WHERE id_curso = ?");
		        statement.setInt(1, id_curso);
		        statement.executeUpdate();
		        statement.close();
		        connection.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		private void alterarcurso(int id_curso, String nome_curso, int id_coordenador) {
		    try {
		        Connection connection = DatabaseConnection.getConnection();
		        PreparedStatement statement = connection.prepareStatement("UPDATE curso SET nome_curso = ?, id_coordenador = ? WHERE id_curso = ?");

		        statement.setString(1, nome_curso);
		        statement.setInt(2, id_coordenador);
		        statement.setInt(3, id_curso);

		        int rowsUpdated = statement.executeUpdate();

		        statement.close();
		        connection.close();

		        if (rowsUpdated > 0) {
		            JOptionPane.showMessageDialog(frmcurso, "Curso alterado com sucesso no banco de dados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(frmcurso, "Não foi possível alterar o curso no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frmcurso, "Erro ao alterar o curso no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}



}