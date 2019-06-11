package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UsuarioGUI extends JFrame implements ActionListener{
	private final JTextField jtNome = new JTextField(15);
	private final JTextField jtEmail = new JTextField(15);
	private final JTextField jtTelefone = new JTextField(15);
        private final JTextField jtEndereco = new JTextField(50);
        private final JTextField jtData_nascimento= new JTextField(50);
	private final JTextField jtId = new JTextField(15);
	
	private final JButton jbSalvar = new JButton("Salvar");
	private final JButton jbNovo = new JButton("Novo");
	
	private final JLabel jlNome = new JLabel("Nome");
	private final JLabel jlEmail = new JLabel("Email");
	private final JLabel jlTelefone = new JLabel("Telefone");
	private final JLabel jlEndereco = new JLabel("Endereco");
        private final JLabel jlData_nascimento = new JLabel("Data de Nascimento");
	private final JLabel jlId = new JLabel("Código");
	
	public UsuarioGUI(){
		super("Cadastro e Consulta");
		setLayout(new GridLayout(8,1));
		
		add(jlId);
		add(jtId);
		add(jlNome);
		add(jtNome);
		add(jlEmail);
		add(jtEmail);
                add(jlTelefone);
		add(jtTelefone);
                add(jlEndereco);
		add(jtEndereco);
                add(jlData_nascimento);
		add(jtData_nascimento);
		add(jbNovo);
		add(jbSalvar);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		jbSalvar.addActionListener(this);
		jbNovo.addActionListener(this);
		jtId.addActionListener(this);
		
		jtNome.setEnabled(false);
		jtEmail.setEnabled(false);
		jtTelefone.setEnabled(false);
		jtEndereco.setEnabled(false);
                jtData_nascimento.setEnabled(false);
		jbSalvar.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new UsuarioGUI();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==jbNovo){
			jtNome.setEnabled(true);
			jtEmail.setEnabled(true);
			jtTelefone.setEnabled(true);
			jtEndereco.setEnabled(true);
                        jtData_nascimento.setEnabled(true);
			jbSalvar.setEnabled(true);
			jtId.setEnabled(false);
			jtId.setText("AUTOMATICO");
			jtNome.requestFocus();
			jbNovo.setEnabled(false);
		}
		
		if(a.getSource()==jbSalvar){
			boolean Prosseguir = false;
			Usuario cl = new Usuario();
			cl.setNome(jtNome.getText());
                        cl.setEmail(jtEmail.getText());
			cl.setEndereco(jtEndereco.getText());
			cl.setTelefone(jtTelefone.getText());
                        cl.setData_nascimento(jtData_nascimento.getText());
			
			if(Prosseguir){
				try{
					cl.cadastrarCliente();
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
					jtNome.setText(" ");
					jtEmail.setText("");
                                        jtTelefone.setText("");
                                        jtEndereco.setText("");
                                        jtData_nascimento.setText("");
					jtId.setText("");
					
					jtNome.setEnabled(false);
					jtEmail.setEnabled(false);
					jtTelefone.setEnabled(false);
					jtEndereco.setEnabled(false);
					jtData_nascimento.setEnabled(false);
					jbSalvar.setEnabled(false);
					jbNovo.setEnabled(true);
					jtId.setEnabled(true);
					
				} catch (SQLException es){
					JOptionPane.showMessageDialog(null, "No momento, nosso banco está passando por dificuldades \nTente novamente em alguns segundos e se o erro insistir em persistir, contate nosso suporte informando a seguinte mensagem: "+es.getMessage());
				}
			}
			
		}
		
		if(a.getSource()==jtId){
			int codigo=0;
			boolean pp = false;
			try{
				codigo = Integer.parseInt(jtId.getText());
				pp=true;
			} catch (Exception b){
				JOptionPane.showMessageDialog(null, "Verifique o código");
			}
			if(pp){
                                try {
                                       Usuario cl = new Usuario().pegaClientePeloCodigo(codigo);
					if(cl!=null){

                                        jtNome.setText(cl.getNome());
                                        jtEmail.setText(cl.getEmail());
                                        jtTelefone.setText((cl.getTelefone()));
                                        jtEndereco.setText(cl.getEndereco());
                                        jtData_nascimento.setText(cl.getData_nascimento());
                                        jtId.setText(String.valueOf(cl.getId()));
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Código não encontrado");
                                    }
                                }   catch (SQLException sql){
                                                JOptionPane.showMessageDialog(null, "Erro: "+sql.getMessage());
                                        }
		}
		
            }
        }
}
