package co.edu.uniquindio.poo;

import java.util.ArrayList;

public class Reserva {
    private int codigoReserva;
    private String fechaReserva;
    private int numeroNoches;
    private int cantidadHuespedes;
    private String estado;          // Pendiente, confirmada o finalizada
    private String metodoPago;      // Efectivo, tarjeta o transferencia
    private double valorTotal;

    private Huesped ownedByHuesped;
    private ArrayList<Habitacion> habitaciones;

    public Reserva(int codigoReserva, String fechaReserva, int numeroNoches, int cantidadHuespedes,
                   String estado, String metodoPago, Huesped ownedByHuesped) {
        this.codigoReserva = codigoReserva;
        this.fechaReserva = fechaReserva;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.ownedByHuesped = ownedByHuesped;
        this.habitaciones = new ArrayList<>();
        this.valorTotal = 0;
    }

    public int getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(int codigoReserva) { this.codigoReserva = codigoReserva; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public int getNumeroNoches() { return numeroNoches; }
    public void setNumeroNoches(int numeroNoches) { this.numeroNoches = numeroNoches; }

    public int getCantidadHuespedes() { return cantidadHuespedes; }
    public void setCantidadHuespedes(int cantidadHuespedes) { this.cantidadHuespedes = cantidadHuespedes; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public Huesped getOwnedByHuesped() { return ownedByHuesped; }
    public void setOwnedByHuesped(Huesped ownedByHuesped) { this.ownedByHuesped = ownedByHuesped; }

    public ArrayList<Habitacion> getHabitaciones() { return habitaciones; }
    public void setHabitaciones(ArrayList<Habitacion> habitaciones) { this.habitaciones = habitaciones; }

    public String agregarHabitacion(Habitacion h) {
        if (h.getEstado().equals("Ocupada") || h.getEstado().equals("Reservada")) {
            return "La habitación " + h.getNumero() + " no está disponible";
        }
        habitaciones.add(h);
        calcularValorTotal();
        if (estado.equals("Confirmada")) {
            h.setEstado("Reservada");
        }
        return "Habitación agregada con éxito";
    }

    public void confirmar() {
        this.estado = "Confirmada";
        for (Habitacion h : habitaciones) {
            h.setEstado("Reservada");
        }
    }

    private void calcularValorTotal() {
        double total = 0;
        for (Habitacion h : habitaciones) {
            total += h.getPrecioPorNoche() * numeroNoches;
        }
        this.valorTotal = total;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigoReserva=" + codigoReserva +
                ", fechaReserva='" + fechaReserva + '\'' +
                ", numeroNoches=" + numeroNoches +
                ", cantidadHuespedes=" + cantidadHuespedes +
                ", estado='" + estado + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", valorTotal=" + valorTotal +
                ", habitaciones=" + habitaciones +
                '}';
    }
}