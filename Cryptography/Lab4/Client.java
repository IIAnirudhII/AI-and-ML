import java.net.*;
import java.io.*;
class client
{

	public String encrypt(String m,String k)
	{
		m=m.toLowerCase();
        k=k.toLowerCase();
        int ki=0;
        String result="";
        for(int i=0;i<m.length();i++)
        {
            if(ki==k.length()){
                ki=0;
            }
            //int c=(m.charCodeAt(i) + k.charCodeAt(ki) -97-97)%26;
            int c=((int)(m.charAt(i)) + (int)(k.charAt(ki)) -97-97)%26;
            result+=(char)(c+97);
            ki+=1;
        }
        return result;
	}
	public static void main(String args[])throws Exception
	{
		Socket s= new Socket("localhost",3333);
		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		client encrypted=new client();
		String m="",k="",c="";
		while(!m.equals("stop"))
		{
			m=br.readLine();
			k=br.readLine();
			c=encrypted.encrypt(m,k);
			dout.writeUTF(c);
			dout.writeUTF(k);
			dout.flush();
			m=din.readUTF();
			System.out.println("Server says decrypted text= "+m);
		}

		dout.close();
		s.close();
	}
}