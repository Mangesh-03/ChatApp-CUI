import java.io.*;
import java.net.*;

class ChatClient
{
    public static void main (String A[]) 
    {   

        try
        {
            System.out.println("Client is ready for the connection");
        Socket sobj = new Socket("localhost",8080);
        
        System.out.println("client is succesfully connected with server");
        
        DataInputStream dis = new DataInputStream(sobj.getInputStream()); // kan 
        DataOutputStream dos = new DataOutputStream(sobj.getOutputStream());// mouth

        BufferedReader bobj = new BufferedReader(new InputStreamReader(System.in));//user input

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Chat Messenger is ready to use");
        System.out.println("----------------------------------------------------------------------------------------------------");

        String str1 = null , str2 = null;

        System.out.println("You : ");

        while(!(str1 = bobj.readLine()).equals("bye"))
        {
            dos.writeUTF(str1);
            str2 = dis.readUTF();

            System.out.println("Server says : " + str2);

            System.out.print("You : ");
        }

        dos.writeUTF("bye");

        dos.close();
        dis.close();
        bobj.close();
        sobj.close();
        }
        catch(Exception eobj)
        {
            System.out.println("Error : "+eobj.getMessage());
            dos.close();
            dis.close();
            bobj.close();
            sobj.close();
        }


    }
}