package com.bazar.sistema.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bazar.sistema.model.Categoria;
import com.bazar.sistema.repository.CategoriaRepository;


@Controller
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
//	Navega até o index
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(Model model) {	
//		model.addAttribute("categoria", new Categoria());
//		return "/index";
//	}
	
	
//	Lista categorias/Verifica se existe o arquivo html categoria
	@RequestMapping(value = "/categoria", method = RequestMethod.GET)	
	public String listar(Model model) { 
		List<Categoria> categorias = categoriaRepository.findAll();	
		model.addAttribute("categorias", categorias);
		return "/categoria";
	}
	
	
//	Verifica se existe o arquivo html cadastrar-categoria
	@RequestMapping(value = "/cadastrar-categoria")
	public String novo() {
		return "/cadastrar-categoria";
	}
	
	
//	Salva e edita categoria
	@RequestMapping(value = "/salvar-categoria")	
	public ModelAndView salvar(@RequestBody @RequestParam(value = "nome", required=false) String nome) {
		Categoria categoria = new Categoria();
		categoria.setNome(nome);

		categoriaRepository.save(categoria);		
		
		return new ModelAndView("redirect:/cadastrar-categoria"); 
		
	}
	
//	Remove categorias
	@RequestMapping(value = "/categoria/excluir/{codigo}")
	public ModelAndView excluir(@PathVariable("codigo") Long id) {
		categoriaRepository.deleteById(id);
		
		return new ModelAndView("redirect:/categoria");
		
	}
	
//	Atualiza categorias
	@RequestMapping(value = "/categoria/atualizar/{idCategoria}", method = RequestMethod.GET)
	public String atualizar(@PathVariable(value = "idCategoria") Long id, Model model) {
		
		Categoria categoria = categoriaRepository.findById(id).get();
		model.addAttribute("categoria", categoria);
		return "/atualizar-categoria";
	}

//	Edita categorias
	@RequestMapping(value = "/editar-categoria")
	public ModelAndView editar(@RequestParam(value = "idCategoria") Long idCategoria, 
								  @RequestParam(value = "nome", required=false) String nome) {
		
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		categoria.setNome(nome);
		categoriaRepository.save(categoria);
		
		return new ModelAndView("redirect:/categoria");
		
	}
//	
}
