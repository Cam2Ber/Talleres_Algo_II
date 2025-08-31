package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = new String(mensaje);
        this.fecha = new Fecha(fecha.dia(), fecha.mes());
        this.horario = new Horario(horario.hora(),horario.minutos());
    }

    public Horario horario() {
        Horario copia_horario = new Horario(this.horario.hora(), this.horario.minutos());
        return copia_horario;
    }

    public Fecha fecha() {
        Fecha copia_fecha = new Fecha(this.fecha.dia(), this.fecha.mes());
        return copia_fecha;
    }

    public String mensaje() {
        String copia_mensaje = new String(this.mensaje);
        return copia_mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje + " @ " + this.fecha + " " + this.horario;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }
        if (otro.getClass() != this.getClass()) {
            return false;
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        boolean mismo_mensaje = (this.mensaje().equals(otroRecordatorio.mensaje()));
        boolean mismo_dia = (this.fecha().equals(otroRecordatorio.fecha()));
        boolean misma_hora = (this.horario().equals(otroRecordatorio.horario()));
        if  (mismo_mensaje && mismo_dia && misma_hora) {
            return true;
        } 
        return false;
    }

}
