package com.br.tarefas.api.constantes;

public abstract class PathEndpoints {

    public static final String AUTENTICAR = "/api/auth";
    public static final String USUARIO_CONTROLLER_BASE = "/api/usuario";
    public static final String CRIAR_CONTA = USUARIO_CONTROLLER_BASE + "/criar-conta";

    public static final String ATUALIZAR_DADOS = USUARIO_CONTROLLER_BASE + "/atualizar-dados";
    private PathEndpoints() {}
}
