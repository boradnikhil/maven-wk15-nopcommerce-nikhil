package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.electronics.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)

public class ElectronicsPageTest extends BaseTest {
    ElectronicsCellPhonesPage electronicsCellPhones;
    CellPhonesListViewPage celloPhonesListViewPage;
    CellphoneListViewNokiaLumiaPage cellphoneListViewNokiaLumiaPage;
    ShoppingCartPage shoppingCartPage;
    SignInPage signInPage;
    RegistrationPage registrationPage;
    RegistrationComplete registrationComplete;
    ShoppingCartPage shoppingCart;
    ShippingOption shippingOption;
    PaymentMethodPage paymentMethodPage;
    PaymentDetail paymentDetail;
    ConfirmOrder confirmOrder;
    HomePage homePage;
    BillingPage billingPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        electronicsCellPhones = new ElectronicsCellPhonesPage();
        celloPhonesListViewPage = new CellPhonesListViewPage();
        cellphoneListViewNokiaLumiaPage = new CellphoneListViewNokiaLumiaPage();
        shoppingCartPage = new ShoppingCartPage();
        signInPage = new SignInPage();
        registrationPage = new RegistrationPage();
        registrationComplete = new RegistrationComplete();
        shoppingCart = new ShoppingCartPage();
        shippingOption = new ShippingOption();
        paymentMethodPage = new PaymentMethodPage();
        paymentDetail = new PaymentDetail();
        confirmOrder = new ConfirmOrder();
        homePage = new HomePage();
        billingPage = new BillingPage();
    }

    @Test(groups = {"sanity", "Regression"})
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        electronicsCellPhones.mouseHoverOnElectronicPageAndClick();
        electronicsCellPhones.getCellPhonesPageWelcomeText();
        electronicsCellPhones.clickOnTheListViewTab();


    }

    @Test(groups = {"smoke", "Regression"})
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() {

        electronicsCellPhones.mouseHoverOnElectronicPageAndClick();
        electronicsCellPhones.getCellPhonesPageWelcomeText();
        electronicsCellPhones.clickOnTheListViewTab();
        celloPhonesListViewPage.clickOnNokiaLumiaLink();
        cellphoneListViewNokiaLumiaPage.getTextFromNokiaLumiaDisplayText();
        Assert.assertEquals(cellphoneListViewNokiaLumiaPage.getTextFromNokiaLumiaDisplayText(), "Nokia Lumia 1020", "Text is not matched");
        cellphoneListViewNokiaLumiaPage.getTextFromNokiaLumiaPrice();
        Assert.assertEquals(cellphoneListViewNokiaLumiaPage.getTextFromNokiaLumiaPrice(), "$349.00", "price is not matched");
        cellphoneListViewNokiaLumiaPage.clearTheQuantityField();
        cellphoneListViewNokiaLumiaPage.UpdatedQuantityForNokiaLumia1020("2");
        cellphoneListViewNokiaLumiaPage.clickOnNokiaLumiaAddToCartButton();
        cellphoneListViewNokiaLumiaPage.getProductAddedToCartMessage();
        Assert.assertEquals(cellphoneListViewNokiaLumiaPage.getProductAddedToCartMessage(), "The product has been added to your shopping cart", "Add to Cart Message is not displayed correctly");
        cellphoneListViewNokiaLumiaPage.closeTheGreenBarByClickingTheCrossButton();
        cellphoneListViewNokiaLumiaPage.mouseHoverOnNokiaLumiaShoppingCartButton();
        cellphoneListViewNokiaLumiaPage.clickOnNokiaLumiaGoToCartButton();
        Assert.assertEquals(shoppingCartPage.getTextFromDisplayMessage(), "Shopping cart", "Not Matching");
        shoppingCartPage.updateTheQuantityField("2");
        Assert.assertEquals(shoppingCartPage.getTextFromAmountElement(), "$698.00", "Not matching");
        shoppingCartPage.clickOnTermsAndConditionsAgreeBox();
        shoppingCartPage.clickOnCheckoutButton();
        Assert.assertEquals(signInPage.getTextFromWelcomeMessage(), "Welcome, Please Sign In!", "Not Matching");
        signInPage.clickOnRegisterTab();
        Assert.assertEquals(registrationPage.getTextFromRegisterMessage(), "Register", "Not Matching");
        registrationPage.enterFirstName("John");
        registrationPage.enterLastName("Smith");
        registrationPage.enterEmailAdderess("john808@gmail.com");
        registrationPage.enterPassword("abc123");
        registrationPage.enterConfirmPassword("abc123");
        registrationPage.clickOnRegisterButton();
        Assert.assertEquals(registrationComplete.getTextFromRegisterationCompleteMessage(), "Your registration completed", "Not Matching");
        registrationComplete.clickOnTheContinueButton();
        Assert.assertEquals(shoppingCart.getTextFromDisplayMessage(), "Shopping cart", "Message is not displyed");
        shoppingCart.clickOnTermsAndConditionsAgreeBox();
        shoppingCart.clickOnCheckoutButton();
        //  billingPage.waitForData();
        billingPage.selectDataFromCountryDropDown();
        billingPage.enterCountryName("Albania");
        //billingPage.waitForData();
        billingPage.waitForElement();
        billingPage.enterCityName("london");
        billingPage.enterAdd1("Barnet");
        billingPage.enterPostalCode("Sw14WV");
        billingPage.enterPhoneNumber("2030920332");
        billingPage.clickOnContinueButton();
        shippingOption.clickOnButton2ndDayAir();
        shippingOption.clickOnContinueButton();
        paymentMethodPage.clickOnCreditCardRadioButton();
        paymentMethodPage.clickOnContinueButton();
        paymentDetail.selectCreditCardType(0);
        paymentDetail.inputCardHoldersName("Peter Rabit");
        paymentDetail.inputCardNumber("5555 5555 5555 4444");
        paymentDetail.inputExpireMonth("12");
        paymentDetail.inputExpireYear("2025");
        paymentDetail.inputCardCode("143");
        paymentDetail.clickOnContinueButton();
        Assert.assertEquals(confirmOrder.getTextFromPaymentMethodText(), "Credit Card", "Not Matching");
        confirmOrder.clickOnConfirmButton();


    }
}
