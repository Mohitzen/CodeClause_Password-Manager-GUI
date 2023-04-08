import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class save_Encrypted_passwords extends JFrame{
    save_Encrypted_passwords(){
        JFrame j=new JFrame("Save Passwords in Encrypted Form");
        ImageIcon icon =new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/background.png");
        JLabel l1=new JLabel("",icon, JLabel.CENTER);
        l1.setBounds(0,-20,600,600);
        j.add(l1);

        JLabel l2=new JLabel("Password");
        l2.setBounds(155,425,200,40);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Sans_Serif",Font.BOLD,20));
        l1.add(l2);

        JLabel l3=new JLabel("Website");
        l3.setBounds(155,375,280,40);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Sans_Serif",Font.BOLD,20));
        l1.add(l3);

        JTextArea t1=new JTextArea();
        l1.add(t1);
        t1.setFont(new Font("Sans_Serif",Font.PLAIN,20));
        t1.setLineWrap(true);
        t1.setWrapStyleWord(true);

        JScrollPane jsp1=new JScrollPane(t1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp1.setBounds(150,380,290,40);
        jsp1.setMaximumSize(new Dimension(30,40));
        l1.add(jsp1);
      
        JPasswordField t2=new JPasswordField();
        t2.setBounds(150,425,290,40);
        t2.setFont(new Font("Sans_Serif",Font.BOLD,20));
        l1.add(t2);
        t2.setEchoChar('*');  
        
        JButton back=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/1.png"));
        back.setBounds(50,50,45,45);
        l1.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new Main();
                j.dispose();
            }
        });


        JButton X=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/lock.png"));
	X.setBounds(500,50,45,45);
	l1.add(X);
    X.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			new Decrypt();
			j.dispose();
		}
	});
        
        JButton b1=new JButton("SAVE");
        b1.setBackground(Color.GREEN);
        b1.setBounds(150,470,290,40);
        b1.setFont(new Font("Sans_Serif",Font.BOLD,30));
        l1.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    String x1=t1.getText();
                    String x2=t2.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String URL="jdbc:mysql://localhost:3306/password_generator";
                    String username="root";
                    String pwd="root";
                    Connection con=DriverManager.getConnection(URL,username,pwd);
            String sql="Insert into password(website, password) values(?,?);";
            // System.out.print("Connected");
            PreparedStatement ps=con.prepareStatement(sql);


            StringBuilder code=new StringBuilder();
            int key=6;
            char[] chars =x2.toCharArray();
            for(char c: chars){
                c+=key;
                code.append(c);
            }

            ps.setString(1,x1);
            ps.setString(2,code.toString());
                            if(!x1.equals("") && !x2.equals("")){
                                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Saved");
            }
        else JOptionPane.showMessageDialog(null,"Insert Proper Details","WARNING",JOptionPane.WARNING_MESSAGE);}
        
        catch(Exception e1){
            e1.printStackTrace();
        }
    }
});

        j.setSize(600,600);
        j.setLayout(null);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
    public static void main(String [] args){
        new save_Encrypted_passwords();
    }
}
