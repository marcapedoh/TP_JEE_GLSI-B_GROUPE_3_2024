package tp.jEE.Groupe3.Service;

import tp.jEE.Groupe3.DAO.ClientDAO;

import java.util.List;

public interface ClientServices {
    ClientDAO findById(Integer id);
    ClientDAO findByNom(String nom);
    List<ClientDAO> findALl();
    void delete(Integer id);
}
