package MyFiveChess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


//�����������
public class ChessJFrame extends JFrame{
	private ChessBoard chessboard;
	private JPanel toolbar;//������
	private JButton button_start,button_back,button_exit;
	
	private JMenuBar menubar;//�˵���
	private JMenu Menu;
	private JMenuItem infoItem;//�˵���
	public ChessJFrame(){
		setTitle("������");
		chessboard=new ChessBoard();
		
		menubar=new JMenuBar();
		Menu=new JMenu("��Ȩ");
		infoItem=new JMenuItem("��Ȩ��Ϣ");
		Menu.add(infoItem);
		
		//�ڲ�������
		MyItemListener lis=new MyItemListener();
		this.infoItem.addActionListener(lis);
		
		menubar.add(Menu);
		setJMenuBar(menubar);//���ò˵���
		toolbar=new JPanel();//�������ʵ����
		button_start=new JButton("���¿�ʼ");
		button_back=new JButton("����");
		button_exit=new JButton("�˳�");
		toolbar.setLayout(new GridLayout(1,3));
		toolbar.add(button_start);
		toolbar.add(button_back);
		toolbar.add(button_exit);
		button_start.addActionListener(lis);
		button_back.addActionListener(lis);
		button_exit.addActionListener(lis);
		add(toolbar,BorderLayout.SOUTH);
		add(chessboard);//���������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim=getToolkit().getScreenSize();
		this.setLocation(600, 200);
		this.setResizable(false);
	    pack();
	}
	private class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==button_start){
				System.out.println("���¿�ʼ");
				chessboard.restartGame();
			}
			else if(e.getSource()==button_exit){
				System.exit(0);
			}
			else if(e.getSource()==button_back){
				chessboard.goback();
			}
			else if(e.getSource()==infoItem){
				System.out.println("��Ȩ����: ����Ϧ��");
				chessboard.info();
			}
		}
	}
	public static void main(String[] args) {  
        ChessJFrame j=new ChessJFrame();
        j.setVisible(true);
    }  
	 
	
}