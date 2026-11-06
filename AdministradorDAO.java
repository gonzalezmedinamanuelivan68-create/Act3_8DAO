package com.mycompany.clinica.daos;

import com.mycompany.clinica.modelos.Administrador;
import com.mycompany.clinica.config.Conexion;
import com.mycompany.clinica.modelos.EEstados;
import com.mycompany.clinica.modelos.ERoles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    public boolean guardar(Administrador admin) {
        Connection con = null;
     
        String sql = "INSERT INTO administradores (usuario, nombres, apellidos, correo, contraseña, rol, ruta_imagen, estados) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombres());
            ps.setString(3, admin.getApellidos());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol().name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado().name());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERROR Guardar: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizar(Administrador admin) {
        Connection con = null;
     
        String sql = "UPDATE administradores SET usuario=?, nombres=?, apellidos=?, correo=?, contraseña=?, rol=?, ruta_imagen=?, estados=? WHERE id=?;";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombres());
            ps.setString(3, admin.getApellidos());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol().name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado().name());
            ps.setInt(9, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERROR Actualizar: " + e.getMessage());
        }
        return false;
    }

    public boolean borrar(int id) {
        Connection con = null;
      
        String sql = "DELETE FROM administradores WHERE id=?;";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERROR Borrar: " + e.getMessage());
        }
        return false;
    }

    public List<Administrador> obtenerTodos() {
        List<Administrador> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM administradores";
        try {
            
            con = Conexion.conectar();
            if (con == null) {
    System.out.println("ERROR: No se pudo conectar a MySQL");
    return lista;
}
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setCorreo(rs.getString("correo")); 
                admin.setContraseña(rs.getString("contraseña"));
                admin.setRol(ERoles.valueOf(rs.getString("rol")));
                admin.setRutaImagen(rs.getString("ruta_imagen"));
                admin.setEstado(EEstados.valueOf(rs.getString("estados")));
                lista.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("ERROR Obtener Todos: " + e.getMessage());
        }
        return lista;
    }
    
}