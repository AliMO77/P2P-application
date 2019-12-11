import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ahmed extends JFrame implements ActionListener
{
	private String name;
	Box box;
	byte[] ahmedbuffer, otherbuffer;
	DatagramSocket ahmed, other;
	JButton button;
	JTextField textField;
	JTextArea textArea;


	public static void main(String[] args) throws UnknownHostException {

		Ahmed jean = new Ahmed("Ahmed");
		Thread thread = new Thread((Runnable) jean);
		thread.start();
	}

	public Ahmed(String name) throws UnknownHostException {

		this.name = name;
		this.setSize(300,300);
		this.setTitle(name);
		this.setBackground(Color.WHITE);
		box = Box.createHorizontalBox();

		textField =new JTextField();
		textField.setBackground(Color.white);
		textField.setForeground(Color.black);
		box.add(textField,BorderLayout.NORTH);
		textArea =new JTextArea();
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);


		box.add(textArea,BorderLayout.CENTER);
		button =new JButton("SEND");
		this.add(button,BorderLayout.SOUTH);
		button.addActionListener(this);
		button.setBackground(Color.PINK);
//		button1.setBackground(Color.WHITE);
		this.add(box);
		this.setVisible(true);
		ahmedbuffer =new byte[2048];
		otherbuffer =new byte[2048];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//****************************************************************

		try {
			ahmed =new DatagramSocket();
			other =new DatagramSocket(9998);
			while(true) {
				DatagramPacket datapack=new DatagramPacket(ahmedbuffer, ahmedbuffer.length);
				other.receive(datapack);
				String msg=new String(datapack.getData());
				//String value=display.getText();
				textArea.append("\n@msg:"+msg);
			}
		}

		catch(Exception e){}

	}

	//*********************************************************************

	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand()=="SEND") {
				String message= textField.getText();
				otherbuffer =message.getBytes();
				DatagramPacket sendpack=new DatagramPacket(otherbuffer, otherbuffer.length,InetAddress.getLocalHost(),9997);
				DatagramPacket sendpack2=new DatagramPacket(ahmedbuffer, ahmedbuffer.length,InetAddress.getByName("127.0.0.1"),9999);
				ahmed.send(sendpack);
				ahmed.send(sendpack2);
				textArea.append("\n@"+name+": "+message);
				textField.setText("");
			}
		}
		catch(Exception a){}
	}




	public String getName() {
		return name;
	}
}