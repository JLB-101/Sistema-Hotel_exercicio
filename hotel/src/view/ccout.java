/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.CRUD;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lb
 */
public class ccout extends JFrame implements ActionListener{
    private JButton btn1, btn2, btn3;
    private JLabel l1,l2,l3,l4,l5,l6,l7,l,l8,l9,l10;
    private JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
    private JTable jTable = new JTable(15, 15);
    private String name, cell, nation, gender, email, idProof, address, checkin,  roomNr,bed,roomType,  checkout, Total,daysTay;  
    private String price, id,total, Dias;
    int priceD, AmounTotal, DiasF;
    
    public ccout() throws HeadlessException {
        super("CUSTOMER CHECK-OUT");
        Container tela = getContentPane();
        
        //-------------------------------------------
        l1 = new JLabel("Custumer Name:");
        l1.setBounds(10, 50, 200, 20);
        t1 = new JTextField();
        t1.setBounds(10, 70, 200, 20);
        t1.setEditable(false);
        //...
        l2 = new JLabel("Price Per Day:");
        l2.setBounds(10, 90, 200, 20);
        t2 = new JTextField();
        t2.setBounds(10, 110, 200, 20);
        t2.setEditable(false);
        //...
        //
        l3 = new JLabel("Check-in Date:");
        l3.setBounds(250, 50, 200, 20);
        t3 = new JTextField();
        t3.setBounds(250, 70, 200, 20);
        t3.setEditable(false);
        //...
        l4 = new JLabel("Nr. of Days Stay:");
        l4.setBounds(10, 130, 200, 20);
        t4 = new JTextField();
        t4.setBounds(10, 150, 200, 20);
        t4.setEditable(false);
        //...
        //
        l5 = new JLabel("Check-out Date:");
        l5.setBounds(250, 90, 200, 20);
        t5 = new JTextField();
        t5.setBounds(250, 110, 200, 20);
        t5.setEditable(false);
        //...
        l6 = new JLabel("totaL-Amount to Collect form Cuatuner:");
        l6.setBounds(250, 130, 200, 20);
        t6 = new JTextField();
        t6.setBounds(250, 150, 200, 20);
        t6.setEditable(false);
        //...
        //
        l7 = new JLabel("Customer Mobile Nr.:");
        l7.setBounds(490, 50, 200, 20);
        t7 = new JTextField();
        t7.setBounds(490, 70, 200, 20);
        t7.setEditable(false);
        //...
        l8 = new JLabel("Email:");
        l8.setBounds(490, 90, 200, 20);
        t8 = new JTextField();
        t8.setBounds(490, 110, 200, 20);
        t8.setEditable(false);//...
        l9 = new JLabel("Room Number:");
        l9.setBounds(10, 30, 100, 20);
        t9 = new JTextField();
        t9.setBounds(250, 30, 100, 20);
       
        btn1 = new JButton("Search");
        btn1.setBounds(490, 30, 100, 20);//...
        btn2 = new JButton("Check-out");
        btn2.setBounds(490, 150, 110, 20);//...
        btn3 = new JButton("Clear");
        btn3.setBounds(610, 150, 80, 20);//...
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"id", "name", "cell", "nation", "gender", "email", "idprof", "address", "checkin", "roomnr", "bed", "roomtype", "priceday", "total($)", "checkout"},
              
                
            },
            new String [] {
                 "id", "name", "cell", "nation", "gender", "email", "idprof", "address", "checkin", "roomnr", "bed", "roomtype", "pricedia", "total($)", "checkout"
                
            }
        ));
        jTable.setBounds(10, 200, 960, 400);//..
        
       
        //-------------------------------------------
        tela.add(l1);tela.add(l2);tela.add(l3);tela.add(l4);tela.add(l5);tela.add(l6);tela.add(l7);tela.add(l8);tela.add(l9);/*tela.add(l10);*/
        tela.add(t1);tela.add(t2);tela.add(t3); tela.add(t4); tela.add(t5);tela.add(t6); tela.add(t7);tela.add(t8);tela.add(t9);
        tela.add(btn1);tela.add(btn2);tela.add(btn3);
        tela.add(jTable);
        //-------------------------------------------
        setUndecorated(false);
        setLocation(220, 60);
        setLayout(null);
        setSize(980, 640);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    
     public static void main(String args[]){
        ccout ccout = new ccout();
        ccout.CcoutDetails();
        ccout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    protected  void CcoutDetails(){
        //Selecionar reserva ou entrada(Check-in) para dar saida(Check-out)
        ResultSet rs = CRUD.getData("SELECT * FROM public.reserva where checkout is NULL;");
        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            model.setRowCount(0);
            try {
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)});
                }rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(adminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btn1){//boatao de busca
            //buscar por numero de quarto
            roomNr = t9.getText();
            System.out.println("Nr="+roomNr+"");
            if(!roomNr.equals("")){
                try {
                    ResultSet rs = CRUD.getData("SELECT * FROM public.reserva where roomnr='"+roomNr+"' and checkout is NULL;");
                    if(rs.next()){
                        int  id = rs.getInt(1);
                        t1.setText(rs.getString(2));
                        t2.setText(rs.getString(13));
                        t3.setText(rs.getString(9));
                        t7.setText(rs.getString(3));
                        
                        
                        //pegando datas da entrada a da saida
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Calendar calendar = Calendar.getInstance();
                        t5.setText(dateFormat.format(calendar.getTime()));
                        String dateBeforeString = rs.getString(9);
                        Date dateBefore = dateFormat.parse(dateBeforeString);
                        String dateAfterString = dateFormat.format(calendar.getTime());
                        Date dateaAfter = dateFormat.parse(dateAfterString);
                        //achar numeros de dias que o hospede ficou
                        long difference = (dateaAfter.getTime() - dateBefore.getTime());
                        int noOfDayStay = (int) (difference/(1000*60*24));
                        if(noOfDayStay == 0){
                            noOfDayStay=1;
                            t4.setText(String.valueOf(noOfDayStay));
                        }
                        priceD = Integer.parseInt(t2.getText());
                        t6.setText(String.valueOf(noOfDayStay*priceD));
                        
                        t8.setText(rs.getString(6));
                        roomType =rs.getString(12);
                        bed =rs.getString(11);
                       
                    }else{
                        JOptionPane.showMessageDialog(null, "pt:Reserva ou entrada (Check-in) \nQuarto["+roomNr+"] nao existe");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    Logger.getLogger(ccout.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ccout.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{JOptionPane.showMessageDialog(null, "pt:Preencha o campo de busca");}
            
            
        }else if(e.getSource() == btn2){
            try {
                name = t1.getText();
                cell = t7.getText();
                email = t8.getText();
                checkout = t5.getText();
                daysTay  = t1.getText();
                Total = t6.getText();
                roomNr = t9.getText();
                /*(!name.equals("")) && (!cell.equals("")) && (!email.equals(""))&& (!checkout.equals("")) && (!total.equals("")) && (!daysTay.equals("")) && (!roomNr.equals("")) ){*/
              
                System.out.println("nome="+name+", cell="+cell+", email="+email+", checkout="+checkout+", days="+daysTay+", total="+Total+", roomNr="+roomNr+"");
                System.out.println("totalpagar='"+Total+"', checkout='"+checkout+"");
                String Query = "UPDATE public.reserva diastay='"+daysTay+"', totalpagar='"+Total+"', checkout='"+checkout+"' WHERE id='"+id+"';";
                CRUD.setData(Query, "----Checkout Sucess---\n----saida sucedida---");
                Query ="UPDATE public.room SET status='unoccupied' WHERE roomnr='"+roomNr+"';";
                
                CRUD.setData(Query, "eng:Room unoccupied again\n pt:Quarto novamente desocupado\n (free||livre)");
                 
            //---------------------------------
            String path="E:\\";//caminho
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            //setar o caminho ara o ficheiro
            PdfWriter.getInstance(doc, new FileOutputStream(path+""+id+".pdf"));
            doc.open();
            Paragraph paragraph1 = new Paragraph("                                                              ---[Mi-Hotel]-Sistema de Gestao de Hotel---\\n");
            doc.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("**************************************************************************************************************************************************************************");
            doc.add(paragraph2);
            
             Paragraph paragraph3 = new Paragraph("\tBill ID: "+id+"\nCustumer(hospede) Details:\nName: "+name+"\nMoboleNr: "+cell+"\nEmail: "+email+"\n");
            doc.add(paragraph3);
            
             doc.add(paragraph2);
            
             Paragraph paragraph4 = new Paragraph("\tRoom Details: \nNumber: "+t9.getText()+" \nRoomType: "+roomType+"\nbed: "+bed+"\nPrice Per Day: "+daysTay+"\n");
            doc.add(paragraph4);
            
            doc.add(paragraph2);
                PdfPTable tb1  = new PdfPTable(4);
                tb1.addCell("Check-in Date: "+t3.getText()+"");
                tb1.addCell("Check-out Date: "+t3.getText()+"");
                tb1.addCell("Nr-days Stay: "+t3.getText()+"");
                tb1.addCell("Total-Aumout Paid: "+t3.getText()+"");
                doc.add(tb1);
                doc.add(paragraph2);
           Paragraph paragraph5 = new Paragraph("Thank you, please visit Again.");
            doc.add(paragraph5);
            doc.close();
            int a = JOptionPane.showConfirmDialog(null, "Do you want to Print Bill?\npt: Deseja Imprimir recibo?\n","Escolha",JOptionPane.YES_NO_OPTION);
            if(a == 0){
                try {
                    if((new File("E:\\"+id+".pdf")).exists()){
                        Process p = Runtime
                                .getRuntime()
                                .exec("rundll32 url.dll, FileProtocolHandler E:\\"+id+".pdf");
                    }
                } catch (Exception ex) {
                    
                }
            }
                setVisible(false);
                new ccin().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro Check-out\n "+ex.getMessage()+"");
                Logger.getLogger(ccout.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource() == btn3){
             setVisible(false);
            new ccout().setVisible(true);
        }
        
    }
    
}
