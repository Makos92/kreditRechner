package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void Should_Convert_Number_To_Percent() {
        Controller controller = new Controller();
        assertEquals(0.2, controller.percentInterestRate(20, Controller.NUMBER_TO_PERCENT));
    }

    @Test
    void Should_Return_Monthy_Interest_Rate() {
        Controller controller = new Controller();
        assertEquals(0.5, controller.monthlyInterestRate(6, Controller.MONTH_IN_THE_YEAR));
    }

    @Test
    void Should_Return_Numbers_Of_Payment() {
        Controller controller = new Controller();
        assertEquals(360, controller.numberOfPayments(30, Controller.MONTH_IN_THE_YEAR));
    }

    @Test
    void Should_Calculate_Total_Paback() {
        Controller controller = new Controller();
        assertEquals(288000, controller.totalPayback(800, 360));
    }

    @Test
    void Should_Calculate_Monthly_Payment() {
        Controller controller = new Controller();
        assertEquals(700.2085326568598, controller.monthlyPayment(10000, 0.07, 120));
    }

}