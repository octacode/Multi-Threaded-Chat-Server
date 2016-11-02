import java.net.*;
import java.io.*;
class udp1
{
	public static void main(String s1[]) throws Exception
	{
		
		DatagramSocket ds=new DatagramSocket(8);
		byte b[]=new byte[1024];
		DatagramPacket dp=new 		DatagramPacket(b,b.length);
		ds.receive(dp);
		String r=new String(dp.getData());
		System.out.println(r.trim());
		
	}
}