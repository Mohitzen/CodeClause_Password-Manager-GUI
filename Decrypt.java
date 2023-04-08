import java.sql.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.datatransfer.*;
import java.awt.event.*;


public class Decrypt extends JFrame{
    Decrypt() {
        JFrame j=new JFrame("Retrieve Passwords in Decrypted Form");
        JLabel l1=new JLabel("", new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/background.png"), JLabel.CENTER);
        l1.setBounds(0,-20,600,600);
        j.add(l1);
JTable t=new JTable();
        JLabel header=new JLabel("Saved Encrypted Passwords");
        header.setBounds(100,30,430,40);
        header.setFont(new Font("Sans_Serif",Font.BOLD,25));
        header.setForeground(Color.WHITE);
        l1.add(header);

        JButton back=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/1.png"));
        back.setBounds(48,77,45,45);
        l1.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new Main();
                j.dispose();
            }
        });

        JButton next=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/2.png"));
	    next.setBounds(500,77,45,45);
	    l1.add(next);
	    next.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			new save_Encrypted_passwords();
			j.dispose();
		}
	});

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username="root";
        String pwd="root";
        String URL="jdbc:mysql://localhost:3306/password_generator";
        Connection con=DriverManager.getConnection(URL, username,pwd);
        String sql="Select ID,Website,Password from password;";
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);

    // t=new JTable(); 
    // t.setBounds(10,420,580,300);
    l1.add(t);
        String[] columns={"ID","Website","Password"};
        DefaultTableModel jt=new DefaultTableModel(columns,0);
        while(rs.next()){
         String k1=rs.getString("ID");
         String k2=rs.getString("Website");
         String k3=rs.getString("Password");

         String[]data={k1,k2,k3};
         jt.addRow(data);
        }
        
        t.setModel(jt); 
        t.setPreferredScrollableViewportSize(new Dimension(500,500));
        t.setFillsViewportHeight(false);
        JScrollPane jps = new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jps.setBounds(10,270,580,300);
        jps.setMaximumSize(new Dimension(30,500));
        l1.add(jps); 
        DefaultTableCellRenderer cr=new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        t.getColumnModel().getColumn(0).setCellRenderer(cr);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    // int index=t.getSelectedRow();
    JButton z=new JButton("Decrypt");
    z.setBounds(93,125,100,45);
    z.setBackground(Color.green);
    z.setForeground(Color.RED);
    l1.add(z);
    z.setEnabled(false);

    JTextField ppp=new JTextField();
    ppp.setBounds(93,75,407,50);
    ppp.setFont(new Font("Sans_Serif",Font.BOLD,20));
    ppp.setBackground(Color.BLACK);
    ppp.setForeground(Color.WHITE);
    ppp.setEnabled(false);
    l1.add(ppp);

    z.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            String cipherText=(String) t.getValueAt(t.getSelectedRow(), 2);
            JPasswordField user=new JPasswordField();
            StringBuilder code=new StringBuilder();
                int key=6;
                char[] chars =cipherText.toCharArray();
                for(int i=0;i<cipherText.length();i++){
                    chars[i]-=key;
                    code.append(chars[i]);
                }
                char[] cha={'M','O','H','I','T'};
            int result=JOptionPane.showOptionDialog(null,new Object[]{"Enter Authorisation Code",user},"Authenticate Identity",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            
            // System.out.println(user.getPassword().toString());
            // System.out.println(Arrays.toString(user.getPassword()));
            // System.out.print(w);

            boolean w=Arrays.toString(cha).equals(Arrays.toString(user.getPassword()));
            if(result==JOptionPane.OK_OPTION && w){
                ppp.setText(code.toString());
        }    if(result==JOptionPane.OK_OPTION&&!w) JOptionPane.showMessageDialog(null, "Authentication Failed");
    
    }
    });
    
    
    JButton b2=new JButton("Copy");
    b2.setBounds(400,125,100,45);
    b2.setBackground(Color.gray);
    l1.add(b2);
    b2.setForeground(Color.WHITE);
    b2.setEnabled(false);
    
    b2.addActionListener(new ActionListener(){
        @Override
		public void actionPerformed(ActionEvent e){
            StringSelection str=new StringSelection(ppp.getText());
			Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(str,null);
			b2.setText("Copied!");
		}
	});
    
    t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e){
            if(!e.getValueIsAdjusting()){
                boolean rowsareselected = t.getSelectedRow()>=0;
                z.setEnabled(rowsareselected);
                b2.setEnabled(rowsareselected);
                b2.setText("Copy");
                ppp.setText("");
            }
        }
    });

j.setSize(600,600);
j.setLayout(null);
j.setLocationRelativeTo(null);
j.setVisible(true);}
public static void main(String[] args){
        new Decrypt();
    }
}
