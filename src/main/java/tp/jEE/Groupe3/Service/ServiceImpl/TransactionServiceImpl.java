package tp.jEE.Groupe3.Service.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.ErrorCodes;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Exception.InvalidOperationException;
import tp.jEE.Groupe3.Repository.TransactionRepository;
import tp.jEE.Groupe3.Service.TransactionServices;
import tp.jEE.Groupe3.Validator.TransactionValidator;
import tp.jEE.Groupe3.models.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionServices {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDAO save(TransactionDAO transactionDAO) {
        List<String> errors= TransactionValidator.validate(transactionDAO);
        if(!errors.isEmpty()){
            log.warn("object not valid");
            throw new InvalidEntityException("la transaction n'est pas valide", ErrorCodes.TRANSACTION_NOT_VALID,errors);
        }
        return TransactionDAO.fromEntity(
                transactionRepository.save(
                        TransactionDAO.toEntity(transactionDAO)
                )
        );
    }

    @Override
    public TransactionDAO findById(Integer id) {
        if(id==null){
            log.error("vous passez une id nul pour faire une recherche en bD");
        }
        Optional<Transaction> transaction= transactionRepository.findById(id);
        return transaction.map(TransactionDAO::fromEntity).orElseThrow(() ->
                new EntityNotFoundException("aucune transaction trouvé pour cet id fourni"));
    }

    @Override
    public TransactionDAO findByLibelle(String libelle) {
        if(!StringUtils.hasLength(libelle)){
            log.error("vous êtes d'accord avec moi que si vous ne fournissez rien vous ne pouvez pas faire de recherche donc arrêtez de vouloir faire crashé l'application merchiiiiiiiiii!");
        }
        Optional<Transaction> transaction= transactionRepository.findTransactionByLibelleTran(libelle);
        return transaction.map(TransactionDAO::fromEntity).orElseThrow(()->
                new EntityNotFoundException("aucune transaction ne correspnd au libelle que vous avez passé en parametre"));
    }

    @Override
    public List<TransactionDAO> findAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("vous passer un id null en paramettre pour une suppression");
            return;
        }
        Optional<Transaction> transaction=transactionRepository.findById(id);
        if (transaction.get().getCompte()==null && transaction.get().getCompte().getClient()==null){
            transactionRepository.deleteById(id);
        }else{
            throw new InvalidOperationException("vous ne pouvez pas supprimé une transaction qui est lié à des comptes et à des clients");
        }
    }
}
