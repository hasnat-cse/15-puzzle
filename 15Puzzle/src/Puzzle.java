
import javax.swing.JOptionPane;

public class Puzzle {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "\n" + "\n" + "\n" + "             Welcome to '15 PUZZLE'                              \n" + "\n" + "\n" + "\n" + "\n" + "             Press OK to enter  the game                              " + "\n" + "\n" + "\n" + "\n" + "\n ", "15 PUZZLE", JOptionPane.PLAIN_MESSAGE);
        JOptionPane p = new JOptionPane();
        String s = p.showInputDialog("Enter server Ip","0.0.0.0");
        Client c = new Client();
        c.set(s);
        StartFrame startFrame= new StartFrame();
        startFrame.setDefaultCloseOperation(startFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);
    }
}
