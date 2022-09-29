package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class Client extends javax.swing.JFrame{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		 int inp=JOptionPane.showConfirmDialog(null,"Escolha o usuário?\nYes - Servidor\nNo - Cliente","Conversa Java",JOptionPane.YES_NO_OPTION);
	        if(inp==0)
	            {
	            new ClasseMestre(true,null).setVisible(true);
	            }
	        else 
	            {
	            String ipstring=JOptionPane.showInputDialog("Por favor. digite o endereço IP");
	            try{
	                InetAddress.getByName(ipstring);
	                new ClasseMestre (false,ipstring).setVisible(true);
	            }catch(Exception e){JOptionPane.showMessageDialog(null,"IP inválido");}
	            }
	        
	        
		Socket s = new Socket ("localhost", 3333);
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        
		String str = "", str2 = "";
		while(!str.equals("stop")) {
			str = br.readLine();
			dout.writeUTF(str);
			dout.flush();
			str2 = din.readUTF();
			System.out.println("Server says: " +str2);
		}
		
		dout.close();
		s.close();
	}

}
