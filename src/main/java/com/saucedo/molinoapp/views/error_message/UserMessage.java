package com.saucedo.molinoapp.views.error_message;

import com.saucedo.molinoapp.Config;

public class UserMessage {
	public static final String NO_ROLE_SELECT="Debe seleccionar almenos un rol";
	public static final String PASSWORD_DIFRENTES="Las passwords no coinciden";
	public static final String FIALD_NULL="No puede estar vacio";
	public static final String FIALD_LENGTH_MINIM_USERNAME="El username debe ser como minimo de "+Config.MIN_LENGHT_PASSWORD_AND_PASSWORD+" caracteres";
	public static final String FIALD_LENGTH_MINIM_PASSWORD="La contraseña debe ser como minimo de "+Config.MIN_LENGHT_PASSWORD_AND_PASSWORD+" caracteres";
}
