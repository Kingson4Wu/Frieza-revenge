package kxw;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Kunxin_Wu
 */
public class GradeCounter extends JPanel {
/*成绩统计类GradeCounter，除了一些统计之外，还提供两个查看图像的按钮：
 * 1、查看成绩统计柱形图
 * 2、查看成绩统计饼图
 */
    private JLabel count;
    private JLabel[] level = new JLabel[5];
    private JButton cylindricity = new JButton("显示成绩统计柱形图");
    private JButton pie = new JButton("显示成绩统计饼图");
    private JButton quit = new JButton("打开主菜单");
    private int[] countSum = new int[5];
    private double[] Percent = new double[5];
    private Cylindricity cylind = new Cylindricity();
    private JFrame cylindFrame = new JFrame();
    private PiePicture piePic = new PiePicture();
    private JFrame piePicFrame = new JFrame();

    public GradeCounter(StudentGrade stuGra) {

        int n = stuGra.getGrade().length;
        double max;
        double min;
        double avg;
        double sum;
        min = max = sum = (double) stuGra.getGrade()[0];     
        for (int i = 0; i < 5; i++) {
            countSum[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            double temp = (double) stuGra.getGrade()[i];
            if (max < temp) {
                max = temp;
            }
            if (min > temp) {
                min = temp;
            }
            sum += temp;
            if (temp < 60) {
                countSum[0]++;
            } else if (temp < 70) {
                countSum[1]++;
            } else if (temp < 80) {
                countSum[2]++;
            } else if (temp < 90) {
                countSum[3]++;
            } else {
                countSum[4]++;
            }
        }
        avg = ((int) sum * 10 / n) / 10.00;
        count = new JLabel("最高分：" + max + "分，最低分：" + min + "分，平均分：" + avg + "分", JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            Percent[i] = ((int) countSum[i] * 10000 / n) / 100.00;
        }

        level[0] = new JLabel("不及格（分数<60）:" + countSum[0] + "人，占" + Percent[0] + "%", JLabel.CENTER);
        level[1] = new JLabel("及格（60<=分数<70）:" + countSum[1] + "人，占" + Percent[1] + "%", JLabel.CENTER);
        level[2] = new JLabel("中等（70<=分数<80）:" + countSum[2] + "人，占" + Percent[2] + "%", JLabel.CENTER);
        level[3] = new JLabel("良好（80<=分数<90）:" + countSum[3] + "人，占" + Percent[3] + "%", JLabel.CENTER);
        level[4] = new JLabel("优秀（90<=分数<100）:" + countSum[4] + "人，占" + Percent[4] + "%", JLabel.CENTER);

        JPanel countResult = new JPanel(new GridLayout(6, 1));
        countResult.add(count);
        for (int i = 0; i < 5; i++) {
            countResult.add(level[i]);
        }
        
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new FlowLayout());
        jpButtons.add(cylindricity);
        jpButtons.add(pie);
        jpButtons.add(quit);

        setLayout(new BorderLayout());
        add(countResult, BorderLayout.CENTER);
        add(jpButtons, BorderLayout.SOUTH);

        //显示柱状图
        cylindricity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] count = countSum;
                cylind.showCylindricity(count);

                cylindFrame.setVisible(true);
                // dispose();
            }
        });

        //显示饼图
        pie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piePic.showPiePicture(Percent);
                piePicFrame.setVisible(true);
            }
        });
        
        cylindFrame.add(cylind);
        cylindFrame.pack();
        cylindFrame.setTitle("成绩统计柱形图");

        piePicFrame.add(piePic);
        piePicFrame.pack();
        piePicFrame.setTitle("成绩统计饼图");

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Menu gradeMenu = new Menu();
                gradeMenu.setLocationRelativeTo(null);
                gradeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gradeMenu.setTitle("学生成绩分析程序");
                gradeMenu.setSize(400, 200);
                //gradeMenu.pack();
                gradeMenu.setVisible(true);
                // dispose();
            }
        });
    }
    //设置合适的大小
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }
}
