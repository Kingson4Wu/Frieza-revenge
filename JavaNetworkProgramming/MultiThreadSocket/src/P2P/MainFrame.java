package P2P;



import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.*;


public class MainFrame extends JFrame {
    JPanel contentPane;
    JButton jbtnSend = new JButton();
    JLabel jlblIP = new JLabel();
    JButton jbtnSetting = new JButton();
    JTabbedPane jtpTransFile = new JTabbedPane();
    TransFileManager tfm=new TransFileManager(jtpTransFile);
    
    
    
    public MainFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.getContentPane().setBackground(new Color(206, 227, 249));
        setSize(new Dimension(400, 300));
        setTitle("P2P文件传输软件");
        this.addWindowListener(new MainFrame_this_windowAdapter(this));
        //给窗口注册一个事件监听器,类在下面定义
        
        jbtnSend.setBackground(new Color(236, 247, 255));
        jbtnSend.setBounds(new Rectangle(14, 14, 85, 25));
        jbtnSend.setFont(new java.awt.Font("宋体", Font.PLAIN, 13));
        jbtnSend.setBorder(BorderFactory.createRaisedBevelBorder());
        //将按钮填充在以（14, 14）这个点为左上角，宽度为85，高度为25的矩形中。
        jbtnSend.setText("发送文件");
        jbtnSend.addActionListener(new MainFrame_jbtnSend_actionAdapter(this));
        //传进MainFrame类型参数this--------------------------------
        
        
        jlblIP.setText("本机IP：");
        jbtnSetting.setBackground(new Color(236, 247, 255));
        jbtnSetting.setBounds(new Rectangle(106, 14, 73, 25));
        jbtnSetting.setFont(new java.awt.Font("宋体", Font.PLAIN, 13));
        jbtnSetting.setBorder(BorderFactory.createRaisedBevelBorder());
        jbtnSetting.setText("设置");
        jbtnSetting.addActionListener(new MainFrame_jbtnSetting_actionAdapter(this));
        //设置凸起来的按钮，很多其他的swing也可用此方法
       // butten.setBorder(BorderFactory.createRaisedBevelBorder());
         //设置凹起来的按钮，很多其他的swing也可用此方法
           //  button.setBorder(BorderFactory.createLoweredBevelBorder());
        
        
        jtpTransFile.setBackground(new Color(206, 227, 249));
        jtpTransFile.setBounds(new Rectangle( 0, 54, 402, 246));
        jlblIP.setFont(new java.awt.Font("宋体", Font.PLAIN, 13));
        jlblIP.setBounds(new Rectangle(197, 18, 180, 16));
        contentPane.setBackground(new Color(206, 227, 249));
        contentPane.setToolTipText("");
        contentPane.add(jtpTransFile);
        contentPane.add(jbtnSend);
        contentPane.add(jbtnSetting);
        contentPane.add(jlblIP);
        tfm.start();
        byte [] ip=InetAddress.getLocalHost().getAddress();
        jlblIP.setText("本机IP："+(ip[0]&0xff)+"."+(ip[1]&0xff)+"."+(ip[2]&0xff)+"."+(ip[3]&0xff));
    }




    public void jbtnSetting_actionPerformed(ActionEvent e) {
        SettingDialog sd=new SettingDialog(this);
        sd.jtfPort.setText(String.valueOf(tfm.port));
        sd.jspnThreadNum.setValue(new Integer(tfm.maxThreadNum));
        //sd.show();
        
        sd.setVisible(true);
        if(sd.flag){
            if(tfm.port!=sd.port){
                tfm.setPort(sd.port);
            }
            tfm.setMaxThreadNum(sd.threadNum);
        }
    }

    public void jbtnSend_actionPerformed(ActionEvent e) {
        TransFileDialog tfd=new TransFileDialog(this);
        //tfd.show();
        tfd.setVisible(true);
        if(tfd.flag){
            for(int i=0;i<tfd.files.length;i++){
                tfm.sendFile(tfd.serverName,tfd.port,tfd.files[i].getAbsolutePath(),tfd.message);
            }
        }
    }

    public void this_windowClosed(WindowEvent e) {
        tfm.close();
    }
}

class MainFrame_this_windowAdapter extends WindowAdapter {
    private MainFrame adaptee;
    MainFrame_this_windowAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosed(WindowEvent e) {
        adaptee.this_windowClosed(e);
    }
}


class MainFrame_jbtnSend_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jbtnSend_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnSend_actionPerformed(e);
    }
}


class MainFrame_jbtnSetting_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jbtnSetting_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnSetting_actionPerformed(e);
    }
}



