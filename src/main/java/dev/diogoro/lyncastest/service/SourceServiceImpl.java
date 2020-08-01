package dev.diogoro.lyncastest.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SourceServiceImpl implements SourceService{

	@Override
	public String obterLinkRepositorio() {
		return "https://github.com/diogoro/lyncas-test";
	}

}
