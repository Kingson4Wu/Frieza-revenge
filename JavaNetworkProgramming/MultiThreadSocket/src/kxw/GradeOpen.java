package kxw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GradeOpen extends JFrame {

    private JLabel instruction;
    private JLabel option;
    private InputField graOpen;
    private JButton quit = new JButton("退出并返回主菜单");
    private JButton modify = new JButton("修改并保存");
    private JButton analyse = new JButton("课程考试成绩分析");
    private File openFile2;
    private int n;
    private StudentGrade gradeRecord;
    private GradeCounter graCount;
    private JFrame graCountFrame = new JFrame();

    public GradeOpen(File openFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        openFile2 = openFile;
        





        Font font = new Font("kxw", Font.BOLD, 20);
        this.instruction = new JLabel(gradeRecord.getGrade_name() + gradeRecord.getCourse() + "学生成绩单", JLabel.CENTER);
        this.option = new JLabel("（可修改并查看详细成绩统计内容）", JLabel.CENTER);
        this.instruction.setFont(font);
        this.option.setFont(font);

        JPanel tip = new JPanel(new GridLayout(2, 1));
        tip.add(this.instruction);
        tip.add(this.option);

        //  System.out.println(gradeRecord.getGrade().length);


        this.n = gradeRecord.getGrade().length;
        this.graOpen = new InputField(n, gradeRecord.getStudent(), gradeRecord.getGrade());




        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new FlowLayout());
        jpButtons.add(analyse);
        jpButtons.add(modify);
        jpButtons.add(quit);

        setLayout(new BorderLayout());
        add(tip, BorderLayout.NORTH);
        add(this.graOpen, BorderLayout.CENTER);
        add(jpButtons, BorderLayout.SOUTH);





        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < n; i++) {
                    String temp = graOpen.getGrade()[i].getText();
                    if (!(temp.matches("[0-9]") || temp.matches("[1-9][0-9]") || temp.matches("100") || temp.matches("[0-9].0") || temp.matches("[1-9][0-9].0") || temp.matches("100.0"))) {
                        JOptionPane.showMessageDialog(null, "修改失败！成绩为0到100的整数，请重新修改");
                        return;
                    }
                }


                ObjectOutputStream gradeOutput = null;


                try {
                    for (int i = 0; i < n; i++) {

                        gradeRecord.getGrade()[i] = Double.parseDouble(graOpen.getGrade()[i].getText());


                    }
                    gradeOutput = new ObjectOutputStream(new FileOutputStream(openFile2));
                    gradeOutput.writeObject(gradeRecord);

                    gradeOutput.close();
                    JOptionPane.showMessageDialog(null, "Modifition Successful!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "the file is lose!");
                } finally {
                    try {
                        gradeOutput.close();
                    } catch (IOException ex) {
                    }
                }

            }
        });


        analyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println(openFile2.getName());

                File fileCounter = new File("D:\\gradeAnalyse\\grade\\" + openFile2.getName());
                StudentGrade gradeRecordCounter = new StudentGrade();
                ObjectInputStream gradeInputCounter = null;
                //重新读入文件数据，以防修改
                try {
                    gradeInputCounter = new ObjectInputStream(new FileInputStream(fileCounter));

                    if (gradeInputCounter.available() == 0) {
                        try {
                            //
                            gradeRecordCounter = (StudentGrade) gradeInputCounter.readObject();
                        } catch (ClassNotFoundException ex) {
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "the file is lose!");
                }
                graCount = new GradeCounter(gradeRecordCounter);
                graCountFrame.add(graCount);
                graCountFrame.setSize(500, 300);
                graCountFrame.setTitle("成绩详细统计数据");
                graCountFrame.setVisible(true);


//                GradeCounter graCou;
//                graCou = new GradeCounter(gradeRecord);
//                graCou.setLocationRelativeTo(null);
//                graCou.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                graCou.setTitle("课程统计成绩结果");
//                graCou.setSize(500, 300);
//                // graCou.pack();
//                graCou.setVisible(true);
                //dispose();

            }
        });

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
                dispose();
            }
        });









    }
}
