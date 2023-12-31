package tp.jEE.Groupe3.Controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tp.jEE.Groupe3.DAO.TransactionDAO;

import java.util.List;

import static tp.jEE.Groupe3.Constant.Utils.APP_ROOT;

@Api(APP_ROOT+"transactions")
public interface TransactionAPI {
    @PostMapping(value = APP_ROOT + "transactions/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer une transaction", notes=" cette methode permet d'enregistrer et modifier une transaction",response = TransactionDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "l'object transaction a ete bien crée ou modifer")
    })
    TransactionDAO save(@RequestBody TransactionDAO transactionDAO);
    @GetMapping(value = APP_ROOT+"transactions/findById/{idTransaction}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une transaction", notes=" cette methode permet de rechercher une transaction par son ID",response = TransactionDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la transaction a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucune transaction n'est trouvé dans la base de donnée")
    })
    TransactionDAO findById(@PathVariable("idTransaction") Integer id);
    @GetMapping(value = APP_ROOT+"transactions/findByLibelle/{lebelle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une transaction", notes=" cette methode permet de rechercher une transaction par son libelle",response = TransactionDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la transaction a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "aucune transaction n'est trouvé dans la base de donnée")
    })
    TransactionDAO findByLibelle(@PathVariable("libelle") String libelle);
    @GetMapping(value = APP_ROOT+"transactions/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des transactions", notes=" cette methode permet de rechercher un article avec tous ses attributs",responseContainer = "List<TransactionDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des transactions/liste vide")
    })
    List<TransactionDAO> findAll();
    @DeleteMapping(value = APP_ROOT+"/article/delete/{idTransaction}")
    void delete(@PathVariable("idTransaction") Integer id);
}
