package P2P;



import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;

public class TransFileDialog extends JDialog {
    File [] files;
    boolean flag=false;
    String serverName;
    int port;
    String message;
    Object [] items;
    public TransFileDialog(JFrame owner) {
        super(owner,true);
        this.items=items;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jlblServerName.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jlblServerName.setText("对方IP");
        jlblServerName.setBounds(new Rectangle(32, 22, 73, 26));
        this.getContentPane().setLayout(null);
        jbtnCancel.setBackground(new Color(236, 247, 255));
        jbtnCancel.setBounds(new Rectangle(294, 213, 71, 25));
        jbtnCancel.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnCancel.setBorder(BorderFactory.createRaisedBevelBorder());
        jbtnCancel.setText("取消");
        jbtnCancel.addActionListener(new
                                     TransFileDialog_jbtnCancel_actionAdapter(this));
        jbtnOK.setBackground(new Color(236, 247, 255));
        jbtnOK.setBounds(new Rectangle(195, 214, 71, 25));
        jbtnOK.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnOK.setBorder(BorderFactory.createRaisedBevelBorder());
        jbtnOK.setText("确定");
        jbtnOK.addActionListener(new TransFileDialog_jbtnOK_actionAdapter(this));
        jbtnFile.setBackground(new Color(236, 247, 255));
        jbtnFile.setBounds(new Rectangle(297, 116, 71, 25));
        jbtnFile.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnFile.setBorder(BorderFactory.createRaisedBevelBorder());
        jbtnFile.setText("浏览");
        jbtnFile.addActionListener(new TransFileDialog_jbtnFile_actionAdapter(this));
        jtfFile.setBackground(Color.white);
        jtfFile.setBorder(BorderFactory.createEtchedBorder());
        jtfFile.setText("");
        jtfFile.setBounds(new Rectangle(127, 118, 164, 21));
        jlblFile.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jlblFile.setText("文件");
        jlblFile.setBounds(new Rectangle(32, 120, 34, 16));
        jtfPort.setBackground(Color.white);
        jtfPort.setBorder(BorderFactory.createEtchedBorder());
        jtfPort.setText("5000");
        jtfPort.setBounds(new Rectangle(129, 72, 62, 21));
        jlblPort.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jlblPort.setText("对方端口号");
        jlblPort.setBounds(new Rectangle(32, 67, 67, 31));
        jtfServerName.setBackground(Color.white);
        jtfServerName.setBorder(BorderFactory.createEtchedBorder());
        this.getContentPane().setBackground(new Color(206, 227, 249));
        this.setTitle("发送文件");
        jlblMessage.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jlblMessage.setText("简短附言");
        jlblMessage.setBounds(new Rectangle(32, 169, 75, 25));
        jtfMessage.setBackground(Color.white);
        jtfMessage.setBorder(BorderFactory.createEtchedBorder());
        jtfMessage.setText("");
        jtfMessage.setBounds(new Rectangle(126, 169, 225, 29));
        this.getContentPane().add(jtfServerName);
        this.getContentPane().add(jtfPort);
        this.getContentPane().add(jlblFile);
        this.getContentPane().add(jlblServerName, null);
        this.getContentPane().add(jtfFile);
        this.getContentPane().add(jbtnFile);
        this.getContentPane().add(jlblPort);
        this.getContentPane().add(jbtnCancel);
        this.getContentPane().add(jbtnOK);
        this.getContentPane().add(jlblMessage);
        this.getContentPane().add(jtfMessage);
        jtfServerName.setText("");
        jtfServerName.setBounds(new Rectangle(127, 25, 220, 21));
        this.setSize(400,300);
        this.setLocation(300,300);
    }
    JLabel jlblServerName = new JLabel();
    JTextField jtfServerName = new JTextField();
    JLabel jlblPort = new JLabel();
    JTextField jtfPort = new JTextField();
    JLabel jlblFile = new JLabel();
    JTextField jtfFile = new JTextField();
    JButton jbtnFile = new JButton();
    JButton jbtnOK = new JButton();
    JButton jbtnCancel = new JButton();
    JLabel jlblMessage = new JLabel();
    JTextField jtfMessage = new JTextField();
    public void jbtnFile_actionPerformed(ActionEvent e) {
        JFileChooser jfc=new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        String fileName="";
        jfc.showOpenDialog(this);
        files=jfc.getSelectedFiles();
        for(int i=0;i<files.length;i++){
            fileName=fileName+files[i].getAbsolutePath()+";";
        }
        jtfFile.setText(fileName);
    }

    public void jbtnOK_actionPerformed(ActionEvent e) {
        if(jtfServerName.getText().trim().equals("")||jtfPort.getText().trim().equals("")||jtfFile.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this,"数据填写错误！");
            return;
        }
        serverName=jtfServerName.getText();
        port=Integer.parseInt(jtfPort.getText());
        message=jtfMessage.getText();
        flag=true;
        this.setVisible(false);
    }

    public void jbtnCancel_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }
}


class TransFileDialog_jbtnCancel_actionAdapter implements ActionListener {
    private TransFileDialog adaptee;
    TransFileDialog_jbtnCancel_actionAdapter(TransFileDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnCancel_actionPerformed(e);
    }
}


class TransFileDialog_jbtnOK_actionAdapter implements ActionListener {
    private TransFileDialog adaptee;
    TransFileDialog_jbtnOK_actionAdapter(TransFileDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnOK_actionPerformed(e);
    }
}


class TransFileDialog_jbtnFile_actionAdapter implements ActionListener {
    private TransFileDialog adaptee;
    TransFileDialog_jbtnFile_actionAdapter(TransFileDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnFile_actionPerformed(e);
    }
}
