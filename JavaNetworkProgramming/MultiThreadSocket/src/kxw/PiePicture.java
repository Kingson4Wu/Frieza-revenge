package kxw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Kunxin_Wu
 */
public class PiePicture extends JPanel {
//饼图
    private pieInstruction pieInstruc = new pieInstruction();
    private double[] percent;
    
    public PiePicture() {
        setLayout(null);//不使用布局管理器
        add(pieInstruc, SpringLayout.EAST);//
    }

    public void showPiePicture(double[] percent) {
        this.percent = percent;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintChildren(g);
        pieInstruc.showPieInstruction();
        int width = getWidth();
        int height = getHeight();
        pieInstruc.setBounds(width / 5 * 4, height / 5 * 2 - 20, 70, 100);//
        int Radius = height / 10 * 2 + 10;
        int doubleRadius = Radius * 2;
        int positionX = width / 4;
        int positionY = height / 5 * 2 - 30;
        int centerX = positionX + Radius;
        int centerY = positionY + Radius;
        int Radius2X = doubleRadius;
        int Radius2Y = doubleRadius;

        g.drawOval(positionX - 1, positionY - 1, Radius2X + 1, Radius2Y + 1);
        g.drawOval(positionX, positionY, Radius2X, Radius2Y);
        //g.drawOval(positionX+1, positionY+1, Radius2X-1,Radius2Y-1);
        
        int n = percent.length;
        int[] percentRate = new int[n];
        for (int i = 0; i < n; i++) {      
            percentRate[i] = (int) (percent[i] * 360 / 100);
        }
        g.setColor(Color.BLACK);

        String[] jlb = new String[n];
        jlb[0] = "<60分\n ";
        jlb[1] = "60-69分\n ";
        jlb[2] = "70-79分\n ";
        jlb[3] = "80-89分\n ";
        jlb[4] = ">=90分\n ";

        int lineX;
        int lineY;
        //g.drawString(jlb[0], lineX, lineY);
        double[] angle = new double[n];
        angle[0] = Math.PI / 4;
        Radius += 40;
        for (int i = 1; i < n; i++) {
            angle[i] = angle[i - 1] + Math.PI * 2 * (percent[i - 1] / 100);
            if (i == 2) {//个别调整位置
                Radius += 20;
            }
            lineX = (int) ((Radius) * Math.cos((angle[i] + angle[i - 1]) / 2));
            lineY = (int) ((Radius) * Math.sin((angle[i] + angle[i - 1]) / 2));
            g.drawString(jlb[i - 1], centerX + lineX, centerY - lineY);
            g.drawString((int) percent[i - 1] + "%", centerX + lineX + 5, centerY - lineY + 15);

            if (i == 2) {//还原
                Radius -= 20;
            }
            // g.drawLine(centerX, centerY, centerX + lineX, centerY - lineY);
        }
        // lineX = (int) ((Radius) * Math.cos((angle[0]+angle[n-1])/2));
        //lineY = (int)((Radius) * Math.sin((angle[0]+angle[n-1])/2));
        Radius -= 20;
        lineX = (int) ((Radius) * Math.cos((angle[0] / 2)));
        lineY = (int) ((Radius) * Math.sin((angle[0]) / 2));
        g.drawString(jlb[n - 1], centerX + lineX, centerY - lineY);
        g.drawString((int) percent[n - 1] + "%", centerX + lineX + 5, centerY - lineY + 15);
       
         g.drawString("成绩百分比", width/5*2, 20);
        
        int start = 45;
        g.setXORMode(Color.BLUE);
        g.fillArc(positionX, positionY, Radius2X, Radius2Y, start, percentRate[0]);
        start += percentRate[0];
        g.setXORMode(Color.PINK);
        g.fillArc(positionX, positionY, Radius2X, Radius2Y, start, percentRate[1]);
        start += percentRate[1];
        g.setXORMode(Color.YELLOW);
        g.fillArc(positionX, positionY, Radius2X, Radius2Y, start, percentRate[2]);
        start += percentRate[2];
        g.setXORMode(Color.GREEN);
        g.fillArc(positionX, positionY, Radius2X, Radius2Y, start, percentRate[3]);
        start += percentRate[3];
        g.setXORMode(Color.RED);
        g.fillArc(positionX, positionY, Radius2X, Radius2Y, start, 360 - start + 45); 
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

    //定义内部类
    class pieInstruction extends JPanel {
        public void showPieInstruction() {
           repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintChildren(g);
            g.setColor(Color.BLACK);
            int width = getWidth();
            int height = getHeight();
            
            g.drawRect(0, 0, width - 1, height - 1);
            String[] level = new String[5];

            level[0] = "<60分";
            level[1] = "60-69分";
            level[2] = "70-79分";
            level[3] = "80-89分";
            level[4] = ">=90分";

            int unit = height / 5;
            g.setColor(Color.BLACK);
            for (int i = 0; i < 5; i++) {
                g.drawString(level[i], 20, 15 + i * unit);
                g.drawRect(5, 5 + i * unit, 10, 10);
            }

            g.setXORMode(Color.BLUE);
            g.fillRect(5, 5, 10, 10);
            g.setXORMode(Color.PINK);
            g.fillRect(5, 5 + unit, 10, 10);
            g.setXORMode(Color.YELLOW);
            g.fillRect(5, 5 + 2 * unit, 10, 10);
            g.setXORMode(Color.GREEN);
            g.fillRect(5, 5 + 3 * unit, 10, 10);//透明的颜色
            g.setXORMode(Color.RED);
            g.fillRect(5, 5 + 4 * unit, 10, 10);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(70, 100);
        }
    }
    //内部类结束
}