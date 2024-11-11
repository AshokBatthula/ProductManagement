package com.pace.library.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pace.library.bean.*;
import com.pace.library.helper.DbConnector;

/**
 * Servlet implementation class ProductUpdateServlet
 */
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean  updateProduct(int id) throws ClassNotFoundException,SQLException{
		PreparedStatement pstatement = null;
		Connection connection = DbConnector.createConnection();
		String updQry="update productmanagement set price = price + price*0.10 where  id = ?";
		pstatement=null;
		pstatement=connection.prepareStatement(updQry);
		pstatement.setInt(1, id);
		int rows = pstatement.executeUpdate();
		boolean isUpdate=true;
		if(rows!=0) {//if row available,it is deleted
			isUpdate=true;//status of updation
			
		}else {
			isUpdate=false;
		}
		DbConnector.closeConnection();
		return isUpdate;
		
		
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product book = new Product();
		boolean rows = false;
		int id;
		
		id=Integer.parseInt(request.getParameter("id"));
		try{
			rows =updateProduct(id);
			
		}catch(Exception e){
			System.out.println("product data could not found");
		}
		if(rows==true){
			System.out.println("Product data is successfully updated");
			RequestDispatcher dis=request.getRequestDispatcher("updateSuccess.html");
			dis.forward(request, response);
		}else{
			System.out.println("product data could not found");
		
		}
	}

}
