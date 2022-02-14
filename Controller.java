package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.NumberFormat;


public class Controller {
    public static final int MONTH_IN_THE_YEAR = 12;
    public static final int NUMBER_TO_PERCENT = 100;
    public double principal = 0;
    public double interestRate = 0;
    public int howManyYears = 0;

    @FXML
    TextField textFieldPrincipal;

    @FXML
    TextField textFieldInterestRate;

    @FXML
    TextField textFieldTerm;

    @FXML
    TextField textFieldMonthlyPayment;

    @FXML
    TextField textFieldTotalPayment;

    @FXML
    Button buttonBerechnen;

    @FXML
    Button buttonNeueBerechnung;

    @FXML
    void buttonBerechnenClicked(ActionEvent event) {
        if (validationPrincipal()) {
            if (validationInterestRate()) {
                if (validationHowManyYears()) {
                    resultMonthlyPayment();
                    resultPayment();
                    buttonBerechnen.setDisable(true);
                }
            }
        }
    }

    @FXML
    void buttonNeueBerechnungClicked(ActionEvent event) {
        buttonBerechnen.setDisable(false);
        textFieldMonthlyPayment.setText("");
        textFieldTotalPayment.setText("");
        textFieldInterestRate.setText("");
        textFieldPrincipal.setText("");
        textFieldTerm.setText("");

    }

    private boolean validationPrincipal() {
        try {
            principal = Double.parseDouble(textFieldPrincipal.getText());
            if (principal >= 0)
                return true;
            else {
                alert("Bitte nennen Sie Ihre Darlehensbeträge in positive Zahl");
                textFieldPrincipal.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            alert("Bitte nennen Sie Ihre Darlehensbeträge");
            textFieldPrincipal.setText("");
            return false;
        }
    }

    private boolean validationInterestRate() {
        try {
            interestRate = Double.parseDouble(textFieldInterestRate.getText());
            if (interestRate >= 0 && interestRate <= 100)
                return true;
            else {
                alert("Bitte nennen Sie Ihre Kredit Sollzins in zahl 0 bis 100");
                textFieldInterestRate.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            alert("Bitte nennen Sie Ihre Kredit Sollzins");
            textFieldInterestRate.setText("");
            return false;
        }
    }

    private boolean validationHowManyYears() {
        try {
            howManyYears = Integer.parseInt(textFieldTerm.getText());
            if (howManyYears >= 0)
                return true;
            else {
                alert("Bitte nennen Sie Ihre Kreditlaufzeit in positive Zahl");
                textFieldTerm.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            alert("Bitte nennen Sie Ihre Kreditlaufzeit in Jahre");
            textFieldTerm.setText("");
            return false;
        }
    }

    private void resultPayment() {
        textFieldTotalPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format
                (totalPayback(monthlyPayment(principal, monthlyInterestRate(percentInterestRate(interestRate, NUMBER_TO_PERCENT), MONTH_IN_THE_YEAR),
                        numberOfPayments(howManyYears, MONTH_IN_THE_YEAR)), numberOfPayments(howManyYears, MONTH_IN_THE_YEAR)))));
    }

    private void resultMonthlyPayment() {
        textFieldMonthlyPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format
                (monthlyPayment(principal, monthlyInterestRate(percentInterestRate(interestRate, NUMBER_TO_PERCENT), MONTH_IN_THE_YEAR),
                        numberOfPayments(howManyYears, MONTH_IN_THE_YEAR)))));
    }

    //Alert fenster
    private void alert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Falsche Eingabe");
        alert.setContentText(content);
        alert.showAndWait();
    }

    //number to percent
    public double percentInterestRate(double interestRate, int numberToPercent) {
        return interestRate / numberToPercent;

    }

    //monats zins
    public double monthlyInterestRate(double percentInterestRate, int monthInTheYear) {
        return percentInterestRate / monthInTheYear;
    }

    // rate zahl
    public int numberOfPayments(int howManyYear, int monthInTheYear) {
        return howManyYear * monthInTheYear;
    }

    //gesamt kredit
    public double totalPayback(double monthlyPayment, int numberOfPayments) {
        return monthlyPayment * numberOfPayments;
    }

    //monats rate
    public double monthlyPayment(double principal, double monthlyInterestRate, int numbersOfPayments) {
        return principal *
                (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numbersOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate, numbersOfPayments)) - 1);
    }
}
