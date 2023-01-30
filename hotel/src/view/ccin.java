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
import javax.management.Query;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juliao LB
 */
public class ccin extends JFrame implements ActionListener {
    private String  QueryRoom,QueryEntrada;
    private JButton btn1, btn2, btn3 = new JButton("search");
    private JLabel l1, l2, l3, l4, l5, l6, l7, l, l8, l9, l10, l11, l12;
    private JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    private JComboBox cb1, cb2, cb3, cb4;
    private String RoomType[] = {"Single room", "twin room", "Double room", "Triple room", "luxury room", "junior suite", "Presidential suite", "bridal suite", "honeymoon suite",}, BedType[] = {"bunk bed", "double bed", "bigger single bed", "smaller single bed", "royal bed"}, sexo[] = {"M", "F"};
    private String name, cell, nation, gender, email, idProof, address, checkin,  roomNr,bed,roomType, total, checkout;  
    private Integer price;
    
   
    
    public ccin() throws HeadlessException {
        super("CUSTOMER CHECK-IN");
        Container tela = getContentPane();
        //-------------------------------------------
        l1 = new JLabel("Name:");
        l1.setBounds(10, 30, 200, 20);
        t1 = new JTextField();
        t1.setBounds(10, 50, 200, 20);//...
        l2 = new JLabel("Mobile NUmber:");
        l2.setBounds(10, 70, 200, 20);
        t2 = new JTextField();
        t2.setBounds(10, 90, 200, 20);//...
        l3 = new JLabel("Nation:");
        l3.setBounds(10, 110, 200, 20);
        t3 = new JTextField();
        t3.setBounds(10, 130, 200, 20);//...
        l4 = new JLabel("Gender:");
        l4.setBounds(10, 150, 200, 20);
        
        cb1 = new JComboBox(sexo);
        cb1.setBounds(10, 170, 200, 20);//...
        l5 = new JLabel("E-mail:");
        l5.setBounds(10, 190, 200, 20);
        t4 = new JTextField();
        t4.setBounds(10, 210, 200, 20);//...
        //================================
        l6 = new JLabel("ID Proof:");
        l6.setBounds(250, 30, 200, 20);
        t5 = new JTextField();
        t5.setBounds(250, 50, 200, 20);
        t5.setEditable(true);
        //...
        l7 = new JLabel("Address:");
        l7.setBounds(250, 70, 200, 20);
        t6 = new JTextField();
        t6.setBounds(250, 90, 200, 20);//...
        l8 = new JLabel("Check IN Date:");
        l8.setBounds(250, 110, 200, 20);
        t7 = new JTextField();
        t7.setBounds(250, 130, 200, 20);//...
        t7.setEditable(false);
       
        //================================
        btn3.setBounds(500, 10, 200, 20);
        l9 = new JLabel("Bed:");
        l9.setBounds(500, 30, 200, 20);
        cb2 = new JComboBox(BedType);
        cb2.setBounds(500, 50, 200, 20);//...
        l10 = new JLabel("Room Type:");
        l10.setBounds(500, 70, 200, 20);
        cb3 = new JComboBox(RoomType);
        cb3.setBounds(500, 90, 200, 20);//...
        l11 = new JLabel("Room Numbber:");
        l11.setBounds(500, 110, 200, 20);
        cb4 = new JComboBox();
        cb4.setBounds(500, 130, 200, 20);//...
        l12 = new JLabel("Price:");
        l12.setBounds(500, 150, 200, 20);
        t8 = new JTextField();
        t8.setBounds(500, 170, 200, 20);
        t8.setEditable(false);
        //...
        btn1 = new JButton("Allote Room");
        btn1.setBounds(250, 210, 200, 20);
        btn2 = new JButton("Clear");
        btn2.setBounds(500, 210, 200, 20);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        //-------------------------------------------
        tela.add(l1);
        tela.add(l2);
        tela.add(l3);
        tela.add(l4);
        tela.add(l5);
        tela.add(l6);
        tela.add(l7);
        tela.add(l8);
        tela.add(l9);
        tela.add(l10);
        tela.add(l11);
        tela.add(l12);
        tela.add(t1);
        tela.add(t2);
        tela.add(t3);
        tela.add(cb1);
        tela.add(t4);
        tela.add(t5);
        tela.add(t6);
        tela.add(cb2);
        tela.add(cb3);
        tela.add(cb4);
        tela.add(t7);
        tela.add(t8);
        tela.add(btn1);
        tela.add(btn2);
        tela.add(btn3);
        //-------------------------------------------
        setUndecorated(false);
        setLocation(300, 150);
        setLayout(null);
        setSize(820, 320);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public void idProff() throws SQLException{
        int id=1;
       try {
            ResultSet rs = CRUD.getData("SELECT  max(id) from reserva;");
            while (rs.next()) {
             t5.setText("ID00"+ (rs.getInt(1)+1)+"");;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ccin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void RoomDetails() {
        //Pegando a data atual(Hoje)
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        //setando os volores dos caompos
        System.out.println("Calendario"+myDateFormat.format(calendar.getTime())+"");
        t7.setText(myDateFormat.format(calendar.getTime()));
        cb4.removeAllItems();
        t8.setText("");
        bed = cb2.getSelectedItem().toString();
        roomType = cb3.getSelectedItem().toString();
        try {
            ResultSet rs = CRUD.getData("SELECT * FROM public.room where bed='" + bed + "'and roomtype='" + roomType + "' and status='unoccupied';");
            while (rs.next()) {
                cb4.addItem(rs.getString(2));

            }
            roomNr = cb4.getSelectedItem().toString();
            rs = CRUD.getData("SELECT * FROM public.room where roomnr='" + roomNr + "' and status='unoccupied';");
            while (rs.next()) {
               t8.setText(rs.getString(5));
            }
            price = Integer.parseInt(t8.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ccin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("O valor do quarto["+cb4.getSelectedItem().toString()+"] e valor ["+t8.getText()+"]mt ");
    }

    public static void main(String args[]) throws SQLException {
        ccin c = new ccin();
        c.idProff();
        c.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer id;
        name = t1.getText();
        cell = t2.getText();
        nation = t3.getText();
        gender = cb1.getSelectedItem().toString();
        email = t4.getText();
        idProof = t5.getText();
        address = t6.getText();
        checkin = t7.getText();
        bed = cb2.getSelectedItem().toString();
        roomType = cb3.getSelectedItem().toString();

        
        if (e.getSource() == btn1) {
            JOptionPane.showMessageDialog(null, "(roomType="+roomType+"),(bed="+bed+"(roomType="+roomNr+"),(Price="+price+")");
            if((name.equals("")) && (cell.equals("")) && (nation.equals("")) && (email.equals("")) && (idProof.equals("")) && (address.equals("")) && (checkin.equals("")) ){
               JOptionPane.showMessageDialog(null, "pt: Campos Vazios");
            }else
            if( (!name.equals("")) && (!cell.equals("")) && (!nation.equals("")) &&  (!gender.equals("")) && (!email.equals("")) && (!idProof.equals("")) && (!address.equals("")) && (!checkin.equals("")) && (!roomNr.equals("")) && (!bed.equals("")) && (!RoomType.equals("")) && (!price.equals(""))) { 
               try {//
                   ResultSet rs = CRUD.getData("SELECT  max(id) from reserva;");
                   while (rs.next()) {
                      id = rs.getInt(1);
                      id = id+1;
                      idProof ="ID00"+id+"";
                      t5.setText(idProof);
                      if(!price.equals("") && (!price.equals(0)) && !roomNr.equals("")){
                          System.out.println("Preco["+price+"] != 0");
                          QueryRoom = "UPDATE public.room SET status='occupied' WHERE roomnr='"+roomNr+"'";
                          try {
                              CRUD.setData(QueryRoom, "pt:Quarto["+roomNr+"] reservado con SUCESSSO!!!\n eng: Room ["+roomNr+"] occupied");
                          } catch (Exception ex) {
                              Logger.getLogger(ccin.class.getName()).log(Level.SEVERE, null, ex);
                          }
                          QueryEntrada = "INSERT INTO public.reserva VALUES (nextval('reserva_id_seq'::regclass), '"+name+"', '"+cell+"', '"+nation+"', '"+gender+"', '"+email+"', '"+idProof+"', '"+address+"', '"+checkin+"', '"+roomNr+"', '"+bed+"', '"+RoomType+"', '"+price+"');";
                          try {
                              CRUD.setData(QueryEntrada, "----Checkin-In-SUCESSS!!!----\n----Entrada COM-SUCESSS\n "+roomNr+"\n ");
                         
                          } catch (Exception ex) {
                              Logger.getLogger(ccin.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      
                      }
                   }
                   //dar entrada(Checl-in) a uma reserva
                   
               } catch (SQLException ex) {
                   Logger.getLogger(ccin.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "ERRO QUERY AO EXECUTOU\n"
                            + ""+ex.getMessage()+"");
               }
               
            }

        } else if (e.getSource() == btn2) {
            setVisible(false);
            new ccin().setVisible(true);

        } else if (e.getSource() == btn3) {
            RoomDetails();
        }

    }

}
