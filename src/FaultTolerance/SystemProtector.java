package FaultTolerance;

import DataStore.DataStore;

import java.util.LinkedList;
import java.util.Queue;


public class SystemProtector implements Runnable{
	
	private Queue<Integer> receivingQueue = new LinkedList<>();
	//private ReceiveThread receiveThread = new ReceiveThread();
	private static DataStore dataStore = DataStore.creatInstance();
	
	
	public SystemProtector()
	{
		Thread t=new Thread(this);
		t.start();
	}
	public void run()
	{
		try {
		int data;
		while(true){
			//data=receiveThread.receive();
			//receivingQueue.add(data);
			//System.out.println(data);
			if(receivingQueue.size()>=500)
				dataStore.saveAll(receivingQueue);
		}
		
		}catch(Exception e )
		{
			System.out.println(e.getMessage());
		}
	}
	
	
};
