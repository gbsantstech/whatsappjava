package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class Server extends javax.swing.JFrame{

	public static void main(String[] args) throws IOException {
		
		int inp=JOptionPane.showConfirmDialog(null,"Escolha o usuário?\nYes - Servidor\nNo - Cliente","Conversa Java",JOptionPane.YES_NO_OPTION);
        if(inp==0)
            {
            new ClasseMestre(true,null).setVisible(true);
            }
        else 
            {
            String ipstring=JOptionPane.showInputDialog("Por favor, digite o endereço IP");
            try{
                InetAddress.getByName(ipstring);
                new ClasseMestre (false,ipstring).setVisible(true);
            }catch(Exception e){JOptionPane.showMessageDialog(null,"IP inválido");}
            }

		ServerSocket ss = new ServerSocket(3333);
		Socket s = ss.accept();
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
        
		String str = "" , str2= "";
		while(!str.equals("stop")) {
			str = din.readUTF();
			System.out.println("Client says: " +str);
			str2 = br.readLine();
			dout.writeUTF(str2);
			dout.flush();
		}
		
		din.close();
		s.close();
		ss.close();
		
	}

}
