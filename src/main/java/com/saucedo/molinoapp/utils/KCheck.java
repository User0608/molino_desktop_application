package com.saucedo.molinoapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KCheck {
	public static final int DEFALUT_MIN_LENGTH = 4;
	public static String MESSAGE_NO_STARTED = "Dato a validad no definido";
	public static String MESSAGE_OK = "success";
	public static String MESSAGE_ERROR_NUMBERS = "Solo esta permitido numeros";
	public static String MESSAGE_ERROR_VALID_CARACTERS = " ,no es uncaracter permitido";

	public static String MESSAGE_ERROR_EMPTY_SPACES = "No se permite espacios en blanco";
	public static String MESSAGE_ERROR_START_SPACES = "Hay espacios al inicio";
	public static String MESSAGE_ERROR_END_SPACES = "Hay espacios al final";
	public static String MESSAGE_ERROR_MANY_SPACES = "Demasiados espacios en blanco";

	public static String MESSAGE_ERROR_NO_EMAIL = "El email ingresado no es valido";

	public static String MESSAGE_ERROR_MIN_LENGTH = "La cantidad minima de carácteres es ";
	public static String MESSAGE_ERROR_MAX_LENGTH = "La cantidad maxima de carácteres es ";
	public static String MESSAGE_ERROR_LENGTH = "El numero de caracteres debe ser ";
	public static String NO_IS_NUMBER = "El dato ingresado debe ser un numero";
	private boolean state;
	private String message;

	public KCheck() {
		state = true;
		message = MESSAGE_NO_STARTED;
	}

	public Filtro in(String target) {
		message = MESSAGE_OK;
		return new Filtro(target);
	}

	public String getMessage() {
		return message;
	}

	// Definicion de clase filtro..
	public class Filtro {
		private String target;

		private Filtro(String target) {
			this.target = target;
		}

		public Filtro onlyNumbers() {
			if (!state)
				return this;
			String validCharacters = "1234567890";
			for (char a : this.target.toCharArray()) {
				if (validCharacters.indexOf(a) == -1) {
					state = false;
					message = MESSAGE_ERROR_NUMBERS;
					break;
				}
			}
			return this;
		}
		public Filtro onlyBasicsCaracteres() {
			if (!state)
				return this;
			String validCharacters = "!\"#$%&'()*+,./:;<=>?@`[\\]^_`{|}~";
			for (char a : this.target.toCharArray()) {
				if (validCharacters.indexOf(a)!=-1) {
					state = false;
					message = a + MESSAGE_ERROR_VALID_CARACTERS;
					break;
				}
			}
			return this;
		}
		
		
		
		public Filtro noEmptySpaces() {
			if (!state)
				return this;
			if (this.target.indexOf(" ") != -1) {
				state = false;
				message = MESSAGE_ERROR_EMPTY_SPACES;
			}
			return this;
		}

		public Filtro noStartWithEmptySpaces() {
			if (!state)
				return this;
			if (this.target.startsWith(" ")) {
				state = false;
				message = MESSAGE_ERROR_START_SPACES;
			}
			return this;
		}

		public Filtro noEndWithEmptySpaces() {
			if (!state)
				return this;
			if (this.target.endsWith("  ")) {
				state = false;
				message = MESSAGE_ERROR_END_SPACES;
			}
			return this;
		}



		public Filtro noInvalidSpaces() {
			if (!state)
				return this;
			if (this.target.indexOf("   ") != -1) {
				state = false;
				message = MESSAGE_ERROR_MANY_SPACES;
			}
			return this;
		}

		public Filtro isEmail() {
			if (!state)
				return this;
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(this.target);
			if (!mather.find()) {
				state = false;
				message = MESSAGE_ERROR_NO_EMAIL;
			}
			return this;
		}

		public Filtro minLen(int length) {
			if (!state)
				return this;
			if (this.target.length() < length) {
				message = MESSAGE_ERROR_MIN_LENGTH + length;
				state = false;
			}
			return this;
		}

		public Filtro minLen() {
			if (!state)
				return this;
			if (this.target.length() < DEFALUT_MIN_LENGTH) {
				message = MESSAGE_ERROR_MIN_LENGTH + DEFALUT_MIN_LENGTH;
				state = false;
			}
			return this;
		}

		public Filtro maxLen(int length) {
			if (!state)
				return this;
			if (this.target.length() > length) {
				message = MESSAGE_ERROR_MAX_LENGTH + length;
				state = false;
			}
			return this;
		}

		public Filtro allowLength(int length) {
			if (!state)
				return this;
			if (this.target.length() != length) {
				message = MESSAGE_ERROR_LENGTH + length;
				state = false;
			}
			return this;
		}
		public Filtro isNumber() {
			if (!state)
				return this;
			try {
				Double.parseDouble(target);
			}catch(NumberFormatException e){
				message = NO_IS_NUMBER;
				state = false;
			}
			return this;
		}

		public boolean ok() {
			return state;
		}

		public boolean notOk() {
			return !state;
		}
	}

	public static void main(String[] args) {
		String target = "38928493";
		KCheck check = new KCheck();
		System.out.println(check.in(target).noEmptySpaces().onlyNumbers().ok());
		System.out.println(check.getMessage());
	}
}
