package MyFiveChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJFrame extends JFrame implements ActionListener{
	private JButton button_single,button_mulity;
	private JLabel label;
	public WindowJFrame(){
		this.setTitle("五子棋");
		this.setBounds(600,200,600,400);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(4,1,6,6));
		
		this.label=new JLabel("连珠五子棋");
		this.label.setFont(new Font("楷体",Font.BOLD,28));
		this.button_single=new JButton("单人模式");
		this.button_mulity=new JButton("双人模式");
		button_single.setFont(new Font("楷体",Font.BOLD,18));
		button_mulity.setFont(new Font("楷体",Font.BOLD,18));
		
		this.button_single.addActionListener(this);
		this.button_mulity.addActionListener(this);
		
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		jp1.add(label);
		jp2.add(button_single);
		jp3.add(button_mulity);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button_single){
			
		}
		if(e.getSource()==button_mulity){
			this.setVisible(false);
			ChessJFrame j=new ChessJFrame();
	        j.setVisible(true);
		}
	}
	public static void main(String[] args) {
		 new WindowJFrame();

	}

}
