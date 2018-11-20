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
public class Join {

    /**
     * Creates a new instance of Join
     */
        private String IDplayer;
    public void setIDplayer(String IDplayer) {
    this.IDplayer = IDplayer;
    }
    public String getIDplayer() {
        return IDplayer;
    }

    private String NAMArole;
    public void setNAMArole(String NAMArole) {
        this.NAMArole = NAMArole;
    }
    public String getNAMArole() {
        return NAMArole;
    }
    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
     public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select a.ID_player, a.Nama, a.ID_role, b.ID_role, b.Nama_role FROM player a INNER JOIN role b ON a.ID_role = b.ID_role ;");
            while(rs.next()){
                Join obj_Join = new Join();
                obj_Join.setIDplayer(rs.getString("ID_player"));
                obj_Join.setNAMA(rs.getString("Nama"));
                obj_Join.setNAMArole(rs.getString("Nama_role"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}

    public Join() {
    }
    
}
