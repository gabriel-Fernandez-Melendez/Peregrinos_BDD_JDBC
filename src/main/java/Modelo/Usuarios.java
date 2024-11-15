package Modelo;

public enum Usuarios {
	//posibles perfiles que puede tomar el usuario
Invitado("invitado"),Responsable_Parada("Responsable_parada"),Peregrino("Peregrino"),Administrador_General("Administrador_general");
	private String tipo_usuario;
	//constructor de la enum
	private Usuarios(String tipo_usuario) {
		this.tipo_usuario=tipo_usuario;
	}
	//getter del cargo, un setter no hace falta ya que siempre vamos a sacar los mismos 4 posible cargos plasmados en la enum
	public String getTipoDeUsuario() {
		return tipo_usuario;
	}
}
