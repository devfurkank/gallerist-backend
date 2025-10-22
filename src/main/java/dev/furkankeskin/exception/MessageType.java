package dev.furkankeskin.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004", "Kayıt Bulunamadı!"),
    TOKEN_IS_EXPIRED("1005", "Token'ın süresi dolmuştur!"),
    USERNAME_NOT_FOUND("1006", "username bulunamadı!"),
    USERNAME_OR_PASSWORD_INVALID("1007", "Kullanıcı Adı veya Şifre hatalı!"),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh Token bulunamadı!"),
    REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh Token'ın süresi doldu!"),
    CURRENCY_RATES_IS_OCCURED("1010", "Döviz kuru alınamadı!"),
    GENERAL_EXCEPTION("9999", "Genel bir hata oluştu!");

    private String code;
    private String message;
    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
