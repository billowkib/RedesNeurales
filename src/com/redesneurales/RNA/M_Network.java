package com.redesneurales.RNA;
 
/*Aqui vendrán metodos para asignar, modificar y actualizar datos
*que el programa usará para calcular los resultados finales
*los parametros que se usan son:
*inputs(int) = cantidad de nodos en la capa de entrada
*hidden_layers (int)= cantidad de nodos en la capa oculta
*outputs (int)= cantidad de nodos en la capa de salida
*input_weights (vector double)= ponderaciones de conexión de la capa de entrada
*hidden_weights(vector double) = ponderaciones de conexión de la capa oculta
*output_weights (vector double)= ponderaciones de conexion de la salida
*b_input (vector double)= sesgos de la capa de entrada
*b_hidden (vector double)= sesgos en la capa oculta
*b_output (vector double)= sesgos en la capa de salida	
*/
public class M_Network {
private int inputs, hidden_layers, outputs;
private double input_weights [];
private double hidden_weights [];
private double output_weights [];
private double b_input [];
private double b_hidden [];
private double b_output [];
//setters
public void setInputs(int inputs){
	this.inputs = inputs;
}
//vector_id usado para identificar que vector se modificara
public void setWVector(double vector [],int vector_id){
	if (vector_id == 1){
		this.input_weights = vector;
	}else{
		if(vector_id == 2){
			this.hidden_weights=vector;
		} else{
			if(vector_id ==3){
				this.output_weights = vector;
			}
			else{
				System.out.print("Favor de ingresar numero de id correcto\n Ningun valor fue asignado");
				return;
			}
		}
	}
}
public void setBVector(double vector [],int b_vector_id){
	if (b_vector_id == 1){
		this.b_input = vector;
	}else{
		if(b_vector_id == 2){
			this.b_hidden=vector;
		} else{
			if(b_vector_id ==3){
				this.b_output = vector;
			}
			else{
				System.out.print("Favor de ingresar numero de id correcto\n Ningun valor fue asignado");
				return;
			}
		}
	}
}
public void setInputWVector(double vector []){
	this.setWVector(vector, 1);
}
public void setHiddenWVector(double vector[]){
	this.setWVector(vector, 2);
}
public void setOutputWVector(double vector[]){
	this.setWVector(vector, 3);
}
public void setInputBVector(double vector []){
	this.setBVector(vector, 1);
}
public void setHiddenBVector(double vector[]){
	this.setBVector(vector, 2);
}
public void setOutputBVector(double vector[]){
	this.setBVector(vector, 3);
}
public void setHiddenLayers(int hidden_layers){
	this.hidden_layers = hidden_layers;
}
public void setOutputs(int outputs){
	this.outputs= outputs;
}
//getters
public int getInputs(){
	return this.inputs;
}
public int getOutputs(){
	return this.outputs;
}
public int getHiddenLayers(){
	return this.hidden_layers;
}
public double [] getInputWVector(){
	return this.input_weights;
}
public double [] getHiddenWVector(){
	return this.hidden_weights;
}
public double [] getOutputWVector(){
	return this.output_weights;
}
public double [] getInputBVector(){
	return this.b_input;
}
public double [] getHiddenBVector(){
	return this.b_hidden;
}
public double [] getOutputBVector(){
	return this.b_output;
}
//Iniciar Valores de los tres elementos con una sola funcion.
public void initRNA(int inputs, int hidden_layers, int outputs){
	this.setInputs(inputs);
	this.setHiddenLayers(hidden_layers);
	this.setOutputs(outputs);
}
}
