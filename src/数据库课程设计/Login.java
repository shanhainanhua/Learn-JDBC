package 数据库课程设计;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
  JPanel jp1,jp2,jp3,jp4=null;//面板
  JLabel username,userpassword,power=null;//标签
  JButton Btnlogin,Btnreset,Btnexit=null;//按钮
  JTextField jtf=null;//文本
  JPasswordField jpf=null;//密码
  JRadioButton jrb1,jrb2=null;  
  ButtonGroup bg=null;  
  
  //菜单项
  JMenuBar jMenuBar=null;
  JMenu jMenu=null;
  JMenuItem jmi1,jmi2=null;
  
  
  //设定用户名和密码  
static String userword;
static String pwd;
 
  public static void main(String[] args) {
	Login ms=new Login();  
 }

public Login()
{
	

	Btnlogin=new JButton("登录");
	Btnreset=new JButton("重置");
	Btnexit=new JButton("退出");
	Btnlogin.addActionListener(this);
	Btnreset.addActionListener(this);
	Btnexit.addActionListener(this);
	
	jMenuBar=new JMenuBar();
	jMenu=new JMenu("选项");
	jmi1=new JMenuItem("开始");
	jmi2=new JMenuItem("退出系统");
	jMenu.add(jmi1);
	jMenu.add(jmi2);
	jMenuBar.add(jMenu);
	
    jrb1=new JRadioButton("教师",true);  
    jrb2=new JRadioButton("学生");  
    bg=new ButtonGroup();  
    bg.add(jrb1);  
    bg.add(jrb2);  
   // jrb2.setSelected(true); 
    jp1=new JPanel();
	jp2=new JPanel();
	jp3=new JPanel();
	jp4=new JPanel();
	username=new JLabel("用户名");
	userpassword=new JLabel("密  码");
	power=new JLabel("权限");;
	jtf=new JTextField(10);	
	jpf=new JPasswordField(10);
	
	jp1.add(username);
	jp1.add(jtf);
	
	jp2.add(userpassword);
	jp2.add(jpf);
	
	jp3.add(power);
	jp3.add(jrb1);
	jp3.add(jrb2);
	
	jp4.add(Btnlogin);
	jp4.add(Btnreset);
	jp4.add(Btnexit);
	this.setJMenuBar(jMenuBar);
	this.add(jp1);
	this.add(jp2);
	this.add(jp3);
	this.add(jp4);
	
    //设置布局管理器  
    this.setLayout(new GridLayout(4,1));  
    //给窗口设置标题  
    this.setTitle("学生成绩管理系统");  
    //设置窗体大小  
    this.setSize(300,250);  
    //设置窗体初始位置  
    this.setLocation(200, 150);  
    //设置当关闭窗口时，保证JVM也退出  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    //显示窗体  
    this.setVisible(true);  
    this.setResizable(true);  
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
    if(e.getActionCommand()=="退出")
    	System.exit(0);
    else  if(e.getActionCommand()=="登录")  
    {  
    	if(!jtf.getText().isEmpty()&&!jpf.getText().isEmpty())
    	{
    		GetSQL.ConnectSQL();
        if(jrb1.isSelected())
        {
        	GetSQL.querytea(jtf.getText());
        	if(GetSQL.pwd==null)
        	{
        		this.clear();
        	}else
        	{
        		this.tealogin();
        	}
        
        }else if(jrb2.isSelected())
        {
        	GetSQL.querystu(jtf.getText());
        	if(GetSQL.pwd==null)
        	{
        		this.clear();
        	}else
        	{
        		this.stulogin();
        	}
        }
    	}else if(jtf.getText().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null, "请输入用户名", "提示消息", JOptionPane.WARNING_MESSAGE);
    		this.clear();
    	}else if(jpf.getText().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null, "请输入密码", "提示消息",JOptionPane.WARNING_MESSAGE);
    		this.clear();
    	}
    }
    else if(e.getActionCommand()=="重置")  
    {
    	  this.clear();
    }
}


public void clear()
{
	jtf.setText("");
	jpf.setText("");
}


//教师登录判断方法
public void tealogin()
{
	if(GetSQL.pwd.equals(jpf.getText()))
	{
		JOptionPane.showMessageDialog(null, "登录成功", "提示消息", JOptionPane.WARNING_MESSAGE);
		this.clear();
		dispose();
		Teacher t=new Teacher();
	}
	else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
	{
		JOptionPane.showMessageDialog(null, "请输入用户名和密码", "提示消息", JOptionPane.WARNING_MESSAGE);
	}
	else if(jtf.getText().isEmpty())
	{
		 JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
	}
	else if(jpf.getText().isEmpty())
	{
		  JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
    }
	else {
		  JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
          //清空输入框  
          this.clear();  
	}
}
public void stulogin()
{
	if(GetSQL.pwd.equals(jpf.getText()))
	{
		JOptionPane.showMessageDialog(null, "登录成功", "提示消息", JOptionPane.WARNING_MESSAGE);
	    this.clear();
	    dispose();
	    Stu_UI ui=new Stu_UI(jtf.getText());
	}
	else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
	{
		JOptionPane.showMessageDialog(null, "请输入用户名和密码", "提示消息", JOptionPane.WARNING_MESSAGE);
	}
	else if(jtf.getText().isEmpty())
	{
		 JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
	}
	else if(jpf.getText().isEmpty())
	{
		  JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
    }
	else {
		  JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
          //清空输入框  
          this.clear();  
	}
}

  
}
