package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;
        private String data_nascimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
	
        public Usuario() {
		super();
	}

	public Usuario(int id, String nome, String email, String telefone, String endereco , String data_nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
                this.data_nascimento = data_nascimento;
                
	}
    
	public Connection FazerConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetojava","postgres","556024");     
	}
	
	public void cadastrarCliente() throws SQLException{
		Statement st = FazerConexao().createStatement();
		String SQL = "insert into usuario(nome,email,telefone,endereco,data_nascimento) values('"+this.nome+"','"+this.email+"','"+this.telefone+"','"+this.endereco+"','"+this.data_nascimento+")";
		st.executeUpdate(SQL);
	}
	
	public Usuario pegaClientePeloCodigo(int id) throws SQLException{
		Connection con = FazerConexao();
		Statement executorSQL = con.createStatement();
		ResultSet dados = executorSQL.executeQuery("select * from usuario where id='"+id+"'");
		if(dados.next()){
			return new Usuario(dados.getInt("id"),dados.getString("nome"),dados.getString("email"),dados.getString("telefone"),dados.getString("endereco"),dados.getString("data_nascimento"));
		} else {
			return null;
		}
	}
        
        
     }
