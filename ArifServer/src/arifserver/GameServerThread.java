

package arifserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


class GameServerThread extends Thread{

    private Server     server    = null;
    private Socket           socket    = null;
    private int              ID        = -1;
    private DataInputStream  streamIn  =  null;
    private DataOutputStream streamOut = null;
    public int easy=0,medium=0,hard=0;
    public int score=0;
    public String name=new String();

    GameServerThread(Server _server, Socket _socket) {
        super();
         server = _server;
         socket = _socket;
         ID     = socket.getPort();
    }


    public void open() throws IOException
    {
        streamIn = new DataInputStream(new
                        BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new
                        BufferedOutputStream(socket.getOutputStream()));
    }

    @Override
     public void run()
    {
        String s;
         System.out.println("Server Thread " + ID + " running.");
         while (true)
         {
             try
             {
                     if ((s=streamIn.readUTF()).equals("Ok"))
                     {
                         server.handle(ID, streamIn.readUTF());
                         server.handle(ID,streamIn.readInt());
                     }
                     else{
 
                            server.handle(ID,s);
                     }
             }
             catch(IOException ioe)
             {
                 System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                 server.remove(ID);
                   stop();
             }
         }
    }

     public void send(String msg)
     {
          try
          {
              streamOut.writeUTF(msg);
              streamOut.flush();
          }
          catch(IOException ioe)
          {
             System.out.println(ID + " ERROR sending: " + ioe.getMessage());
             server.remove(ID);
             stop();
          }
     }
     public void send(int score)
     {
          try
          {
              streamOut.writeInt(score);
              streamOut.flush();
          }
          catch(IOException ioe)
          {
             System.out.println(ID + " ERROR sending: " + ioe.getMessage());
             server.remove(ID);
             stop();
          }
     }



    int getID() {
        return ID;
    }


    void close() throws IOException {
        if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
        if (streamOut != null) streamOut.close();
    }


}
