package com.kxw.basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TestTimer{
	public static void main(String[] args){
		JFrame jf = new JFrame("Clock");
		JButton pause = new JButton("Pause");
		JLabel clock = new JLabel("Clock");
		clock.setBackground(Color.GREEN);
		clock.setOpaque(true);
		clock.setHorizontalAlignment(JLabel.CENTER);
		jf.add(clock,"Center");
		jf.add(pause,"North");
		jf.setSize(140,80);
		jf.setLocation(500,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		MyListener m1 = new MyListener(clock);
		Timer t1 = new Timer(1000,m1);
		t1.setInitialDelay(0);
		t1.start();
		MyListener2 m2 = new MyListener2(t1,pause,clock);
		pause.addActionListener(m2);		
	}	
}

class MyListener  implements ActionListener{
	private JLabel clock;
	public MyListener(JLabel clock){
		this.clock = clock;	
	}	
	@Override
	public void actionPerformed(ActionEvent e){
		Calendar c = new GregorianCalendar();
		String time = c.get(Calendar.YEAR) + "-" +
					  (c.get(Calendar.MONTH) + 1) + "-" +
					  c.get(Calendar.DATE)  + "  " ;
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		String ph = h<10 ? "0":"";
		String pm = m<10 ? "0":"";
		String ps = s<10 ? "0":"";		
		time += ph + h + ":" + pm + m + ":" + ps + s; 
		clock.setText(time);
		clock.repaint();
	}	
}

class MyListener2 implements ActionListener{
	private Timer timer;
	private JButton button;
	private JLabel clock;
	private boolean running= true;
	
	public MyListener2(Timer timer,JButton button,JLabel clock){
		this.timer = timer;	
		this.button = button;
		this.clock = clock;
	}	
	@Override
	public void actionPerformed(ActionEvent e){
		if(running){
			timer.stop();
			button.setText("Replay");
			clock.setBackground(Color.RED);
		}else{
			timer.restart();	
			button.setText("Pause");
			clock.setBackground(Color.green);
		}
		running = !running;
	}	
}