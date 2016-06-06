import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.System.exit;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deserializador {
      
    Object clase_deserializada = null;

    public Deserializador(String archivo_deserializar){
        try{
            FileInputStream flujoEntrada = new FileInputStream(archivo_deserializar);
            ObjectInputStream entrada = new ObjectInputStream(flujoEntrada);
            clase_deserializada = entrada.readObject();


                    System.out.println("***************** <DESERIALIZACIÓN> *******************");
                    
                    if (clase_deserializada == null){
                        System.err.println("(!) Error --> leído null");
                        exit(0);
                    } else {
                        System.out.println("[*]Se ha leído una clase");
                    }
                    System.out.println("\tNombre de la clase:" + clase_deserializada.getClass().getName());
                    int i = 0;
                    for (Method metodo : clase_deserializada.getClass().getDeclaredMethods()){
                        i++;
                        System.out.println("\t - Método " + i + " --> " + metodo.toGenericString());
                    }

                    System.out.println("***************** </DESERIALIZACIÓN> *******************\n\n\n");

            entrada.close();
            flujoEntrada.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Deserializador.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Object getObject(){
        return clase_deserializada;
    }
}