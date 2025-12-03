//////////////////////////////////////////////////////////////////////
// File name :       ChatServer.java
// Discription :     It is Server CUI for communication
// Author :          Mangesh Ashok Bedre
// Date :            26/11/2025
//
//////////////////////////////////////////////////////////////////////
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class ChatServer 
{
    public static void main(String A[]) 
    {
        try
        {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String logFileName = "ChatLog" + timestamp + ".txt";

            FileWriter fwobj = new FileWriter(logFileName, true); //true for append chat at end

            ServerSocket ssobj = new ServerSocket(8080);
            System.out.println("Server is waiting at port number 8080");

            Socket sobj = ssobj.accept();
            System.out.println("Server is successfully connected with the client");

            DataInputStream dis = new DataInputStream(sobj.getInputStream());
            DataOutputStream dos = new DataOutputStream(sobj.getOutputStream());
            BufferedReader bobj = new BufferedReader(new InputStreamReader(System.in)); 

            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("Chat Messenger is ready to use");
            System.out.println("----------------------------------------------------------------------------------------------------");

            String str1 = null, str2 = null;

            while (!(str1 = dis.readUTF()).equals("bye")) 
            {
                
                System.out.println("Client Says : " + str1);
                fwobj.write("Client says: " + str1 + "\n");

                System.out.print("You : ");
                str2 = bobj.readLine();
                dos.writeUTF(str2);

                fwobj.write("Server says: " + str2 + "\n");

                fwobj.flush(); 
            }
            fwobj.write("Client says: " + str1 + "\n");

            fwobj.close();
            dos.close();
            dis.close();
            bobj.close();
            sobj.close();
            ssobj.close();
        }
        catch(Exception eobj)
        {
            System.out.println("Error : "+eobj.getMessage());
            fwobj.close();
            dos.close();
            dis.close();
            bobj.close();
            sobj.close();
            ssobj.close();
        }
    }
}
