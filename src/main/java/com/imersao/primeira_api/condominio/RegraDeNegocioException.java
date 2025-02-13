package com.imersao.primeira_api.condominio;

public class RegraDeNegocioException extends RuntimeException {


    public RegraDeNegocioException(String message) {
        super(message);
    }

    public RegraDeNegocioException() {
      super("Regra de negócio não respeitada");
    }
}
