package com.numbersector.proxy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetSectorProxyTest {

    private static final String BASE_URL_VALUE = "https://challenge-business-sector-api.meza.talkdeskstg.com/";
    private static final String PATH_VALUE = "sector/";

    @InjectMocks
    private GetSectorProxy getSectorProxy;

    @BeforeEach
    void setUpTest() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(getSectorProxy, "baseURL", BASE_URL_VALUE);
        ReflectionTestUtils.setField(getSectorProxy, "path", PATH_VALUE);
    }

    @Test
    void getURIWithSuccess() {
        String number = "+123456789";
        String expectedResult = BASE_URL_VALUE + PATH_VALUE + number;
        String result = getSectorProxy.getURI(number).toString();

        assertEquals(expectedResult, result);
    }

}