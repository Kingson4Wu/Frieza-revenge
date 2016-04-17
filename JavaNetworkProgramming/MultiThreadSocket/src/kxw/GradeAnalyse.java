package kxw;
import javax.swing.JFrame;

/**
 *
 * @author Kunxin_Wu
 */

public class GradeAnalyse {
//主类，调用程序的开始运行界面，使用类Menu
    
    public static void main(String[] args) {

        Menu gradeMenu = new Menu();
        gradeMenu.setLocationRelativeTo(null);
        gradeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeMenu.setTitle("学生成绩分析程序");
        gradeMenu.setSize(400, 200);
        //gradeMenu.pack();
        gradeMenu.setVisible(true);
    }
}
