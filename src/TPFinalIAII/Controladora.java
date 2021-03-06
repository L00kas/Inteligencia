/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TPFinalIAII;

import TPFinalIAII.interfaz.ventanaPrincipal;
import java.util.ArrayList;

/**
 *
 * @author Dei Castelli - Nudelman - Witzke
 */
public class Controladora {
    
    public static void main(String[] args) {
        ventanaPrincipal unaVentana = new ventanaPrincipal();
        unaVentana.setVisible(true);
    }
    
    public static void comenzarAlgoritmo(String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, double lambda) {

        //Calcular porcentajes de Seleccion/Cruza/Mutacion
        porcentajeSeleccion = (porcentajeSeleccion * cantIndividuos) / 100;
        porcentajeCruza = (porcentajeCruza * cantIndividuos) / 100;
        porcentajeMutacion = (porcentajeMutacion * cantIndividuos) / 100;      

        //Generar las restricciones para la operacion
        ArrayList<ArrayList<Integer>> restricciones = obtenerRestricciones(operacion);
        
        //Calcular la peor aptitud
        int maximaAptitud = obtenerMaximaAptitud(restricciones);
        AlgoritmoGenetico hilo1 = new AlgoritmoGenetico(maximaAptitud, operacion, cantIndividuos, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, lambda, restricciones);
        hilo1.start();
        //Generar hilo
//        AlgoritmoGenetico hilo1 = new AlgoritmoGenetico(maximaAptitud, operacion, cantIndividuos, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, lambda, restricciones);
//        AlgoritmoGenetico hilo2 = new AlgoritmoGenetico(maximaAptitud, operacion, cantIndividuos, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, lambda, restricciones);
//        AlgoritmoGenetico hilo3 = new AlgoritmoGenetico(maximaAptitud, operacion, cantIndividuos, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, lambda, restricciones);
//        
//        //Ejecutar los hilos
//        hilo1.start();
//        hilo2.start();
//        hilo3.start();
//        
//        
//        while ((hilo1.isAlive()) && (hilo2.isAlive()) && (hilo3.isAlive())){   
//        }
//        
//        if (hilo1.isAlive()){
//            hilo1.stop();
//        }
//        if (hilo2.isAlive()){
//            hilo2.stop();
//        }
//        if (hilo3.isAlive()){
//            hilo3.stop();
//        }
    }
    
    //Genera las restricciones dada la operacion
    public static ArrayList<ArrayList<Integer>> obtenerRestricciones(String operacion) {
        ArrayList<ArrayList<Integer>> restricciones = new ArrayList<>();
        ArrayList<Integer> posiciones;
        boolean existeRestriccion = false, bandera = false;

        for (int i = 0; i < operacion.length(); i++) {
            if (bandera) {
                //verifica que no existra la restriccion ya creada
                for (int j = 0; j < restricciones.size(); j++) {
                    if (restricciones.get(j).contains(i)) {
                        existeRestriccion = true; //quiere decir que existe ya la restriccion
                        j = restricciones.size();
                    }
                }
                //Si no existe paso a crearla
                if (existeRestriccion == false) {
                    posiciones = new ArrayList<Integer>();
                    for (int k = 0; k < operacion.length(); k++) {//Recorre toda la operacion buscando igualdad con el caracter tomado
                        if (operacion.charAt(k) == operacion.charAt(i)) {
                            posiciones.add(k);
                        }
                    }
                    restricciones.add(posiciones);
                }
                existeRestriccion = false;
            }
            if (operacion.charAt(i) == '=') {
                bandera = true;
            }
        }
        return restricciones;
    }
    
    //calcula la cantidad de restricciones que es igual a la peor aptitud del peor individuo
    private static int obtenerMaximaAptitud(ArrayList<ArrayList<Integer>> restricciones) {
        int maximaAptitud = 0;
        for (int i = 0; i < restricciones.size(); i++) {
            maximaAptitud += restricciones.get(i).size();
        }
        return maximaAptitud;
    }
}
