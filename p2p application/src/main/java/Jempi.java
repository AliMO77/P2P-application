import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.net.*;



public class Jempi extends JFrame implements ActionListener
{

    private String name;
    Box box;
    JTextField textField;
    JTextArea textArea;
    byte[] jempibuffer, otherbuffer;
    DatagramSocket jempi, other;
    JButton button;


    public static void main(String [] args) throws UnknownHostException {

        Jempi jempi=new Jempi("jempi");
        Thread thread = new Thread((Runnable) jempi);
        thread.start();

    }


    public Jempi(String name)
    {
        this.name = name; this.name = name;

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
        otherbuffer =new byte[1024];
        jempibuffer =new byte[1024];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //*******************************************************************
        try
        {
            jempi =new DatagramSocket();
            other =new DatagramSocket(9997);
            while(true) {
                        DatagramPacket datapack = new DatagramPacket(otherbuffer, otherbuffer.length);
                        other.receive(datapack);
                        String msg = new String(datapack.getData());
                        textArea.append("\nmsg:" + msg);
            }
        }
        catch(Exception e){}

    }


    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getActionCommand()=="SEND") {
                String message= textField.getText();
                jempibuffer =message.getBytes();
                DatagramPacket sendpack=new DatagramPacket(jempibuffer, jempibuffer.length,InetAddress.getLocalHost(),9998);
                DatagramPacket sendpack2=new DatagramPacket(jempibuffer, jempibuffer.length,InetAddress.getByName("127.0.0.0"),9999);
                jempi.send(sendpack);
                jempi.send(sendpack2);
                textArea.append("\n@"+name+": "+message);
                textField.setText("");
            }
        }
        catch(Exception a){}
    }



}