package dto;

public class SignUpResponseDto {
    public boolean isSuccess;
    public String message;

    public SignUpResponseDto(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}

