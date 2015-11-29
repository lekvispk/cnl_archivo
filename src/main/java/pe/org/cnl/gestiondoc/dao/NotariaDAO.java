package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.EncargadoArchivo;
import pe.org.cnl.gestiondoc.model.Notaria;

public interface NotariaDAO {
	
	public List<Notaria> buscar(Notaria notaria);
	public void registrar(Notaria notaria);
	public void eliminar(Integer idNotaria);
	public Notaria obtener(Integer idNotaria);
	public void encargar(Integer integer);
	public EncargadoArchivo obtenerEncargado();
	
}
