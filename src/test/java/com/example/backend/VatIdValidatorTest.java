package com.example.backend;

import com.example.backend.service.VatIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class VatIdValidatorTest {

    @InjectMocks
    private VatIdValidator vatIdValidator;

    @Test
    public void emptyStringIsInvalid() {
        assertThat(vatIdValidator.isValid("").isValid()).isFalse();
    }

    @Test
    public void validatesCorrectlyDE() {
        assertThat(vatIdValidator.isValid("DE999999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("DE9999999999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("DE99999999").isValid()).isFalse();
    }

    @Test
    public void validatesCorrectlyAT() {
        assertThat(vatIdValidator.isValid("ATU99999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("AT999999999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("ATU999999999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("ATU9999999").isValid()).isFalse();
    }

    @Test
    public void validatesCorrectlyFR() {
        assertThat(vatIdValidator.isValid("FRXX999999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("FR99999999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("FRXX9999999999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("FRXX99999999").isValid()).isFalse();
    }

    @Test
    public void validatesCorrectlyGB() {
        assertThat(vatIdValidator.isValid("GB999999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("GB999999999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("GBGD999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("GBHA999").isValid()).isTrue();
    }

    @Test
    public void validatesCorrectlyDK() {
        assertThat(vatIdValidator.isValid("DK99999999").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("DK999999999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("DK9999999").isValid()).isFalse();
    }

    @Test
    public void validatesCorrectlyNL() {
        assertThat(vatIdValidator.isValid("NLXXXXXXXXXX99").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("NLX+XXXXXXXX99").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("NLXXXX*XXXXX99").isValid()).isTrue();
        assertThat(vatIdValidator.isValid("NLXXXXXXXXXX9").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("NLXXXXXXXXXX999").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("NLXXXXXXXXX99").isValid()).isFalse();
        assertThat(vatIdValidator.isValid("NLXXXXXXXXXXX99").isValid()).isFalse();
    }

}