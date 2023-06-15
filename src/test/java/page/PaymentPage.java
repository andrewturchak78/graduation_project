package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private final SelenideElement buyInCreditButton = $$("button").find(exactText("Купить в кредит"));
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement cardNumberInput = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthInput = $("[placeholder='08']");
    private final SelenideElement yearInput = $("[placeholder='22']");
    private final SelenideElement cvcInput = $("[placeholder='999']");
    private final SelenideElement ownerInput = $$("input.input__control").get(3);
    private final SelenideElement successNotification = $x("//div[contains(text(),'Успешно')]");
    private final SelenideElement errorNotification = $x("//div[contains(text(),'Ошибка')]");
    private final SelenideElement errorSubYearAndMonth = $x("//span[contains(text(), 'Неверн')]");
    private final SelenideElement errorSubOwner = $x("//span[contains(text(), 'Поле')]");
    private final SelenideElement errorSubCVC = $x("//span[contains(text(), 'формат')]");
    public void clickOnBuyButton(){
        buyButton.click();
    }
    public void clickOnBuyInCreditButton(){
        buyInCreditButton.click();
    }
    public void clickOnContinueButton(){
        continueButton.click();
    }

    public void makePaymentTransfer(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.generateValidMonth(2));
        yearInput.setValue(dataHelper.generateValidYear(2));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public PaymentPage validPaymentTransfer(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        makePaymentTransfer(dataHelper, cardInfo);
        return new PaymentPage();
    }
    public void makeCreditTransfer(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.generateValidMonth(3));
        yearInput.setValue(dataHelper.generateValidYear(4));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public PaymentPage validCreditTransfer(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        makeCreditTransfer(dataHelper, cardInfo);
        return new PaymentPage();
    }
    public PaymentPage checkSuccessNotification(){
        successNotification.shouldBe(visible, Duration.ofSeconds(10));
        return new PaymentPage();
    }
    public PaymentPage checkErrorSubYearAndMonthNotification(){
        errorSubYearAndMonth.shouldBe(visible, Duration.ofSeconds(1));
        return new PaymentPage();
    }
    public PaymentPage checkErrorNotification(){
        errorNotification.shouldBe(visible, Duration.ofSeconds(10));
        return new PaymentPage();
    }
    public PaymentPage checkErrorSubCVCNotification(){
        errorSubCVC.shouldBe(visible, Duration.ofSeconds(1));
        return new PaymentPage();
    }

    public PaymentPage checkErrorSubOwnerNotification(){
        errorSubOwner.shouldBe(visible, Duration.ofSeconds(1));
        return new PaymentPage();
    }
    public void invalidCardNumber(DataHelper dataHelper){
        cardNumberInput.setValue(dataHelper.wrongCardNumber());
        cardNumberInput.shouldBe(empty);
    }
    public void checkInvalidValidityOfCard(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.generateInvalidMonth());
        yearInput.setValue(dataHelper.generateInValidYear(2));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public void checkInvalidValueInMonthAndYear(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.wrongMonth());
        yearInput.setValue(dataHelper.wrongYear());
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public void validationOfOwnerValue(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.generateValidMonth(5));
        yearInput.setValue(dataHelper.generateValidYear(4));
        ownerInput.setValue(dataHelper.wrongUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public void invalidCVCValue(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.generateValidMonth(5));
        yearInput.setValue(dataHelper.generateValidYear(4));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.wrongCVC());
    }
    public void tooLongCVCValue(DataHelper dataHelper){
        cvcInput.setValue(dataHelper.invalidTooLongCVC());
        cvcInput.equals("567");
    }
    public void tryingZeroMonth(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.zeroMonth());
        yearInput.setValue(dataHelper.generateValidYear(2));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.generateRandomCVC());
    }
    public void tryingZeroCVC(DataHelper dataHelper, DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(dataHelper.zeroMonth());
        yearInput.setValue(dataHelper.generateValidYear(2));
        ownerInput.setValue(dataHelper.getRandomValidUser());
        cvcInput.setValue(dataHelper.zeroCVC());
    }
}
