import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.*;


public class TrabajoDSD {

    public static void main(String[] args) {


    	if(args.length != 2){
    		System.out.println("(!) Parámetros incorrectos ./TrabajoDSD archivo_class dni");
    		System.exit(-1);
    	}

    	String archivo_class = args[0];
    	String dni = args[1];

        Deserializador deserializador = new Deserializador(archivo_class);
        ClaseASerializar objeto_deserializado = (ClaseASerializar)deserializador.getObject();


        String ruta = objeto_deserializado.obtenerDirecciónClase();
        String nombre = objeto_deserializado.obtenerNombreClase();
        Object instancia = new Object();

        try{
        	URL url = new URL(ruta);
        	URLClassLoader url_class = new URLClassLoader(new URL[]{url});
        	Class<?> clase_instanciada = url_class.loadClass(nombre);
        	instancia = clase_instanciada.newInstance();
        	Method metodo = instancia.getClass().getMethod("computa",String.class);

        	int result = (int)metodo.invoke(instancia, dni);

        	System.out.println("[*]El resultado de computar el dni es " + result + "\n\n\n");

    	} catch(Exception e){
        	System.out.println("(!)Error " + e.getMessage());

    	}

        try{
        	System.out.println("************ Clase a descubrir ****************");
        	System.out.println(" - Ruta de la clase a descubrir --> " + ruta);
        	System.out.println(" - Nombre de la clase a descubrir --> " + nombre);
        	System.out.println(" - Métodos:");

        	int i = 0;
            for (Method metodo : instancia.getClass().getDeclaredMethods()){
            	i++;
                System.out.println("\t - Método " + i + " --> " + metodo.toGenericString());
            }

    	} catch(Exception e){
        	System.out.println("(!)Error " + e.getMessage());

    	}



    }
    
}
