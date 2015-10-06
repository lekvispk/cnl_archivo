package pe.org.cnl.gestiondoc.service;

import java.util.List;
import pe.org.cnl.gestiondoc.model.Notaria;

public interface NotariaService {
	
	public List<Notaria> buscar(Notaria notaria);
	public void registrar(Notaria notaria);
	public void eliminar(Integer idNotaria);
	public Notaria obtener(Integer idNotaria);
	
}
