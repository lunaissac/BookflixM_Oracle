
package dao;

import java.util.List;
import model.Categoria;


public interface ICategoria {
    void registrar(Categoria categoria) throws Exception;

    void modificar(Categoria categoria) throws Exception;

    void eliminar(Categoria categoria) throws Exception;

    List<Categoria> listarCat() throws Exception;
    
}

