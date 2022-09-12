package com.capg.ProfileService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Profiless")
public class Profile {
    @Transient
    public static final String SEQUENCE_NAME = "profile_sequence";

    @Id
    private int profileId;

    @NotNull(message = "Name cannot be empty")
    @Size(min=3,message=" Name should be minimum 3 Characters")
    private String fullName;

    @NotNull(message="MailId cannot be null")
    @Email
    private String emailId;

    @NotNull
    @Size(min=8, message="it should not bee empty")
    private String password;

    @NotNull(message="Contact cannot be empty")
    @Size(min=10,max = 10,message=" Contact should be 10 digit number ")
    private String mobileNumber;

    //@NotNull(message = "cannot be empty")
    private LocalDate dateOfBirth;

    @NotNull(message=" Gender cannot be null")
    @Size(max=10,message=" Gender should be valid")
    private String gender;

   // @NotNull(message = "add address for delivery")
    private List<Address> address;



}
