/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.web;

import com.user.web.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bleezy
 */
@ManagedBean
@SessionScoped
public class Role {

    /**
     * Creates a new instance of Role
     */
    private String IDrole;
    public void setIDrole(String IDrole) {
    this.IDrole = IDrole;
    }
    public String getIDrole() {
        return IDrole;
    }

    private String NAMArole;
    public void setNAMArole(String NAMArole) {
        this.NAMArole = NAMArole;
    }
    public String getNAMArole() {
        return NAMArole;
    }
    
    private String GAJI;
    public void setGAJI(String GAJI) {
        this.GAJI = GAJI;
    }
    public String getGAJI() {
        return GAJI;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_role(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_ID_role = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from role where ID_role="+Field_ID_role);
          Role obj_role = new Role();
          rs.next();
          obj_role.setIDrole(rs.getString("ID_role"));
          obj_role.setNAMArole(rs.getString("Nama_role"));
          obj_role.setGAJI(rs.getString("Gaji"));
          sessionMap.put("Editrole", obj_role);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditRole.xhtml?faces-redirect=true";   
}

public String Delete_role(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_ID_role = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from role where ID_role=?");
         ps.setString(1, Field_ID_role);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/Role.xhtml?faces-redirect=true";   
}

public String Update_role(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_ID_role= params.get("Update_ID_role");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update role set ID_role=?, Nama_role=?, GAJI=? where ID_role=?");
            ps.setString(1, IDrole);
            ps.setString(2, NAMArole);
            ps.setString(3, GAJI);
            ps.setString(4, Update_ID_role);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/Role.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_role() throws Exception{
        ArrayList list_of_role=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from role");
            while(rs.next()){
                Role obj_role = new Role();
                obj_role.setIDrole(rs.getString("ID_role"));
                obj_role.setNAMArole(rs.getString("Nama_role"));
                obj_role.setGAJI(rs.getString("Gaji"));
                list_of_role.add(obj_role);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_role;
}
    
public String Tambah_role(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into role(ID_role, Nama_role, GAJI) value('"+IDrole+"','"+NAMArole+"','"+GAJI+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/Role.xhtml?faces-redirect=true";
    }
    public Role() {
    }
    
}
