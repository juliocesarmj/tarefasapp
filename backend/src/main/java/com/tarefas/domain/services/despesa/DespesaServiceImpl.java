package com.tarefas.domain.services.despesa;

import com.tarefas.application.dtos.despesa.DespesaDTO;
import com.tarefas.domain.model.Despesa;
import com.tarefas.domain.services.auth.AuthService;
import com.tarefas.infrastructure.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DespesaServiceImpl implements DespesaService {

    private final DespesaRepository despesaRepository;
    private final AuthService authService;

    @Override
    public DespesaDTO novaDespesa(DespesaDTO dto, HttpServletRequest request) {

        Despesa despesa = Despesa.novo(dto);
        try {

            despesa.atribuirDespesaUsuario(authService.authenticated(request));

        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao associar usuario a despesa" + e);
        }
        despesaRepository.save(despesa);
        return dto;
    }

    @Override
    public Despesa atualizar(int id, Despesa despesa) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Despesa> listarDespesas(int usuarioId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Despesa> todasDespesas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Despesa findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Despesa deletar(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
