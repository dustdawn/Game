package MyFiveChess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


//五子棋主框架
public class ChessJFrame extends JFrame{
	private ChessBoard chessboard;
	private JPanel toolbar;//工具栏
	private JButton button_start,button_back,button_exit;
	
	private JMenuBar menubar;//菜单栏
	private JMenu Menu;
	private JMenuItem infoItem;//菜单项
	public ChessJFrame(){
		setTitle("五子棋");
		chessboard=new ChessBoard();
		
		menubar=new JMenuBar();
		Menu=new JMenu("版权");
		infoItem=new JMenuItem("版权信息");
		Menu.add(infoItem);
		
		//内部监听类
		MyItemListener lis=new MyItemListener();
		this.infoItem.addActionListener(lis);
		
		menubar.add(Menu);
		setJMenuBar(menubar);//设置菜单栏
		toolbar=new JPanel();//工具面板实例化
		button_start=new JButton("重新开始");
		button_back=new JButton("悔棋");
		button_exit=new JButton("退出");
		toolbar.setLayout(new GridLayout(1,3));
		toolbar.add(button_start);
		toolbar.add(button_back);
		toolbar.add(button_exit);
		button_start.addActionListener(lis);
		button_back.addActionListener(lis);
		button_exit.addActionListener(lis);
		add(toolbar,BorderLayout.SOUTH);
		add(chessboard);//添加面板对象
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim=getToolkit().getScreenSize();
		this.setLocation(600, 200);
		this.setResizable(false);
	    pack();
	}
	private class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==button_start){
				System.out.println("重新开始");
				chessboard.restartGame();
			}
			else if(e.getSource()==button_exit){
				System.exit(0);
			}
			else if(e.getSource()==button_back){
				chessboard.goback();
			}
			else if(e.getSource()==infoItem){
				System.out.println("版权所有: 晓际夕辰");
				chessboard.info();
			}
		}
	}
	public static void main(String[] args) {  
        ChessJFrame j=new ChessJFrame();
        j.setVisible(true);
    }  
	 
	
}