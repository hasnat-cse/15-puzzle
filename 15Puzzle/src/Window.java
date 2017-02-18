
import java.awt.*;
import javax.swing.*;
public class Window extends JFrame{
    public Window(){
       super("15 Puzzle");
       Panel obj=new Panel();
       Label Lobj=new Label();
       obj.CreatePanel1();
       obj.CreatePanel2();
       this.add(obj.p1,BorderLayout.NORTH);
       this.add(obj.p2,BorderLayout.SOUTH);
       this.add(Lobj.l1,BorderLayout.CENTER);
    }
}