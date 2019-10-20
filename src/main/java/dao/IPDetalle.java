package dao;

import java.util.List;
import model.PDetalle;
import model.Prestamo;

public interface IPDetalle {

    void registrar(PDetalle devolucion) throws Exception;

    void registrarprestamo(PDetalle devolucion) throws Exception;

    void eliminar(PDetalle devolucion) throws Exception;

    List<PDetalle> listarDevolucion() throws Exception;

    int obtenerCantidadCabecera(List<Prestamo> listaprestamocabecera, PDetalle prestamos) throws Exception;

    void ActualizarCabecera(int codigo, int cantidad) throws Exception;

}
