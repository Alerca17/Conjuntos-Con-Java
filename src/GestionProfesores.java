import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GestionProfesores {

    GestionValidaciones validacion = new GestionValidaciones();

    String[] generos = {"Masculino", "Femenino"};
    JComboBox<String> tipoGenero = new JComboBox<>(generos);

    String[] facultades = {"Ingenieria", "Deportes", "Comunicación", "Administracion", "Idiomas", "Ciencias Basicas"};
    JComboBox<String> tipoFacultad = new JComboBox<>(facultades);

    String[] titulos = {"Pregrado", "Especialista", "Maestria", "Doctorado"};
    JComboBox<String> tipoTitulos = new JComboBox<>(titulos);

    String nombre, cedula, fecha, asignaturas, horas;
    boolean swt = true;
    int opc;

    public void ingresarProfesores(ArrayList<Profesor> profesores, String archivo) {

        do {

            Profesor profesor = new Profesor();

            do {

                nombre = JOptionPane.showInputDialog(null, "Ingrese El Nombre", "Nombre", JOptionPane.QUESTION_MESSAGE);

                if (validacion.validarNombre(nombre)) {

                    profesor.setNombre(nombre);
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO DE NOMBRE INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                cedula = JOptionPane.showInputDialog(null, "Ingrese El Numero De Cedula", "Cedula", JOptionPane.QUESTION_MESSAGE);

                if (!validarProfesor(cedula, archivo)) {

                    profesor.setCedula(cedula);
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "CEDULA INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                fecha = JOptionPane.showInputDialog(null, "Ingrese La Fecha De Nacimiento", "Fecha De Nacimiento (DD/MM/YYYY)", JOptionPane.QUESTION_MESSAGE);

                if (validacion.validarFecha(fecha)) {

                    profesor.setFecha(fecha);
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO DE FECHA INCORRECTO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {

                    profesor.setSexo((String) tipoGenero.getSelectedItem());
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UN SEXO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                int option = JOptionPane.showOptionDialog(null, tipoFacultad, "Selecciona La Facultad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {

                    profesor.setFacultad((String) tipoFacultad.getSelectedItem());
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UNA FACULTAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                int option = JOptionPane.showOptionDialog(null, tipoTitulos, "Selecciona El Titulo Del Docente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {

                    profesor.setTitulo((String) tipoTitulos.getSelectedItem());
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UN TITULO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                asignaturas = JOptionPane.showInputDialog(null, "Ingrese La Cantidad De Asignaturas Que Dicta", "Cantidad De Asignaturas", JOptionPane.QUESTION_MESSAGE);

                if (validacion.validarCantidadAsignaturas(asignaturas)) {

                    profesor.setAsignaturas(asignaturas);
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            do {

                horas = JOptionPane.showInputDialog(null, "Ingrese La Cantidad De Horas Que Dicta A La Semana", "Cantidad De Horas", JOptionPane.QUESTION_MESSAGE);

                if (validacion.validarCantidadHoras(horas)) {

                    profesor.setHoras(horas);
                    swt = true;

                } else {

                    JOptionPane.showMessageDialog(null, "FORMATO UNA CANTIDAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    swt = false;
                }
            } while (!swt);

            profesores.add(profesor);

            opc = (JOptionPane.showConfirmDialog(null, "¿Que Quieres Agregar Otro Profesor?", "Nuevo Profesor", JOptionPane.YES_NO_OPTION));

        } while (opc != JOptionPane.NO_OPTION);
    }

    public void profesorPorFacultad(Collection<Profesor> completos, Collection<Profesor> ocasionales, Collection<Profesor> catedra, String facultad) {

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos);
        Collection<Profesor> filtrados = new ArrayList<>();
        conjuntoProfesores.addAll(ocasionales);
        conjuntoProfesores.addAll(catedra);

        for (Profesor profesor : conjuntoProfesores) {

            if ((facultad.equals(profesor.getFacultad()))) {

                filtrados.add(profesor);

            }
        }

        Set<Profesor> conjuntoFiltrado = new HashSet<>(filtrados);
        StringBuilder p = new StringBuilder();
        int i = 1;

        for (Profesor profesor : conjuntoFiltrado) {

            p.append(i).append("). ").append(profesor).append("\n");
            i++;
        }

        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Facultad", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarPorGeneroYContrato(Collection<Profesor> completos, Collection<Profesor> ocasionales, Collection<Profesor> catedra, String genero, String contrato) {

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos);
        Collection<Profesor> filtrados = new ArrayList<>();
        conjuntoProfesores.addAll(ocasionales);
        conjuntoProfesores.addAll(catedra);

        for (Profesor profesor : conjuntoProfesores) {

            if ((genero.equals("Masculino") && profesor.getSexo().equals("Masculino")) || (genero.equals("Femenino") && profesor.getSexo().equals("Femenino"))) {

                if (contrato.equals("Tiempo Completo") && completos.contains(profesor)) {

                    filtrados.add(profesor);

                } else if (contrato.equals("Ocasionales") && ocasionales.contains(profesor)) {

                    filtrados.add(profesor);

                } else if (contrato.equals("Catedra") && catedra.contains(profesor)) {

                    filtrados.add(profesor);
                }
            }
        }

        Set<Profesor> conjuntoFiltrado = new HashSet<>(filtrados);
        StringBuilder p = new StringBuilder();
        int i = 1;

        for (Profesor profesor : conjuntoFiltrado) {

            p.append(i).append(". ").append(profesor).append("\n");
            i++;
        }

        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Contrato Y Genero", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarPorGenero(ArrayList<Profesor> completos, ArrayList<Profesor> ocasionales, ArrayList<Profesor> catedra, String genero) {

        Set<Profesor> conjuntoProfesores = new HashSet<>(completos);
        Collection<Profesor> hombres = new ArrayList<>();
        Collection<Profesor> mujeres = new ArrayList<>();

        conjuntoProfesores.addAll(ocasionales);
        conjuntoProfesores.addAll(catedra);

        for (Profesor profesor : conjuntoProfesores) {

            if (profesor.getSexo().equals("Masculino")) {

                hombres.add(profesor);

            } else {

                mujeres.add(profesor);
            }
        }

        Set<Profesor> conjuntoHombres = new HashSet<>(hombres);
        Set<Profesor> conjuntoMujeres = new HashSet<>(mujeres);
        StringBuilder p = new StringBuilder();
        int i = 1;

        if (genero.equals("Masculino")) {

            for (Profesor profesor : conjuntoHombres) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }

        } else {

            for (Profesor profesor : conjuntoMujeres) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }

        }
        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores Por Genero", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarYContar(ArrayList<Profesor> profesores, String nombreArchivo) {

        StringBuilder p = new StringBuilder();
        int i = 1;

        if ("Completo.txt".equals(nombreArchivo)) {

            Set<Profesor> conjuntoProfesores = new HashSet<Profesor>(profesores);

            for (Profesor profesor : conjuntoProfesores) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }
        }

        if ("Catedra.txt".equals(nombreArchivo)) {

            Set<Profesor> conjuntoProfesores = new HashSet<Profesor>(profesores);

            for (Profesor profesor : conjuntoProfesores) {
                p.append(i).append("). ").append(profesor).append("\n");
                ++i;
            }
        }

        if ("Ocasional.txt".equals(nombreArchivo)) {

            Set<Profesor> conjuntoProfesores = new HashSet<Profesor>(profesores);

            for (Profesor profesor : conjuntoProfesores) {
                p.append(i).append("). ").append(profesor).append("\n");
                ++i;
            }
        }

        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarYContarTodos(Collection<Profesor> completos, Collection<Profesor> catedra, Collection<Profesor> ocasionales) {

        StringBuilder p = new StringBuilder();
        int i = 1;

        Set<Profesor> conjuntoProfesores = new HashSet<Profesor>();

        conjuntoProfesores.addAll(completos);
        conjuntoProfesores.addAll(catedra);
        conjuntoProfesores.addAll(ocasionales);

        for (Profesor profesor : conjuntoProfesores) {

            p.append(i).append("). ").append(profesor).append("\n");
            i++;
        }

        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void listarCombinados
            (Collection<Profesor> completos, Collection<Profesor> catedra, Collection<Profesor> ocasionales, String
                    tipo1, String tipo2, String tipo3) {

        StringBuilder p = new StringBuilder();
        int i = 1;

        if (tipo1.equals("COMPLETO") && tipo2.equals("OCASIONAL") && tipo3.equals("NULL")) {

            Set<Profesor> conjuntoCompletoYOcasional = new HashSet<>(completos);
            conjuntoCompletoYOcasional.retainAll(ocasionales);

            for (Profesor profesor : conjuntoCompletoYOcasional) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }
        }

        if (tipo1.equals("COMPLETO") && tipo2.equals("CATEDRA") && tipo3.equals("NULL")) {

            Set<Profesor> conjuntoCompletoYCatedra = new HashSet<>(completos);
            conjuntoCompletoYCatedra.retainAll(catedra);

            for (Profesor profesor : conjuntoCompletoYCatedra) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }
        }

        if (tipo1.equals("OCASIONAL") && tipo2.equals("CATEDRA") && tipo3.equals("NULL")) {

            Set<Profesor> conjuntoOcasionalYCatedra = new HashSet<>(ocasionales);
            conjuntoOcasionalYCatedra.retainAll(catedra);

            for (Profesor profesor : conjuntoOcasionalYCatedra) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }
        }

        if (tipo1.equals("COMPLETO") && tipo2.equals("OCASIONAL") && tipo3.equals("CATEDRA")) {

            Set<Profesor> conjuntoTotal = new HashSet<>(ocasionales);
            conjuntoTotal.retainAll(catedra);
            conjuntoTotal.retainAll(completos);

            for (Profesor profesor : conjuntoTotal) {

                p.append(i).append("). ").append(profesor).append("\n");
                i++;
            }
        }
        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarProfesores(ArrayList<Profesor> profesores, String nombreArchivo) {

        StringBuilder p = new StringBuilder();

        if ("Completo.txt".equals(nombreArchivo)) {

            profesores.forEach(profesor -> p.append(profesor).append("\n"));
        }

        if ("Catedra.txt".equals(nombreArchivo)) {

            profesores.forEach(profesor -> p.append(profesor).append("\n"));
        }

        if ("Ocasional.txt".equals(nombreArchivo)) {

            profesores.forEach(profesor -> p.append(profesor).append("\n"));
        }

        if (p.isEmpty()) {

            JOptionPane.showMessageDialog(null, "ERROR, NO HAY DATOS DISPONIBLES", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null, p.toString(), "Profesores ", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void insertarProfesores(ArrayList<Profesor> profesores, String nombreArchivo) {

        File fichero = new File(nombreArchivo);
        FileWriter writer = null;

        try {

            writer = new FileWriter(fichero, true);

            for (Profesor p : profesores) {

                String linea = p.getNombre() + "--" + p.getCedula() + "--" + p.getFecha() + "--" + p.getSexo() + "--" + p.getTitulo() + "--" + p.getFacultad() + "--" + p.getAsignaturas() + "--" + p.getHoras() + "\n";
                writer.write(linea);

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (writer != null) {

                    writer.close();
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public ArrayList<Profesor> leerProfesores(String nombreArchivo) {

        File fichero = new File(nombreArchivo);
        Scanner s = null;
        ArrayList<Profesor> profesores = new ArrayList<>();

        try {

            s = new Scanner(fichero);

            while (s.hasNextLine()) {

                String linea = s.nextLine();
                String[] cortarString = linea.split("--");

                if ("Completo.txt".equals(nombreArchivo)) {

                    Profesor profesor = new Profesor();
                    profesor.setNombre(cortarString[0]);
                    profesor.setCedula(cortarString[1]);
                    profesor.setFecha(cortarString[2]);
                    profesor.setSexo(cortarString[3]);
                    profesor.setTitulo(cortarString[4]);
                    profesor.setFacultad(cortarString[5]);
                    profesor.setAsignaturas(cortarString[6]);
                    profesor.setHoras(cortarString[7]);
                    profesores.add(profesor);
                }

                if ("Catedra.txt".equals(nombreArchivo)) {

                    Profesor profesor = new Profesor();
                    profesor.setNombre(cortarString[0]);
                    profesor.setCedula(cortarString[1]);
                    profesor.setFecha(cortarString[2]);
                    profesor.setSexo(cortarString[3]);
                    profesor.setTitulo(cortarString[4]);
                    profesor.setFacultad(cortarString[5]);
                    profesor.setAsignaturas(cortarString[6]);
                    profesor.setHoras(cortarString[7]);
                    profesores.add(profesor);
                }

                if ("Ocasional.txt".equals(nombreArchivo)) {

                    Profesor profesor = new Profesor();
                    profesor.setNombre(cortarString[0]);
                    profesor.setCedula(cortarString[1]);
                    profesor.setFecha(cortarString[2]);
                    profesor.setSexo(cortarString[3]);
                    profesor.setTitulo(cortarString[4]);
                    profesor.setFacultad(cortarString[5]);
                    profesor.setAsignaturas(cortarString[6]);
                    profesor.setHoras(cortarString[7]);
                    profesores.add(profesor);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (s != null) {

                s.close();
            }
        }
        return profesores;
    }

    public boolean validarProfesor(String cedula, String archivo) {

        ArrayList<Profesor> validar = leerProfesores(archivo);
        boolean swt = false;

        if (validacion.validarCedula(cedula)) {

            for (Profesor profesor : validar) {

                if (profesor.getCedula().equals(cedula)) {

                    swt = true;
                    break;
                }
            }

        } else {

            swt = false;
        }
        return swt;
    }
}
