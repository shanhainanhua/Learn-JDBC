package 数据库课程设计;

 import java.awt.*;  
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;  
  
public class Stu_UI extends JFrame implements ActionListener  
{     
	  String num=null,name=null,math=null,chinese=null,english=null,zhengzhi=null,mima=null;
  
         //定义组件  
        JButton jb1,jb2=null;  
        JPanel jp1,jp2,jp3=null;  
        JLabel jlb1,jlb2,jlb3,jlb4=null;  
        
        DefaultTableModel model= null;  
        JTable table= null;  
        JScrollPane jsp = null;  
        public static void main(String[] args) {  
            // TODO Auto-generated method stub  
//          UI  ui=new UI();  
        }  
          
        //构造函数  
        public  Stu_UI(String username)    //不能申明为void!!!!!否则弹不出新界面  
        {  
            jp1=new JPanel();  
     
   
          String[] colnames = { "姓名", "学号", "数学", "语文", "英语", "政治" };  
            model = new DefaultTableModel(colnames, 1);  
            table = new JTable(model);  
            jsp = new JScrollPane(table); 
            GetSQL.ConnectSQL();  
        	try {
        		PreparedStatement pStatement;
        		pStatement=GetSQL.connection.prepareStatement("select * from info where 用户名= ? ");
        		pStatement.setString(1, username);
        		ResultSet resultSet;
        		resultSet=pStatement.executeQuery();
        		if(resultSet.next())
        		{
        			mima=resultSet.getString(3);
        			num=resultSet.getString(4);
        			name=resultSet.getString(5);
        			math=resultSet.getString(6);
        			chinese=resultSet.getString(7);
        			english=resultSet.getString(8);
        			zhengzhi=resultSet.getString(9);
        		
        			
        		}
        	}catch (Exception e) {
				
        		e.printStackTrace();// TODO: handle exception
			}
           
            table.setValueAt(name,0, 0);  
            table.setValueAt(num,0, 1);  
            table.setValueAt(math,0, 2);  
            table.setValueAt(chinese, 0, 3);  
            table.setValueAt(english, 0, 4);  
            table.setValueAt(zhengzhi, 0, 5);  
            
            jp1.add(jsp);                  
            this.add(jp1);               
            //设置布局管理器    
            this.setTitle("学生成绩管理系统");  
            this.setSize(500,300);  
            this.setLocation(200, 200);       
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.setVisible(true);    
           
}  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            // TODO Auto-generated method stub  
              
        }  
}  
