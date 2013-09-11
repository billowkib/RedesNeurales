package com.redesneurales.regresion;

public class M_Regresion {
	private int limite;
	private double [] coeficientes;
	private int totalIterations;
	private double delta;
	private double x ;
	private double y ;
	private double origen;
	private double [] fases;
	private double periodo;
	public M_Regresion(){
		this.setY((double)0);
		this.setOrigen(0);
		
	}
	public void setY (double yEntrada){
		y = yEntrada;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double xEntrada){
		x= xEntrada;
	}
	
	public double getX(){
		return x;
	}
	public void setCoeficientes(double [] coefEntrada){
		coeficientes = coefEntrada;
	}
	
	public double[] getCoeficientes(){
		return coeficientes;
	}
	
	public void setLimite(int limiteEntrada){
		limite = limiteEntrada;
	}
	
	public int getLimite(){
		return limite;
		
	}
	
	public void setDelta(double deltaEntrada){
		delta = deltaEntrada;
	}
	public double getDelta(){
		return delta;
	}
	
	public void setTotalIterations( int iterationsEntrada){
		totalIterations= iterationsEntrada;
	}
	
	public int getTotalIterations() {
		return totalIterations;
	}
	public double getOrigen(){
		
		return origen;
	}
	public void setOrigen(double origenEntrada ){
		origen = origenEntrada;
	}
	public double [] getFases(){
		return fases;
	}
	public void setFases(double fasesEntrada[]){
		fases = fasesEntrada;
	}
	public double getPeriodo(){
		return periodo;
	}
	public void setPeriodo(double periodoEntrada){
		periodo = periodoEntrada;
	}
	
}
