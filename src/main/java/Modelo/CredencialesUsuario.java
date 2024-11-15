package Modelo;

public class CredencialesUsuario {
	private String nombre;
	private String clave;
	private Usuarios tipo_usuario;
	private long id;
	
	public CredencialesUsuario() {
		
	}

	public CredencialesUsuario(String nombre, String clave, Usuarios tipo_usuario, long id) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.tipo_usuario = tipo_usuario;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuarios getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(Usuarios tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		//poner aqui la estructura que se importa a credenciales.txt
		return getNombre()+" "+getClave()+" "+getTipo_usuario().getTipoDeUsuario()+" "+getId()+" \n";
	}
	
	
}
