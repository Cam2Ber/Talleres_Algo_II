package aed;

class ArregloRedimensionableDeRecordatorios {
    private int tamanio_actual;
    private Recordatorio[] arreglo_recordatorio;

    public ArregloRedimensionableDeRecordatorios() {
        this.tamanio_actual = 0;
        this.arreglo_recordatorio = new Recordatorio[0];
    }

    public int longitud() {
        return this.tamanio_actual;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevo_arreglo = new Recordatorio[this.tamanio_actual+1];
        for (int indice = 0; indice<this.tamanio_actual; indice++) {
            nuevo_arreglo[indice] = this.arreglo_recordatorio[indice];
        }
        nuevo_arreglo[tamanio_actual] = i;
        this.tamanio_actual++;
        this.arreglo_recordatorio = nuevo_arreglo;
    }

    public Recordatorio obtener(int i) {
        if (i>=tamanio_actual) {
            return null;
        }
        Recordatorio recordatorio_obtenido = new Recordatorio(this.arreglo_recordatorio[i].mensaje(), this.arreglo_recordatorio[i].fecha(), this.arreglo_recordatorio[i].horario());
        return recordatorio_obtenido;
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
        if (indice<this.tamanio_actual) {
            return;
        }
        this.arreglo_recordatorio[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.tamanio_actual = vector.tamanio_actual;
        this.arreglo_recordatorio = new Recordatorio[vector.tamanio_actual];
        for (int indice = 0; indice<vector.tamanio_actual; indice++) {
            this.arreglo_recordatorio[indice] = new Recordatorio(vector.arreglo_recordatorio[indice].mensaje(), new Fecha(vector.arreglo_recordatorio[indice].fecha().dia(), vector.arreglo_recordatorio[indice].fecha().mes()), new Horario(vector.arreglo_recordatorio[indice].horario().hora(), vector.arreglo_recordatorio[indice].horario().minutos()));
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
