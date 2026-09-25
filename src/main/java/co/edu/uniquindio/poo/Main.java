package co.edu.uniquindio.poo;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Bienvenidos al sistema de gestión del hotel StayPlus");
        String nombreComercial = JOptionPane.showInputDialog(null, "Nombre comercial del hotel");
        String nit = JOptionPane.showInputDialog(null, "NIT del hotel");
        String direccion = JOptionPane.showInputDialog(null, "Dirección del hotel");
        String telefonoHotel = JOptionPane.showInputDialog(null, "Teléfono del hotel");
        int capacidadHabitaciones = Integer.parseInt(
                JOptionPane.showInputDialog(null, "Número máximo de habitaciones del hotel"));

        Hotel hotel = new Hotel(nombreComercial, nit, direccion, telefonoHotel, capacidadHabitaciones);

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Por favor seleccione una opción:\n--- Menú ---\n" +
                            "1. Registrar huésped\n" +
                            "2. Registrar habitación\n" +
                            "3. Registrar reserva\n" +
                            "4. Consultar huésped por teléfono\n" +
                            "5. Control de disponibilidad de habitaciones\n" +
                            "6. Generar matriz de ocupación semanal\n" +
                            "7. Analizar matriz de ocupación\n" +
                            "8. Ver reservas especiales\n" +
                            "9. Consultar ingresos por fecha\n" +
                            "0. Salir"));

            switch (opcion) {
                case 1:
                    crearHuesped(hotel);
                    break;
                case 2:
                    crearHabitacion(hotel);
                    break;
                case 3:
                    crearReserva(hotel);
                    break;
                case 4:
                    consultarHuesped(hotel);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, hotel.controlDisponibilidad());
                    break;
                case 6:
                    hotel.generarMatrizOcupacion();
                    JOptionPane.showMessageDialog(null, "Matriz de ocupación generada con éxito");
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, hotel.analizarMatrizOcupacion());
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, hotel.listarReservasEspeciales());
                    break;
                case 9:
                    consultarIngresos(hotel);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Muchas gracias por usar nuestro sistema");
                    break;
                }

        } while (opcion != 0);
    }

    private static void crearHuesped(Hotel hotel) {
        String documento = JOptionPane.showInputDialog(null, "Documento del huésped");
        String nombreCompleto = JOptionPane.showInputDialog(null, "Nombre completo del huésped");
        String edadTexto = JOptionPane.showInputDialog(null, "Edad del huésped");
        byte edad = Byte.parseByte(edadTexto);
        String telefono = JOptionPane.showInputDialog(null, "Teléfono del huésped");
        String ciudad = JOptionPane.showInputDialog(null, "Ciudad de procedencia del huésped");

        String resultado = hotel.registrarHuesped(documento, nombreCompleto, edad, telefono, ciudad);
        JOptionPane.showMessageDialog(null, resultado);
    }

    private static void crearHabitacion(Hotel hotel) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de la habitación"));
        String tipo = JOptionPane.showInputDialog(null, "Tipo de habitación (Individual, Doble o Suite)");
        int piso = Integer.parseInt(JOptionPane.showInputDialog(null, "Piso"));
        int capacidadMaxima = Integer.parseInt(JOptionPane.showInputDialog(null, "Capacidad máxima de personas"));
        double precioPorNoche = Double.parseDouble(JOptionPane.showInputDialog(null, "Precio por noche"));
        String estado = JOptionPane.showInputDialog(null, "Estado (Disponible, Reservada, Ocupada o Mantenimiento)");

        String resultado = hotel.registrarHabitacion(numero, tipo, piso, capacidadMaxima, precioPorNoche, estado);
        JOptionPane.showMessageDialog(null, resultado);
    }

    private static void crearReserva(Hotel hotel) {
        int codigoReserva = Integer.parseInt(JOptionPane.showInputDialog(null, "Código de la reserva"));
        String fecha = JOptionPane.showInputDialog(null, "Fecha de la reserva");
        int numeroNoches = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de noches"));
        int cantidadHuespedes = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de huéspedes"));
        String metodoPago = JOptionPane.showInputDialog(null, "Método de pago (Efectivo, Tarjeta o Transferencia)");
        String documentoHuesped = JOptionPane.showInputDialog(null, "Documento del huésped que reserva");

        String habitacionesTexto = JOptionPane.showInputDialog(null, "Números de habitación a reservar");
        String[] partes = habitacionesTexto.split(",");
        int[] numerosHabitaciones = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numerosHabitaciones[i] = Integer.parseInt(partes[i].trim());
        }

        String resultado = hotel.registrarReserva(codigoReserva, fecha, numeroNoches, cantidadHuespedes,
                metodoPago, documentoHuesped, numerosHabitaciones);
        JOptionPane.showMessageDialog(null, resultado);
    }

    private static void consultarHuesped(Hotel hotel) {
        String telefono = JOptionPane.showInputDialog(null, "Teléfono del huésped a consultar");
        JOptionPane.showMessageDialog(null, hotel.consultarHuespedPorTelefono(telefono));
    }

    private static void consultarIngresos(Hotel hotel) {
        String fecha = JOptionPane.showInputDialog(null, "Fecha a consultar");
        JOptionPane.showMessageDialog(null, hotel.calcularIngresosPorFecha(fecha));
    }
}