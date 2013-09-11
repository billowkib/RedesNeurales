package com.redesneurales.regresion.Cosenoidal;
import com.redesneurales.regresion.M_Regresion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;

public class RegresionCosenoidal {
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
		double fases [] = M_Regresion.getFases();
		double aux = coeficientes[0];
		sb.append("set term png\n")
		.append("set output 'resultado.png\n")
		.append("F(x)= ")
		.append(aux);
		//F(X)= a0 + a1 cos(b0+ x + 2 pi / R) +a2 cos(b1
		for (int i =1; i< M_Regresion.getLimite(); i++){
			sb.append("+")
			.append(coeficientes[i])
			.append("*cos(")
			.append(fases[i-1])
			.append("+(x*2*pi/")
			.append(M_Regresion.getPeriodo())
			.append("))");
		}
		sb.append("\nplot F(x)");
		System.out.print(sb.toString());
		try {
			file = new File("/home/billowkib/Development/RegresionCosScript.gp");
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
		double [] fases = M_Regresion.getFases();
		result = coeficientes[0];
		
		for (int i= 1; i< M_Regresion.getLimite(); i++){
			
			result += coeficientes[i]*Math.cos(fases[i-1]+(x*(2*Math.PI/M_Regresion.getPeriodo())));
		}
		return result;
	}
	public static void main (String [] args) {
		RegresionCosenoidal Rpol = new RegresionCosenoidal();
		double coeficientesEntrada[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		double fases []= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		for (int i = 0; i<= 19;i++){
			coeficientesEntrada[i] = -1 + (Math.random()*((1-(-1))+1));
			if (i<19){
				fases[i] = -1 +(Math.random()*((1-(-1))+1));
			}
		}
		Rpol.M_Regresion.setFases(fases);
		Rpol.M_Regresion.setPeriodo(-1 + (Math.random()*((10)+1)));
		Rpol.M_Regresion.setOrigen(Double.valueOf(args[2]).doubleValue());
		Rpol.M_Regresion.setCoeficientes(coeficientesEntrada);
		Rpol.M_Regresion.setLimite(coeficientesEntrada.length);
		Rpol.M_Regresion.setDelta(Double.valueOf(args[0]).doubleValue());
		Rpol.M_Regresion.setTotalIterations(Integer.valueOf(args[1]).intValue());
		Rpol.calculaRegresion();
		Rpol.construyeScript();
	}
	
}