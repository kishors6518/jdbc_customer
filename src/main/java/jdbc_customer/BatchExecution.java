package jdbc_customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class BatchExecution {
	public static void main(String[] args)throws Exception {
		Customer customer1=new Customer();
		customer1.setId(1);
		customer1.setName("Kishor");
		customer1.setPhone(8329774543l);
		customer1.setAddress("Latur");
		Customer customer2=new Customer();
		
		customer2.setId(2);
		customer2.setName("Ajit");
		customer2.setPhone(9764985698l);
		customer2.setAddress("Latur");
		
		Customer customer3=new Customer();
		customer3.setId(3);
		customer3.setName("Omkar");
		customer3.setPhone(9850235656l);
		customer3.setAddress("Khed");
		
		List<Customer>list=new ArrayList<Customer>();
		list.add(customer1);
		list.add(customer2);
		list.add(customer3);
		
		String className="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/customerdb";
		String user="root";
		String pass="root";
		String insert="INSERT INTO CUSTOMER(ID,NAME,PHONE,ADDRESS)VALUES(?,?,?,?)";
		//1.Load Drover
		Class.forName(className);
		//2.Establish Connection
		Connection connection=DriverManager.getConnection(url, user, pass);
		//3.Create Statement
		PreparedStatement preparedStatement=connection.prepareStatement(insert);
		for (Customer customer : list) {
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setLong(3, customer.getPhone());
			preparedStatement.setString(4, customer.getAddress());
			
			preparedStatement.addBatch();			
		}
		//4.Execute Statement
		preparedStatement.executeBatch();
		System.out.println("Batch executed");
		//5.Close Connection
		connection.close();
		
	}
}
