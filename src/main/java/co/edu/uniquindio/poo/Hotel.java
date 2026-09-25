package co.edu.uniquindio.poo;

import javax.swing.*;
import java.util.ArrayList;

public class Hotel {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;

    private ArrayList<Huesped> listaHuespedes;
    private Habitacion[] habitaciones;
    private int cantidadHabitaciones;
    private Reserva[] reservas;
    private int cantidadReservas;
    private String[][] matrizOcupacion;

    private static String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    private static String[] tiposValidos = {"Individual", "Doble", "Suite"};
    private static String[] estadosHabitacionValidos = {"Disponible", "Reservada", "Ocupada", "Mantenimiento"};
    private static String[] metodosPagoValidos = {"Efectivo", "Tarjeta", "Transferencia"};

    public Hotel(String nombreComercial, String nit, String direccion, String telefono, int capacidadHabitaciones) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaHuespedes = new ArrayList<>();
        this.habitaciones = new Habitacion[capacidadHabitaciones];
        this.cantidadHabitaciones = 0;
        this.reservas = new Reserva[100];
        this.cantidadReservas = 0;
        this.matrizOcupacion = new String[capacidadHabitaciones][7];
    }
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public ArrayList<Huesped> getListaHuespedes() { return listaHuespedes; }
    public void setListaHuespedes(ArrayList<Huesped> listaHuespedes) { this.listaHuespedes = listaHuespedes; }

    public Habitacion[] getHabitaciones() { return habitaciones; }
    public void setHabitaciones(Habitacion[] habitaciones) { this.habitaciones = habitaciones; }

    public Reserva[] getReservas() { return reservas; }
    public void setReservas(Reserva[] reservas) { this.reservas = reservas; }

    public String[][] getMatrizOcupacion() { return matrizOcupacion; }
    public void setMatrizOcupacion(String[][] matrizOcupacion) { this.matrizOcupacion = matrizOcupacion; }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaHuespedes=" + listaHuespedes +
                '}';
    }

    private boolean estaEnLista(String valor, String[] listaValida) {
        for (String v : listaValida) {
            if (v.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    public String registrarHuesped(String documento, String nombreCompleto, byte edad,
                                   String telefono, String ciudad) {
        if (buscarHuespedPorDocumento(documento) != null) {
            return "Error: ya existe un huésped registrado con ese documento";
        }
        Huesped nuevo = new Huesped(documento, nombreCompleto, edad, telefono, ciudad, this);
        listaHuespedes.add(nuevo);
        return "Huésped registrado con éxito";
    }

    public Huesped buscarHuespedPorDocumento(String documento) {
        for (Huesped h : listaHuespedes) {
            if (h.getDocumento().equals(documento)) {
                return h;
            }
        }
        return null;
    }

    public Huesped buscarHuespedPorTelefono(String telefono) {
        for (Huesped h : listaHuespedes) {
            if (h.getTelefono().equals(telefono)) {
                return h;
            }
        }
        return null;
    }

    public String registrarHabitacion(int numero, String tipo, int piso, int capacidadMaxima,
                                      double precioPorNoche, String estado) {
        if (!estaEnLista(tipo, tiposValidos)) {
            return "El tipo de habitación debe ser Individual, Doble o Suite";
        }
        if (!estaEnLista(estado, estadosHabitacionValidos)) {
            return "El estado debe ser Disponible, Reservada, Ocupada o Mantenimiento";
        }
        if (cantidadHabitaciones >= habitaciones.length) {
            return "El hotel ya alcanzó su capacidad máxima de habitaciones";
        }
        if (buscarHabitacionPorNumero(numero) != null) {
            return "Ya existe una habitación con ese número";
        }
        Habitacion nueva = new Habitacion(numero, tipo, piso, capacidadMaxima, precioPorNoche, estado);
        habitaciones[cantidadHabitaciones] = nueva;
        cantidadHabitaciones++;
        return "Habitación registrada con éxito";
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (int i = 0; i < cantidadHabitaciones; i++) {
            if (habitaciones[i].getNumero() == numero) {
                return habitaciones[i];
            }
        }
        return null;
    }

    public String registrarReserva(int codigoReserva, String fecha, int numeroNoches, int cantidadHuespedes,
                                   String metodoPago, String documentoHuesped, int[] numerosHabitaciones) {
        if (!estaEnLista(metodoPago, metodosPagoValidos)) {
            return "El método de pago debe ser Efectivo, Tarjeta o Transferencia";
        }
        if (cantidadReservas >= reservas.length) {
            return "Se alcanzó el máximo de reservas permitidas";
        }
        Huesped huesped = buscarHuespedPorDocumento(documentoHuesped);
        if (huesped == null) {
            return "No existe un huésped con ese documento";
        }

        Reserva nueva = new Reserva(codigoReserva, fecha, numeroNoches, cantidadHuespedes,
                "Pendiente", metodoPago, huesped);

        for (int numeroHab : numerosHabitaciones) {
            Habitacion hab = buscarHabitacionPorNumero(numeroHab);
            if (hab == null) {
                return "No existe la habitación " + numeroHab;
            }
            String mensajeHab = nueva.agregarHabitacion(hab);
            if (mensajeHab.startsWith("Error")) {
                return mensajeHab;
            }
        }
        nueva.confirmar();
        reservas[cantidadReservas] = nueva;
        cantidadReservas++;
        huesped.agregarReserva(nueva);

        return "Reserva registrada con éxito. Valor total: $" + nueva.getValorTotal();
    }

    public String consultarHuespedPorTelefono(String telefono) {
        Huesped h = buscarHuespedPorTelefono(telefono);
        if (h == null) {
            return "No existe ningún huésped con el teléfono " + telefono;
        }

        String mensaje = "";
        mensaje = mensaje + "Nombre: " + h.getNombreCompleto() + "\n";
        mensaje = mensaje + "Documento: " + h.getDocumento() + "\n";
        mensaje = mensaje + "Ciudad: " + h.getCiudad() + "\n";
        mensaje = mensaje + "Reservas realizadas:\n";

        if (h.getCantidadReservas() == 0) {
            mensaje = mensaje + " sin reservas\n";
        } else {
            for (int i = 0; i < h.getCantidadReservas(); i++) {
                Reserva r = h.getListaReservas()[i];
                mensaje = mensaje + "  - Código " + r.getCodigoReserva()
                        + " | Fecha: " + r.getFechaReserva()
                        + " | Total: $" + r.getValorTotal() + "\n";
            }
        }
        return mensaje;
    }

    public String controlDisponibilidad() {
        int disponibles = 0, ocupadas = 0, mantenimiento = 0;
        Habitacion masCara = null;
        Habitacion masBarata = null;

        for (int i = 0; i < cantidadHabitaciones; i++) {
            Habitacion h = habitaciones[i];

            if (h.getEstado().equals("Disponible")) {
                disponibles++;
            } else if (h.getEstado().equals("Ocupada")) {
                ocupadas++;
            } else if (h.getEstado().equals("Mantenimiento")) {
                mantenimiento++;
            }
            if (masCara == null || h.getPrecioPorNoche() > masCara.getPrecioPorNoche()) {
                masCara = h;
            }
            if (masBarata == null || h.getPrecioPorNoche() < masBarata.getPrecioPorNoche()) {
                masBarata = h;
            }
        }

        String mensaje = "";
        mensaje = mensaje + "Disponibles: " + disponibles + "\n";
        mensaje = mensaje + "Ocupadas: " + ocupadas + "\n";
        mensaje = mensaje + "En mantenimiento: " + mantenimiento + "\n";
        if (masCara != null) {
            mensaje = mensaje + "Habitación más cara: " + masCara.getNumero()
                    + " ($" + masCara.getPrecioPorNoche() + ")\n";
        }
        if (masBarata != null) {
            mensaje = mensaje + "Habitación más barata: " + masBarata.getNumero()
                    + " ($" + masBarata.getPrecioPorNoche() + ")\n";
        }
        return mensaje;
    }

    public void generarMatrizOcupacion() {
        for (int i = 0; i < cantidadHabitaciones; i++) {
            for (int j = 0; j < 7; j++) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "¿La habitación " + habitaciones[i].getNumero() + " está OCUPADA el día " + dias[j] + "?",
                        "Matriz de ocupación", JOptionPane.YES_NO_OPTION);
                matrizOcupacion[i][j] = (respuesta == JOptionPane.YES_OPTION) ? "O" : "D";
            }
        }
    }

    public String analizarMatrizOcupacion() {
        int[] ocupadasPorDia = new int[7];
        int totalOcupadasSemana = 0;

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < cantidadHabitaciones; i++) {
                if (matrizOcupacion[i][j] != null && matrizOcupacion[i][j].equals("O")) {
                    ocupadasPorDia[j]++;
                    totalOcupadasSemana++;
                }
            }
        }

        int diaMayor = 0, diaMenor = 0;
        for (int j = 1; j < 7; j++) {
            if (ocupadasPorDia[j] > ocupadasPorDia[diaMayor]) diaMayor = j;
            if (ocupadasPorDia[j] < ocupadasPorDia[diaMenor]) diaMenor = j;
        }

        String mensaje = "";
        for (int j = 0; j < 7; j++) {
            mensaje = mensaje + dias[j] + ": " + ocupadasPorDia[j] + " habitación(es) ocupada(s)\n";
        }
        mensaje = mensaje + "Día con mayor ocupación: " + dias[diaMayor] + "\n";
        mensaje = mensaje + "Día con menor ocupación: " + dias[diaMenor] + "\n";
        mensaje = mensaje + "Total de habitaciones ocupadas en la semana: " + totalOcupadasSemana;
        return mensaje;
    }

    public static boolean esCapicua(int numero) {
        String original = String.valueOf(numero);
        String invertido = new StringBuilder(original).reverse().toString();
        return original.equals(invertido);
    }
    public String listarReservasEspeciales() {
        String mensaje = "";
        boolean hayEspeciales = false;
        for (int i = 0; i < cantidadReservas; i++) {
            Reserva r = reservas[i];
            if (esCapicua(r.getCodigoReserva())) {
                mensaje = mensaje + "Reserva especial: código " + r.getCodigoReserva() + "\n";
                hayEspeciales = true;
            }
        }
        if (!hayEspeciales) {
            mensaje = mensaje + "No hay reservas con código capicúa.";
        }
        return mensaje;
    }

    public String calcularIngresosPorFecha(String fecha) {
        double ingresoTotal = 0;
        for (int i = 0; i < cantidadReservas; i++) {
            Reserva r = reservas[i];
            if (r.getFechaReserva().equals(fecha)) {
                ingresoTotal += r.getValorTotal();
            }
        }
        return "Ingreso total del " + fecha + ": $" + ingresoTotal;
    }
}
