package com.github.zesetup.decisionup.views.signup;

import com.github.zesetup.decisionup.domain.Company;
import com.github.zesetup.decisionup.domain.User;
import com.github.zesetup.decisionup.service.UserService;
import com.github.zesetup.decisionup.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Sign-up")
@Route(value = "signup", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class SignupView extends Div {

    // Owner form fields
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private PasswordField passwordField = new PasswordField("Password");

    // Company form fields
    private TextField companyName = new TextField("Company name");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<User> userBinder = new Binder(User.class);
    private Binder<Company> companyBinder = new Binder(Company.class);

    public SignupView(UserService userService) {
        addClassName("signup-view");

        add(createOwnerTitle());
        add(createOwnerFormLayout());

        add(createCompanyTitle());
        add(createCompanyFormLayout());

        add(createButtonLayout());


        userBinder.forField(firstName).bind("name");
        userBinder.forField(lastName).bind("surname");
        userBinder.forField(email).bind("email");
        userBinder.forField(passwordField).bind("password");

        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            userBinder.validate();
            Notification.show("Valid:" + userBinder.isValid());
            /*userService.update(userBinder.getBean());
            Notification.show(userBinder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();*/
        });
    }

    private void clearForm() {
        userBinder.setBean(new User());
        companyBinder.setBean(new Company());
    }

    private Component createOwnerTitle() {
        return new H3("Owner person information");
    }

    private Component createOwnerFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName,  email);
        return formLayout;
    }

    private Component createCompanyTitle() {
        return new H3("Company information");
    }

    private Component createCompanyFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(companyName);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private static class PhoneNumberField extends CustomField<String> {
        private ComboBox<String> countryCode = new ComboBox<>();
        private TextField number = new TextField();

        public PhoneNumberField(String label) {
            setLabel(label);
            countryCode.setWidth("120px");
            countryCode.setPlaceholder("Country");
            countryCode.setPattern("\\+\\d*");
            countryCode.setPreventInvalidInput(true);
            countryCode.setItems("+354", "+91", "+62", "+98", "+964", "+353", "+44", "+972", "+39", "+225");
            countryCode.addCustomValueSetListener(e -> countryCode.setValue(e.getDetail()));
            number.setPattern("\\d*");
            number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(countryCode, number);
            layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (countryCode.getValue() != null && number.getValue() != null) {
                String s = countryCode.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                countryCode.clear();
                number.setValue(parts[0]);
            } else if (parts.length == 2) {
                countryCode.setValue(parts[0]);
                number.setValue(parts[1]);
            } else {
                countryCode.clear();
                number.clear();
            }
        }
    }

}
