package pe.org.cnl.gestiondoc.security;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Usuario;

@Repository("userLoginService")
public class AutenticacionJdbcDaoImpl extends JdbcDaoImpl{

        private Logger logger = Logger.getLogger(AutenticacionJdbcDaoImpl.class);
                
        @Autowired
        UsuarioDao usuarioDao;
        
        @Autowired
        public AutenticacionJdbcDaoImpl(DataSource dataSource){
                setDataSource(dataSource);
        }
        
        @Override
        public UserDetails loadUserByUsername(String username) {
            try {
                    
                this.setUsersByUsernameQuery("SELECT u.username as username, u.clave as password, u.estado as enabled  FROM sec_usuarios u WHERE u.username = ?");
                this.setAuthoritiesByUsernameQuery("SELECT username , authorithy as authority FROM sec_authorities WHERE username = ?");
                
                UserDetails user = super.loadUserByUsername(username);
                logger.debug("usuario login "+user.toString());
                Usuario usuario = usuarioDao.obtenerUsuarioPorUsername(username);
                
                Usuario ubean = new Usuario(user.getUsername(), user.getPassword(), user.isEnabled(), user.getAuthorities());
                ubean.setUsername(user.getUsername());
                ubean.setClave(user.getPassword());
                //ubean.setUsrnombrevh(usuario.getUsrnombrevh());
                //ubean.setUsrapepaternovh(usuario.getUsrapepaternovh());
                //ubean.setUsrapematernovh(usuario.getUsrapematernovh());
                //ubean.setUsrfechanacimientodt(usuario.getUsrfechanacimientodt());
                ubean.setEstado( user.isEnabled()==true?1:0 );
                logger.debug("*********"+ubean.toString());
                return ubean;   
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("No hay notaria relacionada a este usuario");
            }
        }

        
}