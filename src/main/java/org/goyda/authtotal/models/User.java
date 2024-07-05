package org.goyda.authtotal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goyda.authtotal.utils.HashManager;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /**
     * +     * Устанавливает пароль для пользователя, хешируя предоставленный пароль с использованием HashManager.
     * +     *
     * +     * @param  password  пароль, который нужно захешировать и установить
     * +
     */
    public void setPassword(String password) {
        this.password = HashManager.hashPassword(password);
    }

    private String password;


}
