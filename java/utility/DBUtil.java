package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnect()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//class not found exception will arrived so it will implemented in try and catch
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rohan","root","rohan24");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}

}
