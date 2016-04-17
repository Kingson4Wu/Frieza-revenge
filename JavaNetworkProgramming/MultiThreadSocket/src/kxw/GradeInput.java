package kxw;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Kunxin_Wu
 */
public class GradeInput extends JFrame {
//课程成绩录入窗口
//使用了定义的类InputField（此类用于显示班级人员名单和对应成绩输入框）
    
    private JLabel course;
    private JLabel defaultZero;
    private JButton sure = new JButton("确定");
    private JButton cancel = new JButton("取消并返回主菜单");
    private InputField gradeIn;
    private String gradeName2;
    private int n;
    private String courseSub;
    Font font = new Font("kxw", Font.BOLD, 20);

    public GradeInput() {
        this.course = null;
        this.gradeName2 = null;
    }

    public GradeInput(String course, ArrayList student, String gradeName) {
        this.gradeName2 = gradeName;
        this.courseSub = course;
        this.course = new JLabel("请输入" + this.gradeName2 + "\n 各位同学" + course + "的课程成绩", JLabel.CENTER);
        this.defaultZero = new JLabel("(！注意：不输入成绩将默认为60分！)", JLabel.CENTER);
        this.course.setFont(font);
        this.defaultZero.setFont(font);

        JPanel tip = new JPanel(new GridLayout(2, 1));
        tip.add(this.course);
        tip.add(this.defaultZero);
        this.n = student.size();
        this.gradeIn = new InputField(n, student);

        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new FlowLayout());
        jpButtons.add(sure);
        jpButtons.add(cancel);


        setLayout(new BorderLayout());
        add(tip, BorderLayout.NORTH);
        add(this.gradeIn, BorderLayout.CENTER);
        add(jpButtons, BorderLayout.SOUTH);

        //把输入成绩以课程成绩类StudentGrade的对象形式写进文件里    
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ObjectOutputStream outputGradeFile = null;
                StudentGrade stuGrd = new StudentGrade(courseSub, n, gradeName2);
                double[] studentGrade;
                studentGrade = stuGrd.getGrade();
                for (int i = 0; i < n; i++) {
                    String temp = gradeIn.getGrade()[i].getText();
                    if (!(temp.matches("[0-9]") || temp.matches("[1-9][0-9]") || temp.matches("100") || temp.matches(""))) {
                        JOptionPane.showMessageDialog(null, "输入失败！成绩为0到100的整数，请重新输入");
                        return;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if ("".equals(gradeIn.getGrade()[i].getText())) {
                        gradeIn.getGrade()[i].setText("60");
                    }
                    studentGrade[i] = Double.parseDouble(gradeIn.getGrade()[i].getText());
                    stuGrd.getStudent()[i] = gradeIn.getStudent()[i].getText();
                }
                try {
                    File gradeFile = new File("D:\\gradeAnalyse\\grade\\" + courseSub + "-" + gradeName2 + ".dat");
                    outputGradeFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(gradeFile)));
                    outputGradeFile.writeObject(stuGrd);
                    outputGradeFile.close();//
                    JOptionPane.showMessageDialog(null, "课程成绩已经成功录入，将返回系统主菜单");
                } catch (IOException ex) {
                } finally {
                    try {
                        outputGradeFile.close();

                        Menu gradeMenu = new Menu();
                        gradeMenu.setLocationRelativeTo(null);
                        gradeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gradeMenu.setTitle("学生成绩分析程序");
                        gradeMenu.setSize(400, 200);
                        //gradeMenu.pack();
                        gradeMenu.setVisible(true);
                        dispose();
                    } catch (IOException ex) {
                    }
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu gradeMenu = new Menu();
                gradeMenu.setLocationRelativeTo(null);
                gradeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gradeMenu.setTitle("学生成绩分析程序");
                gradeMenu.setSize(400, 200);
                //gradeMenu.pack();
                gradeMenu.setVisible(true);
            }
        });
    }
}
