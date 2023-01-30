/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDTO;

/**
 *
 * @author lb
 */
public class Users {
    
   //private Integer id;//id
   private String name, email, password, securityQ, answer, address, gender, cell;
   
   //Contrutor

    public Users(String name, String email, String password, String securityQ, String answer, String address, String gender, String cell) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.securityQ = securityQ;
        this.answer = answer;
        this.address = address;
        this.gender = gender;
        this.cell = cell;
    }
      

    public Users() {
    }
    //Getteres e setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
    
   
        

   
    
   
}
