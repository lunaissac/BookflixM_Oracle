package dao;

import java.util.List;
import model.Editorial;

public interface IEditorial {

    void registrar(Editorial editorial) throws Exception;

    void modificar(Editorial editorial) throws Exception;

    void eliminar(Editorial editorial) throws Exception;

    List<Editorial> listarEdit() throws Exception;

}
