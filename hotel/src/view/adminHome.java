/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import dao.CRUD;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lb
 */
public class adminHome  extends JFrame implements ActionListener{
    JLabel l1 = new JLabel("Search by name or email"),l2;
    JButton btn1 = new JButton("search"), btn2 = new JButton("clear");
    JTextField t1 = new JTextField();
    private JTable tb1 =new JTable(10, 10);
    private  String nORem;
    public adminHome() throws HeadlessException {
        super("ADMIN-HOME");
        Container tela = getContentPane();
        //-------------------------------------------
        
        l1.setBounds(10, 110, 200, 20);
        t1.setBounds(250, 110, 200, 20);
        btn1.setBounds(490, 110, 100, 20);
        btn2.setBounds(650, 110, 100, 20);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        JScrollPane scr = new javax.swing.JScrollPane();
        tb1.setBounds(10, 150, 960, 400);
        
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"nome", "email", "security Questions","answer", "Adress", "status", "gender", "cell"},
            },
            new String [] {
                 "nome", "email", "security Questions", "answer", "Adress", "status", "gender", "cell"
            }
        ));
        scr.setViewportView(tb1);
        //-------------------------------------------
        tela.add(l1); tela.add(t1);tela.add(btn1);tela.add(btn2);tela.add(tb1);
        //-------------------------------------------
        setLayout(null);
        setSize(980, 640);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn1){
            nORem = t1.getText();
            System.out.println("nomeORemail: "+nORem+"");
            ResultSet rs= CRUD.getData("SELECT * FROM public.users where name like'%"+nORem+"%' or email like'%"+nORem+"%'");
            DefaultTableModel model = (DefaultTableModel)tb1.getModel();
            model.setRowCount(0);
            try {
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10), });
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(adminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource() == btn2){
            JOptionPane.showMessageDialog(null, "TESTE DO BOTA LIPAR");
        }
        
    }
    
    public static void main(String args[]) {
      adminHome h = new adminHome();
      h.listUsers();
      h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     //metodo para listar Quartos
    protected void listUsers() {//Query para listar todos Quartos(ROOM)
            ResultSet rs= CRUD.getData("SELECT * FROM public.users;");
            DefaultTableModel model = (DefaultTableModel)tb1.getModel();
            model.setRowCount(0);
            try {
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10), });
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(adminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
