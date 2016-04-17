package kxw;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Kunxin_Wu
 */
public class InputField extends JPanel {
//继承的面板类JPanel,InputField类用于显示班级人员名单和对应成绩输入框
    //InputField类是GradeOpen类和GradeInput类的数据成员

    private JLabel[] student;
    private JTextField[] grade;
    Font font = new Font("kxw", Font.BOLD, 20);
    private JLabel numName = new JLabel("学号和姓名", JLabel.CENTER);
    private JLabel numName2 = new JLabel("学号和姓名", JLabel.CENTER);
    private JLabel gradeText = new JLabel("成绩", JLabel.CENTER);
    private JLabel gradeText2 = new JLabel("成绩", JLabel.CENTER);

    public InputField(int n, ArrayList student) {
        
        this.student = new JLabel[n];
        this.grade = new JTextField[n];
        for (int i = 0; i < n; i++) {
            this.student[i] = new JLabel((String) student.get(i), JLabel.CENTER);
            this.grade[i] = new JTextField(5);//设置为5列
        }
        
        setLayout(new GridLayout((n + 1) / 2 + 1, 4));
        add(numName);
        add(gradeText);
        add(numName2);
        add(gradeText2);
        
        for (int i = 0; i < n; i++) {
            add(this.student[i]);
            add(this.grade[i]);
        }
    }
    
    //构造函数重载
    public InputField(int n, String[] student, double[] grade) {

        this.student = new JLabel[n];
        this.grade = new JTextField[n];
        for (int i = 0; i < n; i++) {
            this.student[i] = new JLabel(student[i], JLabel.CENTER);
            this.grade[i] = new JTextField(5);//设置为5列
            String gradeTemp = String.valueOf(grade[i]);
            this.grade[i].setText(gradeTemp);
        }
      
        setLayout(new GridLayout((n + 1) / 2 + 1, 4));
        add(numName);
        add(gradeText);
        add(numName2);
        add(gradeText2);
        for (int i = 0; i < n; i++) {
            add(this.student[i]);
            add(this.grade[i]);
        }
    }

    public JLabel[] getStudent() {
        return student;
    }

    public JTextField[] getGrade() {
        return grade;
    }
}