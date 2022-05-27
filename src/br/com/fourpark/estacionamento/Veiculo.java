package br.com.fourpark.estacionamento;

public class Veiculo {
	private String placa;
	private String modelo;
	private String tipo;
	private String horarioEntrada;
	private String horarioSaida;
	
	public Veiculo() {
		
	}

	public Veiculo(String placa, String modelo, String tipo, String horarioEntrada) {
		this.placa = placa;
		this.modelo = modelo;
		this.tipo = tipo;
		this.horarioEntrada = horarioEntrada;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}
	
	public String toString() {
		return "Veiculo [PLACA=" + placa + ", MODELO=" + modelo + ", TIPO=" + tipo + ", HORARIO DE ENTRADA="
				+ horarioEntrada +  "HORARIO SAIDA:" + horarioSaida + "]";
	}
	
}
