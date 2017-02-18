
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import sun.applet.Main;



final class GameClientThread extends Thread{

    private Socket           socket   = null;
    private Client      client   = null;
    private DataInputStream  streamIn = null;



    GameClientThread(Client _client, Socket _socket) {
        client   = _client;
        socket   = _socket;
        open();
        start();
    }

    @Override
    public void run()
    {
       String s;
        while (true)
        {
            try
            {
                if((s=streamIn.readUTF()).equals(".bye"))
                {
                    System.exit(0);
                }
                else{
                client.handle(s);
                client.handle(streamIn.readInt());
                }
            }
            catch(IOException ioe)
            {
                System.out.println("Listening error: " + ioe.getMessage());
                client.stop();
            }
        }
    }

     public void open()
    {
        try
        {
            streamIn  = new DataInputStream(socket.getInputStream());
        }
        catch(IOException ioe)
        {
            System.out.println("Error getting input stream: " + ioe);
            client.stop();
        }
    }

    void close() {
        try
         {
             if (streamIn != null) streamIn.close();
         }
         catch(IOException ioe)
         {
             System.out.println("Error closing input stream: " + ioe);
         }
    }
    
}
