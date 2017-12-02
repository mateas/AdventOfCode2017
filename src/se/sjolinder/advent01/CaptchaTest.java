package se.sjolinder.advent01;

import org.junit.jupiter.api.Assertions;

class CaptchaTest {

    @org.junit.jupiter.api.Test
    void part1() {
        Captcha captcha = new Captcha();
        Assertions.assertEquals(3, captcha.Part1("1122"));
        Assertions.assertEquals(4, captcha.Part1("1111"));
        Assertions.assertEquals(0, captcha.Part1("1234"));
        Assertions.assertEquals(9, captcha.Part1("91212129"));
    }

    @org.junit.jupiter.api.Test
    void part2() {
        Captcha captcha = new Captcha();
        Assertions.assertEquals(6, captcha.Part2("1212"));
        Assertions.assertEquals(0, captcha.Part2("1221"));
        Assertions.assertEquals(4, captcha.Part2("123425"));
        Assertions.assertEquals(12, captcha.Part2("123123"));
        Assertions.assertEquals(4, captcha.Part2("12131415"));
    }
}