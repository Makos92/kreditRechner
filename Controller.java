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
        berechnen();
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

    public void berechnen(){
        double principal = 0;
        double interestRate = 0;
        double numbersOfPayments = 0;
        try {
            principal = Double.parseDouble(textFieldPrincipal.getText());
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falsche eingabe");
            alert.setContentText("Bitte nehnen Sie Ihre Darlehensbetrag");
            alert.showAndWait();
            textFieldPrincipal.setText("");
            return;
        }
        try {
            interestRate = Double.parseDouble(textFieldInterestRate.getText()) / NUMBER_TO_PERCENT;
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falsche eingabe");
            alert.setContentText("Bitte nehnen Sie Ihre kredit Sollzins");
            alert.showAndWait();
            textFieldInterestRate.setText("");
            return;
        }
        try {
            numbersOfPayments = Double.parseDouble(textFieldTerm.getText()) * MONTH_IN_THE_YEAR;
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falsche eingabe");
            alert.setContentText("Bitte nehnen Sie Ihre kredit Laufzeit in Jahre");
            alert.showAndWait();
            textFieldTerm.setText("");
            return;
        }

        double monthlyInterestRate = interestRate / MONTH_IN_THE_YEAR;
        double monthlyPayment = principal *
                (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate , numbersOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate , numbersOfPayments)) -1);
        double totalPayback = monthlyPayment * numbersOfPayments;

        textFieldTotalPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(totalPayback)));
        textFieldMonthlyPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(monthlyPayment)));

        buttonBerechnen.setDisable(true);
    }




}
