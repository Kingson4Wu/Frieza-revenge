package kxw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Kunxin_Wu
 */
class Course extends JFrame {
    /*通过单选按钮选择需要输入的课程，并进入下一步输入班级课程成绩，
     * 可取消返回主菜单
     */

    private JButton sure = new JButton("确定");
    private JButton cancel = new JButton("取消并返回主菜单");
    private JLabel input = new JLabel("选择你要录入的课程", JLabel.CENTER);
    private JRadioButton[] jrb;
    Font font = new Font("kxw", Font.BOLD, 20);
    Font font2 = new Font("kxw", Font.BOLD, 16);
    ArrayList courses = new ArrayList();//声明最终变量
    //因为很多函数包括事件驱动函数需要用到，所以需设为全局变量
    String course;//声明最终变量
    int chose = -1;
    //记录用户是否通过单选按钮选择了课程，默认为-1没有选择

    public Course() {
        try {
            input.setFont(font2);
            Scanner in = new Scanner(new File("D:\\gradeAnalyse\\course.txt"));
            while (in.hasNext()) {
                //若用course=in.nextLine!=null,将有异常抛出
                course = in.nextLine();
                courses.add(course);
            }
            int n = courses.size();
            JPanel jbRadioButtons = new JPanel();
            jbRadioButtons.setLayout(new GridLayout(n, 1));
            jrb = new JRadioButton[n];
            ButtonGroup group = new ButtonGroup();//把所有课程选择按钮联合起来
            for (int i = 0; i < n; i++) {
                jbRadioButtons.add(jrb[i] = new JRadioButton((String) courses.get(i)));//从ArrayList中读出的元素要进行类型转换
                jrb[i].setFont(font);
                group.add(jrb[i]);
                jrb[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < jrb.length; i++) {
                            if (e.getSource() == jrb[i]) {
                                chose = i;//得到用户所选择的课程                            
                            }
                        }
                    }
                });
            }
            JPanel jpButtons = new JPanel();
            jpButtons.setLayout(new FlowLayout());
            jpButtons.add(sure);
            jpButtons.add(cancel);

            setLayout(new BorderLayout());
            add(input, BorderLayout.NORTH);
            add(jbRadioButtons, BorderLayout.CENTER);
            add(jpButtons, BorderLayout.SOUTH);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Not find the file!");
        }

        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chose != -1) {
                    JFileChooser fileChooser = new JFileChooser(new File("D:\\gradeAnalyse\\list"));
                    if (fileChooser.showOpenDialog(sure) == JFileChooser.APPROVE_OPTION) {
                        try {
                            String gradeName = fileChooser.getSelectedFile().getName();
                            gradeName = gradeName.substring(0, gradeName.length() - 4);//去掉后缀.txt
                            course = (String) courses.get(chose);

                            File gradeFile = new File("D:\\gradeAnalyse\\grade\\" + course + "-" + gradeName + ".dat");
                            if (gradeFile.exists()) {
                                JOptionPane.showMessageDialog(null, "该班级课程成绩已经存在，不必再次录入，将返回系统主菜单");
                                dispose();
                                Menu gradeMenu = new Menu();
                                gradeMenu.setLocationRelativeTo(null);
                                gradeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                gradeMenu.setTitle("学生成绩分析程序");
                                gradeMenu.setSize(400, 200);
                                //gradeMenu.pack();
                                gradeMenu.setVisible(true);
                            } else {
                                Scanner names;
                                names = new Scanner(fileChooser.getSelectedFile());
                                ArrayList nameRecord = new ArrayList();
                                String name;
                                while (names.hasNext()) {
                                    name = names.nextLine();
                                    nameRecord.add(name);
                                }
                                GradeInput gdInp = new GradeInput(course, nameRecord, gradeName);
                                gdInp.setLocationRelativeTo(null);
                                gdInp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                gdInp.setTitle("学生课程成绩输入");
                                gdInp.setSize(700, 550);
                                //gdInp.pack();
                                gdInp.setVisible(true);
                                dispose();
                            }
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Not find the file!");
                        }
                    }
                } else {//用户没有选择
                    JOptionPane.showMessageDialog(null, "请输入需要选择的课程");
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
