package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Usuario;

public interface UsuarioDao {
	 public List<Usuario> listaUsuarios();
     public List<Usuario> listaUsuarios(Usuario usuario);
     public Usuario obtenerUsuarioPorUsername(String username);
     public Usuario obtenerUsuario(String username);
     public void registrarusuario(Usuario usuario);
     public void modificarusuario(Usuario usuario);
     public void modificarPermisos(Usuario usuario);
     public void agregaPermisoUser(Usuario usuario);
}
