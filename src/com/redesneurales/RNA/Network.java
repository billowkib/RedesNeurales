package com.redesneurales.RNA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class Network {
	Network(){
		System.out.println("Objeto network creado");
	}
	public Network(int inputs, int hidden_layers, int outputs){
		M_Network m_net = new M_Network(); 
		//La RNA se tiene que inicializar o de otra manera no se podrán añadir pesos y demás
		m_net.initRNA(inputs, hidden_layers, outputs);	
	}
	public void DefaultTest1(){
		//contendra la prueba a la red con los valores predeterminados encargados
		//Todos los pesos (bias y ponderaciones) = 1
		//inputs = 2, hidden layers = 1, outputs = 1
		M_Network m_net = new M_Network();
			//inputs;hiddenlayers;outputs;inputVector;hiddenW;outputW;bHidden;bOutput
		String [] testValues ={"2","1","1","1,1","1,1","1","1","1"};
		
		FillProperties(testValues);
		m_net = ComputeOutputs(m_net);	
		System.out.println("Valores de las neuronas en capa oculta:");
		for (int i=0;i<m_net.getHiddenLayers();i++){
			System.out.println(m_net.getHiddenVector()[i]);
		}
		System.out.println("Valores de las neuronas de salida:");
		for (int i=0;i<m_net.getOutputs();i++){
			System.out.println(m_net.getOutputVector()[i]);
		}
	}
	public void DefaultTest2(){
		
	}
	//Empieza metodo para llenar el archivo de propiedades
	public static void FillProperties(String vectors[]){
		//Metodo que llena el archivo de propiedades recibe un vector de strings con los pesos en un formato especifico
		Properties prop = new Properties();
		try{
			prop.setProperty("inputs", vectors[0]);
			prop.setProperty("hidden_layers", vectors[1]);
			prop.setProperty("outputs", vectors[2]);
			prop.setProperty("input_vector", vectors[3] );
			prop.setProperty("hidden_weights", vectors[4]);
			prop.setProperty("output_weights", vectors[5]);
			prop.setProperty("b_hidden", vectors[6]);
			prop.setProperty("b_output", vectors[7]);
			prop.store(new FileOutputStream("rna.properties"), null);
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	//Empieza metodo que hace el parsing de las propiedades de String a un arreglo de dobles
	public static double [] parseProperties(String toParse, int vectorSize){
		String [] to_Vector= toParse.split("\\s*,\\s*");
		double [] parsedArray = new double[vectorSize];
		for (int i= 0; i<vectorSize; i++){
			parsedArray[i] =Double.valueOf(to_Vector[i]);}
		return parsedArray;
	}
	//Metodo que calcula los valores de las neuronas de salida
	public static M_Network ComputeOutputs(M_Network m_net){
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("rna.properties"));
		String str []= {props.getProperty("inputs"),
				props.getProperty("hidden_layers"),
				props.getProperty("outputs"),
				props.getProperty("input_vector"),
				props.getProperty("hidden_weights"),
				props.getProperty("output_weights"),
				props.getProperty("b_hidden"),
				props.getProperty("b_output")
		};
		//M_Network m_net = new M_Network();
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println(str[2]);
		System.out.println(str[3]);
		System.out.println(str[4]);
		System.out.println(str[5]);
		System.out.println(str[6]);
		System.out.println(str[7]);
		m_net.setInputs(Integer.valueOf(str[0]));
		m_net.setHiddenLayers(Integer.valueOf(str[1]));
		m_net.setOutputs(Integer.valueOf(str[2]));
		/*
		 * Para inputVector [n] donde n = inputs (neuronas en la capa de entrada);
		 * Para hiddenWVector [n] donde n = inputs * hidden;
		 * Para outputsWVector [n] donde n = hidden* outputs;
		 * Para b-hidden [n] donde n = hiddenlayers (neuronas en la capa oculta)
		 * para b-output [n] donde n = outputs (neuronas en la capa de salida)
		 * */
		m_net.setInputVector(parseProperties(str[3], m_net.getInputs()));
		
		m_net.setHiddenWVector(parseProperties(str[4], m_net.getInputs()*m_net.getHiddenLayers()));
		
		m_net.setOutputWVector(parseProperties(str[5], m_net.getHiddenLayers()*m_net.getOutputs()));
		
		m_net.setHiddenBVector(parseProperties(str[6], m_net.getHiddenLayers()));
		
		m_net.setOutputBVector(parseProperties(str[7], m_net.getOutputs()));
		
		double [] salida1 = ComputeHiddenValues(m_net);
		double sum =0.0;
		double[] salida2 =new double [m_net.getOutputs()];
		for (int i =0; i<m_net.getOutputs();i++){
			for (int j =0; j<m_net.getHiddenLayers();j++){
				sum = sum+ (salida1[j]*m_net.getOutputWVector()[j]+m_net.getOutputBVector()[i]);
			}
			salida2[i] = SigmoidalActivation(sum);
		}
		m_net.setOutputVector(salida2);
		m_net.setHiddenVector(salida1);
		}catch(IOException ex){
			ex.printStackTrace();
		}
	return m_net;
	}
	public static double SigmoidalActivation (double sum){
		return 1.0/(1+Math.exp((-1.0)*sum));
	}
	public static double[] ComputeHiddenValues (M_Network m_net){
		double sum = 0.0;
		double[] salida = new double[m_net.getHiddenLayers()];
		for (int i = 0; i< m_net.getHiddenLayers(); i++){
			for (int j=0; j<m_net.getInputs();j++){
			sum = sum +(m_net.getInputVector()[j]*m_net.getHiddenWVector()[j]+m_net.getHiddenBVector()[i]);
			}
			salida[i] = SigmoidalActivation(sum);
		}
		return salida;
	}
	public static void main (String[] args){
		//Aqui se irá llenando el flujo del programa, para darme una idea
		while(true){
		System.out.println("Ingresa opcion \n 1.-Llenar pesos manualmente");
		System.out.println("2.- Llenar pesos de manera aleatoria");
		System.out.println("3.- Usar las pruebas proporcionadas por el doctor (2,1,1) con pesos= 1; (2,2,1) con hidden_weights = 1.5 y el resto de pesos con valor de 1");
		Scanner sc = new Scanner(System.in);
		//Pedazo en construcción, usar las propiedades para obtener los valores y generar los casos ya planeados.
			if(sc.nextInt() == 3 ){
				Network net = new Network ();
				net.DefaultTest1();
			}else {
				break;
			}
		}
	}
}
	