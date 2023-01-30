/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CRUD;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author lb
 */
public class Ex1 extends JFrame implements ActionListener {
    //variaveis
    String msgF="eng: Every field is required\n"
                + "pt: Todos campos sao requeridos";
    boolean statusLogin;
    CRUD  crud = new CRUD();
    private String nome,senha,msg;
    //declarar botoes
    JLabel rotulo1,rotulo2;
    JTextField t1;
    JPasswordField t2;
    JButton btn, btn1, btn2;
  
    public Ex1() throws HeadlessException {
        
        super("Exemplo com Label");
        Container tela = getContentPane();
        //-------------------------------------------
        
        rotulo1 = new JLabel ("Email");
        t1 = new JTextField();
        rotulo2 = new JLabel("Senha");
        t2 = new JPasswordField();
        btn = new JButton("login");
        btn1 = new JButton("Signup");
        btn2 = new JButton("Forgot Password");
        btn.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        //-------------------------------------
        
        rotulo1.setBounds(50,80,80,20);
        t1.setBounds(120, 80, 240, 20);
        rotulo2.setBounds(50, 120, 80, 20);
        t2.setBounds(120, 120, 240, 20);
        btn.setBounds(50, 200, 80, 20);
        btn1.setBounds(120, 200, 85, 20);
        btn2.setBounds(200, 200,160, 20);
        //-------------------------------------

        tela.add(rotulo1);tela.add(t1);
        tela.add(rotulo2);tela.add(t2);
        tela.add(btn);tela.add(btn1);tela.add(btn2);
       
        Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();
		
	setLocation(500, 250);
        setLayout(null);
        setSize(400, 250);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
       
    }
    
    //botao logar
    public void actionPerformed (ActionEvent e){
        nome = t1.getText();
        senha = t2.getText();
        System.out.println("senha("+senha+")");
        
        //botao login
        if(e.getSource() == btn){
            if(nome.equals("") && senha.equals("")){
                System.out.println(msgF);
                JOptionPane.showMessageDialog(null, msgF); 
            } 
            
            else if(nome.equalsIgnoreCase("Admin") && senha.equalsIgnoreCase("1234")){//admin
                JOptionPane.showMessageDialog(null, msg ="sucesso!!!");
                setVisible(false);
                new homePage().setVisible(true);
                
            }
            //atendente
            else if(nome != "" && senha != ""){
                //passando argumntos do cadastro para Query
                String Query ="SELECT id, name, password, status FROM public.users WHERE name='"+nome+"'and password='"+senha+"';";
                try {
                    if(crud.login(Query, "PQ")){
                        setVisible(false);
                        new homePage().setVisible(true);
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Aguarde A Permisao do:\n"
                                + "---- ADMINISTRADOR------");
                        setVisible(false);
                        new Ex1().setVisible(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Ex1.class.getName()).log(Level.SEVERE, null, ex);
                }  
            } 
          
        }
        //botao 
        else if (e.getSource() == btn1) {
            setVisible(false);
            new Signup();
        }
        //botao 
        else if (e.getSource() == btn2) {//botao 
            System.out.println(msg ="Forgot=Assword--sso!!!");
            setVisible(false);
            new ForgotPass();
        }
       //end actionPerformed
    }
   
    public static void main(String args[]){
        Ex1 app = new Ex1();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }   
}
