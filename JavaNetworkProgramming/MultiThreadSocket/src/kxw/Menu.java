
package kxw;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class Menu extends JFrame{
    
    
    
    private JButton newFile=new JButton("新建课程考试成绩单");
    private JButton openFile=new JButton("打开课程考试成绩单");
    
    private JLabel welcome=new JLabel("欢迎进入学生成绩分析程序",JLabel.CENTER);

    public Menu() {
        JPanel jpButtons=new JPanel();
    jpButtons.setLayout(new FlowLayout());
        jpButtons.add(newFile);
        jpButtons.add(openFile);
        Font font=new Font("kxw",Font.BOLD,22);
        welcome.setFont(font);
        
        
        setLayout(new BorderLayout());
        add(welcome,BorderLayout.CENTER);
        add(jpButtons,BorderLayout.SOUTH);
        
        newFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              //  System.out.println("fsffsdfs");
               
      
                
                    Course courDialog;
                courDialog = new Course();
                    courDialog.setLocationRelativeTo(null);
        courDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        courDialog.setTitle("所有可选择课程");
        courDialog.setSize(300, 250);
        //courDialog.pack();
        courDialog.setVisible(true);
      dispose();
               
            }                
        });
        
         openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
        
         JFileChooser fileChooser = new JFileChooser(new File("D:\\gradeAnalyse\\grade"));
                    if (fileChooser.showOpenDialog(openFile) == JFileChooser.APPROVE_OPTION) {
                        
                        
                    ////    graOpen.setLocationRelativeTo(null);
					   // graOpen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					   // graOpen.setTitle("学生成绩查看");
					   // graOpen.setSize(600, 500);
					   // graOpen.pack();
					   // graOpen.setVisible(true);
					  dispose(); 
                        
                        
                        
                    }
        
                //System.exit(0);
            }                
        });
        
       
            
            
   
        }
        
       
      
 
      
      
    }
    
    
    
    
    
    
    
    
    
    

