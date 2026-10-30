package ejercicio2_chaining_csv;


class NodoURL {
    RegistroURL dato;
    NodoURL siguiente;

    NodoURL(RegistroURL dato, NodoURL siguiente) {
        
        this.dato = dato;
        this.siguiente = siguiente;
      
    }
}
