package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Produto;

public class ProdutoDAO extends Banco implements DAO<Produto> {
	

    @Override
	public Produto get(int id) {
		Produto produto = null;
		try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = ("SELECT * "
					    + "FROM produto 	"
					    + "WHERE produto.id = " + id);
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				rs.beforeFirst();
				rs.next();
				produto = new Produto(
									 rs.getInt("id"), 
									 rs.getString("nome"), 
									 rs.getFloat("preco"),
						 		     rs.getString("fichaTecnica"), 
						 		     rs.getInt("quantidade"),
						 		     rs.getString("categoria"), 
						 		     rs.getString("imagemUrl")
					 		     );
			}
			st.close();
			System.out.println("Success! --- " + produto.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return produto;
	}

	@Override
	public void add(Produto produto) {
		try {
			Statement st = connection.createStatement();

			String sql = ("INSERT INTO produto (id, nome, preco, fichatecnica, quantidade, categoria, imagemurl) "
					    + "VALUES ('"
					    + produto.getId() +"', '"
					    + produto.getNome() + "', '" 
					    + produto.getPreco() + "', '"
					    + produto.getFichaTecnica() + "', '"
						+ produto.getQuantidade() + "', '"
						+ produto.getCategoria() + "', '"
					    + produto.getImagemUrl() +"');");
			st.executeUpdate(sql);
			System.out.println("Sucess! --- " + produto.toString());
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	@Override
	public void update(Produto produto) {
		try {
			Statement st = connection.createStatement();
			String sql = ("UPDATE produto SET "
					     + "nome = '"+ produto.getNome() +"', " 
					     + "preco = " + produto.getPreco() + ", "
					     + "fichaTecnica = " + produto.getFichaTecnica() + ", "
						 + "quantidade = " + produto.getQuantidade() + ", "
					     + "imagemUrl = " + produto.getImagemUrl()
					     + "WHERE produto.id = "+ produto.getId());
			st.executeUpdate(sql);
			System.out.println("Sucess! --- " + produto.toString());
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	@Override
	public void delete(Produto produto) {
		try {
			Statement st = connection.createStatement();
			String sql = ("DELETE FROM produto WHERE produto.id = " + produto.getId());
			st.executeUpdate(sql);
			st.close();
			System.out.println("Sucess! --- " + produto.toString());
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	
	public Produto[] getAll() {
		Produto[] produto = null;

		try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = ("SELECT * FROM produto");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				rs.last();
				produto = new Produto[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					produto[i] = new Produto(
							 rs.getInt("id"), 
							 rs.getString("nome"), 
							 rs.getFloat("preco"),
				 		     rs.getString("fichaTecnica"), 
				 		     rs.getInt("quantidade"),
				 		     rs.getString("categoria"), 
				 		     rs.getString("imagemUrl"));
				}
			}
			st.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
				
		return produto;
	}

	
	public int getIdMax() {
		// TODO Auto-generated method stub
		int id = 0;
		try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = ("SELECT MAX(id) FROM produto");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("max");
			}
			st.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return id;
	}

}