package pe.org.cnl.gestiondoc.service;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Usuario;

public interface UsuarioService {
	 public List<Usuario> listaUsuarios();
     public List<Usuario> listaUsuarios(Usuario usuario);
     public List<Usuario> listaUsuariosPorRole(String role);
     public Usuario obtenerUsuarioPorUsername(String username);
     public Usuario obtenerUsuario(String username);
     public void registrarusuario(Usuario usuario);
     public void modificarusuario(Usuario usuario);
     public void modificarPermisos(Usuario usuario);
     public void agregaPermisoUser(Usuario usuario);
}
