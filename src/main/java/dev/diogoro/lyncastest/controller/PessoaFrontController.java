package dev.diogoro.lyncastest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.service.PessoaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PessoaFrontController {

	@Autowired
	private final PessoaService pessoaService;

	@RequestMapping("/")
	public ModelAndView obterListaPessoas() {
		ModelAndView mv = new ModelAndView("/pessoas");
		mv.addObject("pessoas", pessoaService.obterListaPessoas());
		return mv;
	}

	@RequestMapping("/adicionar")
	public ModelAndView adicionarPessoa(PessoaDto pessoa) {

		ModelAndView mv = new ModelAndView("/adicionarPessoa");
		mv.addObject("pessoa", pessoa);

		return mv;
	}

	@RequestMapping("/salvar")
	public ModelAndView cadastrarPessoa(PessoaDto pessoa, BindingResult result) {

		if (result.hasErrors()) {
			return adicionarPessoa(pessoa);
		}
		if (pessoa.getId() != null) {
			UUID id = pessoa.getId();
			pessoa.setId(null);
			pessoa.setDataCriacao(null);
			pessoa.setDataUltimaAtualizacao(null);
			pessoaService.atualizarPessoa(id, pessoa);
		} else {
			pessoaService.cadastrarPessoa(pessoa);
		}

		return obterListaPessoas();
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView edit(@PathVariable("id") UUID id) {

		return adicionarPessoa(pessoaService.obterPessoaPorId(id));
	}

	@RequestMapping("/apagar/{id}")
	public ModelAndView delete(@PathVariable("id") UUID id) {

		pessoaService.apagarPessoa(id);

		return obterListaPessoas();
	}

	@ModelAttribute("dateFormat")
	public String dateFormat() {
		return "dd/MM/yyyy";
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat());
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}
}
