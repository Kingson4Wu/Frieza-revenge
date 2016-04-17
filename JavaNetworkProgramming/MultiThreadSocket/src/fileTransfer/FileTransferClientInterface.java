package fileTransfer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;





public  class FileTransferClientInterface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ipAddress=new JTextField(5);//设置为5列;
	private JButton fileChose=new JButton("选择文件");
	private JButton send=new JButton("开始发送");
	private File[] files;
	
	
	   public FileTransferClientInterface() {
	       // JPanel jpButtons = new JPanel();
	       // jpButtons.setLayout(new FlowLayout());//设置JPanel的布局管理
	       // jpButtons.add(fileChose);
	        //jpButtons.add(send);
	       // Font font = new Font("kxw", Font.BOLD, 22);//定义创建字体
	      //  welcome.setFont(font);//设置字体

	        setLayout(new GridLayout(3, 1, 5, 5));////设置JFrame的布局管理器
	        add(ipAddress);
	       add(fileChose);
	       add(send);

	        
	       fileChose.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
        	   JFileChooser fileChooser = new JFileChooser(new File("E:"));
        	   fileChooser.setMultiSelectionEnabled(true);
         
        	   if (fileChooser.showOpenDialog(fileChose) == JFileChooser.APPROVE_OPTION) {

        		files=  fileChooser.getSelectedFiles();
        		
        		
        		
        		   
        		   
        		   
            }
           }
        });
	       
	       
	       
	       send.addActionListener(new ActionListener() {
	           @Override
	            public void actionPerformed(ActionEvent e) {
	        	
	        	   new TransferClient(files).service();  
	        		   
	        		  System.out.println(ipAddress.getText()+"---------------------------------"); 
	        		   
	            
	           }
	        });

	    }
	
	
	
}
