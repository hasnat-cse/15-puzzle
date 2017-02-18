package arifserver;


 import java.io.File;
 import java.io.FileNotFoundException;
 import java.lang.IllegalStateException;
 import java.util.NoSuchElementException;
 import java.util.Scanner;


 public class ReadFile
 {
      private Scanner input;
      public String name[]=new String[10];
      public int score[]=new int[10];
      public ReadFile(){
          for(int i=0;i<10;i++)
              name[i]="void";
      }
      public void openFile1()
      {
          try
          {
             input = new Scanner(new File("easy.txt"));
          }
          catch ( FileNotFoundException fileNotFoundException )
          {
             System.err.println( "Error opening file." );
             System.exit( 1 );
          }
      }
      public void openFile2()
      {
          try
          {
             input = new Scanner(new File("medium.txt"));
          } // end try
          catch ( FileNotFoundException fileNotFoundException )
         {
            System.err.println( "Error opening file." );
            System.exit( 1 );
         }
     }
     public void openFile3()
     {
          try
          {
             input = new Scanner(new File("hard.txt"));
          }
          catch ( FileNotFoundException fileNotFoundException )
          {
             System.err.println( "Error opening file." );
             System.exit( 1 );
          }
     }
     public void readRecords()
     {
          try
          {
               int i=0;
               while(input.hasNext())
               {
                        name[i]=input.next();
                        score[i]=input.nextInt();
                        i++;
               }
          }
          catch ( NoSuchElementException elementException )
          {
             System.err.println( "File improperly formed." );
             input.close();
             System.exit( 1 );
          }
          catch ( IllegalStateException stateException )
          {
           System.err.println( "Error reading from file." );
           System.exit( 1 );
          }
     }
     public void closeFile()
     {
          if ( input != null )
         input.close();
     }
 }