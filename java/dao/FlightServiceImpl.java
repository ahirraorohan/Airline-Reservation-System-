package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Flight;
import service.FlightService;
import utility.DBUtil;

public class FlightServiceImpl implements FlightService {

	@Override
	public Flight addFlight(Flight flight) {
		try(Connection con=DBUtil.getConnect();)
		
		{
			int status=0;
			String flight_insert_query="insert into Flight_Info2 values(?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(flight_insert_query);
			ps.setLong(1, flight.getFlightNumber());
			ps.setString(2, flight.getFlightType());
			ps.setString(3, flight.getFlightSource());
			ps.setString(4, flight.getFlightDestination());
			ps.setString(5, flight.getLayOffPoints());
			LocalDate lobj=flight.getFlyDate();
			java.sql.Date dof=java.sql.Date.valueOf(lobj);
			ps.setDate(6, dof);
			ps.setDouble(7, flight.getFlightCharge());
			status=ps.executeUpdate();
			if(status>0)
			{
				return flight;
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Flight> viewAllFlight() {
		// TODO Auto-generated method stub
		List<Flight> listOfFlight=new ArrayList();
		try(Connection con=DBUtil.getConnect())
				{
					String fetch_query="Select * from Flight_Info2";
					PreparedStatement ps=con.prepareStatement(fetch_query);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						long fno=rs.getLong(1);
						String fType=rs.getString(2);
						String src=rs.getString(3);
						String dest=rs.getString(4);
						String layOffs=rs.getString(5);
						java.sql.Date d1=rs.getDate(6);
						double fcost=rs.getDouble(7);
						
						//converting java.sql.Date into LocalDate
						LocalDate id=d1.toLocalDate();
						
						//creating flight object
						Flight flightObj=new Flight(fno, fType, src, dest, layOffs, id,fcost);
						
						//adding object into list
						listOfFlight.add(flightObj);
					
					}
					
				}
				catch(Exception e)
				{
					 System.out.println(e);
				}
		return listOfFlight;
	}

	@Override
	public int editFlight(long flightNumber, Flight newFlight) {
        int status=0;
        try(Connection con=DBUtil.getConnect())
        {
        	String update_query="update Flight_Info2 set"
        			+ " flight_type=?,"
        			+ " flightsrc=? , flight_dest=?,lay_off=?,"
        			+ "fly_date=?, flight_cost=? where flight_num=?";
        	
     PreparedStatement ps=con.prepareStatement(update_query);
     ps.setString(1, newFlight.getFlightType());
     ps.setString(2, newFlight.getFlightSource());
     ps.setString(3, newFlight.getFlightDestination());
     ps.setString(4, newFlight.getLayOffPoints());
     LocalDate ld=newFlight.getFlyDate();
     java.sql.Date d2=java.sql.Date.valueOf(ld);
     ps.setDate(5, d2);
     ps.setDouble(6,newFlight.getFlightNumber());
     ps.setLong(7, newFlight.getFlightNumber());
     status=ps.executeUpdate();
   }catch(Exception e)
        {
        	System.out.println(e);
        }
		
	return status;
	}

	@Override
	public int removeFlight(long flightNumber) {
		// TODO Auto-generated method stub
		int delStatus=0;
		try(Connection con=DBUtil.getConnect())
		{
			String delQuery="delete from Flight_Info2 where flight_num=?";
			PreparedStatement ps=con.prepareStatement(delQuery);
			ps.setLong(1, flightNumber);
			delStatus=ps.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return delStatus;
	}

	@Override
	public Flight getFlightByNumber(long flightNum) {
		Flight flightObj=null;
		   try(Connection con=DBUtil.getConnect())
		   {
			 String fetch_query_by_number="select * from Flight_Info2 where flight_num=?";
		PreparedStatement ps=con.prepareCall(fetch_query_by_number);	  
			ps.setLong(1,flightNum);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				long fno=rs.getLong(1);
				String ftype=rs.getString(2);
				String src=rs.getString(3);
				String dest=rs.getString(4);
				String layOffs=rs.getString(5);
				java.sql.Date d1=rs.getDate(6);
				double fcost=rs.getDouble(7);
				
				//converting java.sql.Date into LocalDate
				LocalDate ld=d1.toLocalDate();
				
				//creatiing flight object
			 flightObj=new Flight(fno, ftype, src, dest, layOffs, ld, fcost);	
			}
			   
			  }catch(Exception e)
		   {
			   System.out.println(e);
		   }
				
				
				return flightObj;
			}
	
	public List<Flight> getFlightByLocation(String s, String d,LocalDate doj)
	{
		List<Flight> list=new ArrayList();
		try(Connection con=DBUtil.getConnect())
		{
	String fetch="select * from Flight_Info where flightsrc=? and "
			+ "flight_dest=? and fly_date=?";		
		PreparedStatement ps=con.prepareStatement(fetch);
		ps.setString(1, s);
		ps.setString(2, d);
		ps.setDate(3, java.sql.Date.valueOf(doj));
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
		
			long fno=rs.getLong(1);
			String ftype=rs.getString(2);
			String src=rs.getString(3);
			String dest=rs.getString(4);
			String layOffs=rs.getString(5);
			java.sql.Date d1=rs.getDate(6);
			double fcost=rs.getDouble(7);
			
			//converting java.sql.Date into LocalDate
			LocalDate ld=d1.toLocalDate();
			
			//creatiing flight object
	Flight	 flightObj=new Flight(fno, ftype, src, dest, layOffs, ld, fcost);
	list.add(flightObj);
		}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return list;
		
		
		
		
	}
	
}
