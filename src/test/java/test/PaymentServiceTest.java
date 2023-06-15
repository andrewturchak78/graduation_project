package test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;

public class PaymentServiceTest {

    PaymentPage page;
    DataHelper dataHelper;

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    void url() {
        open("http://localhost:8080");
    }

    @Test
    void validPaymentTransfer() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.validPaymentTransfer(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkSuccessNotification();
        SQLHelper.Status statusApproved = SQLHelper.getStatusFromPayment();
        Assertions.assertEquals("APPROVED", statusApproved.getStatus());
    }

    @Test
    void invalidPaymentTransfer() {
        PaymentPage page = new PaymentPage();
        var secondCardInfo = getSecondCardNumber();
        page.clickOnBuyButton();
        page.validCreditTransfer(dataHelper, secondCardInfo);
        page.clickOnContinueButton();
        page.checkErrorNotification();
        SQLHelper.Status statusDeclined = SQLHelper.getStatusFromPayment();
        Assertions.assertEquals("DECLINED", statusDeclined.getStatus());
    }

    @Test
    void validCreditTransfer() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.validCreditTransfer(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkSuccessNotification();
        SQLHelper.Status statusApproved = SQLHelper.getStatusFromCredit();
        Assertions.assertEquals("APPROVED", statusApproved.getStatus());
    }

    @Test
    void invalidCreditTransfer() {
        PaymentPage page = new PaymentPage();
        var secondCardInfo = getSecondCardNumber();
        page.clickOnBuyInCreditButton();
        page.validCreditTransfer(dataHelper, secondCardInfo);
        page.clickOnContinueButton();
        page.checkErrorNotification();
        SQLHelper.Status statusDeclined = SQLHelper.getStatusFromCredit();
        Assertions.assertEquals("DECLINED", statusDeclined.getStatus());
    }

    @Test
    void invalidCardNumberInPayment() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyButton();
        page.invalidCardNumber(dataHelper);
    }

    @Test
    void invalidValidityOfCardInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.checkInvalidValidityOfCard(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void invalidValueOfMonthAndYearInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.checkInvalidValueInMonthAndYear(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void paymentTransferByWrongCardInPayment() {
        PaymentPage page = new PaymentPage();
        var wrongCardInfo = randomInvalidCardNumber();
        page.clickOnBuyButton();
        page.validPaymentTransfer(dataHelper, wrongCardInfo);
        page.clickOnContinueButton();
        page.checkErrorNotification();
    }

    @Test
    void invalidValueOfUserInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.validationOfOwnerValue(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubOwnerNotification();
    }

    @Test
    void invalidCVCValueInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.invalidCVCValue(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
    }

    @Test
    void tooLongCVCValueInPayment() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyButton();
        page.tooLongCVCValue(dataHelper);
    }

    @Test
    void invalidCardNumberInCredit() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyInCreditButton();
        page.invalidCardNumber(dataHelper);
    }

    @Test
    void invalidValidityOfCardInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.checkInvalidValidityOfCard(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void invalidValueOfMonthAndYearInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.checkInvalidValueInMonthAndYear(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void paymentTransferByWrongCardInCredit() {
        PaymentPage page = new PaymentPage();
        var wrongCardInfo = randomInvalidCardNumber();
        page.clickOnBuyInCreditButton();
        page.validPaymentTransfer(dataHelper, wrongCardInfo);
        page.clickOnContinueButton();
        page.checkErrorNotification();
    }

    @Test
    void invalidValueOfUserInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.validationOfOwnerValue(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubOwnerNotification();
    }

    @Test
    void invalidCVCValueInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.invalidCVCValue(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
    }

    @Test
    void tooLongCVCValueInCredit() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyInCreditButton();
        page.tooLongCVCValue(dataHelper);
    }

    @Test
    void cleanFormInPayment() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyButton();
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
        page.checkErrorSubOwnerNotification();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void cleanFormInCredit() {
        PaymentPage page = new PaymentPage();
        page.clickOnBuyInCreditButton();
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
        page.checkErrorSubOwnerNotification();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void testingZeroMonthInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.tryingZeroMonth(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void testingZeroCVCInPayment() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyButton();
        page.tryingZeroCVC(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
    }

    @Test
    void testingZeroMonthInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.tryingZeroMonth(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubYearAndMonthNotification();
    }

    @Test
    void testingZeroCVCInCredit() {
        PaymentPage page = new PaymentPage();
        var firstCardInfo = getFirstCardNumber();
        page.clickOnBuyInCreditButton();
        page.tryingZeroCVC(dataHelper, firstCardInfo);
        page.clickOnContinueButton();
        page.checkErrorSubCVCNotification();
    }
}
