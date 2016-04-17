package ftp.server;
import java.net.ServerSocket;       
import java.net.Socket;  
  
import org.apache.log4j.Logger;  
  
/** 
 * 角色——服务器A 
 * @author Leon 
 * 
 */  
public class ServerA{  
  
    public static void main(String[] args){  
        final String F_DIR = "c:/test";//根路径  
        final int PORT = 22;//监听端口号  
        Logger.getRootLogger();  
        Logger logger = Logger.getLogger("com");  
          
        try{  
            ServerSocket s = new ServerSocket(PORT);  
            logger.info("Connecting to server A...");  
            logger.info("Connected Successful! Local Port:"+s.getLocalPort()+". Default Directory:'"+F_DIR+"'.");  
          
            while( true ){  
                //接受客户端请求  
                Socket client = s.accept();  
                //创建服务线程  
                new ClientThread(client, F_DIR).start();  
            }  
        } catch(Exception e) {  
            logger.error(e.getMessage());  
            for(StackTraceElement ste : e.getStackTrace()){  
                logger.error(ste.toString());  
            }  
        }  
    }  
  
}  