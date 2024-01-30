/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { CompteDAO } from './compteDAO';


export interface TransactionDAO { 
    id?: number;
    dateCreation?: string;
    montant?: number;
    libelleTran?: string;
    typeTransaction?: TransactionDAO.TypeTransactionEnum;
    compte?: CompteDAO;
}
export namespace TransactionDAO {
    export type TypeTransactionEnum = 'DEPOT' | 'RETRAIT' | 'VIREMENT';
    export const TypeTransactionEnum = {
        Depot: 'DEPOT' as TypeTransactionEnum,
        Retrait: 'RETRAIT' as TypeTransactionEnum,
        Virement: 'VIREMENT' as TypeTransactionEnum
    };
}


