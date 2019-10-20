package dao;

import java.util.List;
import model.PrestamoDet;

public interface IPrestamoDet {

    void registra(PrestamoDet PDet) throws Exception;

    void modificar(PrestamoDet PDet) throws Exception;

    void eliminar(PrestamoDet PDet) throws Exception;

    List<PrestamoDet> ListarPreDet() throws Exception;
}
