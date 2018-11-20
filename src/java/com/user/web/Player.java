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
public class Player {

    /**
     * Creates a new instance of Player
     */
    private String IDplayer;
    public void setIDplayer(String IDplayer) {
      this.IDplayer = IDplayer;
    }
    public String getIDplayer() {
        return IDplayer;
    }

    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }
    
    private String IDrole;
    public void setIDrole(String IDrole) {
        this.IDrole = IDrole;
    }
    public String getIDrole() {
        return IDrole;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_player(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_ID_player = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from player where ID_player="+Field_ID_player);
          Player obj_player = new Player();
          rs.next();
          obj_player.setIDplayer(rs.getString("ID_player"));
          obj_player.setNAMA(rs.getString("Nama"));
          obj_player.setIDrole(rs.getString("ID_role"));
          sessionMap.put("Editplayer", obj_player);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditPlayer.xhtml?faces-redirect=true";   
}

public String Delete_player(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_ID_player = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from player where ID_player=?");
         ps.setString(1, Field_ID_player);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/Player.xhtml?faces-redirect=true";   
}

public String Update_player(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_ID_player= params.get("Update_ID_player");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update player set ID_player=?, Nama=?, ID_role=? where ID_player=?");
            ps.setString(1, IDplayer);
            ps.setString(2, NAMA);
            ps.setString(3, IDrole);
            ps.setString(4, Update_ID_player);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/Player.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_player() throws Exception{
        ArrayList list_of_player=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from player");
            while(rs.next()){
                Player obj_player = new Player();
                obj_player.setIDplayer(rs.getString("ID_player"));
                obj_player.setNAMA(rs.getString("Nama"));
                obj_player.setIDrole(rs.getString("ID_role"));
                list_of_player.add(obj_player);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_player;
}
    
public String Tambah_player(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into player(ID_player, Nama, ID_role) value('"+IDplayer+"','"+NAMA+"','"+IDrole+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/Player.xhtml?faces-redirect=true";
    }
    public Player() {
    }
    
}
