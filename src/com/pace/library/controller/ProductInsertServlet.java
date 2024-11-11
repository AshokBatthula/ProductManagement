package com.pace.library.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pace.library.bean.Product;
import com.pace.library.bean.*;
import com.pace.library.helper.DbConnector;

/**
 * Servlet implementation class ProductInsertServlet
 */
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int insertProductDetails(Product product) throws ClassNotFoundException,SQLException{
		PreparedStatement pstatement=null;
		Connection connection = DbConnector.createConnection();
		String insQry="insert into productmanagement values(?,?,?,?)";
		pstatement=connection.prepareStatement(insQry);
		pstatement.setInt(1, product.getId());
		pstatement.setString(2, product.getName());
		pstatement.setString(3, product.getBrand());
		pstatement.setFloat(4, product.getPrice());
		
		int rows=pstatement.executeUpdate();
		//System.out.println(rows + " Rows inserted");
		DbConnector.closeConnection();
		return rows;
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Product product = new Product();
		int rows=0;
		int id;
		String name,brand;
		float price;
		
		id=Integer.parseInt(request.getParameter("id"));
		name=request.getParameter("name");
		brand=request.getParameter("brand");
		price=Float.parseFloat(request.getParameter("price"));
		
		product.setId(id);
		product.setName(name);
		product.setBrand(brand);
		product.setPrice(price);
		
		System.out.println(product.getName());
		try{
			rows=insertProductDetails(product);
		}catch(Exception e){
			System.err.println("Product data could not inserted!");
		}
		if(rows==1){
			System.out.println("Product data inserted successfully");
			RequestDispatcher dis=request.getRequestDispatcher("insertSuccess.html");
			dis.forward(request, response);
		}else {
			System.out.println("Product data could not inserted");
		}
		
	}
		

}
