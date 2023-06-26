package br.com.leodean.Cadastro.Utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatorUtils {

    public Boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
