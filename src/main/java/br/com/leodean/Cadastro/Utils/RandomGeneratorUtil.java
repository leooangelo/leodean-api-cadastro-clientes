package br.com.leodean.Cadastro.Utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomGeneratorUtil {

    public String gerarRandomID() {
        return UUID.randomUUID().toString();
    }
}
