package view;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import model.WebServerThread;


@SuppressWarnings({ "unused", "serial" })
public class MainWin extends JFrame implements ActionListener{
//----------------------继承了ActionListener--------
//利用下面的 actionPerformed方法进行实践监听---------------------


	/**
	 * @param args
	 */
	private JPanel jp,jp1,jp2,jp3;
	private JLabel jp1_jl1,jp1_jl2,jp1_jl3;
	private JTextField jp1_jtf1,jp1_jtf2,jp1_jtf3;
	private JButton jp1_jb1,jp1_jb2;
	private JLabel jp2_jl1,jp2_jl2;
	public static JLabel jp2_jl3,jp2_jl4;
	private JButton jp_jb1,jp_jb2;
	private JScrollPane jsp;
	public static JTextArea jta;

	public MainWin()
	{
		//处理最里面的jp1
		jp=new JPanel();
		jp.setLayout(null);
		jp_jb1=new JButton("开始");
		jp_jb1.addActionListener(this);
		jp_jb1.setBounds(195,200,100,40);

		jp_jb2=new JButton("退出");
		jp_jb2.addActionListener(this);
		jp_jb2.setBounds(345,200,100,40);

		jp.add(jp_jb1);
		jp.add(jp_jb2);

		//处理左上
		jp1=new JPanel();
		jp1.setBorder(BorderFactory.createTitledBorder("设置"));
		jp1.setBounds(10, 30, 300, 150);
		jp1.setLayout(null);
		jp1_jl1=new JLabel("端   口   号:",JLabel.CENTER);
		jp1_jl1.setBounds(15, 30, 100, 30);
		jp1_jl2=new JLabel("指定路径:",JLabel.CENTER);
		jp1_jl2.setBounds(15, 65, 100, 30);
		jp1_jl3=new JLabel("密码文件:",JLabel.CENTER);
		jp1_jl3.setBounds(15, 100, 100, 30);
		jp1_jtf1=new JTextField(10);
		jp1_jtf1.setBounds(120, 30, 100, 25);
		jp1_jtf2=new JTextField(10);
		jp1_jtf2.setBounds(120, 65, 100, 25);
		jp1_jtf3=new JTextField(10);
		jp1_jtf3.setBounds(120, 100, 100, 25);
		jp1_jb1=new JButton("...");
		jp1_jb1.setBounds(225, 70, 30, 20);
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton("...");
		jp1_jb2.setBounds(225, 105, 30, 20);
		jp1_jb2.addActionListener(this);
		jp1.add(jp1_jl1);
		jp1.add(jp1_jtf1);
		jp1.add(jp1_jl2);
		jp1.add(jp1_jtf2);
		jp1.add(jp1_jl3);
		jp1.add(jp1_jtf3);
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);

		//处理右上
		jp2=new JPanel();
		jp2.setBorder(BorderFactory.createTitledBorder("活动"));
		jp2.setBounds(330, 30, 300, 150);
		jp2.setLayout(null);
		jp2_jl1=new JLabel("发送:",JLabel.CENTER);
		jp2_jl1.setBounds(15, 45, 100, 30);
		jp2_jl3=new JLabel();
		jp2_jl3.setBounds(120, 45, 100, 30);

		jp2_jl2=new JLabel("收到:",JLabel.CENTER);
		jp2_jl2.setBounds(15, 95, 100, 30);
		jp2_jl4=new JLabel();
		jp2_jl4.setBounds(120, 95, 100, 30);
		jp2.add(jp2_jl1);
		jp2.add(jp2_jl2);
		jp2.add(jp2_jl3);
		jp2.add(jp2_jl4);

		//处理底部
		jp3=new JPanel();
		jp3.setBorder(BorderFactory.createTitledBorder("连接"));
		jp3.setBounds(10, 270, 620, 190);
		jp3.setLayout(null);
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jsp.setBounds(10, 20, 600, 160);
		jp3.add(jsp);

		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);

		this.add(jp);
		//窗口设置
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setTitle("Dialog");
		this.setSize(650, 500);
		this.setLocation(width/4, height/4-50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainWin mainwin=new MainWin();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp_jb1)//----------------------------检测事件来源-----------
		{
			//启动服务器，指定资源路径和监听端口号
			//new MyWebServer(jp1_jtf2.getText(),Integer.parseInt(jp1_jtf1.getText()));
			int port=Integer.parseInt(jp1_jtf1.getText());
			String path=jp1_jtf2.getText();
			try {
				System.out.println("我是服务器，在"+port+"监听");
				jta.append("服务器启动，在端口"+port+"监听"+"\n");
				ServerSocket ss=new ServerSocket(port);
				new ServerMonitor(path,ss).start();//start方法即启动了线程的run方法
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==jp_jb2)
		{

			this.dispose();
		}
		else if(e.getSource()==jp1_jb1)
		{
			//得到主目录-----选择目录-----
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.showOpenDialog(this);
			String path=jfc.getSelectedFile().getAbsolutePath();
			jp1_jtf2.setText(path);
		}
		else if(e.getSource()==jp1_jb2)
		{
			//得到密码文件
		}
	}

	class ServerMonitor extends Thread
	{
		String path;
		ServerSocket ss;
		public ServerMonitor(String path,ServerSocket ss)
		{
			this.path=path;
			this.ss=ss;
		}
		@SuppressWarnings("unchecked")
		public void startServer(String path,ServerSocket ss) {                //启动线程池
			@SuppressWarnings("rawtypes")
			ThreadLocal localSocket=new ThreadLocal();
			/*线程池，参数意思依次为：
			 * 线程池中所保存的线程数，包括空闲线程
			 * 线程池中允许的最大线程数
			 * 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。以秒为单位
			 * 执行前用于保持任务的队列。此队列仅由保持 execute 方法提交的 Runnable 任务
			 * 执行线程的服务
			 * */
			@SuppressWarnings({ "rawtypes" })
			ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 50, 3,TimeUnit.SECONDS, new ArrayBlockingQueue(10),new ThreadPoolExecutor.DiscardOldestPolicy());
			for (;;){
				try {
					System.out.println("haha");
					Socket s=ss.accept();
//					jta.append(s.toString());
					jta.append("客户端ip："+s.getRemoteSocketAddress().toString()+"\n"+"********************");
					jta.append("服务器端："+s.getLocalAddress().toString()+"\n");
					jta.append("服务器名："+s.getInetAddress().getHostName()+"\n");
					localSocket.set(s);          //得到客户端线程
					threadPool.execute(new WebServerThread((Socket)localSocket.get(),path));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		public void run()
		{
			this.startServer(path, ss);
		}
	}

}
