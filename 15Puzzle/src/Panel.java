import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class Panel implements KeyListener  {
     private int arr[]=new int[16];
     //private int arr[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,0,15};
     private JButton topButton[]=new JButton[6];
     private JButton board[]=new JButton[16];
     public JPanel p1=new JPanel();
     public JPanel p2=new JPanel();
     private int countmove=0;
     private int allow=0,play=0;
     public static int easy=0,medium=0,hard=0;
     public String level=new String();
     LastFrame LF=new LastFrame();
     public Panel(){
         for(int i=0;i<16;i++)
         {
             board[i]=new JButton();
             board[i].addKeyListener(this);
         }
     }
     public void Createrand3(){
         int x,a;
         Random randval=new Random();
         for(int i=0;i<16; )
         {
             x=randval.nextInt(16);
             a=0;
             for(int j=0;j<i;j++)
             {
                 if(arr[j]==x)
                 {
                     a=1;
                     break;
                 }
             }
             if(a==0)
             {
                 arr[i]=x;
                 i++;
             }
         }
         
     }
     public void Createrand2(){
         int x,a;
         Random randval=new Random();
         for(int k=0;k<6;k++)
             arr[k]=k+1;
         for(int i=6;i<16; )
         {
             x=randval.nextInt(16);
             a=0;
             for(int j=0;j<i;j++)
             {
                 if(arr[j]==x)
                 {
                     a=1;
                     break;
                 }
             }
             if(a==0)
             {
                 arr[i]=x;
                 i++;
             }
         }
         
     }
     public void Createrand1(){
         int x,a;
         Random randval=new Random();
         for(int k=0;k<10;k++)
             arr[k]=k+1;
         for(int i=10;i<16; )
         {
             x=randval.nextInt(16);
             a=0;
             for(int j=0;j<i;j++)
             {
                 if(arr[j]==x)
                 {
                     a=1;
                     break;
                 }
             }
             if(a==0)
             {
                 arr[i]=x;
                 i++;
             }
         }
         
     }
     public void levelShow()
    {
     if(easy==1)
         {
            level="LEVEL : BEGINNER";
         }
         else if(medium==1)
         {
             level="LEVEL : INTERMEDIATE";
         }
         else if(hard==1)
         {
              level="LEVEL : EXPERT";
         }
         Label.l1.setText("  "+level+"                  Moves: "+Integer.toString(countmove));
     }
     public void CreatePanel1(){
       levelShow();
       p1.setLayout(new GridLayout(2,3,4,0));
       Font fontButtons=new Font("ARIAL",Font.ITALIC,20);
       topButton[0]=new JButton("Back");
       topButton[3]=new JButton("New");
       topButton[4]=new JButton("Start");
       topButton[1]=new JButton("Help");
       topButton[2]=new JButton("About");
       topButton[5]=new JButton("Score");
       ButtonHandler handler= new ButtonHandler();
        for(int i=0;i<6;i++)
       {
           topButton[i].setFont(fontButtons);
           topButton[i].setForeground(Color.BLUE);
           p1.add(topButton[i]);
           topButton[i].addActionListener(handler);
           topButton[i].addKeyListener(this);
       }
    }
     public void SetButton(){
         if(easy==1)
         {
            Createrand1();
         }
         else if(medium==1)
         {
             Createrand2();
         }
         else if(hard==1)
         {
             Createrand3();
         }
       for(int k=0;k<16;k++)
       {
              if(arr[k]!=0)
              {
                String str=new String(Integer.toString(arr[k]));
                board[k].setText(str);
              }
              else board[k].setText(" ");
              board[k].setForeground(Color.RED);
              if(board[k].getText().compareTo(" ")==0)
                    board[k].setBackground(Color.white);
              else if(k%2==0)
                    board[k].setBackground(Color.BLACK);
              else board[k].setBackground(Color.DARK_GRAY);
         }
     }
      public void CreatePanel2(){
       p2.setLayout(new GridLayout(4,4));
       SetButton();
       for(int k=0;k<16;k++)
       {
           board[k].setFont(new Font("ARIAL",Font.ITALIC,60));
           p2.add(board[k]);
       }
       ButtonHandler handler= new ButtonHandler();
       for(int i=0;i<16;i++){
            board[i].addActionListener(handler);
           
          }
     }
     public void swap(int i,int j){
         int check=0;
         String str1=board[i].getText();
         String str2=board[j].getText();
         board[i].setText(str2);
         board[j].setText(str1);
         board[i].setBackground(Color.white);
         if(j%2==0) board[j].setBackground(Color.BLACK);
         else board[j].setBackground(Color.DARK_GRAY);
         countmove++;
         for(int k=0;k<15;k++)
         {
             if(board[k].getText().compareTo(Integer.toString(k+1))!=0)
             {
                 check=1;
             }
         }
         if(check==0) 
         {
             String s1=Integer.toString(countmove);
             String s2="Total Moves: "+s1;
             String name=JOptionPane.showInputDialog("Enter your name:","Unknown");
             if(name!=null)
             {
                Client.name=name;
                Client.point=(3000-countmove)/3;
                Client.score=1;
             }
             allow=1;
             play=0;
             LF.setS(s2);
             LF.setflag(0);
             LF.setVisible(true);
         }
         Label.l1.setText("  "+level+"                  Moves: "+Integer.toString(countmove));
     }
      public void keyPressed(KeyEvent event){
            int i,j,a,k;
            
            if(LF.getflag()==1)
            {if(allow==0&&play==1){
                for(k=0;k<16;k++){
                    if(board[k].getText().compareTo(" ")==0)
                        break;
                }
                i=k/4;
                j=k%4;
                a=event.getKeyCode();

                if(a== KeyEvent.VK_LEFT && j!=3){
                     swap(k+1,k);
                }
                else if (a ==  KeyEvent.VK_UP && i!=3) {
                     swap(k+4,k);
                }
                else if (a ==  KeyEvent.VK_RIGHT && j!=0) {
                     swap(k-1,k);
                }
                else if(a ==  KeyEvent.VK_DOWN && i!=0){
                     swap(k-4,k);
                }

            }
            }
        }
      public void keyReleased(KeyEvent event){

      }
      public void keyTyped(KeyEvent event){

      }
     private class ButtonHandler implements ActionListener {

         public void actionPerformed(ActionEvent e)
        {
            int j,i;
            if(LF.getflag()==1){
            for(int k=0;k<16;k++)
                if(e.getSource()==board[k]&&allow==0&&play==1)
                {
                    i=k/4;
                    j=k%4;
                    if(board[k].getText().compareTo(" ")!=0)
                    {
                        if(i!=0&&board[k-4].getText().compareTo(" ")==0)
                        {
                            swap(k,k-4);
                        }
                        else if(i!=3&&board[k+4].getText().compareTo(" ")==0)
                        {
                            swap(k,k+4);
                        }
                        else if(j!=0&&board[k-1].getText().compareTo(" ")==0)
                        {
                            swap(k,k-1);
                        }
                        else if(j!=3&&board[k+1].getText().compareTo(" ")==0)
                        {
                            swap(k,k+1);
                        }
                    }
                }
                if(e.getSource()==topButton[0])
                {
                    easy=0;
                    medium=0;
                    hard=0;
                    Client.back=1;
                    StartFrame.frame.dispose();
                    StartFrame startFrame= new StartFrame();
                    startFrame.setVisible(true);

                }
                else if(e.getSource()==topButton[3])
                {
                    countmove=0;
                    allow=0;
                    play=0;
                    Label.l1.setText("  "+level+"                  Moves: "+Integer.toString(countmove));
                    SetButton();
                }
                else if(e.getSource()==topButton[4])
                {
                    play=1;
                   
                }
                else if(e.getSource()==topButton[1])
                {
                     JOptionPane.showMessageDialog(null, "\n"
            + "\n"
            + "         Click the 'Start' button to start the game\n"
            + "\n"
            + "         Click the 'New' button for a new try\n"
            + "\n"
            +"          Click the 'Back' button to go to the main menu\n"
            +"\n"
            + "         Controls:   \n"
            + "\n"
            + "             Mouse: Left button click                             \n "
            + "             Keyboard: Arrow keys            \n"
            + "\n"
            + "\n ", "Help", JOptionPane.PLAIN_MESSAGE);
                }
                else if(e.getSource()==topButton[2])
                {
                   JOptionPane.showMessageDialog(null, "\n"
            + "\n"
            + "\n"
            + "         Created by:\n"
            + "\n"
            + "\n"
            + "                 Md. Arif Hasnat\n"
            + "                  CSE(09), BUET\n" 
            + "                   @ Mango Inc.\n "
            + "\n"
            + "\n"
            + "                                                 '31 Dec 2011'\n"
            + "\n", "About", JOptionPane.PLAIN_MESSAGE);
                }
            else if(e.getSource()==topButton[5])
                {          
                    Client.send=1;
                }
            }
    }
  }
}
