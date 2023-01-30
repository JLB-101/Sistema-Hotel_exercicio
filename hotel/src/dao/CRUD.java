package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.ForgotPass;

/**
 *
 * @author Juliao LB
 */
public class CRUD {
    static Connection con= null;
    static Statement stm = null;
    static ResultSet rs = null;
    static Dao dao = new Dao();
    boolean statusLogin;
    
    //inserir valor na BD
    public static void setData(String Query, String msg) throws Exception{
        //passando a conexao para objecto con do Connection
        try {
        con = dao.getConnection();
        stm = con.createStatement();
        stm.execute(Query);
       
        JOptionPane.showMessageDialog(null, msg);
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO\n "+e.getMessage()+"");
        }
       con.close();
    }
    
    public boolean login(String Query, String msg)throws Exception{
        String email = "", senha ="";
        
        try {//verificar ligin
            con = dao.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(Query);
            if (rs.next()) {
                System.out.println(email = rs.getString(2));
                System.out.println(senha = rs.getString(4));
               statusLogin  =  Boolean.parseBoolean(rs.getString(8)) ;
            }
            if(!msg.equals("") && !email.equals("")){
                JOptionPane.showMessageDialog(null, msg);
            }else if(email.equals("")){
                msg="USURIO NAO EXISTE";
                
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
        }
        con.close();
        return statusLogin;
    }
    
    public void forgotPass(String Query, String msg)throws Exception{
        String email = "", senha ="";
        
        ForgotPass fp = new ForgotPass();
        try {//verificar ligin
            con = dao.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(Query);
           
            if (rs.next()) {
                
                fp.t2.setEditable(false);
                fp.t1.setEditable(false);
                fp.t2.setText(rs.getString(5));
                fp.t1.setText(rs.getString(3));
                email = rs.getString(5);
                msg ="pt:Conta do USario["+email+"] Encontra";
            }
            if(!msg.equals("") && !email.equals("")){
                JOptionPane.showMessageDialog(null, msg);
            }else if(email.equals("")){
                msg="-------[forgotPass]-------\npt:Conta Nao Encontra-\n"
                 + "Nao pode Mudar senha";
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
        }
        con.close();
    }
    public void newPass(String Query, String msg)throws Exception{
        String email = "", senha ="";
        short check = 0;
        ForgotPass fp = new ForgotPass();
        try {//verificar ligin
            con = dao.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(Query);
            if (rs.next()) {
                setData("UPDATE public.users SET password='"+msg+"' WHERE email='"+rs.getString(3)+"';", "pt: Senha Atulizada com Sucesso");
                fp.t2.setEditable(false);
                fp.t1.setEditable(false);
                fp.t3.setEditable(false);
                fp.t4.setEditable(false);
                fp.t1.setText(rs.getString(3));
                fp.t2.setText(rs.getString(5));
                fp.t3.setText(rs.getString(6));
                fp.t4.setText(rs.getString(4));
                email = rs.getString(3);
                msg="pt:Senha Atualizada";
            }
            if(!msg.equals("") && !email.equals("")){
                JOptionPane.showMessageDialog(null, msg);
            }else if(email.equals("")){
                msg="-------[newPass]-------\npt:Conta nao Encontrada-2\n"
                 + "Nao pode Mudar senha";
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
        }
        con.close();
    }
        
    public static ResultSet getData(String Query) {
        Connection con= null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {//selecionar dados na BD
            con = dao.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(Query);
            return rs;
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "pt:erro ao tentar selecionar\n"+e);
             return null;
        } 
    }
    
}
