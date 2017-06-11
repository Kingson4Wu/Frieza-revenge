package P2P;



import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TransFilePanel extends JPanel {
    int status;
    boolean fileSender = false;
    boolean isClosed = false;
    String fileName;
    boolean isConfirm = false;
    boolean isCanneled=false;
    long transFileLength=0;
    JProgressBar jpgbFileTrans = new JProgressBar();
    JButton jbtnOK = new JButton();
    JButton jbtnCannel = new JButton();
    JTextArea jtaFileTransStatus = new JTextArea();
    public TransFilePanel(boolean fileSender) {
        this.fileSender = fileSender;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        jpgbFileTrans.setBounds(new Rectangle(19, 104, 209, 18));
        jbtnOK.setBackground(new Color(236, 247, 255));
        jbtnOK.setBounds(new Rectangle(233, 101, 67, 25));
        jbtnOK.setText("接收");
        jbtnOK.addActionListener(new TransFilePanel_jbtnOK_actionAdapter(this));
        jbtnCannel.setBackground(new Color(236, 247, 255));
        jbtnCannel.setBounds(new Rectangle(306, 101, 67, 25));
        jbtnCannel.setText("取消");
        jbtnCannel.addActionListener(new
                                     TransFilePanel_jbtnCannel_actionAdapter(this));
        this.setBackground(new Color(206, 227, 249));
        this.setBorder(BorderFactory.createEtchedBorder());
        jtaFileTransStatus.setBackground(new Color(206, 227, 249));
        jtaFileTransStatus.setDisabledTextColor(Color.orange);
        jtaFileTransStatus.setText("");
        jtaFileTransStatus.setWrapStyleWord(true);
        jtaFileTransStatus.setBounds(new Rectangle(20, 33, 345, 55));
        this.add(jpgbFileTrans);
        this.add(jbtnOK);
        this.add(jbtnCannel);
        this.add(jtaFileTransStatus);
        jpgbFileTrans.setVisible(false);
        jbtnOK.setVisible(false);
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
        case SocketThread.FILE_TRANS_STATUS_FILENAME: {
            jbtnOK.setVisible(false);
            jbtnCannel.setVisible(false);
            break;
        }
        case SocketThread.FILE_TRANS_STATUS_WAITFORCONFIRM: {
            if (fileSender) {
                jbtnOK.setVisible(false);
                jbtnCannel.setVisible(true);
                jpgbFileTrans.setVisible(false);
            } else {
                jbtnOK.setVisible(true);
                jbtnCannel.setVisible(true);
                jpgbFileTrans.setVisible(false);
            }
            break;
        }
        case SocketThread.FILE_TRANS_STATUS_CONTEXT: {
            jbtnOK.setVisible(false);
            jbtnCannel.setVisible(true);
            jpgbFileTrans.setVisible(true);
            break;
        }
        case SocketThread.FILE_TRANS_STATUS_SUCCESS:
        case SocketThread.FILE_TRANS_STATUS_FAIL: {
            jbtnCannel.setText("关闭");
            jbtnOK.setVisible(false);
            jbtnCannel.setVisible(true);
            jpgbFileTrans.setVisible(false);
            break;
        }
        }
    }

    public int getStatus() {
        return status;
    }

    public void jbtnOK_actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        File aFile;
        jfc.showSaveDialog(this);
        aFile = jfc.getSelectedFile();
        if (aFile != null) {
            fileName = jfc.getSelectedFile().getAbsolutePath();
            isConfirm = true;
        }
    }

    public void jbtnCannel_actionPerformed(ActionEvent e) {
        if("取消".equals(jbtnCannel.getText())){
            isCanneled=true;
        }
        else{
            isClosed=true;
        }
    }
}


class TransFilePanel_jbtnCannel_actionAdapter implements ActionListener {
    private TransFilePanel adaptee;
    TransFilePanel_jbtnCannel_actionAdapter(TransFilePanel adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnCannel_actionPerformed(e);
    }
}


class TransFilePanel_jbtnOK_actionAdapter implements ActionListener {
    private TransFilePanel adaptee;
    TransFilePanel_jbtnOK_actionAdapter(TransFilePanel adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jbtnOK_actionPerformed(e);
    }
}
