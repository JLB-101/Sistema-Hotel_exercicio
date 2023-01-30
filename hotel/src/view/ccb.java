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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lb
 */
public class ccb extends JFrame implements ActionListener{
    private  JLabel l9;
    private JTable tb1;
    private JButton btn1; 
    private JTextField t9;
    private JScrollPane sr;
    public ccb() throws HeadlessException {
        super("CUSTOMER DETAILS-BILL");
        Container tela = getContentPane();
        l9 = new JLabel("Search by check out date:");
        l9.setBounds(10, 110, 200, 20);
        t9 = new JTextField();
        t9.setBounds(250, 110, 200, 20);
        btn1 = new JButton("Search");
        btn1.setBounds(490, 110, 100, 20);//...
        
        tb1 = new  JTable(16, 16);
        tb1.setBounds(10, 150, 960, 540);
        
        sr = new JScrollPane(tb1);
        
        tela.add(l9);/*tela.add(l10);*/
        tela.add(t9);
        tela.add(btn1);
        tela.add(tb1);tela.add(sr);
        btn1.addActionListener(this);
        //-------------------------------------------
        setUndecorated(false);
        setLocation(220, 90);
        setLayout(null);
        setSize(980, 640);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
       
    }
    
    public void ccb(){
        //Pegando a data atual(Hoje)
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendario"+myDateFormat.format(calendar.getTime())+"");
        t9.setText(myDateFormat.format(calendar.getTime()));
        
         //Selecionar reserva ou entrada(Check-in) para dar saida(Check-out)
        ResultSet rs = CRUD.getData("SELECT * FROM public.reserva where checkout is not NULL;");
        DefaultTableModel model = (DefaultTableModel)tb1.getModel();
            model.setRowCount(0);
            try {
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(15)});
                }rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(adminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String args[]){
        ccb ccb = new ccb();
        ccb.ccb();
        ccb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}
