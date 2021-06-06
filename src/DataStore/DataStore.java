package DataStore;


import java.io.PrintWriter;
import java.util.Queue;


//Singleton Design Pattern
public class DataStore {
	private String allDataFileName ="All_data.text";
	private PrintWriter allFile;
	private static DataStore obj=null;
	
	
	private DataStore() {}
	
	public static DataStore creatInstance()
	{
		if(obj==null)obj=new DataStore();
		return obj; 
	}
	
	public void saveAll(Queue <Integer>q)
	{
		if(allFile ==null) {
			try {
			allFile =new PrintWriter(allDataFileName);
			}catch(Exception e)
			{
				System.out.println(e);
			}
			finally {
				allFile.close();
			}
		}
		
		for(int i=0; i<q.size(); i++) allFile.print(q.poll());
	}
	

}
