package dao;

import java.util.List;
import model.Prestamo;

public interface IPrestamo {

    void registrar(Prestamo prestamo) throws Exception;

    void modificar(Prestamo prestamo) throws Exception;

    void eliminar(Prestamo prestamo) throws Exception;
    
    List<Prestamo> listarPre() throws Exception;

}
