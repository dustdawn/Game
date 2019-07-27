package MyFiveChess;


import java.awt.Color;  
import java.awt.Cursor;  
import java.awt.Dimension;  
import java.awt.Graphics;    

 
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import java.awt.event.MouseMotionListener;  
 
  

import javax.swing.*;  
 
  
public class ChessBoard extends JPanel implements MouseListener {  
   public static final int BJ=30;//�߾�  
   public static final int WJ=35;//������  
   public static final int ROWS=15;//��������  
   public static final int COLS=15;//��������  
     
   Chess[] chessList=new Chess[(ROWS+1)*(COLS+1)];//��ʼÿ������Ԫ��Ϊnull  
   boolean isBlack=true;//Ĭ�Ͽ�ʼ�Ǻ�����  
   boolean gameOver=false;//��Ϸ�Ƿ����  
   int chessCount;//��ǰ�������ӵĸ���  
   int xIndex,yIndex;//��ǰ�������ӵ�����  
     
 
   public ChessBoard(){  
        
       setBackground(Color.lightGray);
        
       addMouseListener(this);  
       addMouseMotionListener(new MouseMotionListener(){  
           public void mouseDragged(MouseEvent e){  
                 //�����ק
           }  
             
           public void mouseMoved(MouseEvent e){  
             int x1=(e.getX()-BJ+WJ/2)/WJ;  
             //�������������λ��ת����������  
             int y1=(e.getY()-BJ+WJ/2)/WJ;  
             //��Ϸ�Ѿ�����������  
             //���������ⲻ����  
             //x��yλ���Ѿ������Ӵ��ڣ�������  
             if(x1<0||x1>ROWS||y1<0||y1>COLS||gameOver||findChess(x1,y1))  
                 setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //Ĭ��ָ���� 
             //�������״̬  
             else setCursor(new Cursor(Cursor.HAND_CURSOR));  //��ָ��
               
           }  
       });  
   }   
     
    
  
//����  
   public void paintComponent(Graphics g){  
       
       super.paintComponent(g);//�����ػ�
       
      
         
       for(int i=0;i<=ROWS;i++){//������  
           g.drawLine(BJ,BJ+i*WJ, BJ+COLS*WJ, BJ+i*WJ);  
       }  
       for(int i=0;i<=COLS;i++){//������  
           g.drawLine(BJ+i*WJ, BJ, BJ+i*WJ, BJ+ROWS*WJ);  
             
       }  
         
       //������  
       for(int i=0;i<chessCount;i++){  
           //���񽻲��x��y����  
           int xPos=chessList[i].getX()*WJ+BJ;  
           int yPos=chessList[i].getY()*WJ+BJ;  
           g.setColor(chessList[i].getColor());//������ɫ  
           g.fillOval(xPos-Chess.R/2, yPos-Chess.R/2, Chess.R,Chess.R);  
           
           
           
           //������һ�����ӵĺ���ο�  
             
           if(i==chessCount-1){//��������һ������  
               g.setColor(Color.red);  
               g.drawRect(xPos-Chess.R/2, yPos-Chess.R/2,  
                           34, 35);  
           }  
       }  
   }  
     
   public void mousePressed(MouseEvent e){//���������ϰ���ʱ����  
         
       //��Ϸ����ʱ����������  
       if(gameOver) return;  
         
       String colorName=isBlack?"����":"����";  
         
       //�������������λ��ת������������  
       xIndex=(e.getX()-BJ+WJ/2)/WJ;  
       yIndex=(e.getY()-BJ+WJ/2)/WJ;  
         
       //���������ⲻ����  
       if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)  
           return;  
         
       //���x��yλ���Ѿ������Ӵ��ڣ�������  
       if(findChess(xIndex,yIndex))return;  
         
       //���Խ���ʱ�Ĵ���  
       Chess ch=new Chess(xIndex,yIndex,isBlack?Color.black:Color.white);  
       chessList[chessCount++]=ch;  
       repaint();//֪ͨϵͳ���»���  
        
         
       //���ʤ���������ʾ��Ϣ�����ܼ�������  
         
       if(isWin()){  
           String msg=String.format("��ϲ��%sӮ�ˣ�", colorName);  
           JOptionPane.showMessageDialog(this, msg);  
           gameOver=true;  
       }  
       isBlack=!isBlack;  
     }  
   
     
     
   private boolean isWin(){  
       int continueCount=1;//�������ӵĸ���  
        
       //��������Ѱ��  
       for(int x=xIndex-1;x>=0;x--){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(x,yIndex,c)!=null){  
               continueCount++;  
           }else  
               break;  
       }  
      //������Ѱ��  
       for(int x=xIndex+1;x<=COLS;x++){  
          Color c=isBlack?Color.black:Color.white;  
          if(getChess(x,yIndex,c)!=null){  
             continueCount++;  
          }else  
             break;  
       }  
       if(continueCount>=5){  
             return true;  
       }else   
       continueCount=1;  
         
       //������һ����������  
       //��������  
       for(int y=yIndex-1;y>=0;y--){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(xIndex,y,c)!=null){  
               continueCount++;  
           }else  
               break;  
       }  
       //��������Ѱ��  
       for(int y=yIndex+1;y<=ROWS;y++){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(xIndex,y,c)!=null)  
               continueCount++;  
           else  
              break;  
         
       }  
       if(continueCount>=5)  
           return true;  
       else  
           continueCount=1;  
         
         
       //������һ�������������б��  
       //����Ѱ��  
       for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=COLS;x++,y--){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(x,y,c)!=null){  
               continueCount++;  
           }  
           else break;  
       }  
       //����Ѱ��  
       for(int x=xIndex-1,y=yIndex+1;x>=0&&y<=ROWS;x--,y++){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(x,y,c)!=null){  
               continueCount++;  
           }  
           else break;  
       }  
       if(continueCount>=5)  
           return true;  
       else continueCount=1;  
         
         
       //������һ�������������б��  
       //����Ѱ��  
       for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(x,y,c)!=null)  
               continueCount++;  
           else break;  
       }  
       //����Ѱ��  
       for(int x=xIndex+1,y=yIndex+1;x<=COLS&&y<=ROWS;x++,y++){  
           Color c=isBlack?Color.black:Color.white;  
           if(getChess(x,y,c)!=null)  
               continueCount++;  
           else break;  
       }  
       if(continueCount>=5)  
           return true;  
       else continueCount=1;  
         
       return false;  
     }  
     
     
   private Chess getChess(int xIndex,int yIndex,Color color){  
       for(Chess p:chessList){  
           if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex  
                   &&p.getColor()==color)  
               return p;  
       }  
       return null;  
   }  
     
     
   public void restartGame(){  
       //�������  
       for(int i=0;i<chessList.length;i++){  
           chessList[i]=null;  
       }  
       //�ָ���Ϸ��صı���ֵ  
       isBlack=true;  
       gameOver=false; //��Ϸ�Ƿ����  
       chessCount =0; //��ǰ�������Ӹ���  
       repaint();  
   }  
     
   //���� 
   public void goback(){  
       if(chessCount==0)  
           return ;  
       chessList[chessCount-1]=null;  
       chessCount--;  
       if(chessCount>0){  
           xIndex=chessList[chessCount-1].getX();  
           yIndex=chessList[chessCount-1].getY();  
       }  
       isBlack=!isBlack;  
       repaint();  
   }  
     
   //�����  
  
   public Dimension getPreferredSize(){  
       return new Dimension(BJ*2+WJ*COLS,BJ*2  
                            +WJ*ROWS);  
   }
   public void info(){
	   String msg=String.format("��Ȩ����: ����Ϧ��");  
       JOptionPane.showMessageDialog(this, msg,"about",JOptionPane.PLAIN_MESSAGE);  
   }
   
   
 //����mouseListener�ķ���  
   public void mouseClicked(MouseEvent e){  
       //��갴��������ϵ���ʱ����  
   }  
     
   public void mouseEntered(MouseEvent e){  
       //�����뵽�����ʱ����  
   }  
   public void mouseExited(MouseEvent e){  
       //����뿪���ʱ����  
   }  
   public void mouseReleased(MouseEvent e){  
       //��갴ť��������ͷ�ʱ����  
   }  
   //�����������в����Ƿ�������Ϊx��y�����Ӵ���  
   private boolean findChess(int x,int y){  
       for(Chess c:chessList){  
           if(c!=null&&c.getX()==x&&c.getY()==y)  
               return true;  
       }  
       return false;  
   }  
     
     
     
}  