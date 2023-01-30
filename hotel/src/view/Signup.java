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
public class Signup extends JFrame implements ActionListener {
    //
    private String name, email, password, securityQ, answer, address, gender, cell;
    String msg,msg1 ="CADASTRADO COM SUCESSO BD !!!",msg2="ERRRO BD !!!", Query, msgF="eng: Every field is required\n"
                + "pt: Todos campos sao requeridos";
    CRUD  crud = new CRUD();
    String[]gd = {"M","F"};
    String[]qt = {"what is your name?", "which is your favorite color?", "whare did you born?","your pet's name?"};
    //
   
    private JButton btn1, btn2, btn3;
    private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    private JTextField t1, t2, t3, t4, t5, t6;
    private JComboBox jc = new JComboBox(gd);
    private JComboBox Sq;
    public Signup() throws HeadlessException {
        super("Signup");
        Container tela = getContentPane();
        
        l1 = new JLabel("Nome:");
        l1.setBounds(10, 60, 100, 20);
        t1 = new JTextField();
        t1.setBounds(150, 60, 350, 20);
        //
        l2 = new JLabel("Email:");
        l2.setBounds(10, 100, 100, 20);
        t2 = new JTextField();
        t2.setBounds(150, 100, 350, 20);
        //
        l3 = new JLabel("Password:");
        l3.setBounds(10, 140, 100, 20);
        t3 = new JTextField();
        t3.setBounds(150, 140, 350, 20);
        //
        l4 = new JLabel("Security Question:");
        l4.setBounds(10, 180, 100, 20);
        Sq = new JComboBox(qt);
        Sq.setBounds(150, 180, 350, 20);
        //
        l5 = new JLabel("Answer:");
        l5.setBounds(10, 220, 100, 20);
        t4 = new JTextField();
        t4.setBounds(150, 220, 350, 20);
        //
        l6 = new JLabel("Address:");
        l6.setBounds(10, 260, 100, 20);
        t5 = new JTextField();
        t5.setBounds(150, 260, 350, 20);
        
        l8 = new JLabel("Gender:");
        l8.setBounds(10, 300, 100, 20);
        jc.setBounds(150, 300, 350, 20);
        
        l9 = new JLabel("Mobile Nr:");
        l9.setBounds(10, 340, 100, 20);
        t6 = new JTextField();
        t6.setBounds(150, 340, 350, 20);
        
        l7 = new JLabel("Signup");
        l7.setBounds(10, 10, 200, 20);
        
        btn1 = new JButton("Signup");//escuta
        btn1.setBounds(10, 400, 100, 20); 
        btn2 = new JButton("Login");//escuta
        btn2.setBounds(150, 400, 100, 20);
        btn3 = new JButton("Forgot Password");
        btn3.setBounds(300, 400, 200, 20);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        //-----------------------------------------t6
        tela.add(l1);tela.add(l2);tela.add(l3);tela.add(l4);tela.add(l5);tela.add(l6);tela.add(l7);tela.add(l8);tela.add(l9);
        tela.add(t1);tela.add(t2);tela.add(t3);tela.add(Sq);tela.add(t4); tela.add(t5);tela.add(jc);tela.add(t6);
        tela.add(btn1);tela.add(btn2);tela.add(btn3);
        //-----------------------------------------
        setLocation(370, 140);
        setLayout(null);
        setSize(640, 480);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //pegando os argumentos da interface
        this.name = t1.getText();
        this.email = t2.getText();
        this.password = t3.getText();
        this.securityQ = Sq.getSelectedItem().toString();
        this.answer = t4.getText();
        this.address =  t5.getText();
        this.gender = jc.getSelectedItem().toString();
        this.cell = t6.getText();
        
        //verificando os campos
        if(e.getSource() == btn1){
            if(name.equals("") && email.equals("") && password.equals("") && securityQ.equals(securityQ) && answer.equals("") && address.equals("") && gender.equals(gender)&& cell.equals("")){
                JOptionPane.showMessageDialog(null, msgF); 
            }
            else {
                try { 
                    //passando argumntos do cadastro para Query
                    Query ="INSERT INTO public.users(id, name, email, password, securityq, answerq, address, status, gender, cell)VALUES ( nextval('users_id_seq'::regclass), '"+name+"', '"+email+"', '"+password+"', '"+securityQ+"', '"+answer+"', '"+address+"', 'false', '"+gender+"', '"+cell+"');";//passando a Query para CRUD
                    crud.setData(Query, msg1);
                    setVisible(false);
                    new Signup().setVisible(true);
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, msg2);
                    Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
                    
                } 
            }
            
        }else if (e.getSource() == btn2){
            System.out.println(msg = "back to login");
            setVisible(false);
            new Ex1();
            
        }else if (e.getSource() == btn3){
            System.out.println(msg = "back to ForgotPAss");
            setVisible(false);
            new ForgotPass();
            
        }
    }

    public static void main(String args[]) {
        Signup signup = new Signup();
        signup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
