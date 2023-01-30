/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CRUD;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lb
 */
public class ForgotPass extends JFrame implements ActionListener{
    private String name, email, password, securityQ, answer, address, msg;
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JLabel l1, l2, l3, l4, l5;
    public JTextField t1, t2, t3, t4;

    
    String qt[] = {"onde vives?", "mome do teu pets?", "sua cor favorita"};
    
    public ForgotPass() throws HeadlessException {
        super("Forgot Password");
        Container tela = getContentPane();
        
        l1 = new JLabel("Email:");
        l1.setBounds(10, 60, 100, 20);
        t1 = new JTextField();
        t1.setBounds(150, 60, 230, 20);
        btn4 = new JButton("Search");
        btn4.setBounds(400, 60, 100, 20);
        //
    
        //
        l2 = new JLabel("Security Question:");
        l2.setBounds(10, 100, 100, 20);
        t2 = new JTextField();
        t2.setBounds(150, 100, 350, 20);
        //
        l3 = new JLabel("Answer:");
        l3.setBounds(10, 220, 100, 20);
        t3 = new JTextField();
        t3.setBounds(150, 220, 350, 20);
        
        //
        l4 = new JLabel("New PassWord:");
        l4.setBounds(10, 260, 100, 20);
        t4 = new JTextField();
        t4.setBounds(150, 260, 350, 20);
        
        l5 = new JLabel("Forgot PassWord ?");
        l5.setBounds(10, 10, 200, 20);
        
        //
        t2.setEditable(false);
        
        btn1 = new JButton("Save");
        btn1.setBounds(10, 400, 100, 20);
        btn2 = new JButton("Signup");
        btn2.setBounds(150, 400, 100, 20);
        btn3 = new JButton("login");
        btn3.setBounds(300, 400, 100, 20);
        btn5 = new JButton("clear");
        btn5.setBounds(400, 400, 100, 20);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        //-----------------------------------------
        tela.add(l1);tela.add(l2);tela.add(l3);tela.add(l4);tela.add(l5);
        tela.add(t1);tela.add(t2);tela.add(t3);tela.add(t4);
        tela.add(btn1);tela.add(btn2);tela.add(btn3);tela.add(btn4);tela.add(btn5);
        //-----------------------------------------
         //-------------------------------------------
        
        setLocation(350, 150);
        setLayout(null);
        setSize(640, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false); 
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        this.email= t1.getText();
        this.securityQ = t2.getText();
        this.answer = t3.getText();
        this.password = t4.getText();
        CRUD crud = new CRUD();
        System.out.println("email="+email+", seq="+securityQ+", asn="+answer+", pas="+password+"");
        //verificando os campos
        if(e.getSource() == btn1){
            
            if((!email.equals(""))  && (!answer.equals("")) && (!password.equals(""))){
                
                try {//Executar query
                    setVisible(false);
                    String Query ="SELECT * FROM public.users where email='"+email+"' and securityq='"+securityQ+"' and answerq='"+answer+"';";
                    crud.newPass(Query, password);
                    
                 } catch (Exception ex) {
                    Logger.getLogger(ForgotPass.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }else if((name.equals(""))  && (answer.equals("")) && (password.equals(""))){
                JOptionPane.showMessageDialog(null, "eng: Every field is required\n"
                + "pt: Todos campos sao requeridos");
             
                
        
                
                
            }
        }else if (e.getSource() == btn2){
            System.out.println(msg = "back to login");
            setVisible(false);
            new Signup();
            
        }else if (e.getSource() == btn3){
            System.out.println(msg = "back to ForgotPAss");
            setVisible(false);
            new Ex1();
            
        }
        else if (e.getSource() == btn4){
           
            if((email.equals(""))){
                JOptionPane.showMessageDialog(null, "eng: Every field is required\n"
                + "pt: Todos campos sao requeridos");
                
            }else{
                 try {//Executar query
                    setVisible(false);
                    String Query ="SELECT * FROM public.users where email='"+email+"';";
                    crud.forgotPass(Query, "PQ");
                    
                 } catch (Exception ex) {
                    Logger.getLogger(ForgotPass.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
            
        }
        else if (e.getSource() == btn5){
            System.out.println(msg = "back to ForgotPAss");
            setVisible(false);
            new ForgotPass();
        }
        
    }
    
    public static void main(String args[]) {
       ForgotPass fp = new ForgotPass();
       fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
