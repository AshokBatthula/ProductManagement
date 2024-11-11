package com.pace.library.controller;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pace.library.bean.Product;
import com.pace.library.helper.DbConnector;


/**
 * Servlet implementation class ProductDeleteServlet
 */
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean deleteProductDetails(int id) throws ClassNotFoundException,SQLException{
		PreparedStatement pstatement = null;
		Connection connection = DbConnector.createConnection();
		String delQry="delete from productmanagement where  id=?";
		pstatement=connection.prepareStatement(delQry);
		pstatement.setInt(1, id);
		int rows=pstatement.executeUpdate();
		boolean isDelete=true;
		if(rows!=0) {
			isDelete=true;//status is deleted
		}else {
			isDelete=false;
		}
		//System.out.println(rows + " Rows deleted");
		DbConnector.closeConnection();
		return  isDelete;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Product product = new Product();
		boolean rows = false;
		int id;
		
		
		id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		out.println("Book id " + id);
		try {
			rows =deleteProductDetails(id);
		} catch (Exception e) {
			System.err.println("Product data could not deleted!");
		}
		if(rows==true){
			System.out.println("Book data successfully deleted");
			RequestDispatcher dis=request.getRequestDispatcher("deleteSuccess.html");
			dis.forward(request, response);
		}else{
			System.out.println("Product data coould not found");
		}
		
	}

}
