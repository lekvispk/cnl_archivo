package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> listaUsuarios() {
		return usuarioDao.listaUsuarios();
	}

	@Override
	public List<Usuario> listaUsuarios(Usuario usuario) {
		return usuarioDao.listaUsuarios(usuario);
	}

	@Override
	public List<Usuario> listaUsuariosPorRole(String role) {
		return usuarioDao.listaUsuariosPorRole(role);
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		return usuarioDao.obtenerUsuarioPorUsername(username);
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioDao.obtenerUsuario(username);
	}

	@Override
	public void registrarusuario(Usuario usuario) {
		usuarioDao.registrarusuario(usuario);
	}

	@Override
	public void modificarusuario(Usuario usuario) {
		usuarioDao.modificarusuario(usuario);
	}

	@Override
	public void modificarPermisos(Usuario usuario) {
		usuarioDao.modificarPermisos(usuario);
	}

	@Override
	public void agregaPermisoUser(Usuario usuario) {
		usuarioDao.agregaPermisoUser(usuario);
	}

}
