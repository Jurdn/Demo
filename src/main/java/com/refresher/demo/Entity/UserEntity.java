package com.refresher.demo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Getter
@Setter
@ToString
public class UserEntity {

    @Id
    public String id;
    @NonNull
    public String username;
    @NonNull
    public String firstName;
    @NonNull
    public String lastName;
    @NonNull
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
