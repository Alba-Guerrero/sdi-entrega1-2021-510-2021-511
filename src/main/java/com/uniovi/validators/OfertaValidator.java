package com.uniovi.validators;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.Oferta;


@Component
public class OfertaValidator implements Validator{

	

	@Override
	public boolean supports(Class<?> clazz) {
		return Oferta.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Oferta oferta = (Oferta) target;

		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "Error");
		if (oferta.getDescripcion().length()<5 ) {
			errors.rejectValue("descripcion", "Error.oferta.descripcion");
		}
		if (oferta.getDetalle().length()<5 ) {
			errors.rejectValue("detalle", "Error.oferta.detalle");
		}
		if (oferta.getPrecio()<0) {
			errors.rejectValue("precio", "Error.oferta.precio");
		}
		
	}

}
