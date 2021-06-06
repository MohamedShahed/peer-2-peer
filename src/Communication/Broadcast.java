package Communication;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

public class Broadcast{
    private static ArrayList<InetAddress> broadcastList; // here we have all the peer address
    public Broadcast()
    {
        broadcastList = new ArrayList<>(); // here we have all the peer address
    }
    /*
     * list all address for all peers in at list of InetAddress
     *
     */
    public void listAllBroadcastAddresses() throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements())
        {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback() || !networkInterface.isUp())continue;

            networkInterface.getInterfaceAddresses().stream().map(a -> a.getBroadcast())
                    .filter(Objects::nonNull).forEach(broadcastList::add);
        }
    }


    /*
	 * make a sendUDPPacket for specific peer
	 * */
    public void sendUDPPacket(String UDPMessage, InetAddress address) throws IOException {
        DatagramSocket socket = null;
        socket = new DatagramSocket();
        socket.setBroadcast(true);
        byte[] buffer = UDPMessage.getBytes();
        int port = 4444;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        socket.close();
    }


    public void broadcast() throws IOException {
        for(int i=0; i<broadcastList.size(); i++) sendUDPPacket("hi", broadcastList.get(i));
    }

    public void printBroadcastList(){
        System.out.println(broadcastList.size());
        for (int i = 0; i < broadcastList.size(); i++) {
            System.out.println(broadcastList.get(i).toString());
        }
    }
    public static void main(String [] args)
    {

    }
}