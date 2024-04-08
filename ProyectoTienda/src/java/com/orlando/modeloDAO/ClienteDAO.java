package com.orlando.modeloDAO;

import com.orlando.config.Conexion;
import com.orlando.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO implements Validar{
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int validar(Cliente clie) {
        int r=0;
        String sql="select * from cliente where Nombres=? AND Email=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, clie.getNom());
            ps.setString(2, clie.getEmail());
            rs=ps.executeQuery();
            while(rs.next()){
                r=r+1;
                clie.setNom(rs.getString("Nombres"));
                clie.setEmail(rs.getString("Email"));
            }
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    
}
