package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import service.UserInterface;
import utility.DBUtil;

public class UserServletImpl  implements UserInterface{

	@Override
	public User addUser(User user) {
		int status=0;
		try(Connection con=DBUtil.getConnect();)
		{
			String inser_query="insert into user_details(user_name,user_password, user_phone,  user_dob,user_address ,user_type,user_gender) values(?,?,?,?,?,?,?)";
			
			PreparedStatement ps=con.prepareStatement(inser_query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setLong(3, user.getUserPhone());
			java.util.Date d1= (user.getUserDob());
			long timeInMillis=d1.getTime();
			java.sql.Date dob=new java.sql.Date(timeInMillis);
			ps.setDate(4, dob);
			ps.setString(5, user.getUserAddress());
			ps.setString(6, user.getUserType());
			ps.setString(7, user.getGender());
			status=ps.executeUpdate();
			
			
          
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(status>0)
		{
			return user;
		}return null;
		
		//String inser_query="insert into user_details(user_name,user_password, user_phone,  user_dob,user_address ,user_type,user_gender) values(?,?,?,?,?,?,?)";
		
		
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getLogin(String userName, String password) {
		// TODO Auto-generated method stub
		
		try(Connection con=DBUtil.getConnect())
		{
			String loginQuery="Select * from user_details where user_name=? and user_password=?";
			PreparedStatement ps=con.prepareStatement(loginQuery);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				long uid=rs.getLong(1);
				String un=rs.getString(2);
				String pw=rs.getString(3);
				long ph=rs.getLong(4);
				java.sql.Date d1=rs.getDate(5);
				String uadd=rs.getString(6);
				String utype=rs.getString(7);
				String gen=rs.getString(8);
				
				//to construct the user object we need to convert sql date into util
				long timeInMillis=d1.getTime();
				java.util.Date dob=new java.util.Date(timeInMillis);
				//user(long userId, String userName, String userPAssword, String userType,)
				//Date userDob, String gender
				
				User user=new User(uid,un,pw,utype,uadd,ph,dob,gen);
				
				return user;
			}
		}
		catch(Exception e)
		{
			
		}
			
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateUser(User newUser, long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
