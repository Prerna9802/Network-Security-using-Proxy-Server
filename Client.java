import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Client extends JFrame {

    Socket socket;
    BufferedReader br;
    PrintWriter out;

    //Declare components
    private JLabel heading = new JLabel("Client Area");
    private JTextArea messageArea= new JTextArea();
    private JTextField messageInput= new JTextField();
    private Font font= new Font("Roboto",Font.PLAIN,20);

    //constructor
    public Client()
    {
        try {
            System.out.println("Sending Request to Server");
            socket= new Socket("10.6.253.58",5643);
            System.out.println("Connection Done!");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket. getOutputStream());


            createGUI();
            handleEvents();
            startReading();
            //startWriting();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void handleEvents()
    {
       messageInput.addKeyListener(new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            //System.out.println("Key Released! "+e.getKeyCode());
            if(e.getKeyCode() == 10) {
                //System.out.println("You have pressed Enter Button");
                String contentToSend=messageInput.getText();
                messageArea.append("Me :"+contentToSend+"\n");
                out.println(contentToSend);
                out.flush();
                messageInput.setText("");
                messageInput.requestFocus();
            }
        }
        
       });
    }

    private void createGUI()
    {
        //GUI code..
        this.setTitle("Client Messenger[END]");
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //coding for component
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setIcon(new ImageIcon("clogo.jpg"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        messageArea.setEditable(false);
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);

        //setting the layout
        this.setLayout(new BorderLayout());

        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane= new JScrollPane(messageArea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);

    }


    //starts reading[METHOD]
    public void startReading()
    {
        //will read and deliver the thread
        Runnable r1=()->{
            System.out.println("Reader Started...");
            try{
            while(true)
            {
                String msg= br.readLine();
                if(msg.equals("exit"))
                {
                    System.out.println("Server terminated the chat!");
                    JOptionPane.showMessageDialog(this, "Server Terminated the Chat!");
                    messageInput.setEnabled(false);
                    socket.close();
                    break;
                }
                    //System.out.println("Server: "+ msg);
                    messageArea.append("Server : " + msg+"\n"); 
            }
            } catch (Exception e)
            {
                //e.printStackTrace();
                System.out.println("Connection Closed!");
            }
        };
        new Thread(r1).start();
    }

    //starts writing[METHOD]
    public void startWriting()
    {
        Runnable r2=()->{
            System.out.println("Writer Started...");
            try{
            while(!socket.isClosed())
            {
                    BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();
                    if(content.equals("exit"))
                {
                    socket.close();
                    break;
                }
            }
            System.out.println("Connection is Closed!");
            } catch (Exception e)
            {
                e.printStackTrace();
            } 
        };
        new Thread(r2).start();
    }

     
    public static void main(String[] args) {
        System.out.println("This is Client...");
        new Client();
    }
    
}