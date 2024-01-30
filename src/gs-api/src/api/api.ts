export * from './authenticationController.service';
import { AuthenticationControllerService } from './authenticationController.service';
export * from './clientController.service';
import { ClientControllerService } from './clientController.service';
export * from './compteController.service';
import { CompteControllerService } from './compteController.service';
export * from './transactionController.service';
import { TransactionControllerService } from './transactionController.service';
export const APIS = [AuthenticationControllerService, ClientControllerService, CompteControllerService, TransactionControllerService];
