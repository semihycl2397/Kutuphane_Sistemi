package kodluyoruz.librarysystem.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodluyoruz.librarysystem.core.utilities.Results.ErrorDataResult;

@RestControllerAdvice
public class RestExceptionHandler {

	  //Sistemde bir exception oluşursa bu methodu çağır demek.
	  //Hataları exceptions parametresi olarak verdik
	  //hataları map e ekle ve dondur.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public ErrorDataResult<Object>handleValidationException(MethodArgumentNotValidException exceptions){
	  	Map<String,String> validationErrors=new HashMap<String,String>();
	      for(FieldError fieldError :exceptions.getBindingResult().getFieldErrors()) {
	      	validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	      	
	      }
	    ErrorDataResult<Object> errors=new ErrorDataResult<Object>(validationErrors,"Validations errors");
	    return errors;
	  }
}
