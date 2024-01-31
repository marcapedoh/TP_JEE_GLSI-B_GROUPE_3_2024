package tp.jEE.Groupe3.Controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tp.jEE.Groupe3.DAO.TransactionDAO;

import java.time.LocalDate;
import java.util.List;

import static tp.jEE.Groupe3.Constant.Utils.APP_ROOT;

@Api(APP_ROOT+"transactions")
public interface TransactionAPI {
    @GetMapping(value = APP_ROOT+"transactions/findById/{idTransaction}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une transaction", notes=" cette methode permet de rechercher une transaction par son ID",response = TransactionDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la transaction a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucune transaction n'est trouvé dans la base de donnée")
    })
    TransactionDAO findById(@PathVariable("idTransaction") Integer id);
    @GetMapping(value = APP_ROOT+"transactions/findByLibelle/{libelle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une transaction", notes=" cette methode permet de rechercher une transaction par son libelle",response = TransactionDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la transaction a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucune transaction n'est trouvé dans la base de donnée")
    })
    TransactionDAO findByLibelle(@PathVariable("libelle") String libelle);
    @GetMapping(value = APP_ROOT+"transactions/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des transactions", notes=" cette methode permet de rechercher une liste de transaction avec tous ses attributs",responseContainer = "List<TransactionDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des transactions/liste vide")
    })
    List<TransactionDAO> findAll();

    @GetMapping(value = APP_ROOT+"transactions/findAllPerPeriod/{dateDebut}/{dateFin}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des transactions dans une periode ", notes=" cette methode permet de rechercher une liste des transactions avec tous ses attributs",responseContainer = "List<TransactionDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des transactions/liste vide")
    })
    List<TransactionDAO> findAllPerPeriod(@PathVariable("dateDebut") LocalDate dateDebut,@PathVariable("dateFin") LocalDate dateFin);
    @GetMapping(value = APP_ROOT+"transactions/findAllByClientId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des transactions pour un client ", notes=" cette methode permet de rechercher une liste des transactions avec tous ses attributs pour un client",responseContainer = "List<TransactionDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des transactions/liste vide")
    })
    List<TransactionDAO> findAllByClientId(@PathVariable("id") Integer id);


    @GetMapping(value = APP_ROOT+"transactions/findAllByClientIdBetweenDate/{dateDebut}/{dateFin}/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des transactions pour un client dans une période ", notes=" cette methode permet de rechercher une liste des transactions avec tous ses attributs pour un client dans une période donnée",responseContainer = "List<TransactionDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des transactions/liste vide")
    })
    List<TransactionDAO> findAllByClientIdBetweenDate(@PathVariable("dateDebut") LocalDate dateDebut,@PathVariable("dateFin") LocalDate dateFin,@PathVariable("id") Integer id);
}
