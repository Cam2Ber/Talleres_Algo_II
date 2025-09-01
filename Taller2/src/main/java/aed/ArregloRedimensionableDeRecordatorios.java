package aed;

class ArregloRedimensionableDeRecordatorios {
    private int longitud;
    private Recordatorio[] arreglo_Recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.longitud = 0;
        this.arreglo_Recordatorios = new Recordatorio[0];
    }

    public int longitud() {
        return this.longitud;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevo_arreglo = new Recordatorio[this.longitud+1];
        for (int indice = 0;indice<this.longitud;indice++) {
            nuevo_arreglo[indice] = this.arreglo_Recordatorios[indice];
        }
        nuevo_arreglo[this.longitud] = i;
        longitud++;
        this.arreglo_Recordatorios = nuevo_arreglo;
    }

    public Recordatorio obtener(int i) {
        if (i>=longitud) {
            return null;
        }
        Recordatorio recordatorio_en_i = new Recordatorio(this.arreglo_Recordatorios[i].mensaje(),this.arreglo_Recordatorios[i].fecha(),this.arreglo_Recordatorios[i].horario());
        return recordatorio_en_i;
    }

    public void quitarAtras() {
        Recordatorio[] nuevo_arreglo = new Recordatorio[this.longitud-1];
        for (int indice=0; indice<this.longitud-1;indice++) {
            nuevo_arreglo[indice] = this.arreglo_Recordatorios[indice];
        }
        longitud--;
        this.arreglo_Recordatorios = nuevo_arreglo;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        if (indice >= this.longitud) {
            return;
        }
        this.arreglo_Recordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.longitud = vector.longitud;
        this.arreglo_Recordatorios = new Recordatorio[vector.longitud];
        for (int indice = 0; indice<vector.longitud; indice++) {
            String mensaje_copia = new String(vector.arreglo_Recordatorios[indice].mensaje());
            Fecha fecha_copia = new Fecha(vector.arreglo_Recordatorios[indice].fecha());
            Horario horario_copia = new Horario(vector.arreglo_Recordatorios[indice].horario().hora(),vector.arreglo_Recordatorios[indice].horario().minutos());
            this.arreglo_Recordatorios[indice] = new Recordatorio(mensaje_copia,fecha_copia,horario_copia);
        }
    }
    //En total, se realizan una (1) reserva para el arreglo de recordatorios del mismo tamaño que vector, y luego, por cada elemento en vector (lo cual es equivalente a su longitud, de ahora en adelante referido como L) se realizan cuatro (4) reservas:
    //Una para el mensaje, una para la fecha, otra para el horario, y una última para el Recordatorio en sí. En total se hacen 4*L+1, lo cual representa un crecimiento lineal del uso de la memoria según el tamaño del vector.

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(this);
        return copia;
    }
    //Continuando con lo escrito sobre el constructor por copia, este método solamente agrega una (1) nueva reserva, para crear la variable copia. El total es 4*L+2, haciendo de la relación entre el uso de memoria y el tamaño del vector una relación linear.
}
