
package arifserver;
import java.net.*;
import java.io.*;

public class Server implements Runnable{
    private GameServerThread clients[] = new GameServerThread[50];
    private ServerSocket server = null;
    private Thread       thread = null;
    private int clientCount = 0;
    
    public void set()
    {
        try
        {
            System.out.println("Binding to port " + 1234 + ", please wait  ...");
            server = new ServerSocket(1234);
            //System.out.println(server.getInetAddress());
            System.out.println("Server started: " + server);
            start();
        }
        catch(IOException ioe)
        {
            System.out.println("Can not bind to port " + 1234 + ": " + ioe.getMessage());

        }

    }


    private void start() {
        if (thread == null)
        {
            thread = new Thread (this);
            thread.start();
        }
    }

    public void run() {
         while (thread != null)
        {
            try
            {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            }
            catch(IOException ioe)
            {
                System.out.println("Server accept error: " + ioe);
                stop();
            }
        }
    }

    private void addThread(Socket socket)
    {
        if (clientCount < clients.length)
        {
            System.out.println("Client accepted: " + socket);
            clients[clientCount] = new GameServerThread(this,socket);
             try
             {
                 clients[clientCount].open();
                 clients[clientCount].start();
                 clientCount++;
             }
             catch(IOException ioe)
             {
                 System.out.println("Error opening thread: " + ioe);
             }
        }
        else
            System.out.println("Client refused: maximum " + clients.length + " reached.");
    }



    private int findClient(int ID) {
        for (int i = 0; i < clientCount; i++)
        {
            if (clients[i].getID() == ID)
            {
                return i;
            }
        }
        return -1;
    }

    void remove(int ID) {
        int pos = findClient(ID);
        if (pos >= 0)
        {
            GameServerThread toTerminate = clients[pos];
            System.out.println("Removing client thread " + ID + " at " + pos);
            if (pos < clientCount)
            {
                for (int i = pos+1; i < clientCount; i++)
                {
                    clients[i-1] = clients[i];
                }
                clientCount--;
            }
            try
            {
                toTerminate.close();
            }
            catch(IOException ioe)
            {
                 System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.stop();
        }
    }

  

    private void stop() {

        if (thread != null)
        {
            thread.stop();
            thread = null;
        }
    }

    void handle(int ID, String input) {
        System.out.println(input);
        if (input.equals(".bye"))
        {
           clients[findClient(ID)].send(".bye");
           remove(ID);
        }
        else if(input.equals("easy"))
        {
            clients[findClient (ID)].easy=1;
        }
        else if(input.equals("medium"))
        {
            clients[findClient (ID)].medium=1;
        }
        else if(input.equals("hard"))
        {
            clients[findClient (ID)].hard=1;
        }
        else if(input.equals("back"))
        {
            clients[findClient (ID)].easy=0;
            clients[findClient (ID)].medium=0;
            clients[findClient (ID)].hard=0;
        }
        else   if(input.equals("Show Score"))
            {             
                ReadFile rf=new ReadFile();
                if(clients[findClient (ID)].easy==1)
                {
                    rf.openFile1();
                }
                else if(clients[findClient (ID)].medium == 1)
                {
                    rf.openFile2();
                }
                else if(clients[findClient (ID)].hard == 1)
                {
                    rf.openFile3();
                }
                rf.readRecords();
                
                for(int i=0;i<10;i++)
                {
                    clients[findClient(ID)].send(rf.name[i]);
                    clients[findClient(ID)].send(rf.score[i]);
                }   
                rf.closeFile();    
            }
        else{
            clients[findClient (ID)].name=input;            
        }
    }
    public void handle(int ID,int i)
    {
        clients[findClient (ID)].score=i;
        System.out.println(i);
        WriteFile obj=new WriteFile();
        if(clients[findClient (ID)].easy==1)
        {
            obj.readData1();
            obj.openFile1();
        }
        else if(clients[findClient (ID)].medium == 1)
        {
            obj.readData2();
            obj.openFile2();
        }
        else if(clients[findClient (ID)].hard == 1)
        {
            obj.readData3();
            obj.openFile3();
        }
       obj.addRecords(clients[findClient (ID)].name, clients[findClient (ID)].score);
       obj.closeFile();
    }
}