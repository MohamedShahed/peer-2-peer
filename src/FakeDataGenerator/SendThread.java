package FakeDataGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

//Maybe we make it static as we want only one send thread that sends data every some time
public class SendThread implements Runnable{
    private Socket s;
    private int fakeNumber;
    InetAddress sendAdress;
    public SendThread(int fakeNumber, InetAddress sendAdress){
        this.fakeNumber = fakeNumber;
        this.sendAdress = sendAdress;
    }
    public SendThread(){}

    public Socket getSocket(){return s;}

    /*
     * send the generated data to another peer
     * */
    public  void sendMesg()throws IOException
    {

        int port = 2000;
        s=new Socket(sendAdress, port);
        PrintWriter pr=new PrintWriter(s.getOutputStream());
        pr.write(fakeNumber);
        pr.flush();
    }

    public void run()
    {
        try {
            sendMesg();
        }catch (IOException e)
        {
            System.out.println("Some IO Exception happend. Sorry!");
        }
    }
}
