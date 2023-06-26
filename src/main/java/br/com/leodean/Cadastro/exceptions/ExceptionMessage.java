package br.com.leodean.Cadastro.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

@Component
public class ExceptionMessage {

    private static Environment environment;
    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        environment = this.env;
    }

    private ExceptionMessage() {
    }

    public static String buscarMessage(String codigoErro) {
        ResourceBundle messages = getMessages();
        if (!messages.containsKey(codigoErro)) {
            return codigoErro;
        }
        return messages.getString(codigoErro);
    }

    public static Boolean containsInErrors(String codigoErro) {
        return getMessages().containsKey(codigoErro);
    }

    private static ResourceBundle getMessages() {
        String language = "pt";
        String country = "BR";
        if (environment != null && isNotEmpty(environment.getProperty("errors.language"))
                && isNotEmpty(environment.getProperty("errors.country"))) {
            language = environment.getProperty("errors.language").toLowerCase();
            country = environment.getProperty("errors.country").toLowerCase();
        }
        Locale aLocale = new Locale(language, country);
        ResourceBundle messages = ResourceBundle.getBundle("erros/Erros", aLocale);
        return messages;
    }
}
