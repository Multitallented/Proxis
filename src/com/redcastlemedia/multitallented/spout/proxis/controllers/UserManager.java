package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;


/**
 *
 * @author Multitallented
 */
public class UserManager {
    private final Proxis proxis;
    public UserManager(Proxis proxis) {
        this.proxis = proxis;
    }

    public static User getUser(String username) {
        //TODO fix this

        return null;
    }

}
