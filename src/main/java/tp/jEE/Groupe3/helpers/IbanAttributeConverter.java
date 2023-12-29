package tp.jEE.Groupe3.helpers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.iban4j.Iban;

@Converter(autoApply = true)
public class IbanAttributeConverter implements AttributeConverter<Iban, String> {

    @Override
    public String convertToDatabaseColumn(Iban iban) {
        if(iban!=null){
            return iban.toString();
        }else return null;
    }

    @Override
    public Iban convertToEntityAttribute(String dbData) {
        return (dbData != null) ? Iban.valueOf(dbData) : null;
    }
}