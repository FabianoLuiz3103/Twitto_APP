package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.bean.Postagem;
import br.com.fiap.bean.UsuarioRede;

public class UsuarioRedeDAO{
	
	private Connection conexao;
	
	public UsuarioRedeDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void cadastrarUsuario(UsuarioRede usuario) {
		 PreparedStatement pstm = null;
		    String sql = "INSERT INTO usuarioRede (nome, idade, email, senha, dataCadastro) VALUES (?, ?, ?, ?, ?)";
		    

		    try {
		        pstm = conexao.prepareStatement(sql); 
		        java.util.Date dataCadastro = usuario.getDataCadastro();
		        java.sql.Date sqlDate = new java.sql.Date(dataCadastro.getTime());

		        pstm.setString(1, usuario.getNome());
		        pstm.setInt(2, usuario.getIdade());
		        pstm.setString(3, usuario.getEmail());
		        pstm.setString(4, usuario.getSenha());
		        pstm.setDate(5, sqlDate);

		        pstm.executeUpdate();

		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		        try {
		            if (pstm != null) {
		                pstm.close();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		
	}
	
	
	public List<UsuarioRede> listarTodos() {
		List<UsuarioRede> listaUsuarios = new ArrayList<UsuarioRede>();
		PreparedStatement pstm = null;
		ResultSet rst = null;
		String sql = "SELECT * FROM usuarioRede ORDER BY TO_NUMBER(SUBSTR(idusuarioRede, 2)) ";

		try {
			pstm = conexao.prepareStatement(sql);
			rst = pstm.executeQuery();

			while (rst.next()) {
				UsuarioRede usuario = new UsuarioRede();
				usuario.setIdUsuario(rst.getString("idUsuarioRede"));
				usuario.setNome(rst.getString("nome"));
				usuario.setIdade(rst.getInt("idade"));
				usuario.setEmail(rst.getString("email"));
				usuario.setSenha(rst.getString("senha"));
				usuario.setDataCadastro(rst.getDate("datacadastro"));
				usuario.setDataUpdate(rst.getDate("dataupdate"));

				listaUsuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rst != null) {
					rst.close();
				}
				if (pstm != null) {
					pstm.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaUsuarios;
	}
	
	public List<UsuarioRede> listarComPostagem(){
		 UsuarioRede usuario = null;
		    List<UsuarioRede> lista = new ArrayList<UsuarioRede>();
		    String sql = "SELECT U.nome, U.idade, U.email, U.datacadastro, U.dataupdate, P.idpostagem, P.mensagem, P.qtdcurtidas, P.datapostagem, P.dataupdate "
		            + "FROM usuarioRede U INNER JOIN postagem P ON U.idusuarioRede = P.usuario_idusuario order by U.idusuarioRede, TO_NUMBER(SUBSTR(P.idpostagem, 2))";

		    try {
		        try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
		            pstm.execute();
		            ResultSet rst = pstm.getResultSet();

		            while (rst.next()) {
		                if (usuario == null || !usuario.getEmail().equals(rst.getString("email"))) {
		                    UsuarioRede usu = new UsuarioRede();
		                    usu.setNome(rst.getString("nome"));
		                    usu.setIdade(rst.getInt("idade"));
		                    usu.setEmail(rst.getString("email"));
		                    usuario.setSenha(rst.getString("senha"));
		                    usu.setDataCadastro(rst.getDate("datacadastro"));
		                    usu.setDataUpdate(rst.getDate("dataupdate"));
		                    lista.add(usu);
		                    usuario = usu;
		                }

		                if (rst.getString("idpostagem") != null) { 
		                    Postagem posts = new Postagem();
		                    posts.setIdPostagem(rst.getString("idpostagem"));
		                    posts.setMensagem(rst.getString("mensagem"));
		                    posts.setQtdCurtidas(rst.getInt("qtdcurtidas"));
		                    posts.setDataPostagem(rst.getDate("datapostagem"));
		                    posts.setDataUpdate(rst.getDate("dataupdate"));
		                    usuario.setPosts(posts);
		                }
		            }

		            rst.close();
		            pstm.close();
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
		    return lista;
	}
	

	public void atualizarUsuario(String nome, int idade, String email, String senha, Date dataUpdate, String idUsuarioRede) {
		PreparedStatement pstm = null;
		String sql = "UPDATE usuarioRede U SET U.nome = ?, U.idade = ?, U.email = ?, U.senha = ?, U.dataupdate = ? WHERE U.idusuarioRede = ? ";
		try {
			pstm = conexao.prepareStatement(sql);

			pstm.setString(1, nome);
			pstm.setInt(2, idade);
			pstm.setString(3, email);
			pstm.setString(4, senha);
			pstm.setDate(5, new java.sql.Date(dataUpdate.getTime()));
			pstm.setString(6, idUsuarioRede);
			pstm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void excluirUsuario(String idUsuario) {
		PreparedStatement pstm = null;
		String sql = "DELETE FROM usuarioRede WHERE idusuarioRede= ?";

		try {
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, idUsuario);
			pstm.execute();
			

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public UsuarioRede buscar(String idUsuario) {
		UsuarioRede usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement(
					"SELECT * FROM usuarioRede WHERE idusuarioRede = ?");
			stmt.setString(1, idUsuario);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String idUsuarioRede = rs.getString("idusuarioRede");
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				String email = rs.getString("email");

				usuario = new UsuarioRede(nome, idade, email);
				usuario.setIdUsuario(idUsuarioRede);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

	public boolean validarUsuario(UsuarioRede usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM usuarioRede WHERE email = ? AND senha = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void alterarSenha(String senha, Date dataUpdate, String idUsuario) {
		PreparedStatement pstm = null;
		String sql = "UPDATE usuarioRede U SET U.senha = ?, U.dataupdate = ? WHERE U.idusuarioRede = ? ";
		try {
			pstm = conexao.prepareStatement(sql);

			pstm.setString(1, senha);
			pstm.setDate(2, new java.sql.Date(dataUpdate.getTime()));
			pstm.setString(3, idUsuario);
			pstm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
