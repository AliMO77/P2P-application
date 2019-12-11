package src.main.java;//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
//
//import java.io.*;
//
//import java.net.*;
//
//
//
//public class Friend extends JFrame implements ActionListener
//{
//    Server server;
//    private String name;
//    int port;
//    InetAddress address;
//    Box box;
//    JTextField txtfield;
//    JTextArea txtarea;
//    byte[] friendbffr,clientbffr;
//    DatagramSocket client,friend;
//    JButton button1;
//
//
//    public static void main(String [] args) throws UnknownHostException {
//
//        Friend server=new Friend("Ali");
//
//    }
//
//
//    public Friend (String name)
//    {
//        this.name = name; this.name = name;
//        this.address = address;
//
//        this.setSize(300,300);
//        this.setTitle(name);
//        this.setBackground(Color.WHITE);
//        box = Box.createHorizontalBox();
//
//        txtfield=new JTextField();
//        txtfield.setBackground(Color.white);
//        txtfield.setForeground(Color.black);
//        box.add(txtfield,BorderLayout.NORTH);
//        txtarea=new JTextArea();
//        txtarea.setBackground(Color.black);
//        txtarea.setForeground(Color.white);
//
//
//        box.add(txtarea,BorderLayout.CENTER);
//        button1=new JButton("SEND");
//        this.add(button1,BorderLayout.SOUTH);
//        button1.addActionListener(this);
//        button1.setBackground(Color.PINK);
////		button1.setBackground(Color.WHITE);
//        this.add(box);
//        this.setVisible(true);
//        clientbffr=new byte[1024];
//        friendbffr=new byte[1024];
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //*******************************************************************
//        try
//        {
//            client=new DatagramSocket();
//            friend=new DatagramSocket(9997);
//            while(true) {
//                        DatagramPacket datapack = new DatagramPacket(clientbffr, clientbffr.length);
//                        friend.receive(datapack);
//                        String msg = new String(datapack.getData());
//                        txtarea.append("\nJean:" + msg);
//            }
//        }
//        catch(Exception e){}
//
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if(e.getActionCommand()=="SEND") {
//                String message=txtfield.getText();
//                friendbffr=message.getBytes();
//                DatagramPacket sendpack=new DatagramPacket(friendbffr,friendbffr.length,InetAddress.getLocalHost(),9998);
//                DatagramPacket sendpack2=new DatagramPacket(friendbffr,friendbffr.length,InetAddress.getLocalHost(),9999);
//                client.send(sendpack);
//                client.send(sendpack2);
//                txtarea.append("\n@"+name+": "+message);
//                txtfield.setText("");
//            }
//        }
//        catch(Exception a){}
//    }
//
//
//
//}