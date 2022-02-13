package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.NumberFormat;


public class Controller {
    private static final int MONTH_IN_THE_YEAR = 12;
    private static final int NUMBER_TO_PERCENT = 100;
    private double principal = 0;
    private double interestRate = 0;
    private int howManyYears = 0;

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
    void buttonNeueBerechnungClicked(ActionEvent event){
        buttonBerechnen.setDisable(false);
        textFieldMonthlyPayment.setText("");
        textFieldTotalPayment.setText("");
        textFieldInterestRate.setText("");
        textFieldPrincipal.setText("");
        textFieldTerm.setText("");

    }

    private boolean validationPrincipal() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Falsche eingabe");
        try {
            principal = Double.parseDouble(textFieldPrincipal.getText());
            if (principal >= 0)
                return true;
            else {
                alert.setContentText("Bitte nehnen Sie Ihre Darlehensbetrag in positive Zahl");
                alert.showAndWait();
                textFieldPrincipal.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Bitte nehnen Sie Ihre Darlehensbetrag");
            alert.showAndWait();
            textFieldPrincipal.setText("");
            return false;
        }
    }
    private boolean validationInterestRate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Falsche eingabe");
        try {
            interestRate = Double.parseDouble(textFieldInterestRate.getText());
            if (interestRate >= 0 && interestRate <= 100 )
                return true;
            else {
                alert.setContentText("Bitte nehnen Sie Ihre kredit Sollzins in zalh 0 bis 100");
                alert.showAndWait();
                textFieldInterestRate.setText("");
                return false;
            }
        }
        catch (NumberFormatException e) {
            alert.setContentText("Bitte nehnen Sie Ihre kredit Sollzins");
            alert.showAndWait();
            textFieldInterestRate.setText("");
            return false;
        }


    }
    private boolean validationHowManyYears(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Falsche eingabe");
        try {
            howManyYears = Integer.parseInt(textFieldTerm.getText()) ;
            if (howManyYears >= 0)
                return true;
            else{
                alert.setContentText("Bitte nehnen Sie Ihre kredit Laufzeit in positive Zahl");
                alert.showAndWait();
                textFieldTerm.setText("");
                return false;
            }
        }
        catch (NumberFormatException e){
            alert.setContentText("Bitte nehnen Sie Ihre kredit Laufzeit in Jahre");
            alert.showAndWait();
            textFieldTerm.setText("");
            return false;
        }
    }

    private void resultPayment() {
        textFieldTotalPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format
                (totalPayback(monthlyPayment(principal , monthlyInterestRate(percentInterestRate(interestRate , NUMBER_TO_PERCENT) , MONTH_IN_THE_YEAR) ,
                numberOfPayments(howManyYears , MONTH_IN_THE_YEAR)),numberOfPayments(howManyYears , MONTH_IN_THE_YEAR)))));
    }

    private void resultMonthlyPayment(){
        textFieldMonthlyPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format
                (monthlyPayment(principal , monthlyInterestRate(percentInterestRate(interestRate , NUMBER_TO_PERCENT) , MONTH_IN_THE_YEAR) ,
                numberOfPayments(howManyYears , MONTH_IN_THE_YEAR)))));
    }

    //number to percent
    private double percentInterestRate (double interestRate , int numberToPercent){
        double percentInterestRate = interestRate / numberToPercent;
        return percentInterestRate;

    }
    //monats zins
    private double monthlyInterestRate (double percentInterestRate , int monthInTheYear){
        double monthlyInterestRate = percentInterestRate / monthInTheYear;
        return monthlyInterestRate;
    }

    // rate zahl
    private int numberOfPayments (int howManyYear, int monthInTheYear){
        int numberOfPayments = howManyYear * monthInTheYear;
        return numberOfPayments;
    }
    //gesamt kredit
    private double totalPayback (double monthlyPayment , int numberOfPayments){
        double totalPayback = monthlyPayment * numberOfPayments;
        return totalPayback;
    }

    //monats rate
    private double monthlyPayment (double principal , double monthlyInterestRate , int numbersOfPayments){
        double monthlyPayment = principal *
                (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate , numbersOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate , numbersOfPayments)) -1);
        return monthlyPayment;
    }
}
