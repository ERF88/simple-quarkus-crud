package com.github.erf88;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

	@GET
	public List<Produto> buscarTodosProdutos() {
		return Produto.listAll();
	}

	@GET
	@Path("{id}")
	public Produto buscarUmProduto(@PathParam("id") Long id) {
		Optional<Produto> result = Produto.findByIdOptional(id);

		if (result.isPresent()) {
			return result.get();
		} else {
			throw new NotFoundException();
		}
	}
	
	@POST
	@Transactional
	public void cadastrarProduto(ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setValor(dto.getValor());
		produto.persist();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public void atualizarProduto(@PathParam("id") Long id, ProdutoDTO dto) {
		Optional<Produto> result = Produto.findByIdOptional(id);

		if (result.isPresent()) {
			Produto produto = result.get();
			produto.setNome(dto.getNome());
			produto.setValor(dto.getValor());
			produto.persist();
		} else {
			throw new NotFoundException();
		}

	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void deletarProduto(@PathParam("id") Long id) {
		Optional<Produto> result = Produto.findByIdOptional(id);

		result.ifPresentOrElse(Produto::delete, () -> {
			throw new NotFoundException();
		});

	}

}
