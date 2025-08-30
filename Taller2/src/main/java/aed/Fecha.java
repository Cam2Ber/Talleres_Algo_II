package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    public String toString() {
        String fecha = this.dia + "/" +this.mes;
        return fecha;
    }

    @Override
    public boolean equals(Object otra) {
        if (otra == null) {
            return false;
        }
        if (otra.getClass() != this.getClass()) {
            return false;
        }
        Fecha otraFecha = (Fecha) otra;
        if (this.dia == otraFecha.dia && this.mes == otraFecha.mes) {
            return true;
        } 
        return false;
    }

    public void incrementarDia() {
        if (this.dia+1 <= diasEnMes(this.mes)) {
            this.dia = this.dia+1;
        }
        else {
            this.dia = 1;
            this.mes = (this.mes+1)%12;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
