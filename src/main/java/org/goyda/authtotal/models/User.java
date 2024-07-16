package org.goyda.authtotal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goyda.authtotal.utils.HashManager;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    private String name;
    private String password;
    @Column(name = "is_loginned")
    private boolean isLoginned = false;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    public boolean loginAvailable() {
        return ChronoUnit.DAYS.between(this.getLastLogin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()) > 3;
    }

    public boolean loginUnavailable() {
        return !loginAvailable();
    }

    public void setPassword(String password) {
        this.password = HashManager.hashPassword(password);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(HashManager.hashPassword(password));
    }

}
