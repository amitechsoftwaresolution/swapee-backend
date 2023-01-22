package io.swapee.swapeebackend.common_library.enums;

/**
 * @author Minoltan Issack on 5/14/2022
 */
public enum ResponseMessage {
    MISSING_REQUIRED_FIELD("Missing required field."),
    EMAIL_ALREADY_EXISTS("Email already exists."),
    INVALID_EMAIL_ADDRESS("Invalid email address."),
    EMAIL_NOT_EXISTS("Email address does not exists"),
    INCORRECT_PASSWORD("Incorrect password."),
    RECORD_DOES_NOT_EXISTS("Record does not exists."),
    UNAUTHORIZED_REQUEST("Unauthorized request."),
    SOMETHING_WENT_WRONG("Something went wrong."),
    REST_API_ERROR("Rest api exception"),
    ERROR_UPLOADING("Error while uploading file. Please try again."),
    INVALID_REFERENCE_ID("Invalid reference id."),
    HTTP_SENDING_FAILED("HTTP request sending failed."),
    SUCCESSFULLY_SENT("Successfully sent."),
    INVALID_USER_TOKEN("Invalid user token"),
    PAYMENT_UNSUCCESSFUL("Payment unsuccessful"),
    INVALID_MOBILE_FORMAT("Invalid mobile number format."),
    PAYMENT_SUCCESSFUL("Payment successful."),
    PAYMENT_TOKEN_EXPIRE("Payment Failed. Payment Gateway Access token, Refresh token both Expired."),
    INVALID_MOBILE_NUMBER("Mobile number should starts with 94 and 10 digit number."),
    REQUEST_PROCESSING_FAILED("Request processing failed."),
    SUCCESSFULLY_ADDED("Successfully added."),
    SUCCESSFULLY_DELETED("Successfully deleted."),
    SUCCESSFULLY_UPDATED("Successfully updated.");
    private String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

