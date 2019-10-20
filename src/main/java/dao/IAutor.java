
package dao;

import java.util.List;
import model.Autor;


public interface IAutor {
    
    void registrar(Autor autor) throws Exception;

    void modificar(Autor autor) throws Exception;

    void eliminar(Autor autor) throws Exception;

    List<Autor> listarAutor() throws Exception;
    
}
