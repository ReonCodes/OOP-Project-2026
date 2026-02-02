// NUMBER 2 STUDENT REGISTRATION SYSTEM
// GROUP I - VICTORIA UNIVERSITY: OOP (1301 ST)
// OMONA EMMANUEL - VU-BSF-2503-1833-DAY
// VANESSAH NTABADDE - VU-BBC-2511-1563-DAY
// MPAKA JONATHAN - VU-BSF-2503-2210-DAY
// LWANYAGA IBRAHIM - VU-BCS-2503-1062-DAY
// ELVIS TREVOR AMUNYO - VU-BCS-2503-0114-DAY

package com.example.javafx;


import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.*;
import java.nio.file.*;
import java.util.Objects;

public class MainController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField confirmEmailAddressField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ComboBox<String> comboYear, comboMonth, comboDay;
    @FXML
    private RadioButton maleRadio, femaleRadio;
    @FXML
    private RadioButton civilRadio, csaeRadio, electricalRadio, eacRadio, mechanicalRadio;
    @FXML
    private Label summaryLabel;

    @FXML
    private Label checkFirstName;
    @FXML
    private Label checkLastName;
    @FXML
    private Label checkEmail1;
    @FXML
    private Label checkEmail2;
    @FXML
    private Label checkPassword1;
    @FXML
    private Label checkPassword2;
    @FXML
    private Label checkYear;

    String firstName = "";
    String lastName = "";
    String email = "";
    String confirmEmail = "";
    String password = "";
    String confirmPassword = "";
    String year = "";
    String month = "";
    String date = "";
    String sex = "";
    String department = "";
    String summary;
    boolean invalidEmail = false;
    boolean emailMismatch = false;
    boolean passwordMismatch = false;
    boolean invalidPassword = false;
    int validateAge;


    @FXML
    private void initialize() {
        // SAVE VALUE FROM firstNameField INTO firstName variable IF MOUSE LEAVES TEXT FIELD
        // OR TAB IS PRESSED OR ANOTHER BOX IS CLICKED
        firstNameField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                firstName = firstNameField.getText();
                System.out.println(firstName);
            }
        });

        lastNameField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                lastName = lastNameField.getText();
                System.out.println(lastName);
            }
        });

        emailAddressField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                email = emailAddressField.getText();
                System.out.println(email);
            }
        });

        confirmEmailAddressField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                confirmEmail = confirmEmailAddressField.getText();
                System.out.println(confirmEmail);
            }
        });

        passwordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                password = passwordField.getText();
                System.out.println(password);
            }
        });

        confirmPasswordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) { // focus lost
                confirmPassword = confirmPasswordField.getText();
                System.out.println(confirmPassword);
            }
        });

        // SET Select Month FIELD IN GUI TO A MINIMUM AND MAXIMUM YEAR VALUE
        int minYear = 1930;
        int maxYear = 2026;
        comboYear.setItems(FXCollections.observableArrayList(
                java.util.stream.IntStream.rangeClosed(minYear, maxYear)
                        .mapToObj(String::valueOf)
                        .toList()
        ));

        /* SET Select Month FIELD IN GUI TO SHOW THE MONTHS AS NUMBERS FROM 01 (JAN)-12 (DEC) */
        comboMonth.setItems(FXCollections.observableArrayList(
                "01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"
        ));

        comboMonth.valueProperty().addListener((obs, oldMonth, newMonth) -> updateDays());
        comboYear.valueProperty().addListener((obs, oldYear, newYear) -> updateDays());
        comboYear.valueProperty().addListener((obs, oldVal, newVal) -> year = newVal);
        comboMonth.valueProperty().addListener((obs, oldVal, newVal) -> month = newVal);
        comboDay.valueProperty().addListener((obs, oldVal, newVal) -> date = newVal);
    }

    // UPDATE DATE FIELD IN GUI BASED ON YEAR AND MONTH FIELD VALUES
    private void updateDays() {
        String selectedMonth = comboMonth.getValue();
        String selectedYear = comboYear.getValue();

        if (selectedMonth == null || selectedYear == null) return;

        int year = Integer.parseInt(selectedYear);
        int daysInMonth;

        switch (selectedMonth) {
            case "04", "06", "09", "11" -> daysInMonth = 30;
            // CALCULATE LEAP YEAR
            case "02" -> daysInMonth = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
            default -> daysInMonth = 31;
        }

        // GENERATE DATES DYNAMICALLY E.G. IF IT'S A LEAP YEAR...SET FEBRUARY TO 29 DAYS
        comboDay.setItems(FXCollections.observableArrayList(
                java.util.stream.IntStream.rangeClosed(1, daysInMonth)
                        .mapToObj(String::valueOf)
                        .toList()
        ));
    }

    @FXML
    public void getSex(ActionEvent event){
        if (maleRadio.isSelected()) {
            sex = maleRadio.getText();
        } else if (femaleRadio.isSelected()) {
            sex = femaleRadio.getText();
        }
        System.out.println(sex);
    }

    @FXML
    public void getDept(ActionEvent event){
        if (civilRadio.isSelected()) {
            department = civilRadio.getText();
        } else if (csaeRadio.isSelected()) {
            department = csaeRadio.getText();
        } else if (electricalRadio.isSelected()) {
            department = electricalRadio.getText();
        } else if (eacRadio.isSelected()) {
            department = eacRadio.getText();
        } else if (mechanicalRadio.isSelected()) {
            department = mechanicalRadio.getText();
        }
        System.out.println(department);
    }

    @FXML
    void cancelButton(MouseEvent event) {
        System.out.println("Cancel clicked!");
    }

    @FXML
    void submitButton(MouseEvent event) {
        validateEmail(email, confirmEmail);
        validatePassword(password, confirmPassword);
        try {
            validateAge = 2026 - Integer.parseInt(year);
        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
            checkYear.setText("Check year!");
        }
        System.out.println("Submit clicked!");
        if (firstName == "") {
            checkFirstName.setText("Enter name!");
            checkLastName.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkYear.setText("");
        } else if (lastName == "") {
            checkLastName.setText("Enter name!");
            checkFirstName.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkYear.setText("");
        } else if (invalidEmail || emailMismatch) {
            checkEmail1.setText("Check email!");
            checkEmail2.setText("Check email!");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkFirstName.setText("");
            checkYear.setText("");
            checkLastName.setText("");
            summaryLabel.setText("Invalid Email or emails do not match!");
        } else if (invalidPassword) {
            checkPassword1.setText("Invalid password!");
            checkPassword2.setText("Invalid password!");
            checkEmail1.setText("");
            checkFirstName.setText("");
            checkEmail2.setText("");
            checkLastName.setText("");
            checkYear.setText("");
            summaryLabel.setText("Password must be 8-20 characters long and contain at least 1 letter or number!");
        } else if (passwordMismatch) {
            checkFirstName.setText("");
            checkPassword1.setText("Mismatch!");
            checkPassword2.setText("Mismatch!");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkLastName.setText("");
            summaryLabel.setText("Passwords do not match!");
            checkYear.setText("");
        } else if (validateAge < 16 || validateAge > 60) {
            checkYear.setText("Check year!");
            summaryLabel.setText("Age must be in the range 16-60!");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkFirstName.setText("");
            checkLastName.setText("");
        } else if (sex == "") {
            summaryLabel.setText("Please select a sex!");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkFirstName.setText("");
            checkLastName.setText("");
            checkYear.setText("");
        } else if (department == "") {
            summaryLabel.setText("Please select department");
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkFirstName.setText("");
            checkLastName.setText("");
            checkYear.setText("");
        } else {
            checkEmail1.setText("");
            checkEmail2.setText("");
            checkPassword1.setText("");
            checkPassword2.setText("");
            checkFirstName.setText("");
            checkLastName.setText("");
            checkYear.setText("");
            int studentID = 1;
            printResults(studentID);
            writeCsv(studentID);
            studentID++;
        }
    }

    private void printResults(int id) {
        String paddedId = String.format("%05d", id);
        summary = "ID: " + year + "-" + paddedId + " | " + firstName + " | " + lastName + " | " + email + " | " + password + " | " + year + "-" + month + "-" + date + " | " + sex + " | " + department;
        summaryLabel.setText(summary);

        System.out.println(summary);
    }

    private void validateEmail(String email1, String email2){
        emailMismatch = !email1.equals(email2);
        final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        invalidEmail = !email1.matches(EMAIL_REGEX);
    }

    private void validatePassword(String pass1, String pass2){
        passwordMismatch = !pass1.equals(pass2);
        final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
        invalidPassword = !password.matches(PASSWORD_REGEX);


    }

    private void writeCsv(int id){
        String paddedId = String.format("%05d", id);
        Path filePath = Paths.get("students.csv");
        boolean fileExists = Files.exists(filePath);

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("students.csv", true))) {
            if (!fileExists) {
                writer.write("Student ID, First Name, Last Name, Email Address, Password, Date of Birth, Sex, Department");
                writer.newLine();
            }

            writer.write(year + "-" + paddedId + ", " + firstName + ", " + lastName + ", " + email + ", " + password + ", " + year + "-" + month + "-" + date + ", " + sex + ", " + department);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

}
