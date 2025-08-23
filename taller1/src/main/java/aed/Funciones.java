package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        int res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        double dist = x*x+y*y;
        dist = Math.sqrt(dist);
        return dist;
    }

    boolean esPar(int n) {
        int res_div = n/2;
        boolean paridad = (res_div*2==n);
        return paridad;
    }

    boolean esBisiesto(int n) {
        boolean div_4 = ((n/4)*4 == n);
        boolean div_100 = ((n/100)*100 == n);
        boolean div_400 = ((n/400)*400 == n);
        return ((div_4 && !div_100) || div_400);
    }

    boolean esBisiesto_alt(int n) {
        boolean div_4 = ((n/4)*4 == n);
        if (div_4) {
            boolean div_100 = ((n/100)*100 == n);
            if (!div_100){
                return true;
            }
            boolean div_400 = ((n/400)*400 == n);
            if (div_400){
                return true;
            }
        }
        return false;
    }

    int factorialIterativo(int n) {
        int res = 1;
        int contador = 1;
        while (contador < n) {
            res = res*(contador+1);
            contador++;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        if (n==0){
            return 1;
        }
        return n*factorialRecursivo(n-1);
    }

    boolean esPrimo(int n) {
        int punto_medio = n/2+1;
        int iterador = 2;
        boolean Primo = (n>1);
        while (Primo && iterador<punto_medio){
            int division = n/iterador;
            boolean es_divisible = (division*iterador==n);
            if (es_divisible){
                Primo = false;
            }
            iterador++;
        }
        return Primo;
    }

    int sumatoria(int[] numeros) {
        int sum = 0;
        for (int elem:numeros){
            sum = sum+elem;
        }
        return sum;
    }

    int busqueda(int[] numeros, int buscado) {
        boolean encontrado = false;
        int indice = 0;
        while (indice<numeros.length && !encontrado){
            encontrado = numeros[indice] == buscado;
            indice++;
        }
        return (indice-1);
    }

    boolean tienePrimo(int[] numeros) {
        boolean contiene_primo = false;
        int indice = 0;
        while (!contiene_primo && indice<numeros.length){
            contiene_primo = esPrimo(numeros[indice]);
            indice++;
        }
        return contiene_primo;
    }

    boolean todosPares(int[] numeros) {
        boolean tP = true;
        int indice = 0;
        while (tP && indice<numeros.length){
            tP = esPar(numeros[indice]);
            indice++;
        }
        return tP;
    }

    boolean esPrefijo(String s1, String s2) {
        int longitud = s1.length();
        boolean pre = longitud<=s2.length(); //Si s1 es mas largo que s2, entonces que s1 sea un prefijo de s2 ya es imposible
        int indice = 0;
        while (pre && indice<longitud){
            pre = (s1.charAt(indice)==s2.charAt(indice));
            indice++;
        }
        return pre;
    }

    boolean esSufijo(String s1, String s2) {
        int indice_inverso = 0;
        boolean post = s1.length()<=s2.length(); //Mismo razonamiento que antes
        while (post && indice_inverso<s1.length()){
            post = (s1.charAt(s1.length()-1-indice_inverso)==s2.charAt(s2.length()-1-indice_inverso));
            indice_inverso++;
        }
        return post;
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b); //Faltaban parentesis al principio (fix: agregar parentesis a a||b)
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;
        if (xs.length != ys.length){ //Seria mejor un While, pero eso implicaria reescribir la función (fix: if comprueba longitud)
            res = false;
        }
        else{
            for (int i = 0; i < xs.length; i++) {
                if (xs[i] != ys[i]) {
                    res = false;
                }
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        for (int i = 0; i < xs.length-1; i++) { //Se estaba fijando el elemento siguiente sin comprobar si habia un elemento siguiente (fix:-1)
            if (xs[i] > xs [i+1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0]; //Inicializar res en 0 asume que xs no es 100% negativos (fix: 'res=0' a 'res=xs[0]')
        for (int i = 0; i < xs.length; i++) { //Hay un = de mas (fix:remover el igual)
            if (xs[i] > res) res = xs[i]; //Cambiaba res por el indice, no por el valor (fix: de '= i' a '= xs[i]')
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true; //Un arreglo vacio tambien tiene todos elementos positivos (fix: 'res = false' a 'res = true')
        for (int x : xs) {
            if (x <= 0) { //No hay razón para cambiar res a true una vez se descubre que es false (fix: cambiar if de x>0 a x<=0, remover posibilidad de reasignar true a res)
                res = false;
            }
        }
        return res;
    }

}
