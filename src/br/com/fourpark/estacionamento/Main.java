package br.com.fourpark.estacionamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Double valorDia = 0.00;
		Double valorHora = 15.00;
		Vaga[] estacionamento = new Vaga[50];
		ArrayList<String> historico = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Integer opcao;  
		
		for (int i = 0; i < estacionamento.length; i++) {
			estacionamento[i] = new Vaga();
			estacionamento[i].setPosicao(i+1);
			estacionamento[i].setOcupado(false);
		}
		
		do{
			System.out.print("\n ------- MENU ESTACIONAMENTO --------"
					+"\n"
					+"\n|  1 - Registrar entrada do veículo  |"
					+"\n|  2 - Registrar saída do veículo    |"
					+"\n|  3 - Verificar vagas disponíveis   |"
					+"\n|  4 - Verificar vagas ocupadas      |"
					+"\n|  5 - Historico de veiculos         |"
					+"\n| ---------------------------------  |"
					+"\n|            0 - S A I R             |"
					+"\n"
					+"\n Informe a opção desejada >>> ");
			
			opcao = sc.nextInt();
			
			switch(opcao) {
				case 1: 
					Veiculo veiculo = new Veiculo();
					Date hora;
					sc.nextLine();
					System.out.print("\nInforme a placa do veiculo >>> ");
					veiculo.setPlaca(sc.nextLine());
					System.out.print("Informe o modelo do veiculo >>> ");
					veiculo.setModelo(sc.nextLine());
					System.out.print("Informe o tipo do veiculo >>> ");
					veiculo.setTipo(sc.nextLine());
					
					while (true) {
						System.out.print("Informe o horario de entrada >>> ");

						try {
							hora = sdf.parse(sc.next());
							veiculo.setHorarioEntrada(sdf.format(hora));
							break;
						} catch (ParseException e) {
							System.err.println("Formato de hora inválida, tente novamente!");
						}
					}

					
					for (int i = 0; i < estacionamento.length; i++) {
						if(!estacionamento[i].getOcupado()) {
							estacionamento[i].setVeiculo(veiculo); 
							estacionamento[i].setOcupado(true);
							System.out.println("\nvaga " + estacionamento[i].getPosicao() + " ocupada com sucesso!");
							System.out.println("Entrou no estacionamento " + estacionamento[i].getVeiculo().getHorarioEntrada());
							break;
						}
				}
					break;
					
				case 2:
					int vaga;
					boolean validaPosicao;
					
					do {
						System.out.print("\nInforme a vaga a ser desocupada >>> ");
						vaga = sc.nextInt();
						validaPosicao = vaga > estacionamento.length || vaga < 1;
						if(validaPosicao) {
							System.err.println("\nVALOR INVALIDO!");
						}
					}
					while(validaPosicao);
					
					vaga = vaga-1;
					sc.nextLine();
					if(estacionamento[vaga].getOcupado()) {
						if (estacionamento[vaga].getOcupado()) {
							while (true) {
								System.out.print("Informe o horario de Saida do estacionamento >>> ");

								try {
									hora = sdf.parse(sc.next());
									estacionamento[vaga].getVeiculo().setHorarioSaida(sdf.format(hora));
									
									if (hora.before(sdf.parse(estacionamento[vaga].getVeiculo().getHorarioEntrada()))) {
										System.err.println("Hora de saida menor que a hora de entrada!");
										continue;
									} else if (hora.equals(sdf.parse(estacionamento[vaga].getVeiculo().getHorarioEntrada()))) {

										System.err.println("\nHora de saida igual a de entrada!");
										continue;
									}
									break;
								} catch (ParseException e) {
									System.err.println("Formato de hora inválida, tente novamente!");
								}
							}
						}
		
							int horaEntrada = Integer.parseInt(estacionamento[vaga].getVeiculo().getHorarioEntrada().substring(0, 2));
							int minutoEntrada = Integer.parseInt(estacionamento[vaga].getVeiculo().getHorarioEntrada().substring(3,5));
							System.out.println("\nHorario de entrada: " + horaEntrada + ":" + minutoEntrada);
							
							int horaSaida = Integer.parseInt(estacionamento[vaga].getVeiculo().getHorarioSaida().substring(0,2));
							int minutoSaida = Integer.parseInt(estacionamento[vaga].getVeiculo().getHorarioSaida().substring(3,5));
							System.out.println("Horario de saida: " + horaSaida + ":" + minutoSaida);
							
							int resultadoHora = horaSaida - horaEntrada;
							int resultadoMinuto = minutoSaida - minutoEntrada;
							double valorMinuto = valorHora/60;
							double valor = resultadoMinuto*valorMinuto + resultadoHora*valorHora;
							valorDia += valor;
							historico.add("PLACA:" + estacionamento[vaga].getVeiculo().getPlaca()
									+" | "
									+ "MODELO:" + estacionamento[vaga].getVeiculo().getModelo()
									+" | "
									+ "ENTRADA:" + estacionamento[vaga].getVeiculo().getHorarioEntrada()
									+ " | "
									+ "SAIDA:" + estacionamento[vaga].getVeiculo().getHorarioSaida()  
									+ " | "
									+"VALOR PAGO: R$" + valor +"\n");
							
						System.out.println("\nVALOR A SER PAGO É: R$" + valor);
						System.out.println("Vaga desocupada");
						
						estacionamento[vaga].setOcupado(false);
						estacionamento[vaga].setVeiculo(null);
					}
					else {
						System.out.println("\nVaga já está vazia");
					}
					break;
					
				case 3:
					for(int i = 0; i < estacionamento.length; i++) {
						if(!estacionamento[i].getOcupado()) {
							System.out.println("A vaga " + estacionamento[i].getPosicao() + " está disponível");
						}
					}
					break;
					
				case 4:
					int cont = 0;
					for(int i = 0; i < estacionamento.length; i++) {
						if(estacionamento[i].getOcupado()) {
							System.out.println("\nA vaga " + estacionamento[i].getPosicao() + " está ocupada");
							System.out.println(estacionamento[i].getVeiculo().toString());
							cont = 1;
						}
					}
					if(cont == 0) {
						System.out.println("\nTODAS AS VAGAS ESTÃO DISPONÍVEIS!");
					}
					break;
				
				case 5:
					System.out.println("=====================================");
					System.out.println("VALOR ARRECADADO NO DIA R$" + valorDia );
					System.out.println("=====================================\n");
					System.out.println("==========CARROS LIBERADOS==========\n" + historico);
					System.out.println("=====================================");
				
			}
				
		}while(opcao != 0);
		sc.close();
			System.out.println("\nSISTEMA ENCERRADO!");
	
	}
}

