package co.edu.uniquindio.poo;

import java.util.Arrays;

public class Huesped {

    private String documento;
    private String nombreCompleto;
    private byte edad;
    private String telefono;
    private String ciudad;

    private Hotel ownedByHotel;
    private Reserva[] listaReservas;
    private int cantidadReservas;

    public Huesped(String documento, String nombreCompleto, byte edad, String telefono,
                   String ciudad, Hotel ownedByHotel) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.ownedByHotel = ownedByHotel;
        this.listaReservas = new Reserva[10];
        this.cantidadReservas = 0;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public byte getEdad() { return edad; }
    public void setEdad(byte edad) { this.edad = edad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Hotel getOwnedByHotel() { return ownedByHotel; }
    public void setOwnedByHotel(Hotel ownedByHotel) { this.ownedByHotel = ownedByHotel; }

    public Reserva[] getListaReservas() { return listaReservas; }
    public void setListaReservas(Reserva[] listaReservas) { this.listaReservas = listaReservas; }

    public int getCantidadReservas() { return cantidadReservas; }

    public void agregarReserva(Reserva r) {
        listaReservas[cantidadReservas] = r;
        cantidadReservas++;
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "documento='" + documento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", listaReservas=" + Arrays.toString(listaReservas) +
                '}';
    }
}
