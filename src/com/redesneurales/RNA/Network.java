package com.redesneurales.RNA;

import java.util.Scanner;

public class Network {
	public Network(int inputs, int hidden_layers, int outputs){
		M_Network m_net = new M_Network(); 
		//La RNA se tiene que inicializar o de otra manera no se podrán añadir pesos y demás
		m_net.initRNA(inputs, hidden_layers, outputs);	
	}
	public static void main (String[] args){
		//Aqui se irá llenando el flujo del programa, para darme una idea
		Network net = new Network(Integer.valueOf(args[1]),Integer.valueOf(args[2]),Integer.valueOf(args[3]));
		while(true){
		System.out.println("Ingresa opcion \n 1.-Llenar pesos manualmente");
		System.out.println("2.- Llenar pesos de manera aleatoria");
		System.out.println("3.- Usar las pruebas proporcionadas por el doctor (2,1,1) con pesos= 1; (2,2,1) con hidden_weights = 1.5 y el resto de pesos con valor de 1");
		Scanner sc = new Scanner(System.in);
		//Pedazo en construcción, usar las propiedades para obtener los valores y generar los casos ya planeados.
			if(sc.nextInt() == 1 ){
				
			}else {
				break;
			}
		}
	}
}
