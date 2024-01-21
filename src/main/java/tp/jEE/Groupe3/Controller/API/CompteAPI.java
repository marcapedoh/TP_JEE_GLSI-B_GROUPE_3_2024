package tp.jEE.Groupe3.Controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.iban4j.Iban;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.DAO.TransactionDAO;

import java.util.List;

import static tp.jEE.Groupe3.Constant.Utils.APP_ROOT;

@Api(APP_ROOT+"comptes")
public interface CompteAPI {
    @PostMapping(value = APP_ROOT+"comptes/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer un compte", notes=" cette methode permet d'enregistrer et modifier un compte",response = CompteDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "l'object compte a ete bien crée ou modifer")
    })
    CompteDAO save(@RequestBody CompteDAO compteDAO);
    @GetMapping(value = APP_ROOT+"comptes/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une compte", notes=" cette methode permet de rechercher un compte par son ID",response = CompteDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le compte a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucun compte n'est trouvé dans la base de donnée")
    })
    CompteDAO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"comptes/findByNumeroCpt/{iban}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un compte", notes=" cette methode permet de rechercher un compte par son ID",response = CompteDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la transaction a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucune transaction n'est trouvé dans la base de donnée")
    })
    CompteDAO findByNumeroCpt(@PathVariable("iban") String iban);
    @GetMapping(value = APP_ROOT+"comptes/rechargerCompte/{montant}/{iban}",produces = MediaType.APPLICATION_JSON_VALUE)
    boolean rechargerCompte(@PathVariable("montant") double montant,@PathVariable("iban") String iban);
    @GetMapping(value = APP_ROOT+"comptes/faireRetrait/{montant}/{iban}",produces = MediaType.APPLICATION_JSON_VALUE)
    boolean faireRetrait(@PathVariable("montant") double montant,@PathVariable("iban") String iban);


    @PostMapping(value = APP_ROOT+"comptes/faireVirement/{iban1}/{iban2}/{montant}",produces = MediaType.APPLICATION_JSON_VALUE)
    TransactionDAO faireVirement(@PathVariable("iban1") String iban1,@PathVariable("iban2") String iban2,@PathVariable("montant") double montant);
    @GetMapping(value = APP_ROOT+"comptes/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des comptes", notes=" cette methode permet de retourner des comptes ",responseContainer = "List<CompteDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des comptes/liste vide")
    })
    List<CompteDAO> findAll();
    @DeleteMapping(value = APP_ROOT+"comptes/delete/{id}")
    void delete(@PathVariable("id") Integer id);
}
