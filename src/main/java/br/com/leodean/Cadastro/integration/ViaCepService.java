package br.com.leodean.Cadastro.integration;

import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.integration.interfaces.IViaCepService;
import br.com.leodean.Cadastro.domain.integration.ViaCepResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService implements IViaCepService {
    @Value("${cep.url}")
    private String cepUrl;

    /**
     * Fazendo requsição http com restTamplete e formando a url de requisição
     * @param cep
     * @return
     */
    @Override
    public ViaCepResponse getCep(String cep){
        try {
            RestTemplate restTemplate = new RestTemplate();

            var url = cepUrl + cep + "/json/";
            ResponseEntity<ViaCepResponse> response = restTemplate.getForEntity(url, ViaCepResponse.class );

            return response.getBody();
        }catch (ExceptionApiCadastro e){
            throw e;
        }catch (Exception e){
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CEP-01");
        }

    }

    /**
     * Fazendo requsição http com restTamplete utilizando Builder para gerar a url, utilizando map para criar parametros de requisição na ulr
     * @param cep
     * @return
     */
    //@Override
//    public ViaCepResponse getCep(String cep){
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> urlParams = new HashMap<>();
//        urlParams.put("cep", cep);
//        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(cepUrl);
//        ResponseEntity<ViaCepResponse> response = restTemplate.getForEntity(uri.buildAndExpand(urlParams).toUri(), ViaCepResponse.class );
//
//        return response.getBody();
//    }
}
