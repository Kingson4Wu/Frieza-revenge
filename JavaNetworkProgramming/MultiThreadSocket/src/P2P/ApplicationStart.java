package P2P;



import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;

public class ApplicationStart {
    boolean packFrame = false;

    /**
     * Construct and show the application.
     */
    public ApplicationStart() {
        MainFrame frame = new MainFrame();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();//设置 Frame 为可以容纳所有组件的最小尺寸
        } else {
            frame.validate();//更新之后显示
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//得到屏幕分辨率
        
        //Toolkit是一个工具类.不需要实例化.getDefaultToolkit()是他的一个静态方法
        	//这个方法的 返回值(在此处为对象)还有一个方法getScreenSize(),最后的这个方法返回一个Dimension类型的对象.
        //是返回当先分辨率的.
        
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        //显示在屏幕中央
        frame.setVisible(true);
    }

    /**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                    
                    
                    
//这是设置图形界面外观的.java的图形界面外观有3种,默认是java的金属外观,还有就是windows系统,motif系统外观.
//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
// 这是把外观设置成你所使用的平台的外观,也就是你这个程序在哪个平台运行,显示的窗口,对话框外观将是哪个平台的外观.        
                    
                    

                    
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new ApplicationStart();
            }
        });
    }
}

//解释：SwingUtilities.invokeLater()方法使事件派发线程上的可运行对象排队。
//当可运行对象排在事件派发队列的队首时，就调用其run方法。
//其效果是允许事件派发线程调用另一个线程中的任意一个代码块。



//还有一个方法SwingUtilities.invokeAndWait()方法，它也可以使事件派发线程上的可运行对象排队。  
//他们的不同之处在于：SwingUtilities.invokeLater()在把可运行的对象放入队列后就返回，
//而SwingUtilities.invokeAndWait()一直等待知道已启动了可运行的run方法才返回。
//如果一个操作在另外一个操作执行之前必须从一个组件获得信息，则应使用SwingUtilities.invokeAndWait()方法。
