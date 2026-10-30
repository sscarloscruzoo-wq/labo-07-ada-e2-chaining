package ejercicio2_chaining_csv;


public class TablaHashChaining {
    private final NodoURL[] tabla;
    private int cantidad;
    private int colisiones;

    public TablaHashChaining(int capacidad) {
        
        if(capacidad<=0 ) throw new IllegalArgumentException("capacidad invalida");
        tabla = new NodoURL[capacidad];
    }

    private int indice(String codigo) {
        // TODO 1: Transformar codigo -> hash -> bucket.
        return Math.floorMod(codigo.hashCode(), tabla.length);
    }

    public void insertar(RegistroURL registro) {
        // TODO 2: Calcular bucket.
        // TODO 3: Detectar si existe colisión.
        // TODO 4: Insertar un NodoURL en la cadena de ESE MISMO bucket.
        // TODO 5: Actualizar cantidad.
        int pos  = indice (registro.codigo());
        if(tabla[pos] != null)colisiones++;
        tabla[pos] = new NodoURL(registro, tabla[pos]);
        cantidad++;
    }

    public Resultado buscar(String codigo) {
        // TODO 6: Ir directamente al bucket correspondiente.
        // TODO 7: Recorrer SOLO su cadena.
        // TODO 8: Contar comparaciones.
        int pos = indice(codigo);
        int comparaciones=0;
        for(NodoURL a = tabla[pos]; a!=null ;a = a.siguiente){
            comparaciones++;
            if(a.dato.codigo().equals(codigo)){
                return new Resultado(a.dato, pos, comparaciones);
            }
        }
        return new Resultado(null, pos, comparaciones);
    }

    public int cadenaMaxima() {
        // TODO 9: Recorrer los buckets y encontrar la cadena más larga.
        int max = 0;
      
        for(NodoURL inicio : tabla){
            int n = 0;
            
            for(NodoURL a = inicio; a != null ; a = a.siguiente)n++;
            max = Math.max(max, n);
        }
        return max;
    }

    public double factorCarga() {return (double)cantidad / tabla.length;}
    public int getColisiones(){return colisiones;}
        // TODO 10: alpha = n / m.

    



    public void imprimir() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(i + " -> ");
            for (NodoURL a = tabla[i]; a != null; a = a.siguiente) {
                System.out.print("[" + a.dato.codigo() + "] -> ");
            }
            System.out.println("null");
        }
    }

    public record Resultado(RegistroURL registro, int bucket, int comparaciones) {}
}
