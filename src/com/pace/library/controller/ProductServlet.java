package com.pace.library.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pace.library.bean.Product;
import com.pace.library.dao.ProductDao;
import com.pace.library.helper.DbConnector;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<Product> getProducts() throws ClassNotFoundException,SQLException{
		
		ArrayList<Product> productList = null;
			//Process to get books from table into bbookList:
		//Get the book data from table
		//.Add each book to BookList.
		//3.Return BookList
			try {
				//Open connection
				Connection connection = DbConnector.createConnection();
				PreparedStatement pstatement = connection.prepareStatement("select * from productmanagement");
				ResultSet resultSet = pstatement.executeQuery();
				//Book book;
				productList=new ArrayList<Product>();
				while(resultSet.next()) {
					int pId;
					String pName;
					String pBrand;
					float price;
					//declare the pojo
					Product product=new Product();
					//get the row details
					pId=resultSet.getInt(1);
					pName=resultSet.getString(2);
					pBrand=resultSet.getString(3);
					price=resultSet.getFloat(4);
					//Add this data to book bean
					//set the pojo with retrieved values from the row
					product.setId(pId);
					product.setName(pName);
					product.setBrand(pBrand);
					product.setPrice(price);
					//add the book to booklist
					productList.add(product);
					product=null;
				}//end of while loop
				
					
			}
			catch(SQLException sqex) {
				System.out.println(sqex.getMessage());
				
			}finally {
				DbConnector.closeConnection();
			}
			return productList;	
		}

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product product=new Product();
		ArrayList<Product> proList=new ArrayList<Product>();
		ProductDao productDao=new ProductDao();
		try{
			proList=productDao.getProducts();
			HttpSession session=request.getSession();
			session.setAttribute("proList", proList);
			
			RequestDispatcher dis=request.getRequestDispatcher("showProductDetails.jsp");
			dis.forward(request, response);
			
		}catch(Exception e){
			System.out.println("Product could not found");
			System.out.println(proList.size());
		}
		
	}

}
