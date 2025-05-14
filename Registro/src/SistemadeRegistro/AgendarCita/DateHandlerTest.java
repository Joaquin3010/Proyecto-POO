package SistemadeRegistro.AgendarCita;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class DateHandlerTest {

    @Test
    public void testEsFechaValida() {
        LocalDate hoy = LocalDate.now();
        LocalDate futura = hoy.plusDays(1);
        LocalDate pasada = hoy.minusDays(1);

        assertTrue("La fecha de hoy debe ser válida", DateHandler.esFechaValida(hoy));
        assertTrue("Una fecha futura debe ser válida", DateHandler.esFechaValida(futura));
        assertFalse("Una fecha pasada no debe ser válida", DateHandler.esFechaValida(pasada));
    }

    @Test
    public void testEsHoraValida() {
        LocalTime horaValida = LocalTime.of(10, 0);
        LocalTime antesDeHorario = LocalTime.of(8, 59);
        LocalTime despuesDeHorario = LocalTime.of(17, 1);
        LocalTime bordeInicio = LocalTime.of(9, 0);
        LocalTime bordeFin = LocalTime.of(17, 0);

        assertTrue("10:00 es una hora válida", DateHandler.esHoraValida(horaValida));
        assertFalse("8:59 es antes del horario permitido", DateHandler.esHoraValida(antesDeHorario));
        assertFalse("17:01 es después del horario permitido", DateHandler.esHoraValida(despuesDeHorario));
        assertTrue("9:00 debe ser válida (inicio)", DateHandler.esHoraValida(bordeInicio));
        assertTrue("17:00 debe ser válida (fin)", DateHandler.esHoraValida(bordeFin));
    }

    @Test
    public void testEsDiaLaboral() {
        LocalDate lunes = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate sabado = LocalDate.now().with(DayOfWeek.SATURDAY);
        LocalDate domingo = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertTrue("Lunes debe ser día laboral", DateHandler.esDiaLaboral(lunes));
        assertFalse("Sábado no debe ser día laboral", DateHandler.esDiaLaboral(sabado));
        assertFalse("Domingo no debe ser día laboral", DateHandler.esDiaLaboral(domingo));
    }

    @Test
    public void testObtenerDiasDelMes() {
        assertEquals("Enero debe tener 31 días", 31, DateHandler.obtenerDiasDelMes(2023, 1));
        assertEquals("Febrero 2023 debe tener 28 días", 28, DateHandler.obtenerDiasDelMes(2023, 2));
        assertEquals("Febrero 2024 debe tener 29 días", 29, DateHandler.obtenerDiasDelMes(2024, 2));
        assertEquals("Abril debe tener 30 días", 30, DateHandler.obtenerDiasDelMes(2023, 4));
    }

    @Test
    public void testEsAnioBisiesto() {
        assertFalse("2023 no es bisiesto", DateHandler.esAnioBisiesto(2023));
        assertTrue("2024 es bisiesto", DateHandler.esAnioBisiesto(2024));
        assertFalse("2100 no es bisiesto", DateHandler.esAnioBisiesto(2100));
        assertTrue("2000 es bisiesto", DateHandler.esAnioBisiesto(2000));
    }
}
