package io.swapee.swapeebackend.common_library.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Minoltan Issack on 5/13/2022
 */

@Getter
@Setter
public class Response {

    private String language = "en";
    private String statusInfo;
    private String entityType;
    private Date lastModified;
    private Object data;
    private boolean success = true;
    private String errorMessage;
    private String requestedURI;

    public Response(){ lastModified = new Date();}

    public Response(Object data){
        this.data = data;
        lastModified = new Date();
        entityType = data.getClass().toString();
    }

    public Response(Object data, String statusInfo) {
        this.data = data;
        this.statusInfo = statusInfo;
        lastModified = new Date();
        entityType = data.getClass().toString();
    }

    public Response(String language, String statusInfo, String entityType, Date lastModified, Object data) {
        this.language = language;
        this.statusInfo = statusInfo;
        this.entityType = entityType;
        this.lastModified = lastModified;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                '}';
    }

}
