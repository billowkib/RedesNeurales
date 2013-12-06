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
	
	public void RunFromProps (){
		/*Con este metodo se podrá correr el programa con las propiedades ya existentes en
		 * el archivo rna.properties, las reglas para que las propiedades esten bien son
		 * Las propiedades inputs, hidden_layers y outputs pueden ser cualquier entero
		 * La propiedad input_vector debe contener (inputs) elementos
		 * la propiedad hidden_weights debe contener (inputs * hidden_layers) elementos
		 * La propiedad output_weights debe contener (hidden_layers * outputs) elementos
		 * La propiedad b_hidden debe contener (hidden_layers) elementos
		 * La propiedad b_outputs debe contener (output) elementos 
		 * Se recomienda correr primero DefaultTest1 o DefaultTest2 para generar el archivo
		 * Una vez generado se puede modificar el archivo libremente*/
		M_Network m_net = new M_Network();
		//inputs;hiddenlayers;outputs;inputVector;hiddenW;outputW;bHidden;bOutput
	m_net = ComputeOutputs(m_net);
	printResults(m_net);
		
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
		printResults(m_net);

	}
	public void DefaultTest2(){
		//contendra la prueba a la red con los valores predeterminados encargados
		//Todos los pesos (bias y ponderaciones) = 1
		//Los pesos que van a la capa oculta serán de 1.5
		//inputs = 2, hidden layers = 2, outputs = 1
		M_Network m_net = new M_Network();
			//inputs;hiddenlayers;outputs;inputVector;hiddenW;outputW;bHidden;bOutput
		String [] testValues ={"2","2","1","1,1","1.5,1.5,1.5,1.5","1,1","1,1","1"};
		
		FillProperties(testValues);
		m_net = ComputeOutputs(m_net);
		printResults(m_net);
	}
	public static void printResults (M_Network m_net){
		System.out.println("Valores de las neuronas en capa oculta:");
		for (int i=0;i<m_net.getHiddenLayers();i++){
			System.out.println(m_net.getHiddenVector()[i]);
		}
		System.out.println("Valores de las neuronas de salida:");
		for (int i=0;i<m_net.getOutputs();i++){
			System.out.println(m_net.getOutputVector()[i]);
		}
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
	public static M_Network GetAndAssignProps (M_Network m_net){
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
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return m_net;
	}
	public static M_Network ComputeOutputs(M_Network m_net){
		m_net = GetAndAssignProps(m_net);
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
		Network net = new Network();
		while(true){
		System.out.println("Ingresa opcion ");
		System.out.println("1.-Hacer prueba proporcionada (2,1,1) con pesos =1");
		System.out.println("2.-Usar pruebas proporcionada (2,2,1) con hidden_weights = 1.5 y el resto de pesos con valor de 1");
		System.out.println("3.-Usar archivo de propiedades rna.properties (el archivo ya debe existir, se puede crear automaticamente con la opcion 1 o 2 y despues ser modificado)");
		System.out.println("Presionar cualquier otra tecla acabara con la ejecucion del programa");
		Scanner sc = new Scanner(System.in);
		//Pedazo en construcción, usar las propiedades para obtener los valores y generar los casos ya planeados.
		int option = sc.nextInt();
			if(option == 1 ){
				
				net.DefaultTest1();
			}else {
				if (option == 2) {
					
					net.DefaultTest2();
				}
				else{
					 if(option ==3){
						 net.RunFromProps();
					 }
					 else {
						 break;
					 }
				}
				
			}
		}
	}
}
	