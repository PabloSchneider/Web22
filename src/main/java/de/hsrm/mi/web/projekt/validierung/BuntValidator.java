package de.hsrm.mi.web.projekt.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BuntValidator implements ConstraintValidator<Bunt, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String r,g,b;
        if(value == null || value.equals("")){
            return true;
        }
        if(!value.matches("#((([0-9]|[a-f]|[A-F]){3})|(([0-9]|[a-f]|[A-F]){6}))")){
            return false;
        }
        if(value.length() == 7){
            r = value.substring(1, 3);
            g = value.substring(3, 5);
            b = value.substring(5, 7);
        }else if(value.length() == 4){
            r = value.substring(1, 2);
            g = value.substring(2, 3);
            b = value.substring(3, 4);
        }else{
            return false;
        }
        if(r.equals(g)||r.equals(b)||b.equals(g)){
            return false;
        }
        return true;
    }
}
