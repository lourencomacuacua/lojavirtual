package com.projeto.sistema1.converter;

import javax.xml.transform.Source;

import org.hibernate.exception.spi.ConversionContext;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDouble implements org.springframework.core.convert.converter.Converter<String, Double>{

	@Override//Esse converter vai ser chamdo automaticamento pelo spring no memoento em que ele estiver a submeter o valor do formulario para o cc
	public Double convert(String source) {
		source=source.trim();
		if(source.length() >0) {
			source=source.replace(".", "").replace(",",".");//Primeiro removo os pontos que separam a casa dos 1000 e depois substituir a vírgula da casa decila por . pois o banco de dados o campo double é reconhecido por . e nao ,
			return Double.parseDouble(source);
		}
		return 0.;
	}

}
