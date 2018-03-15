package com.lema.bmi;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Krzysiek on 14.03.2018.
 */
public class MainActivityTest {
    MainActivity mMainActivity = new MainActivity();

    @Test
    public void testBmiCount(){
        Assert.assertEquals("Nie prawidlowy wynik BMI",2.0 ,mMainActivity.bmiCount(1,1));
    }



}