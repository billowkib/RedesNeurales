#!/bin/sh
#Script que ejecuta el programa en Java que calcula la regresion
#polinomial.
DELTA=.1 #Numero default para el delta del programa
ITERACIONES=100 #Numero default para iteraciones del programa
ORIGEN=0 #Numero default para origen de la grafica
if [ -n "$1" ]
#Probar si existe el primer parametro del script
then 
    delta=$1
else 
    delta=$DELTA #Valor Default si no se especifico nada
fi
if [ -n "$2" ]
then 
    iteraciones=$2
else
    iteraciones=$ITERACIONES
fi
if [ -n "$3" ]
then 
    origen=$3
else 
    origen=$ORIGEN
fi
java -classpath /home/billowkib/Development/Projects/RedesNeuronales/RedesNeurales/bin/ com.redesneurales.regresion.Polinomial.RegresionPolinomial $delta $iteraciones $origen
cd /home/billowkib/Development
gnuplot Regresionscript.gp
eog -f resultado.png
exit 0