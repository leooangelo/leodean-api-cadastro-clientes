package br.com.leodean.Cadastro.integration.interfaces;

import br.com.leodean.Cadastro.domain.integration.ViaCepResponse;

public interface IViaCepService {

    ViaCepResponse getCep(String cep);
}
