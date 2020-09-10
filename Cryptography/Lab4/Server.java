import java.net.*;
import java.io.*;
class Server
{
	public String decrypt(String c,String k)
	{
        c=c.toLowerCase();
        k=k.toLowerCase();
        int ki=0;
        String result="";
        for(var i=0;i<c.length();i++)
        {
            if(ki==k.length()){
                ki=0;
            }
            //int d=(c.charCodeAt(i) - k.charCodeAt(ki)+26)%26;
            int d=((int)(c.charAt(i)) - (int)(k.charAt(ki))+26)%26; 

            result+=(char)(d+97);
            ki+=1;
        }
        return(result);            
	}

	public static void main(String args[])throws Exception
	{
		ServerSocket ss=new ServerSocket(3333);
		Socket s=ss.accept();
		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		Server decrypted=new Server();
		String c ="",k="",m="";
		while(!c.equals("stop"))
		{
			c=din.readUTF();
			k=din.readUTF();
			System.out.println("client says encrypted text= "+c);
			m=decrypted.decrypt(c,k);
			//str2=br.readLine();
			dout.writeUTF(m);
			dout.flush();
		}
		din.close();
		s.close();
		ss.close();
	}
}
