package kxw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Kunxin_Wu
 */
public class Cylindricity extends JPanel {
//柱形图
    private int[] count;
    public void showCylindricity(int[] count) {
        this.count = count;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (count == null) {
            return;
        }
        super.paintChildren(g);
        
        int width = getWidth();
        int height = getHeight();
        int interval = (width - 40) / count.length;
        int individualWidth = (int) (((width - 40) / 4) * 0.40);

        String[] level = new String[5];
        level[0] = "<60分";
        level[1] = "60-69分";
        level[2] = "70-79分";
        level[3] = "80-89分";
        level[4] = ">=90分";

        int maxCount = 0;
        for (int i = 0; i < count.length; i++) {
            if (maxCount < count[i]) {
                maxCount = count[i];
            }
        }
        int standardCount = 0;

        while (standardCount < maxCount) {
            standardCount += 5;
        }

        int n = standardCount / 5 + 1;
        int top = 5;
        int buttom = height - 45;
        int unit = (buttom - top) / n;
        g.drawString("人数", 5, 30);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20, buttom - (n - 1) * unit, width - 50, (n - 1) * unit);
        g.setColor(Color.BLACK);
        g.drawLine(19, buttom - (n - 1) * unit, 19, buttom);

        for (int i = 0; i < n; i++) {
            g.drawLine(20, buttom - i * unit, width - 50, buttom - i * unit);
            g.drawString(String.valueOf(0 + i * 5), 5, buttom - i * unit);
        }
        
        int x = 30;
        for (int i = 0; i < count.length; i++) {
            
            int barHeight = (int) (((double) count[i] / (double) standardCount) * (n - 1) * unit);
            g.setColor(Color.BLUE);
            g.fillRect(x, buttom - barHeight, individualWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, buttom - barHeight, individualWidth, barHeight);
            g.drawString("   " + level[i] + "", x, height - 30);
            x += interval;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(580, 300);
    }
}
