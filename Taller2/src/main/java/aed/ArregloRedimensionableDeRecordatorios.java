package aed;

class ArregloRedimensionableDeRecordatorios {
    private int tamanio_actual;
    private Recordatorio[] arreglo_recordatorio;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
    }

    public int longitud() {
        return this.tamanio_actual;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevo_arreglo = new Recordatorio[this.tamanio_actual+1];
        for (int indice = 0; indice<this.tamanio_actual; indice++) {
            nuevo_arreglo[indice] = this.arreglo_recordatorio[indice];
        }
        nuevo_arreglo[tamanio_actual+1] = i;
        this.tamanio_actual++;
        this.arreglo_recordatorio = nuevo_arreglo;
    }

    public Recordatorio obtener(int i) {
        if (i>=tamanio_actual) {
            return null;
        }
        Recordatorio rec_obtenido = new Recordatorio(this.arreglo_recordatorio[i].mensaje(),this.arreglo_recordatorio[i].fecha(),this.arreglo_recordatorio[i].horario());
        return rec_obtenido;
    }

    public void quitarAtras() {
        Recordatorio[] nuevo_arreglo = new Recordatorio[this.tamanio_actual-1];
        for (int indice = 0; indice<tamanio_actual-1; indice++) {
            nuevo_arreglo[indice] = this.arreglo_recordatorio[indice];
        }
        this.tamanio_actual = this.tamanio_actual-1;
        this.arreglo_recordatorio = nuevo_arreglo;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
