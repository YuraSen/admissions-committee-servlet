package utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PHONE_PATTERN =
            "\\d{10}";

    public PhoneValidator() {
        pattern = Pattern.compile(PHONE_PATTERN);
    }

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
