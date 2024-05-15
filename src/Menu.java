import javax.swing.*;
import java.util.ArrayList;

public class Menu {

    GestionProfesores gestionProfesores = new GestionProfesores(); // 1
    ArrayList<Profesor> profesores = new ArrayList<>(); // 1
    ArrayList<Profesor> completos = gestionProfesores.leerProfesores("Completo.txt"); // 1
    ArrayList<Profesor> catedras = gestionProfesores.leerProfesores("Catedra.txt"); // 1
    ArrayList<Profesor> ocasionales = gestionProfesores.leerProfesores("Ocasional.txt"); // 1

    int opc; // 1

    public void menuPrincipal() { // 573n + 374

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Ingresar Profesor
                2).Mostrar Profesores
                3).Listar Y Contar
                4).Salir
                """, "Menu Profesor", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:
                menuIngresar(); // 459n + 107
                break; // 1
            case 2:
                menuMostrar(); // 9n + 29
                break; // 1

            case 3:
                menuAvanzados(); // 105n + 229
                break; // 1

            case 4:
                System.exit(0); // 1
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }
    }

    public void menuAvanzados() { // 105n + 229

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Listar Y Contar Profesores
                2).Listar Y Contar Combinados
                3).Opciones Avanzadas
                4).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:
                menuListarYContarProfesores(); // 30n + 69
                break; // 1

            case 2:
                menuListarYContarCombinados(); // 48n + 81
                break; // 1

            case 3:
                menuOpcionesAvanzadas(); // 27n + 71
                break; // 1

            case 4:
                menuPrincipal(); // 573n + 374
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }

    }

    public void menuOpcionesAvanzadas() { // 27n + 71

        String[] generos = {"Masculino", "Femenino"}; // 1
        JComboBox<String> tipoGenero = new JComboBox<>(generos); // 1

        String[] contratos = {"Tiempo Completo", "Ocasionales", "Catedra"}; // 1
        JComboBox<String> tipoContrato = new JComboBox<>(contratos); // 1

        String[] facultades = {"Ingenieria", "Deportes", "Comunicación", "Administracion", "Idiomas", "Ciencias Basicas"}; // 1
        JComboBox<String> tipoFacultad = new JComboBox<>(facultades); // 1

        boolean swt = true; // 1

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Hombres Y Mujeres Por Contrato
                2).Listar Y Contar Profesores Por Facultad
                3).Cantidad De Hombres Y Mujeres
                4).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // 1
                    int opc = JOptionPane.showOptionDialog(null, tipoContrato, "Selecciona El Tipo De Contrato", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // 1

                    if (option == JOptionPane.OK_OPTION && opc == JOptionPane.OK_OPTION) {  // 1

                        String sexo = (String) tipoGenero.getSelectedItem(); // 1
                        String contrato = (String) tipoContrato.getSelectedItem(); // 1
                        gestionProfesores.listarPorGeneroYContrato(completos, ocasionales, catedras, sexo, contrato); // 11n + 10
                        swt = true; // 1

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE LOS DATOS VALIDOS", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                        swt = false; // 1
                    }
                } while (!swt); // 1

                menuOpcionesAvanzadas(); // 27n + 71
                break; // 1

            case 2:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoFacultad, "Selecciona La Facultad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // 1

                    if (option == JOptionPane.OK_OPTION) { // 1

                        String facultad = (String) tipoFacultad.getSelectedItem(); // 1
                        gestionProfesores.profesorPorFacultad(completos, ocasionales, catedras, facultad); //6n + 10
                        swt = true; // 1

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE UNA FACULTAD VALIDA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                        swt = false; // 1
                    }
                } while (!swt); // 1

                menuOpcionesAvanzadas(); // 27n + 71
                break; // 1

            case 3:

                do {

                    int option = JOptionPane.showOptionDialog(null, tipoGenero, "Selecciona El Genero", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // 1

                    if (option == JOptionPane.OK_OPTION) { // 1

                        String sexo = (String) tipoGenero.getSelectedItem(); // 1
                        gestionProfesores.listarPorGenero(completos, ocasionales, catedras, sexo); // 10n + 13
                        swt = true; // 1

                    } else {

                        JOptionPane.showMessageDialog(null, "INGRESE UN SEXO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                        swt = false; // 1
                    }
                } while (!swt); // 1

                menuOpcionesAvanzadas();  // 27n + 71
                break; // 1

            case 4:
                menuAvanzados(); // // 105n + 229
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }
    }

    public void menuListarYContarCombinados() { // 48n + 81

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Profesores Tiempo Completo Y Ocasional
                2).Profesores Tiempo Completo Y Catedra
                3).Profesores Ocasionales Y Catedra
                4).Profesores Tiempo Completo, Catedra Y Ocasional
                5).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE)); // 1


        switch (opc) { // 1

            case 1:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "OCASIONAL", "NULL"); // 12n + 18
                menuListarYContarCombinados(); // 48n + 81
                break; // 1

            case 2:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "CATEDRA", "NULL"); // 12n + 18
                menuListarYContarCombinados(); // 48n + 81
                break; // 1

            case 3:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "OCASIONAL", "CATEDRA", "NULL"); // 12n + 18
                menuListarYContarCombinados(); // 48n + 81
                break; // 1

            case 4:
                gestionProfesores.listarCombinados(completos, catedras, ocasionales, "COMPLETO", "OCASIONAL", "CATEDRA"); // 12n + 18
                menuListarYContarCombinados(); // 48n + 81
                break; // 1

            case 5:
                menuAvanzados(); // 105n + 229
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }
    }

    public void menuListarYContarProfesores() { // 30n + 69

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Ingresa Una Opcion
                1).Profesores Tiempo Completo
                2).Profesores Ocasionales
                3).Profesores De Catedra
                4).Todos Los Profesores
                5).Volver
                """, "Menu Avanzados", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:
                gestionProfesores.listarYContar(completos, catedras, ocasionales, "Completo.txt"); // 9n + 17
                menuListarYContarProfesores(); // 30n + 69
                break; // 1

            case 2:
                gestionProfesores.listarYContar(completos, catedras, ocasionales, "Ocasional.txt"); // 9n + 17
                menuListarYContarProfesores(); // 30n + 69
                break; // 1

            case 3:
                gestionProfesores.listarYContar(completos, catedras, ocasionales, "Catedra.txt"); // 9n + 17
                menuListarYContarProfesores(); // 30n + 69
                break; // 1

            case 4:
                gestionProfesores.listarYContarTodos(completos, catedras, ocasionales); // 3n + 9
                menuListarYContarProfesores(); // 30n + 69
                break; // 1

            case 5:
                menuAvanzados(); // 105n + 229
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }
    }

    public void menuMostrar() { // 9n + 29

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                1).Mostrar Profesor Tiempo Completo
                2).Mostrar Profesor Ocasional
                3).Mostrar Profesor Catedra
                4).Volver
                """, "Menu Ingresar Profesor", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:
                gestionProfesores.mostrarProfesores(completos, "Completo.txt"); // 3n + 7
                menuMostrar(); // 9n + 29
                break;// 1

            case 2:
                gestionProfesores.mostrarProfesores(ocasionales, "Ocasional.txt"); // 3n + 7
                menuMostrar(); // 9n + 29
                break; // 1

            case 3:
                gestionProfesores.mostrarProfesores(catedras, "Catedra.txt"); // 3n + 7
                menuMostrar(); // 9n + 29
                break; // 1

            case 4:
                menuPrincipal(); // 573n + 374
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);// 1
                break; // 1
        }
    }

    public void menuIngresar() { // 459n + 107

        opc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                1).Ingresar Profesor Tiempo Completo
                2).Ingresar Profesor Ocasional
                3).Ingresar Profesor Catedra
                4).Volver
                """, "Menu Ingresar Profesor", JOptionPane.INFORMATION_MESSAGE)); // 1

        switch (opc) { // 1

            case 1:
                gestionProfesores.ingresarProfesores(profesores, "Completo.txt"); // 114n + 13
                gestionProfesores.insertarProfesores(profesores, "Completo.txt"); // 3n + 9
                profesores.clear(); // 1
                completos = gestionProfesores.leerProfesores("Completo.txt"); // 36n + 10
                menuIngresar(); // 459n + 107
                break; // 1

            case 2:
                gestionProfesores.ingresarProfesores(profesores, "Ocasional.txt"); // 114n + 13
                gestionProfesores.insertarProfesores(profesores, "Ocasional.txt"); // 3n + 9
                profesores.clear(); // 1
                ocasionales = gestionProfesores.leerProfesores("Ocasional.txt"); //36n + 10
                menuIngresar(); // 459n + 107
                break; // 1

            case 3:
                gestionProfesores.ingresarProfesores(profesores, "Catedra.txt"); // 114n + 13
                gestionProfesores.insertarProfesores(profesores, "Catedra.txt"); // 3n + 9
                profesores.clear(); // 1
                catedras = gestionProfesores.leerProfesores("Catedra.txt"); // 36n + 10
                menuIngresar();// 459n + 107
                break; // 1

            case 4:
                menuPrincipal();// 573n + 374
                break; // 1

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE); // 1
                break; // 1
        }
    }
}
