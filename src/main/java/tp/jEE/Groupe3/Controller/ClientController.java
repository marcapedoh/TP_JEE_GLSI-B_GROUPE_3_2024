package tp.jEE.Groupe3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tp.jEE.Groupe3.Controller.API.ClientAPI;
import tp.jEE.Groupe3.DAO.ClientDAO;
import tp.jEE.Groupe3.Service.ClientServices;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClientController implements ClientAPI {
    private ClientServices clientServices;
    @Autowired
    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @Override
    public ClientDAO findById(Integer id) {
        return clientServices.findById(id);
    }

    @Override
    public ClientDAO findByNom(String nom) {
        return clientServices.findByNom(nom);
    }

    @Override
    public List<ClientDAO> findALl() {
        return clientServices.findALl();
    }

    @Override
    public void delete(Integer id) {
        clientServices.delete(id);
    }
}
