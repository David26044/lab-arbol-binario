package co.edu.unipiloto.estdatos.binarios.interfaz;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Screen
{
    public static void clear()
    {
         String OS = System.getProperty("os.name");
         String ins = null;

         if(OS.contains("Windows"))
         {
              ins = "cls";
              for(int c = 0; c < 1000; c++)
              {
                     System.out.println("\b") ;
              }
         }
         else
         {
              ins = "clear";
         }

         try
         {
              Runtime r = Runtime.getRuntime();
              Process pr = r.exec(new String[]{ins});
              pr.waitFor();
              String line = "";
 
              try(BufferedReader reader = 
              new BufferedReader(new InputStreamReader(pr.getInputStream())))
              {
                     while ((line = reader.readLine())!= null) 
                     {
                            System.out.println(line);
                     }
              }
              
         }
         catch(Exception e)
         {

         }
    }

    public void halfClear()
    {
          for(int i = 0; i < 12; i++)
          {
              System.out.println();
          }
    }

}
