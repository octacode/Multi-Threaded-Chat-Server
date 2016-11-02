import java.net.*;
import java.io.*;
import java.util.*;
public class myserver
{
	ArrayList al,al1;
	ServerSocket ss;
	Socket s;
	int flag=0;
	public myserver()
	{
		try
		{
		al=new ArrayList();
		al1=new ArrayList();
		ss=new ServerSocket(8);
		System.out.println("Server Started");
		while(true)
		{
			s=ss.accept();
			System.out.println("client started");
			al.add(s);
			Runnable r=new mythread(s,al);
			Thread t=new Thread(r);
			t.start();
		}
		}
		catch(Exception e){}
	}
	public static void main(String s[])
	{
		new myserver();
	}
	class mythread implements Runnable
	{
		Socket s;
		ArrayList al;
		
		mythread(Socket s,ArrayList al)
		{
			this.s=s;
			this.al=al;
		}
		public void run()
		{
			String s1="";
			
			try
			{
				DataInputStream din=new DataInputStream(s.getInputStream());
				do
				{
					s1=din.readUTF();
					if(s1.startsWith("*"))
					{
					System.out.println(s1);
					}
					else if(s1.startsWith("("))
					{
						char c1[]=new char[s1.length()];
						s1.getChars(0,s1.length()-1,c1,0);
						int h=0;
						for(int i=0;i<c1.length;i++)
						{
							if(c1[i]==' ')
							{
							h=i;
				
							break;
							}
						}
						
						String s=new String(c1);
						String stt=s.substring(2,h);
						System.out.println(stt);
						Object ob[]=al1.toArray();
						String sr[]=new String[ob.length];
						for(int j=0;j<ob.length;j++)
						{
							int y=0;
							sr[j]=(String) ob[j];
							char c2[]=new char[sr[j].length()];
							sr[j].getChars(0,sr[j].length()-1,c2,0);
							for(int i=0;i<c2.length;i++)
							{
								if(c2[i]==' ')
								{
									y=i;
									break;
								}
							}
							String sj=new String(c2);
							String stt1=sj.substring(0,y);
							System.out.println(stt1);
							if(stt1.equals(stt))
							{
								System.out.println("gh");
								al1.remove(j);
								al.remove(j);
								break;
							}	
						}
						String h1="'";	

						Object ob1[]=al1.toArray();
						String sr1[]=new String[ob1.length];
						for(int j=0;j<ob1.length;j++)
						{
							sr1[j]=(String) ob1[j];
							h1=h1+sr1[j]+"\n";
							System.out.println("object  "+h1);
						}
						telleveryone(h1);		
					}
					else
					{
						String h="'";	
						al1.add(s1);
						Object ob[]=al1.toArray();
						String sr[]=new String[ob.length];
						for(int j=0;j<ob.length;j++)
						{
							sr[j]=(String) ob[j];
							h=h+sr[j]+"\n";
							System.out.println("object  "+h);
						}
						telleveryone(h);			
					}
					telleveryone(s1);
				}
				while(s1!=null);
				
				
			}
			catch(Exception e){e.printStackTrace();}
		}
		public void telleveryone(String s1)
		{
			Iterator i=al.iterator();
			while(i.hasNext())
			{
				try
				{
				Socket sc=(Socket)i.next();
				DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
				dout.writeUTF(s1);
				dout.flush();
				
				}
				
				catch(Exception e){e.printStackTrace();}
			}
			
		}
		
	}	
}