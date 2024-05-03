import javax.swing.*;
import java.util.ArrayList;

public class Menu {

    GestionProfesores gestionProfesores = new GestionProfesores();
    ArrayList<Profesor> profesores = new ArrayList<>();
    ArrayList<Profesor> completos = gestionProfesores.leerProfesores("Completo.txt");
    ArrayList<Profesor> catedras = gestionProfesores.leerProfesores("Catedra.txt");
    ArrayList<Profesor> ocasionales = gestionProfesores.leerProfesores("Ocasional.txt");

    int opc;

    public void menuPrincipal() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Ingresar Profesor
                2).Mostrar Profesores
                3).Listar Y Contar
                4).Salir
                """, "Menu Profesor", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:
                menuIngresar();
                break;
            case 2:
                menuMostrar();
                break;

            case 3:
                menuAvanzados();
                break;

            case 4:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void menuAvanzados() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Listar Y Contar Profesores
                2).Listar Y Contar Combinados
                3).Opciones Avanzadas
                4).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:
                menuListarYContarProfesores();
                break;

            case 2:
                menuListarYContarCombinados();
                break;

            case 3:
                menuOpcionesAvanzadas();
                break;

            case 4:
                menuPrincipal();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }

    public void menuOpcionesAvanzadas() {

        String[] generos = {"Masculino", "Femenino"};
        JComboBox<String> tipoGenero = new JComboBox<>(generos);

        String[] contratos = {"Tiempo Completo", "Ocasionales", "Catedra"};
        JComboBox<String> tipoContrato = new JComboBox<>(contratos);

        String[] facultades = {"Ingenieria", "Deportes", "Comunicación", "Administracion", "Idiomas", "Ciencias Basicas"};
        JComboBox<String> tipoFacultad = new JComboBox<>(facultades);

        boolean swt = true;

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Hombres Y Mujeres Por Contrato
                2).Listar Y Contar Profesores Por Facultad
                3).Cantidad De Hombres Y Mujeres
                4).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    int opc = JOptionPane.showOptionDialog(null, tipoContrato, "Selecciona El Tipo De Contrato", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    if (option == JOptionPane.OK_OPTION && opc == JOptionPane.OK_OPTION) {

                        String sexo = (String) tipoGenero.getSelectedItem();
                        String contrato = (String) tipoContrato.getSelectedItem();
                        gestionProfesores.listarPorGeneroYContrato(completos, ocasionales, catedras, sexo, contrato);
                        swt = true;

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE LOS DATOS VALIDOS", "ERROR", JOptionPane.ERROR_MESSAGE);
                        swt = false;
                    }
                } while (!swt);

                menuOpcionesAvanzadas();
                break;

            case 2:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoFacultad, "Selecciona La Facultad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    if (option == JOptionPane.OK_OPTION) {

                        String facultad = (String) tipoFacultad.getSelectedItem();
                        gestionProfesores.profesorPorFacultad(completos, ocasionales, catedras, facultad);
                        swt = true;

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE UNA FACULTAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
                        swt = false;
                    }
                } while (!swt);
                menuOpcionesAvanzadas();
                break;

            case 3:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    if (option == JOptionPane.OK_OPTION) {

                        String sexo = (String) tipoGenero.getSelectedItem();
                        gestionProfesores.listarPorGenero(completos, ocasionales, catedras, sexo);
                        swt = true;

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE UN SEXO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
                        swt = false;
                    }
                } while (!swt);
                menuOpcionesAvanzadas();
                break;

            case 4:
                menuAvanzados();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void menuListarYContarCombinados() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Profesores Tiempo Completo Y Ocasional
                2).Profesores Tiempo Completo Y Catedra
                3).Profesores Ocasionales Y Catedra
                4).Profesores Tiempo Completo, Catedra Y Ocasional
                5).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE));


        switch (opc) {

            case 1:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "OCASIONAL", "NULL");
                menuListarYContarCombinados();
                break;

            case 2:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "CATEDRA", "NULL");
                menuListarYContarCombinados();
                break;

            case 3:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "OCASIONAL", "CATEDRA", "NULL");
                menuListarYContarCombinados();
                break;

            case 4:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "OCASIONAL", "CATEDRA");
                menuListarYContarCombinados();
                break;

            case 5:
                menuAvanzados();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void menuListarYContarProfesores() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Profesores Tiempo Completo
                2).Profesores Ocasionales
                3).Profesores De Catedra
                4).Todos Los Profesores
                5).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:
                gestionProfesores.listarYContar(completos, "Completo.txt");
                menuListarYContarProfesores();
                break;

            case 2:
                gestionProfesores.listarYContar(ocasionales, "Ocasional.txt");
                menuListarYContarProfesores();
                break;

            case 3:
                gestionProfesores.listarYContar(catedras, "Catedra.txt");
                menuListarYContarProfesores();
                break;

            case 4:
                gestionProfesores.listarYContarTodos(completos, catedras, ocasionales);
                menuListarYContarProfesores();
                break;

            case 5:
                menuAvanzados();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void menuMostrar() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                1).Mostrar Profesor Tiempo Completo
                2).Mostrar Profesor Ocasional
                3).Mostrar Profesor Catedra
                4).Volver
                """, "Menu Ingresar Profesor", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:
                gestionProfesores.mostrarProfesores(completos, "Completo.txt");
                menuMostrar();
                break;

            case 2:
                gestionProfesores.mostrarProfesores(ocasionales, "Ocasional.txt");
                menuMostrar();
                break;

            case 3:
                gestionProfesores.mostrarProfesores(catedras, "Catedra.txt");
                menuMostrar();
                break;

            case 4:
                menuPrincipal();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void menuIngresar() {

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                1).Ingresar Profesor Tiempo Completo
                2).Ingresar Profesor Ocasional
                3).Ingresar Profesor Catedra
                4).Volver
                """, "Menu Ingresar Profesor", JOptionPane.INFORMATION_MESSAGE));

        switch (opc) {

            case 1:
                gestionProfesores.ingresarProfesores(profesores, "Completo.txt");
                gestionProfesores.insertarProfesores(profesores, "Completo.txt");
                profesores.clear();
                completos = gestionProfesores.leerProfesores("Completo.txt");
                menuIngresar();
                break;

            case 2:
                gestionProfesores.ingresarProfesores(profesores, "Ocasional.txt");
                gestionProfesores.insertarProfesores(profesores, "Ocasional.txt");
                profesores.clear();
                ocasionales = gestionProfesores.leerProfesores("Ocasional.txt");
                menuIngresar();
                break;

            case 3:
                gestionProfesores.ingresarProfesores(profesores, "Catedra.txt");
                gestionProfesores.insertarProfesores(profesores, "Catedra.txt");
                profesores.clear();
                catedras = gestionProfesores.leerProfesores("Catedra.txt");
                menuIngresar();
                break;

            case 4:
                menuPrincipal();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
