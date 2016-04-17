package fileTransfer;
import javax.swing.JFrame;

/**
 *
 * @author Kunxin_Wu
 */

public class FileTransferStart {
//主类，调用程序的开始运行界面，使用类Menu
    
    public static void main(String[] args) {

    	FileTransferClientInterface client = new FileTransferClientInterface();
    	
        client.setLocationRelativeTo(null);
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.setTitle("文件传输客户端");
        client.setSize(400, 200);
        //client.pack();
        client.setVisible(true);
    }
}
