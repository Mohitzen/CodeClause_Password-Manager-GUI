import java.awt.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.lang.Math;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame{
	Main(){
	ImageIcon img=new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/background.png");
		JLabel bg = new JLabel("",img,JLabel.CENTER);
		bg.setBounds(0,-20,600,600);
		
		JFrame j=new JFrame("Password  Generator");
		// j.setContentPane(bg);
		j.add(bg);
	
	JLabel l=new JLabel(" Generated Password ");
	l.setBounds(140,320,440,50);
	l.setFont(new Font("Sans_Serif",Font.BOLD,25));
	bg.add(l);
	l.setForeground(Color.WHITE);
	
	JButton b=new JButton("Generate");
	b.setBounds(200,415,200,60);
	b.setFont(new Font("Sans_Serif",Font.BOLD,20));
	b.setForeground(Color.black);
	b.setBackground(Color.green);
	bg.add(b);

	JButton b2=new JButton("Copy Password");
	b2.setBounds(200,480,200,60);
	b2.setFont(new Font("Sans_Serif",Font.BOLD,18));
	b2.setForeground(Color.WHITE);
	b2.setBackground(Color.DARK_GRAY);
	bg.add(b2);
	
	JPasswordField t=new JPasswordField();
	t.setBounds(200,370,200,40);
	t.setFont(new Font("Sans_Serif",Font.PLAIN,16));
	t.setEnabled(false);
	t.setBackground(Color.BLACK);
	bg.add(t);
	
	j.addWindowFocusListener(new WindowAdapter(){
	public void windowGainedFocus(WindowEvent e){
	t.requestFocusInWindow();
	}});

	
	b2.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			StringSelection str=new StringSelection(t.getText());
			Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(str,null);
			b2.setText("Copied!");
		}
	});

	// File ii=new File("/home/mohit/Downloads/Screenshot from 2023-04-04 13-59-56.png");
	// if(ii.exists()){System.out.print("yes");}


	ImageIcon icon=new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/openi.png");
	
	JButton see_password=new JButton(icon);
	see_password.setBounds(175,9,22,22);
	t.add(see_password);
	see_password.setVisible(false);

	JButton hide_password=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/closei.png"));
	hide_password.setBounds(175,9,22,22);
	t.add(hide_password);
	hide_password.setVisible(true);

	see_password.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			t.setEchoChar('*');
			hide_password.setVisible(true);
			see_password.setVisible(false);
		}
	});

	hide_password.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			t.setEchoChar((char)0);
			hide_password.setVisible(false);
			see_password.setVisible(true);
		}
	});

	b.addActionListener(new ActionListener() {  
		@Override
		public void actionPerformed(ActionEvent e){
			see_password.setVisible(false);
			hide_password.setVisible(true);
			String pw="";
			int len=15;
		String uc="QqW1!wE@e2#Rr$T3%tY^y4&Uu*I5(iO)o6_Pp+A7-aS=s8[Dd]F9{fG}g0\\~\"Hh|Jj;Kk'Ll,Zz.Xx/Cc?Vv>BbN<nM:m";
		for(int i=0;i<len;i++) {
			int index=(int)(uc.length()*Math.random());
				char c=uc.charAt(index);
				pw+=c;
		} 
			t.setEchoChar('*');
			t.setText(pw);
			b2.setText("Copy Password");
		}
	});

	JButton back=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/2.png"));
	back.setBounds(50,50,45,45);
	bg.add(back);
	back.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			new save_Encrypted_passwords();
			j.dispose();
		}
	});

	JButton X=new JButton(new ImageIcon("/home/mohit/Desktop/Project/PASSWORD_GENERATOR/images/lock.png"));
	X.setBounds(500,50,45,45);
	bg.add(X);
	X.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e){
			new Decrypt();
			j.dispose();
		}
	});
	
	j.setLayout(null);
	j.setSize(600,600);
	j.setBackground(Color.BLACK);
	j.setLocationRelativeTo(null);
	j.setVisible(true);}
	
	public static void main(String[] args) {

 		new Main();
	}

}