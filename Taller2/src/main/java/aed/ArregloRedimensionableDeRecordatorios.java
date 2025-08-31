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

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(this);
        return copia;
    }
}
