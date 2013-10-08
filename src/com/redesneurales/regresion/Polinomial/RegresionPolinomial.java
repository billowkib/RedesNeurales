package com.redesneurales.regresion.Polinomial;
import com.redesneurales.regresion.M_Regresion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;

public class RegresionPolinomial {
	public M_Regresion M_Regresion = new M_Regresion();
	private double x = M_Regresion.getX();
	private double y;
	StringBuilder st = new StringBuilder();
	//numero + numero1x + numero2x^² + numero3^3 + numero4^4 + etc... 
	//In Construction
	public void calculaRegresion() {
		for (int i= 0; i< M_Regresion.getTotalIterations(); i++ ){
			x = i * M_Regresion.getDelta()+M_Regresion.getOrigen();
			y = calculaY(x);
			System.out.print("Valor en X= "+x+", ");
			System.out.println("Valor en Y= "+y);
		}
		
	}//End of construction place
	public void construyeScript(){
		
		FileOutputStream fop = null;
		File file =null;
		StringBuilder sb = new StringBuilder();
		double coeficientes[] = M_Regresion.getCoeficientes();
		double aux = coeficientes[0];
		sb.append("set term png\n")
		.append("set output 'resultado.png\n")
		.append("F(x)= ")
		.append(aux);
		
		for (int i =1; i< M_Regresion.getLimite(); i++){
			sb.append("+")
			.append(coeficientes[i])
			.append("*x**")
			.append(i);
		}
		sb.append("\nplot F(x)");
		System.out.print(sb.toString());
		try {
			//Se Cambio la dirección donde se guarda el script resultante
			file = new File("~/Regresionscript.gp");
			fop = new FileOutputStream(file);
			// Si no existe el archivo, se crea
			if (!file.exists()){
				file.createNewFile();
			}
			//Cargar contenido en bytes
			byte[] sbEnBytes = sb.toString().getBytes();
			fop.write(sbEnBytes);
			fop.flush();
			fop.close();
			System.out.println("Terminado");
		}catch (IOException e){
		e.printStackTrace();	
		}finally {
			try {
				if (fop != null) {
					fop.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public double calculaY(double x){
		double result;
		double [] coeficientes = M_Regresion.getCoeficientes();
		result = coeficientes[0];
		for (int i= 1; i< M_Regresion.getLimite(); i++){
			
			result += coeficientes[i]*Math.pow(x, i);
		}
		return result;
	} 
	public static void main (String [] args) {
		RegresionPolinomial Rpol = new RegresionPolinomial();
		double coeficientesEntrada[]= {0,0,0,0,0,0,0,0};
		for (int i = 0; i<= 7;i++){
			coeficientesEntrada[i] = -1 + (Math.random()*((1-(-1))+1));
		}
		Rpol.M_Regresion.setOrigen(Double.valueOf(args[2]).doubleValue());
		Rpol.M_Regresion.setCoeficientes(coeficientesEntrada);
		Rpol.M_Regresion.setLimite(coeficientesEntrada.length);
		Rpol.M_Regresion.setDelta(Double.valueOf(args[0]).doubleValue());
		Rpol.M_Regresion.setTotalIterations(Integer.valueOf(args[1]).intValue());
		Rpol.calculaRegresion();
		Rpol.construyeScript();
	}
	
}