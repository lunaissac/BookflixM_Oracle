package dao;

import java.util.List;
import model.Libro;

public interface ILibro {

    void registrar(Libro libro) throws Exception;

    void modificar(Libro libro) throws Exception;

    void eliminar(Libro libro) throws Exception;

    List<Libro> listarLib() throws Exception;

    int obtenerCantidad(List<Libro> listaLibro, Libro libro) throws Exception;

    void ActualizarStock(int codigo, int cantidad) throws Exception;
}
