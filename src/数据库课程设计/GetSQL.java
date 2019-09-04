package 数据库课程设计;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GetSQL {
    static String userword;
    static String pwd;
    
    static String username;
    static String mima;
    static String num;
    static String english;
    static String name;
    static String chinese;
    static String zhengzhi;
    static String math;
    
    static String age;
    static String salary;
    static String sex;
    static String zhicheng;
    static String teanum;
    static String teaname;
    
    static Connection connection=null;
    static PreparedStatement pStatement=null;
    static ResultSet resultSet=null;
    public static void ConnectSQL() {
		try {
			 Class.forName("com.mysql.jdbc.Driver"); 
			 connection = DriverManager.getConnection(
			            "jdbc:mysql://localhost:3306/test","root","wt369258147wt");
			             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
              System.out.println("The SQL is connected");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    public static void  querystu(String username) {
		try {
			pStatement=connection.prepareStatement("select * from info where 用户名= ? ");
			pStatement.setString(1, username);
			resultSet=pStatement.executeQuery();
			   while(resultSet.next()) {
				userword=resultSet.getString(2);
				pwd=resultSet.getString(3);
				System.out.println("成功从数据库获取到密码和用户名");
				System.out.println(userword+"\t"+pwd+"\t");
			}
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
    
    public static void alterstu(String num1,String math1,String chinese1,String english1,String zhengzhi1) {
    	 String username1=null,mima1 = null,name1=null;
    	try {
    		PreparedStatement pStatement;
    		pStatement=connection.prepareStatement("select * from info where 学号=? ");
    		pStatement.setString(1, num1);
    		resultSet=pStatement.executeQuery();
    		if(resultSet.next())
    		{
    			username1=resultSet.getString(2);
    			mima1=resultSet.getString(3);
    			name1=resultSet.getString(5);
    		}
    	}catch (Exception e1) {
			// TODO: handle exception
    		e1.printStackTrace();
		}
    	
		try {
			PreparedStatement pStatement2;
			pStatement2 = connection.prepareStatement("delete from info where 学号= ? ");	
			pStatement2.setString(1, num1);
		    pStatement2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement statement3;
		try {
			statement3 = connection.prepareStatement("INSERT INTO info VALUES(?,?,?,?,?,?,?,?,?)");  
			statement3.setString(1, "0");
        statement3.setString(2, username1);
        statement3.setString(3,mima1 );
        statement3.setString(4, num1);
        statement3.setString(5, name1);
        statement3.setString(6, math1);
        statement3.setString(7, chinese1);
        statement3.setString(8, english1);
        statement3.setString(9, zhengzhi1);
        statement3.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JOptionPane.showMessageDialog(null, "修改学生信息成功", "提示消息", JOptionPane.WARNING_MESSAGE);
    }
    public static void deletestu(String username) {
    	try {
			pStatement=connection.prepareStatement("delete from info where 学号= ? ");
			pStatement.setString(1, username);
			pStatement.executeUpdate();
			JOptionPane.showMessageDialog(null,"删除学生信息成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
			}
		catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
    public static void insertstu(String username,String mima,String num,String stuname,String math,String chinese,String english,String zhengzhi) {
	 	try {
	 		PreparedStatement statement=connection.prepareStatement("INSERT INTO info VALUES(?,?,?,?,?,?,?,?,?)");
	        statement.setString(1, "0");
	        statement.setString(2, username);
            statement.setString(3,mima );
            statement.setString(4, num);
            statement.setString(5, stuname);
            statement.setString(6, math);
            statement.setString(7, chinese);
            statement.setString(8, english);
            statement.setString(9, zhengzhi);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "添加学生信息成功", "提示消息", JOptionPane.WARNING_MESSAGE);
	 	}catch (SQLException e) {
			// TODO: handle exception
	 		e.printStackTrace();
		}
	}
    
    public static void  querytea(String name) {
		try {
			pStatement=connection.prepareStatement("select * from info_tea where 用户名= ? ");
			pStatement.setString(1, name);
			resultSet=pStatement.executeQuery();
			while(resultSet.next()) {
				userword=resultSet.getString(2);
				pwd=resultSet.getString(3);
				System.out.println("成功从数据库获取到密码和用户名");
				System.out.println(userword+"\t"+pwd+"\t");
			}
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}
    
    public static void getdatastu(String s) {
    	try {
    		pStatement=connection.prepareStatement("select * from info where 学号=? ");
    		pStatement.setString(1, s);
    		resultSet=pStatement.executeQuery();
    		if(resultSet.next())
    		{
    			username=resultSet.getString(2);
    			mima=resultSet.getString(3);
    			num=resultSet.getString(4);
    			name=resultSet.getString(5);
    			math=resultSet.getString(6);
    			chinese=resultSet.getString(7);
    			english=resultSet.getString(8);
    			zhengzhi=resultSet.getString(9);
    		}else
    		{
    			JOptionPane.showMessageDialog(null, "没有此学生，请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);
    		}
    	}catch (Exception e1) {
			// TODO: handle exception
    		e1.printStackTrace();
		}
	}
    
    public static void  getdatatea(String s) {
    	try {
    		pStatement=connection.prepareStatement("select * from info_tea where 教师编号号=?");
    		pStatement.setString(1, s);
    		resultSet=pStatement.executeQuery();
    		if(resultSet.next())
    		{
    			teanum=resultSet.getString(4);
    			teaname=resultSet.getString(5);
    			sex=resultSet.getString(6);
    			salary=resultSet.getString(7);
    			zhicheng=resultSet.getString(8);
    			age=resultSet.getString(9);
    		}else {
    			JOptionPane.showMessageDialog(null, "没有此教师，请重新输入", "提示消息", JOptionPane.WARNING_MESSAGE);
    		}
    	}catch (Exception e1) {
			// TODO: handle exception
    		e1.printStackTrace();
		}
		
	}
}
