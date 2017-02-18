
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;


public class Client implements Runnable{

    private Socket socket              = null;
    private Thread thread              = null;
    private DataOutputStream streamOut = null;
    private GameClientThread client    = null;
    private DataInputStream streamIN=null;
    public static int send=0;
    public static int score=0;
    public static int bye=0;
    public static String name=null;
    public static int point=0;
    public static int easy=0,medium=0,hard=0,back=0;

    public void set(String s)
    {
        
        System.out.println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(s,1234);
            System.out.println("Connected: " + socket);
            start();
        }
        catch(UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
            JOptionPane p=new JOptionPane();
            p.showMessageDialog(null,"no server Found");
            System.exit(1);
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
            JOptionPane p=new JOptionPane();
            p.showMessageDialog(null,"no server Found");
            System.exit(1);
        }
    }

    public void start() throws IOException {

            streamIN   = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            if (thread == null)
            {
                
                client = new GameClientThread(this, socket);
                thread = new Thread(this);
                thread.start();    
            }
    }
    public void run()
    {
        while(thread!=null)
        {
            try
            {
                if(send==1)
                {
                    send=0;
                    streamOut.writeUTF("Show Score");
                    streamOut.flush();
                }
                else if(score == 1)
                {                
                    score=0;
                    streamOut.writeUTF("Ok");
                    streamOut.writeUTF(name);
                    streamOut.writeInt(point); 
                    streamOut.flush();
                }
                else if(bye == 1)
                {
                    bye=0;
                    streamOut.writeUTF(".bye");
                    streamOut.flush();
                }
                else if (easy == 1)
                {
                    easy=0;
                    streamOut.writeUTF("easy");
                    streamOut.flush();
                
                }
                else if(medium == 1)
                {
                    medium=0;
                    streamOut.writeUTF("medium");
                    streamOut.flush();
                }
                else if(hard == 1)
                {
                    hard=0;
                    streamOut.writeUTF("hard");
                    streamOut.flush();
                }
                else if(back == 1)
                {
                    back=0;
                    streamOut.writeUTF("back");
                    streamOut.flush();
                }
            }
            catch(IOException ioe)
            {
                System.out.println("Sending error: " + ioe.getMessage());
                stop();
            }         
        }
    }
    String[] n=new String[20];
    int i=0;
    int [] a=new int[20];
    int j=0;
    void handle(String msg) {
            n[i]=msg;
            i++;       
    }
    void handle(int msg)
    {
        a[j]=msg;
        j++;
        if(j==10)
            show();
    }
    
    void show()
    {
        i=0;
        j=0;
        String s=new String();
        s="Name"+"           "+"Rank        "+"Score"+"\n"+"\n";
        for(int k=0;k<10;k++)
        {
           s=s+n[k]+"                   "+(k+1)+"               "+a[k]+"\n";
        }
        JOptionPane p=new JOptionPane();
        p.showMessageDialog(null,s,"Score",JOptionPane.INFORMATION_MESSAGE);
    }

    void stop() {
        if (thread != null)
        {
            thread.stop();
            thread = null;
        }
        try
        {
            if (streamIN  != null)  streamIN.close();
            if (streamOut != null)  streamOut.close();
            if (socket    != null)  socket.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error closing ...");
            client.close();
            client.stop();
        }

    }
}
