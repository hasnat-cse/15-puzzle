package arifserver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class WriteFile {
    private Formatter output;
     public String name[]=new String[10];
      public int score[]=new int[10];
    
    public void readData1(){
        ReadFile rd=new ReadFile();
        rd.openFile1();
        rd.readRecords();
        name=rd.name;
        score=rd.score;
        rd.closeFile();
    }
    public void readData2(){
        ReadFile rd=new ReadFile();
        rd.openFile2();
        rd.readRecords();
        name=rd.name;
        score=rd.score;
        rd.closeFile();
    }
    public void readData3(){
        ReadFile rd=new ReadFile();
        rd.openFile3();
        rd.readRecords();
        name=rd.name;
        score=rd.score;
        rd.closeFile();
    }
    public void openFile1()
    {
      try
      {
         output = new Formatter( "easy.txt" );
      } 
      catch ( SecurityException securityException )
      {
         System.err.println("You do not have write access to this file." );
         System.exit( 1 );
      } 
      catch ( FileNotFoundException fileNotFoundException )
      {
         System.err.println( "Error opening or creating file." );
         System.exit( 1 );
      }
    }
    public void openFile2()
    {
      try
      {
         output = new Formatter( "Medium.txt" );
      }
      catch ( SecurityException securityException )
      {
         System.err.println("You do not have write access to this file." );
         System.exit( 1 );
      }
      catch ( FileNotFoundException fileNotFoundException )
      {
         System.err.println( "Error opening or creating file." );
         System.exit( 1 );
      }
    }
    public void openFile3()
    {
      try
      {
         output = new Formatter( "hard.txt" );
      }
      catch ( SecurityException securityException )
      {
         System.err.println("You do not have write access to this file." );
         System.exit( 1 );
      }
      catch ( FileNotFoundException fileNotFoundException )
      {
        System.err.println( "Error opening or creating file." );
        System.exit( 1 );
      }
    }
    public void addRecords(String s, int a)
    {
      for(int i=0;i<10;i++)
      {
         if(a>score[i])
         {
             for(int j=9;j>i;j--)
             {
                 score[j]=score[j-1];
                 name[j]=name[j-1];
             }
            score[i]=a;
            name[i]=s;
            break;
         }
     }
     try
     {
            for(int i=0;i<10;i++)
            {
                output.format("%s       %d \n",name[i],score[i]);
            }
     }
     catch ( FormatterClosedException formatterClosedException )
     {
            System.err.println( "Error writing to file." );
            return;
     }
   }

   public void closeFile()
   {
      if ( output != null )
      output.close();
   }
}

