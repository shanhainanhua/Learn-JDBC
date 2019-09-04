package 数据库课程设计;
import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  
 
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
 
public class Teacher extends JFrame implements ActionListener {  
 
   // 定义组件  
   JLabel jl2,jl,jl3,jl4 = null;  
   JTextField jtf,jtf2 = null;  
   JButton jbquery1,jbquery2,jbalter1,jbdelete1,jbinsert1= null;  
   JPanel jp1, jp2,jp3,jp4,jp5,jp6 = null;  
 
   DefaultTableModel model,model2 = null;  
   JTable table,table2 = null;  
   JScrollPane jsp,jsp2 = null;  
 
// public static void main(String[] args) {  
       // TODO Auto-generated method stub  
//     Teacher6 t = new Teacher6();  
// }  
 
   // 构造函数  
   public Teacher() {  
       // 创建组件       
       jl = new JLabel("请输入学号：");  
       jl2=new JLabel("请输入教工号：");    
         
       jl3=new JLabel("学生信息表：");  
       jl4=new JLabel("教师信息表：");  
         
       jtf = new JTextField(10);  
       jtf2 = new JTextField(10);  
       jbquery1= new JButton("查询1");  
       jbquery2 = new JButton("查询2");  
       
       jbalter1=new JButton("修改");
       jbdelete1=new JButton("删除");
       jbinsert1=new JButton("插入");
       // 设置监听  
       jbquery1.addActionListener(this);  
       jbquery2.addActionListener(this); 
       jbalter1.addActionListener(this);
       jbinsert1.addActionListener(this);
       jbdelete1.addActionListener(this);
       // 设置表格1  
       String[] colnames = { "姓名", "学号", "数学", "语文", "英语", "政治" };  
       model = new DefaultTableModel(colnames, 3);  
       table = new JTable(model);  
       jsp = new JScrollPane(table);  
       //设置表格2  
       String[] col = { "姓名", "教工号", "性别", "工资", "职称", "年龄" };  
       model2 = new DefaultTableModel(col, 3);  
       table2 = new JTable(model2);  
       jsp2 = new JScrollPane(table2);  
         
 
       jp1 = new JPanel();  
       jp2 = new JPanel();  
       jp3 = new JPanel();  
       jp4 = new JPanel();  
       jp5 = new JPanel();  
       jp6 = new JPanel();  
       jp5.setLayout(new BorderLayout());  
       jp6.setLayout(new BorderLayout());  
 
       jp1.add(jl);  
       jp1.add(jtf);  
       jp1.add(jbquery1);  
       jp1.add(jbalter1);
       jp1.add(jbdelete1);
       jp1.add(jbinsert1);
       jp1.setLayout(new FlowLayout(FlowLayout.LEFT));  
       jp1.setPreferredSize(new Dimension(20,20));  
         
       jp3.add(jl2);  
       jp3.add(jtf2);  
       jp3.add(jbquery2);  
       jp3.setLayout(new FlowLayout(FlowLayout.LEFT));  
 
       jp2.add(jsp);  
       jp4.add(jsp2);  
         
       jp5.add(jl3,BorderLayout.SOUTH);  
       jp6.add(jl4,BorderLayout.SOUTH);  
 
       this.add(jp1);  
       this.add(jp5);  
       this.add(jp2);  
       this.add(jp3);  
       this.add(jp6);  
       this.add(jp4);  
         
       this.setLayout(new GridLayout(6, 1));  
       this.setTitle("学生成绩管理系统—教师");  
       this.setSize(500, 500);  
       this.setLocation(150, 150);  
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
       this.setVisible(true);  
       this.setResizable(false);  
         
 
   }  
 
   @Override  
   public void actionPerformed(ActionEvent e) {  
 
       if (e.getActionCommand().equals("查询1")  &&!jtf.getText().isEmpty()) {                         
           // 当点击查询按钮1时，首先与数据库建立连接  
           GetSQL.ConnectSQL();  
           GetSQL.getdatastu(jtf.getText());  
           jtf.setText("");  
//         System.out.println(GetSQL.english);  
//         System.out.println(GetSQL.chinese);  
           // 将数据填入表格    
           int start=0;
           while(table.getValueAt(start, 0) != null)
           {
        	   start++;
           }
           table.setValueAt(GetSQL.name, start, 0);  
           table.setValueAt(GetSQL.num, start, 1);  
           table.setValueAt(GetSQL.math, start, 2);  
           table.setValueAt(GetSQL.chinese, start, 3);  
           table.setValueAt(GetSQL.english, start, 4);  
           table.setValueAt(GetSQL.zhengzhi, start, 5);                  
                 
       }else if (e.getActionCommand().equals("查询2")  &&!jtf2.getText().isEmpty())  
       {  
           // 当点击查询按钮1时，首先与数据库建立连接  
           GetSQL.ConnectSQL();  
           System.out.println(jtf2.getText());
           GetSQL.getdatatea(jtf2.getText());  
           jtf2.setText("");  
          
           // 将数据填入表格   
           int start=0;
           while(table2.getValueAt(start, 0) != null)
           {
        	   start++;
           }
           table2.setValueAt(GetSQL.teaname, start, 0);  
           table2.setValueAt(GetSQL.teanum, start, 1);  
           table2.setValueAt(GetSQL.sex, start, 2);  
           table2.setValueAt(GetSQL.salary, start, 3);  
           table2.setValueAt(GetSQL.zhicheng, start, 4);  
           table2.setValueAt(GetSQL.age, start, 5);  
       }else if(e.getActionCommand().equals("修改")&&!jtf.getText().isEmpty())
       {
    	   AlterStuInfo alterStuInfo=new AlterStuInfo(jtf.getText());
       }else if(e.getActionCommand().equals("删除")&&!jtf.getText().isEmpty())
       {
    	   GetSQL.ConnectSQL();
    	   GetSQL.deletestu(jtf.getText());
       }else if(e.getActionCommand().equals("插入"))
       {
    	   InsertStuInfo insertStuInfo=new InsertStuInfo();
       }
       
       else  
       {  
           JOptionPane.showMessageDialog(null , "请输入要查询的编号","提示消息",JOptionPane.WARNING_MESSAGE);  
       }  
 
   }  
}  

