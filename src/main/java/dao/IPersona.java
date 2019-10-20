package dao;

import java.util.List;
import model.Persona;

public interface IPersona {

    void registrar(Persona persona) throws Exception;

    void modificar(Persona persona) throws Exception;

    void eliminar(Persona persona) throws Exception;

    List<Persona> listarPer() throws Exception;

    List<Persona> listarPerTip() throws Exception;
    
    List<Persona> listarLector() throws Exception;

}
