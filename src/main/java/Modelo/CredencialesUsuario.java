package Modelo;

public class CredencialesUsuario {
	private long id;
	private String nombre;
	private String clave;
	private Usuarios tipo_usuario;
	
	
	public CredencialesUsuario() {
		
	}

	public CredencialesUsuario(long id,String nombre, String clave, Usuarios tipo_usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.tipo_usuario = tipo_usuario;
		
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
