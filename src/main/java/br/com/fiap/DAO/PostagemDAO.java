package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.bean.Postagem;

public class PostagemDAO {

		private Connection conexao;

		public PostagemDAO(Connection conexao) {
			this.conexao = conexao;
		}

		public void cadastrarPost(Postagem post) {
			PreparedStatement pstm = null;
			String sql = "INSERT INTO postagem (mensagem, qtdcurtidas, datapostagem, usuario_idusuario) VALUES (?, ?, ?, ?)";
			try {

				pstm = conexao.prepareStatement(sql);
				

				java.util.Date dataPostagem = post.getDataPostagem();
				java.sql.Date sqlDate = new java.sql.Date(dataPostagem.getTime());
				pstm.setString(1, post.getMensagem());
				pstm.setInt(2, post.getQtdCurtidas());
				pstm.setDate(3, sqlDate);
				pstm.setString(4, post.getIdUsuario());

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

		public List<Postagem> listarPosts() {
			List<Postagem> listaPosts = new ArrayList<Postagem>();
			PreparedStatement pstm = null;
			ResultSet rst = null;
			String sql = "SELECT * FROM postagem ORDER BY TO_NUMBER(SUBSTR(idpostagem, 2)) DESC";

			try {
				pstm = conexao.prepareStatement(sql);
				rst = pstm.executeQuery();

				while (rst.next()) {
					Postagem post = new Postagem();
					post.setIdPostagem(rst.getString("idpostagem"));
					post.setMensagem(rst.getString("mensagem"));
					post.setQtdCurtidas(rst.getInt("qtdcurtidas"));
					post.setDataPostagem(rst.getDate("datapostagem"));
					post.setDataUpdate(rst.getDate("dataupdate"));
					post.setIdUsuario(rst.getString("usuario_idusuario"));

					listaPosts.add(post);
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
			return listaPosts;

		}
		
		public List<Postagem> listarPorUsuario(String idUsuario) {
			List<Postagem> listaPosts = new ArrayList<Postagem>();
			PreparedStatement pstm = null;
			ResultSet rst = null;
			String sql = "SELECT * FROM postagem WHERE usuario_idusuario = ? ORDER BY TO_NUMBER(SUBSTR(idpostagem, 2)) DESC";

			try {
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, idUsuario);
				rst = pstm.executeQuery();

				while (rst.next()) {
					Postagem post = new Postagem();
					post.setIdPostagem(rst.getString("idpostagem"));
					post.setMensagem(rst.getString("mensagem"));
					post.setQtdCurtidas(rst.getInt("qtdcurtidas"));
					post.setDataPostagem(rst.getDate("datapostagem"));
					post.setDataUpdate(rst.getDate("dataupdate"));
					post.setIdUsuario(rst.getString("usuario_idusuario"));

					listaPosts.add(post);
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
			return listaPosts;
		}

		public void atualizarPost(String mensagem, Date dataUpdate, String idPostagem) {
			PreparedStatement pstm = null;
			String sql = "UPDATE postagem P SET P.mensagem = ?, P.dataupdate = ? WHERE P.idpostagem = ? ";
			try {
				
				pstm = conexao.prepareStatement(sql);

				pstm.setString(1, mensagem);
				pstm.setDate(2, new java.sql.Date(dataUpdate.getTime()));
				pstm.setString(3, idPostagem);
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

		public void excluirPost(String idPostagem) {
			PreparedStatement pstm = null;
			String sql = "DELETE FROM postagem WHERE idpostagem = ?";

			try {
				pstm = conexao.prepareStatement(sql);
				pstm.setString(1, idPostagem);
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
		
		public Postagem buscar(String idPostagem) {
			Postagem postagem = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conexao.prepareStatement(
						"SELECT * FROM postagem INNER JOIN usuarioRede ON postagem.idusuario = usuarioRede.idusuarioRede WHERE postagem.idpostagem = ?");
				stmt.setString(1, idPostagem);
				rs = stmt.executeQuery();

				if (rs.next()) {
					String idPost = rs.getString("idpostagem");
					String mensagem = rs.getString("mensagem");
					String idUsuario = rs.getString("idusuarioRede");
					//String nome = rs.getString("nome");

					postagem = new Postagem(mensagem);
					postagem.setIdUsuario(idUsuario);
					postagem.setIdPostagem(idPost);
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
			return postagem;
		}
		
		 public boolean atualizarNumeroCurtidas(String idPostagem) {
		        PreparedStatement pstmt = null;
		        try {
		      

		            String sql = "UPDATE postagem SET qtdcurtidas = qtdcurtidas + 1 WHERE idpostagem = ?";
		            pstmt = this.conexao.prepareStatement(sql);
		            pstmt.setString(1, idPostagem);
		            int rowsUpdated = pstmt.executeUpdate();

		            return rowsUpdated > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
		        } finally {
		            
		            if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }
		
		
		public int obterNumeroCurtidas(String idPostagem) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int curtidas = 0;
	        try {
	      

	            String sql = "SELECT qtdcurtidas FROM postagem WHERE idPostagem=?";
	            pstmt = this.conexao.prepareStatement(sql);
	            pstmt.setString(1, idPostagem);
	            rs = pstmt.executeQuery();
	            
	           if(rs.next()) {
	        	   curtidas = rs.getInt("qtdcurtidas");
	           }

	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        } finally {
	            
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        return curtidas;
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

