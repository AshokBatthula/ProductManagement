package com.pace.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pace.library.bean.Product;
import com.pace.library.helper.DbConnector;

public class ProductDao {
	private Connection connection=null;
	private PreparedStatement pstatement=null;
	private ResultSet resultSet=null;

	ArrayList<Product> productList=null;
    
	//************************Get book Data*********************//
	public ArrayList<Product> getProducts() throws ClassNotFoundException,SQLException{
	
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
	//****************show list of books**************//
	public void showProductList(ArrayList<Product>productList2) {
		System.out.println("In DAO layer");
		for(Product product:productList) {
			System.out.print(product.getId());
			System.out.print("\t"+product.getName());
			System.out.print("\t"+product.getBrand());
			System.out.println(product.getPrice());
		}
	}

}
