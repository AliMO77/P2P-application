package src.main.java;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.net.*;



public class Server extends JFrame implements ActionListener
{

    protected String name;
    int port;
    InetAddress address;
    Box box;
    JTextField txtfield;
    JTextArea txtarea;
    byte[] serverbffr,clientbffr;
    DatagramSocket client,server;
    JButton button1;


    public static void main(String [] args) throws UnknownHostException {

        Server server=new Server("Server");

    }


    public Server(String name)
    {
        this.name = name; this.name = name;

        this.setSize(300,300);
        this.setTitle(name);
        this.setBackground(Color.WHITE);
        box = Box.createHorizontalBox();

        txtfield=new JTextField();
        txtfield.setBackground(Color.white);
        txtfield.setForeground(Color.black);
        box.add(txtfield,BorderLayout.NORTH);
        txtarea=new JTextArea();
        txtarea.setBackground(Color.black);
        txtarea.setForeground(Color.white);


        box.add(txtarea,BorderLayout.CENTER);
        button1=new JButton("SEND");
        this.add(button1,BorderLayout.SOUTH);
        button1.addActionListener(this);
        button1.setBackground(Color.PINK);
//		button1.setBackground(Color.WHITE);
        this.add(box);
        this.setVisible(true);
        clientbffr=new byte[1024];

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        serverbffr=new byte[1024];


        //*******************************************************************
        try
        {
            client=new DatagramSocket();
            server=new DatagramSocket(9999);
            while(true)
            {
                DatagramPacket datapack=new DatagramPacket(clientbffr,clientbffr.length);
                server.receive(datapack);
                String msg=new String(datapack.getData());
                txtarea.append("\nclient:"+msg);
            }
        }
        catch(Exception e){}

    }


    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getActionCommand()=="SEND") {
                String message=txtfield.getText();
                serverbffr=message.getBytes();
                DatagramPacket sendpack=new DatagramPacket(serverbffr,serverbffr.length,InetAddress.getLocalHost(),9998);
                DatagramPacket sendpack2=new DatagramPacket(serverbffr,serverbffr.length,InetAddress.getLocalHost(),9997);
                client.send(sendpack);
                client.send(sendpack2);
                txtarea.append("\n@"+name+": "+message);
                txtfield.setText("");
            }
        }
        catch(Exception a){}
    }



}