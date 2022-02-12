package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.NumberFormat;


public class Controller {
    private static final int MONTH_IN_THE_YEAR = 12;

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
    void buttonBerechnenClicked(ActionEvent event){
        double principal = Double.valueOf(textFieldPrincipal.getText());
        double interestRate = Double.valueOf(textFieldInterestRate.getText()) / 100;
        double numbersOfPayments = Double.valueOf(textFieldTerm.getText()) * MONTH_IN_THE_YEAR;
        double monthlyInterestRate = interestRate / MONTH_IN_THE_YEAR;
        double monthlyPayment = principal *
                (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate , numbersOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate , numbersOfPayments)) -1);
        double totalPayback = monthlyPayment * numbersOfPayments;

        textFieldTotalPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(totalPayback)));
        textFieldMonthlyPayment.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(monthlyPayment)));

        buttonBerechnen.setDisable(true);
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


}
