package com.company.practics.practic_1;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Predicate<String> emailValidation = new EmailValidator();
        Predicate<String> emailValidationLambda = email ->
                Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$",
                        Pattern.CASE_INSENSITIVE).matcher(email).matches();


        System.out.println(emailValidation.test("mail@mail.ru"));
        System.out.println(emailValidation.test("@mail.rail.ru"));
        System.out.println(emailValidationLambda.test("mail@mail.ru"));
        System.out.println(emailValidationLambda.test("@mail.rail.ru"));
    }
}
