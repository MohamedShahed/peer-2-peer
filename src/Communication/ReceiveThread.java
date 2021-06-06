package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ReceiveThread implements Runnable{

    private ServerSocket ss;
    private Socket s;
    private Broadcast broadcast;//Maybe removed.


    public ReceiveThread(){}
    public ServerSocket getServerSocket(){return ss;}
    /*
     * receiving the fake data
     * */
    public int receive()throws NullPointerException, IOException
    {
        int portNum=2000;
        if(ss == null)
            ss=new ServerSocket(portNum);
        s=ss.accept();
        InputStreamReader in=new InputStreamReader(s.getInputStream());
        BufferedReader bf=new BufferedReader(in);
        return bf.read();

    }
    /*
     * if a now peer send hi message then will update all address
     * and send the own generated data file  <<here ur work mostafa
     * */
    public void receivingHi()throws IOException
    {
        ArrayList<InetAddress> broadcastList;
        if(ss == null)
            ss=new ServerSocket(2000);
        s=ss.accept();
        InputStreamReader in=new InputStreamReader(s.getInputStream());
        BufferedReader bf=new BufferedReader(in);
        String hi=bf.readLine();


        if(hi=="hi")broadcast.listAllBroadcastAddresses();

    }

    public void run(){
        try{
            System.out.println(receive());
        } catch (IOException ioe)
        {
            System.out.println("Some IO error happend. Try again later.");
        }
    }
}
