package com.tarefas.domain.services.despesa;

import com.tarefas.domain.model.Despesa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DespesaServiceImpl implements DespesaService {
	
	private final com.tarefas.domain.infrastructure.DespesaRepository despesaRepository;
	
	@Override
	public Despesa novaDespesa(Despesa despesa) {
		
		Despesa novaDespesa = new Despesa();
		novaDespesa.setTitulo(despesa.getTitulo());
		novaDespesa.setDescricao(despesa.getDescricao());
		novaDespesa.setValor(despesa.getValor());
		novaDespesa.setTipoDespesa(despesa.getTipoDespesa());
		novaDespesa.setDataDespesa(despesa.getDataDespesa());
		novaDespesa.setUsuario(despesa.getUsuario());
		
		despesaRepository.save(despesa);
		return novaDespesa;
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
