package P2P;



import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingDialog extends JDialog {
    boolean flag=false;
    int threadNum;
    int port;
    JLabel jlblThreadNum = new JLabel();
    JSpinner jspnThreadNum = new JSpinner(new SpinnerNumberModel(5,1,10,1));
    JLabel jlblPort = new JLabel();
    JTextField jtfPort = new JTextField();
    JButton jbtnOK = new JButton();
    JButton jbtnCancel = new JButton();

    public SettingDialog() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public SettingDialog(JFrame owner) {
        super(owner,true);
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        jlblThreadNum.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jlblThreadNum.setText("最大线程数");
        jlblThreadNum.setBounds(new Rectangle(30, 17, 75, 21));
        this.getContentPane().setLayout(null);
        jspnThreadNum.setBorder(BorderFactory.createEtchedBorder());
        jspnThreadNum.setBounds(new Rectangle(123, 15, 63, 22));
        jbtnOK.addActionListener(new SettingDialog_jbtnOK_actionAdapter(this));
        jbtnCancel.addActionListener(new SettingDialog_jbtnCancel_actionAdapter(this));
        jlblPort.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnOK.setBackground(new Color(236, 247, 255));
        jbtnOK.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnOK.setBorder(BorderFactory.createRaisedBevelBorder());
        jbtnCancel.setBackground(new Color(236, 247, 255));
        jbtnCancel.setFont(new java.awt.Font("宋体", Font.PLAIN, 12));
        jbtnCancel.setBorder(BorderFactory.createRaisedBevelBorder());
        jtfPort.setBackground(Color.white);
        jtfPort.setBorder(BorderFactory.createEtchedBorder());
        this.getContentPane().setBackground(new Color(206, 227, 249));
        this.setTitle("设置");
        this.getContentPane().add(jspnThreadNum, null);
        jbtnCancel.setBounds(new Rectangle(213, 85, 71, 25));
        jbtnCancel.setText("取消");
        jbtnOK.setBounds(new Rectangle(132, 85, 71, 25));
        jbtnOK.setText("确定");
        jtfPort.setText("");
        jtfPort.setBounds(new Rectangle(123, 52, 62, 21));
        jlblPort.setText("端口号");
        jlblPort.setBounds(new Rectangle(32, 50, 64, 16));
        this.getContentPane().add(jlblThreadNum, null);
        this.getContentPane().add(jtfPort);
        this.getContentPane().add(jlblPort);
        this.getContentPane().add(jbtnCancel);
        this.getContentPane().add(jbtnOK);
        this.setSize(300,160);
        this.setLocation(300,260);
    }


    public void jbtnOK_actionPerformed(ActionEvent e) {
        if("".equals(jtfPort.getText().trim())){
            JOptionPane.showMessageDialog(this,"数据输入错误！");
            return;
        }
        threadNum=((Integer)jspnThreadNum.getValue()).intValue();
        port=Integer.parseInt(jtfPort.getText());
        flag=true;
        this.setVisible(false);
    }

    public void jbtnCancel_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }
}


class SettingDialog_jbtnCancel_actionAdapter implements ActionListener {
    private SettingDialog adaptee;
    SettingDialog_jbtnCancel_actionAdapter(SettingDialog adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnCancel_actionPerformed(e);
    }
}


class SettingDialog_jbtnOK_actionAdapter implements ActionListener {
    private SettingDialog adaptee;
    SettingDialog_jbtnOK_actionAdapter(SettingDialog adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnOK_actionPerformed(e);
    }
}
