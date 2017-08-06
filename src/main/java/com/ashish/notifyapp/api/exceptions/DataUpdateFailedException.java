/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.exceptions;

/**
 *
 * @author Ashish
 */
public class DataUpdateFailedException extends RuntimeException {

    public DataUpdateFailedException(String message) {
        super(message);
    }
}
