/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lydia.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Lydia (1672014)
 */
public class Utility {

    public static boolean isEmptyField(TextField... textFields) {
        for (TextField tic : textFields) {
            if (tic.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            showAlert("Not a number", "Your input is not a number",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public static void showAlert(String header,
            String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static int StoI(String s) {
        return Integer.valueOf(s);

    }

}
