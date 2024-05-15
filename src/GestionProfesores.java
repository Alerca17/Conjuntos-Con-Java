import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GestionProfesores {

    GestionValidaciones validacion = new GestionValidaciones(); // 1

    String[] generos = {"Masculino", "Femenino"}; // 1
    JComboBox<String> tipoGenero = new JComboBox<>(generos); // 1

    String[] facultades = {"Ingenieria", "Deportes", "Comunicación", "Administracion", "Idiomas", "Ciencias Basicas"}; // 1
    JComboBox<String> tipoFacultad = new JComboBox<>(facultades); // 1

    String[] titulos = {"Pregrado", "Especialista", "Maestria", "Doctorado"}; // 1
    JComboBox<String> tipoTitulos = new JComboBox<>(titulos); // 1

    String nombre, cedula, fecha, asignaturas, horas; // 1
    boolean swt = true; // 1
    int opc; // 1

    public void ingresarProfesores(ArrayList<Profesor> profesores, String archivo) { // 114n + 13

        do {

            Profesor profesor = new Profesor(); // n

            do {

                nombre = JOptionPane.showInputDialog(null, "Ingrese El Nombre", "Nombre", JOptionPane.QUESTION_MESSAGE); // n

                if (validacion.validarNombre(nombre)) { // n

                    profesor.setNombre(nombre); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO DE NOMBRE INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                cedula = JOptionPane.showInputDialog(null, "Ingrese El Numero De Cedula", "Cedula", JOptionPane.QUESTION_MESSAGE); // n

                if (!validarProfesor(cedula, archivo)) { //41n + 13

                    profesor.setCedula(cedula); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "CEDULA INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                fecha = JOptionPane.showInputDialog(null, "Ingrese La Fecha De Nacimiento", "Fecha De Nacimiento (DD/MM/YYYY)", JOptionPane.QUESTION_MESSAGE); // n

                if (validacion.validarFecha(fecha)) { // 15n

                    profesor.setFecha(fecha); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO DE FECHA INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // n

                if (option == JOptionPane.OK_OPTION) { // n

                    profesor.setSexo((String) tipoGenero.getSelectedItem()); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UN SEXO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                int option = JOptionPane.showOptionDialog(null, tipoFacultad, "Selecciona La Facultad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // n

                if (option == JOptionPane.OK_OPTION) { // n

                    profesor.setFacultad((String) tipoFacultad.getSelectedItem()); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UNA FACULTAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                int option = JOptionPane.showOptionDialog(null, tipoTitulos, "Selecciona El Titulo Del Docente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // n

                if (option == JOptionPane.OK_OPTION) { // n

                    profesor.setTitulo((String) tipoTitulos.getSelectedItem()); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UN TITULO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                asignaturas = JOptionPane.showInputDialog(null, "Ingrese La Cantidad De Asignaturas Que Dicta", "Cantidad De Asignaturas", JOptionPane.QUESTION_MESSAGE); // n

                if (validacion.validarCantidadAsignaturas(asignaturas)) { // n

                    profesor.setAsignaturas(asignaturas); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            do {

                horas = JOptionPane.showInputDialog(null, "Ingrese La Cantidad De Horas Que Dicta A La Semana", "Cantidad De Horas", JOptionPane.QUESTION_MESSAGE); // n

                if (validacion.validarCantidadHoras(horas)) { // n

                    profesor.setHoras(horas); // n
                    swt = true; // n

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO UNA CANTIDAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE); // n
                    swt = false; // n
                }
            } while (!swt); // n

            profesores.add(profesor); // n

            opc = (JOptionPane.showConfirmDialog(null, "¿Que Quieres Agregar Otro Profesor?", "Nuevo Profesor", JOptionPane.YES_NO_OPTION)); // n

        } while (opc != JOptionPane.NO_OPTION); // n
    }

    public void profesorPorFacultad(Collection<Profesor> completos, Collection<Profesor> ocasionales, Collection<Profesor> catedra, String facultad) { //6n + 10

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos); //1
        Collection<Profesor> filtrados = new ArrayList<>(); //1
        conjuntoProfesores.addAll(ocasionales); //1
        conjuntoProfesores.addAll(catedra); //1

        for (Profesor profesor : conjuntoProfesores) { //n

            if ((facultad.equals(profesor.getFacultad()))) { //n

                filtrados.add(profesor); //n

            }
        }

        Set<Profesor> conjuntoFiltrado = new HashSet<>(filtrados); //1
        StringBuilder p = new StringBuilder(); //1
        int i = 1; //1

        for (Profesor profesor : conjuntoFiltrado) { //n

            p.append(i).append("). ").append(profesor).append("\n");//n
            i++; //n
        }

        if (p.isEmpty()) { //1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); //1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Facultad", JOptionPane.INFORMATION_MESSAGE); //1
        }
    }

    public void listarPorGeneroYContrato(Collection<Profesor> completos, Collection<Profesor> ocasionales, Collection<Profesor> catedra, String genero, String contrato) { // 11n + 10

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos); // 1
        Collection<Profesor> filtrados = new ArrayList<>(); // 1
        conjuntoProfesores.addAll(ocasionales); // 1
        conjuntoProfesores.addAll(catedra); // 1

        for (Profesor profesor : conjuntoProfesores) { // n

            if ((genero.equals("Masculino") && profesor.getSexo().equals("Masculino")) || (genero.equals("Femenino") && profesor.getSexo().equals("Femenino"))) { // n

                if (contrato.equals("Tiempo Completo") && completos.contains(profesor)) { // n

                    filtrados.add(profesor); // n

                } else if (contrato.equals("Ocasionales") && ocasionales.contains(profesor)) { // n

                    filtrados.add(profesor); // n

                } else if (contrato.equals("Catedra") && catedra.contains(profesor)) { // n

                    filtrados.add(profesor); // n
                }
            }
        }

        Set<Profesor> conjuntoFiltrado = new HashSet<>(filtrados); // 1
        StringBuilder p = new StringBuilder(); // 1
        int i = 1; // 1

        for (Profesor profesor : conjuntoFiltrado) { // n

            p.append(i).append(". ").append(profesor).append("\n"); // n
            i++; // n
        }

        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Contrato Y Genero", JOptionPane.INFORMATION_MESSAGE); // 1
        }
    }

    public void listarPorGenero(ArrayList<Profesor> completos, ArrayList<Profesor> ocasionales, ArrayList<Profesor> catedra, String genero) { // 10n + 13

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos); // 1
        Collection<Profesor> hombres = new ArrayList<>(); // 1
        Collection<Profesor> mujeres = new ArrayList<>(); // 1

        conjuntoProfesores.addAll(ocasionales); // 1
        conjuntoProfesores.addAll(catedra); // 1

        for (Profesor profesor : conjuntoProfesores) { // n

            if (profesor.getSexo().equals("Masculino")) { // n

                hombres.add(profesor); // n

            } else {

                mujeres.add(profesor); // n
            }
        }

        Set<Profesor> conjuntoHombres = new HashSet<>(hombres); // 1
        Set<Profesor> conjuntoMujeres = new HashSet<>(mujeres); // 1
        StringBuilder p = new StringBuilder(); // 1
        int i = 1; // 1

        if (genero.equals("Masculino")) { // 1

            for (Profesor profesor : conjuntoHombres) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }

        } else {

            for (Profesor profesor : conjuntoMujeres) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }

        }
        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Genero", JOptionPane.INFORMATION_MESSAGE); // 1
        }
    }

    public void listarYContar(Collection<Profesor> completos, Collection<Profesor> catedra, Collection<Profesor> ocasionales, String nombreArchivo) { // 9n + 17

        StringBuilder p = new StringBuilder(); // 1
        int i = 1; // 1

        Set<Profesor> conjuntoCompletos = new HashSet<>(completos); // 1
        Set<Profesor> conjuntoCatedra = new HashSet<>(catedra); // 1
        Set<Profesor> conjuntoOcasional = new HashSet<>(ocasionales); // 1

        if ("Completo.txt".equals(nombreArchivo)) { // 1

            conjuntoCompletos.removeAll(conjuntoCatedra); // 1
            conjuntoCompletos.removeAll(conjuntoOcasional); // 1

            for (Profesor profesor : conjuntoCompletos) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }
        }

        if ("Catedra.txt".equals(nombreArchivo)) { // 1

            conjuntoCatedra.removeAll(conjuntoCompletos); // 1
            conjuntoCatedra.removeAll(conjuntoOcasional); // 1

            for (Profesor profesor : conjuntoCatedra) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                ++i; // n
            }
        }

        if ("Ocasional.txt".equals(nombreArchivo)) { // 1

            conjuntoOcasional.removeAll(conjuntoCatedra); // 1
            conjuntoOcasional.removeAll(conjuntoCompletos); // 1

            for (Profesor profesor : conjuntoOcasional) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                ++i; // n
            }
        }

        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores", JOptionPane.INFORMATION_MESSAGE); // 1
        }
    }

    public void listarYContarTodos(Collection<Profesor> completos, Collection<Profesor> catedra, Collection<Profesor> ocasionales) { // 3n + 9

        StringBuilder p = new StringBuilder(); // 1
        int i = 1; // 1

        Set<Profesor> conjuntoProfesores = new HashSet<Profesor>(); // 1

        conjuntoProfesores.addAll(completos); // 1
        conjuntoProfesores.addAll(catedra); // 1
        conjuntoProfesores.addAll(ocasionales); // 1

        for (Profesor profesor : conjuntoProfesores) { //n

            p.append(i).append("). ").append(profesor).append("\n"); // n
            i++; // n
        }

        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE); // 1
        }

    }

    public void listarCombinados(Collection<Profesor> completos, Collection<Profesor> catedra, Collection<Profesor> ocasionales, String tipo1, String tipo2, String tipo3) { // 12n + 18

        StringBuilder p = new StringBuilder(); // 1
        int i = 1; // 1

        if (tipo1.equals("COMPLETO") && tipo2.equals("OCASIONAL") && tipo3.equals("NULL")) { // 1

            Set<Profesor> conjuntoCompletoYOcasional = new HashSet<>(completos); // 1
            conjuntoCompletoYOcasional.retainAll(ocasionales); // 1

            for (Profesor profesor : conjuntoCompletoYOcasional) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }
        }

        if (tipo1.equals("COMPLETO") && tipo2.equals("CATEDRA") && tipo3.equals("NULL")) { // 1

            Set<Profesor> conjuntoCompletoYCatedra = new HashSet<>(completos); // 1
            conjuntoCompletoYCatedra.retainAll(catedra); // 1

            for (Profesor profesor : conjuntoCompletoYCatedra) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }
        }

        if (tipo1.equals("OCASIONAL") && tipo2.equals("CATEDRA") && tipo3.equals("NULL")) { // 1

            Set<Profesor> conjuntoOcasionalYCatedra = new HashSet<>(ocasionales); // 1
            conjuntoOcasionalYCatedra.retainAll(catedra); // 1

            for (Profesor profesor : conjuntoOcasionalYCatedra) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }
        }

        if (tipo1.equals("COMPLETO") && tipo2.equals("OCASIONAL") && tipo3.equals("CATEDRA")) { // 1

            Set<Profesor> conjuntoTotal = new HashSet<>(ocasionales); // 1
            conjuntoTotal.retainAll(catedra); // 1
            conjuntoTotal.retainAll(completos); // 1

            for (Profesor profesor : conjuntoTotal) { // n

                p.append(i).append("). ").append(profesor).append("\n"); // n
                i++; // n
            }
        }
        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE); // 1
        }
    }

    public void mostrarProfesores(ArrayList<Profesor> profesores, String nombreArchivo) { // 3n + 7

        StringBuilder p = new StringBuilder(); // 1

        if ("Completo.txt".equals(nombreArchivo)) { // 1

            profesores.forEach(profesor -> p.append(profesor).append("\n"));// n
        }

        if ("Catedra.txt".equals(nombreArchivo)) { // 1

            profesores.forEach(profesor -> p.append(profesor).append("\n"));// n
        }

        if ("Ocasional.txt".equals(nombreArchivo)) { // 1

            profesores.forEach(profesor -> p.append(profesor).append("\n")); // n
        }

        if (p.isEmpty()) { // 1

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE); // 1

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE); // 1
        }
    }

    public void insertarProfesores(ArrayList<Profesor> profesores, String nombreArchivo) { // 3n + 9

        File fichero = new File(nombreArchivo); // 1
        FileWriter writer = null; // 1

        try {

            writer = new FileWriter(fichero, true); // 1

            for (Profesor p : profesores) { // n

                String linea = p.getNombre() + "--" + p.getCedula() + "--" + p.getFecha() + "--" + p.getSexo() + "--" + p.getTitulo() + "--" + p.getFacultad() + "--" + p.getAsignaturas() + "--" + p.getHoras() + "\n"; // n
                writer.write(linea); // n

            }

        } catch (IOException e) { // 1

            e.printStackTrace(); // 1

        } finally {

            try {

                if (writer != null) { //1

                    writer.close(); // 1
                }

            } catch (IOException e) { // 1

                e.printStackTrace(); // 1
            }
        }
    }

    public ArrayList<Profesor> leerProfesores(String nombreArchivo) { // 36n + 10

        File fichero = new File(nombreArchivo); // 1
        Scanner s = null; // 1
        ArrayList<Profesor> profesores = new ArrayList<>(); // 1

        try {

            s = new Scanner(fichero); // 1

            while (s.hasNextLine()) { // n + 1

                String linea = s.nextLine(); // n
                String[] cortarString = linea.split("--"); // n

                if ("Completo.txt".equals(nombreArchivo)) { // n

                    Profesor profesor = new Profesor(); // n
                    profesor.setNombre(cortarString[0]); // n
                    profesor.setCedula(cortarString[1]); // n
                    profesor.setFecha(cortarString[2]); // n
                    profesor.setSexo(cortarString[3]); // n
                    profesor.setTitulo(cortarString[4]); // n
                    profesor.setFacultad(cortarString[5]); //n
                    profesor.setAsignaturas(cortarString[6]); //n
                    profesor.setHoras(cortarString[7]); // n
                    profesores.add(profesor); // n
                }

                if ("Catedra.txt".equals(nombreArchivo)) { // n

                    Profesor profesor = new Profesor(); // n
                    profesor.setNombre(cortarString[0]); // n
                    profesor.setCedula(cortarString[1]); // n
                    profesor.setFecha(cortarString[2]); // n
                    profesor.setSexo(cortarString[3]); // n
                    profesor.setTitulo(cortarString[4]); // n
                    profesor.setFacultad(cortarString[5]); //n
                    profesor.setAsignaturas(cortarString[6]); //n
                    profesor.setHoras(cortarString[7]); // n
                    profesores.add(profesor); // n
                }

                if ("Ocasional.txt".equals(nombreArchivo)) { // n

                    Profesor profesor = new Profesor(); // n
                    profesor.setNombre(cortarString[0]); // n
                    profesor.setCedula(cortarString[1]); // n
                    profesor.setFecha(cortarString[2]); // n
                    profesor.setSexo(cortarString[3]); // n
                    profesor.setTitulo(cortarString[4]); // n
                    profesor.setFacultad(cortarString[5]); //n
                    profesor.setAsignaturas(cortarString[6]); //n
                    profesor.setHoras(cortarString[7]); // n
                    profesores.add(profesor); // n
                }
            }
        } catch (IOException e) { // 1

            e.printStackTrace(); // 1

        } finally {

            if (s != null) { // 1

                s.close(); // 1
            }
        }
        return profesores; // 1
    }

    public boolean validarProfesor(String cedula, String archivo) { //41n + 13

        ArrayList<Profesor> validar = leerProfesores(archivo); // 36n + 10
        boolean swt = false; // 1

        if (validacion.validarCedula(cedula)) { // 1

            for (Profesor profesor : validar) { // n

                if (profesor.getCedula().equals(cedula)) { // n

                    swt = true; // n
                    break; // n
                }
            }

        } else {

            swt = false; // n
        }
        return swt; // 1
    }
}
