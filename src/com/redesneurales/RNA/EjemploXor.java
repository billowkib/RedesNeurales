package com.redesneurales.RNA;

public class EjemploXor {

		private static final int MAX_ITERACIONES = 500;

		//pesos
		private static double p13, p23, p14, p24, p35, p45;
		//sesgos
		private static double theta3, theta4, theta5;
		//salidas
		private static double gamma3, gamma4, gamma5;
		//gradientes de error 
		private static double delta3, delta4, delta5;
		//nuevos pesos
		private static double dw13, dw14, dw23, dw24, dw35, dw45, dt3, dt4, dt5;
		//constante de aprendizaje
		private static double alpha = 0.5;
		private static double error;
		private static double errorCuadMed;
		private static int iteraciones = 0;
		private static boolean loop = true;

		private static double sigmoid(double exponent)
		{
		    return (1.0/(1 + Math.pow(Math.E, (-1) * exponent)));
		}

		private static void activarNeurona(int x1, int x2, int gd5)
		{
		    gamma3 = sigmoid(x1*p13 + x2*p23 - theta3);
		    gamma4 = sigmoid(x1*p14 + x2*p24 - theta4);
		    gamma5 = sigmoid(gamma3*p35 + gamma4*p45 - theta5);

		    error = gd5 - gamma5;

		    ajustePesos(x1, x2);
		}

		private static void ajustePesos(int x1, int x2)
		{
		    delta5 = gamma5 * (1 - gamma5) * error;
		    dw35 = alpha * gamma3 * delta5;
		    dw45 = alpha * gamma4 * delta5;
		    dt5 = alpha * (-1) * delta5;

		    delta3 = gamma3 * (1 - gamma3) * delta5 * p35;
		    delta4 = gamma4 * (1 - gamma4) * delta5 * p45;

		    dw13 = alpha * x1 * delta3;
		    dw23 = alpha * x2 * delta3;
		    dt3 = alpha * (-1) * delta3;
		    dw14 = alpha * x1 * delta4;
		    dw24 = alpha * x2 * delta4;
		    dt4 = alpha * (-1) * delta4;

		    p13 = p13 + dw13;
		    p14 = p14 + dw14;
		    p23 = p23 + dw23;
		    p24 = p24 + dw24;
		    p35 = p35 + dw35;
		    p45 = p45 + dw45;
		    theta3 = theta3 + dt3;
		    theta4 = theta4 + dt4;
		    theta5 = theta5 + dt5;
		}

		public static void main(String[] args)
		{
			//Asignacion de los pesos y sesgos iniciales
		    p13 = 0.5;
		    p14 = 0.9;
		    p23 = 0.4;
		    p24 = 1.0;
		    p35 = -1.2;
		    p45 = 1.1;
		    theta3 = 0.8;
		    theta4 = -0.1;
		    theta5 = 0.3;

		    System.out.println("Red Neuronal del XOR");
		    //proceso de entrenamiento
		    while(loop)
		    {
		        activarNeurona(1,1,0);
		        errorCuadMed = error * error;
		        activarNeurona(0,1,1);
		        errorCuadMed += error * error;
		        activarNeurona(1,0,1);
		        errorCuadMed += error * error;
		        activarNeurona(0,0,0);
		        errorCuadMed += error * error;

		        iteraciones++;

		        if(iteraciones >= MAX_ITERACIONES)
		        {
		            System.out.println("Se alcanzo el limite de " + MAX_ITERACIONES + " iteraciones, el programa termina.");
		            System.exit(0);
		        }

		        System.out.println(iteraciones + " " + errorCuadMed);

		        if (errorCuadMed < 0.001)
		        {
		            loop = false;
		        }
		    }
		}
		}

