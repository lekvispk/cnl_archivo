package pe.org.cnl.gestiondoc.util;

public class ParametroUtil {

	public static final int ALERTA = 1;
	public static final int CIRCULAR = 2;
	public static final int OPOSICION = 3;
	public static final int RESPUESTA = 23;
	public static final int RESPUESTA_CNL = 24;
	public static final int RESPUESTA_NOTARIOS = 25;
	
	public static final int ENTIDAD_TODAS = -1;
	public static final int ENTIDAD_NOTARIA = 1;
	public static final int ENTIDAD_OTRAS = 2;
	public static final int ENTIDAD_CNL = 3;
	
	
	public static final int RELACION_PERSONA_IMPLICADO = 1;
	public static final int RELACION_PERSONA_SOLICITANTE = 2;
	public static final int RELACION_PERSONA_INFORMANTE = 3;
	
	public static final int TIPO_RELACION_ALERTA = 1;
	public static final int TIPO_RELACION_OPOCISION = 2;
	public static final int TIPO_RELACION_CIRCULAR = 3;
	public static final int TIPO_RELACION_AUTORIOZACION = 4;
	public static final int TIPO_RELACION_PODERONP = 5;
	
	public static final String REMITENTE_DEFAULT="archivocnl@notarios.org.pe";
	public static enum TipoOperacion{CREAR,ACTUALIZAR,ELIMINAR};
	
	public static final String TIPO_OPERACION_CREACION = "CREACION";
	public static final String TIPO_OPERACION_MODIFICACION = "MODIFICACION";
	public static final String TIPO_OPERACION_ELIMINACION = "ELIMINACION";
	
	public static long FILE_MAX_SIZE = 4194304;
	
	public final class TipoRelacionRespuesta{
		public static final int ALERTA = 1;
		public static final int OPOSICION = 2;
		public static final int NOTARIA = 3;
		public static final int COLEGIO = 4;
	}
	

	public static enum EstadoTramite {
		REGISTRADO(1), ATENDIDO(2), DERIVADO(3), RESPONDIDO(4), NOTIFICADO(5), CONCLUIDO(6);
		public int value;
 
		private EstadoTramite(int value) {
			this.value = value;
		}
	}
}

