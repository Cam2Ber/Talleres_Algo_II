package aed;

public class Agenda {
    private Fecha dia_de_hoy;
    private ArregloRedimensionableDeRecordatorios recordatorios;

    public Agenda(Fecha fechaActual) {
        this.dia_de_hoy = fechaActual;
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String mensaje_final = this.dia_de_hoy + "\n=====\n";
        for (int indice = 0; indice<this.recordatorios.longitud(); indice++) {
            Recordatorio recordatorio_en_indice = this.recordatorios.obtener(indice);
            if (recordatorio_en_indice.fecha().equals(this.dia_de_hoy)) {
                mensaje_final = mensaje_final+recordatorio_en_indice.toString()+"\n";
            }
        }
        return mensaje_final;
    }

    public void incrementarDia() {
        this.dia_de_hoy.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha hoy_copia = new Fecha(this.dia_de_hoy);
        return hoy_copia;
    }

}
