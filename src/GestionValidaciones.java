import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionValidaciones {

    public boolean validarCedula(String cedula) {

        return cedula.matches("\\d{6,10}");
    }

    public boolean validarFecha(String fecha) {

        String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(19[0-9][0-9]|20[0-1][0-9]|202[0-4])$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fecha);

        if (matcher.matches()) {

            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(3, 5));
            int ano = Integer.parseInt(fecha.substring(6));

            if (mes == 2) {

                if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {

                    return dia <= 29;

                } else {

                    return dia <= 28;
                }
            }

            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {

                return dia <= 30;
            }

            return true;
        }
        return false;
    }

    public boolean validarNombre(String nombre) {

        return nombre.matches("^([A-Z][a-z]+)( [A-Z][a-z]+)? ([A-Z][a-z]+)( [A-Z][a-z]+)?$");
    }

    public boolean validarCantidadAsignaturas(String nAsignaturas) {

        return nAsignaturas.matches("^[1-9]|10$");
    }

    public boolean validarCantidadHoras(String horas) {

        return horas.matches("^(1?[0-9]|20)$");
    }
}
