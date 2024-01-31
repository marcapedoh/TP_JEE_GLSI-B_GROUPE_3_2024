package tp.jEE.Groupe3.Controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tp.jEE.Groupe3.DAO.ClientDAO;
import tp.jEE.Groupe3.DAO.CompteDAO;

import java.util.List;

import static tp.jEE.Groupe3.Constant.Utils.APP_ROOT;

@Api(APP_ROOT+"clients")
public interface ClientAPI {
    @GetMapping(value = APP_ROOT+"clients/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client", notes=" cette methode permet de rechercher un client par son ID",response = ClientDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le client a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucun client n'est trouvé dans la base de donnée")
    })
    ClientDAO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"clients/findByNom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client", notes=" cette methode permet de rechercher un client par son nom",response = ClientDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le client a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucun client n'est trouvé dans la base de donnée")
    })
    ClientDAO findByNom(@PathVariable("nom") String nom);

    @GetMapping(value = APP_ROOT+"clients/findByUsername/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client", notes=" cette methode permet de rechercher un client par son username",response = ClientDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le client a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucun client n'est trouvé dans la base de donnée")
    })
    ClientDAO findByUsername(@PathVariable("username") String username);
    @GetMapping(value = APP_ROOT+"clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des clients", notes=" cette methode permet de retourner des clients ",responseContainer = "List<ClientDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des clients/liste vide")
    })
    List<ClientDAO> findALl();
    @DeleteMapping(value = APP_ROOT+"clients/delete/{id}")
    void delete(@PathVariable("id") Integer id);
}
