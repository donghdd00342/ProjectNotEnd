package com.project.notend.notend.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenId {
    @SerializedName("id_token")
    @Expose
    private String idToken;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
