package org.lemonade;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.lemonade.exceptions.NotSupportedException;
import org.lemonade.gui.values.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *
 */
public class EvaluateTest {

    private GuiIntegerValue zero;
    private GuiIntegerValue two;
    private GuiIntegerValue one;
    private GuiDecimalValue zeroPointFive;
    private GuiDecimalValue onePointZero;
    private GuiDecimalValue onePointFive;
    private GuiDecimalValue twoPointZero;
    private GuiMoneyValue oneFifty;
    private GuiMoneyValue twoFifty;
    private GuiNumericalValue<?> onePointTwo;

    private GuiBooleanValue boolTrue;
    private GuiBooleanValue boolFalse;

    private GuiUndefinedValue undefined;

    private GuiDateValue date;
    private GuiDateValue dateTwo;

    @Before
    public void setUp() throws ParseException {
        zero = new GuiIntegerValue(0);
        two = new GuiIntegerValue(2);
        one = new GuiIntegerValue(1);

        zeroPointFive = new GuiDecimalValue(0.5);
        onePointFive = new GuiDecimalValue(1.5);
        onePointZero = new GuiDecimalValue(1.0);
        twoPointZero = new GuiDecimalValue(2.0);

        oneFifty = new GuiMoneyValue(1.50);
        twoFifty = new GuiMoneyValue(2.50);

        onePointTwo = new GuiDecimalValue(1.2);

        date = new GuiDateValue(LocalDate.of(2010, 1, 1));
        dateTwo = new GuiDateValue(LocalDate.of(2012, 1, 1));

        undefined = new GuiUndefinedValue();

        boolTrue = new GuiBooleanValue(true);
        boolFalse = new GuiBooleanValue(false);
    }

    @Test
    public void testBooleanValue() {
        GuiBooleanValue boolTrue = new GuiBooleanValue(true);
        GuiBooleanValue boolFalse = new GuiBooleanValue(false);

        assertThat(boolTrue.getValue()).isInstanceOf(Boolean.class);
        assertThat((Boolean) boolTrue.or(boolFalse).getValue()).isTrue();
        assertThat((Boolean) boolTrue.or(boolTrue).getValue()).isTrue();
        assertThat((Boolean) boolTrue.and(boolFalse).getValue()).isFalse();
        assertThat((Boolean) boolTrue.and(boolTrue).getValue()).isTrue();
    }

    @Test
    public void testIntegerValue() {
        assertThat(one.compareTo(one)).isEqualTo(0);
    }

    @Test
    public void testNumericPlus() {
        GuiValue<?> onePlusTwo = one.plus(two);
        GuiValue<?> onePlusOnePointFive = one.plus(onePointFive);
        GuiValue<?> onePlusOneFifty = one.plus(oneFifty);
        GuiValue<?> onePlusOnePointTwo =  one.plus(onePointTwo);

        assertThat(onePlusTwo.getValue()).isEqualTo(3);
        assertThat(onePlusTwo).isInstanceOf(GuiIntegerValue.class);

        assertThat(onePlusOnePointFive.getValue()).isEqualTo(2.5);
        assertThat(onePlusOnePointFive).isInstanceOf(GuiDecimalValue.class);

        assertThat(onePlusOneFifty.getValue()).isEqualTo(2.50);
        assertThat(onePlusOneFifty).isInstanceOf(GuiMoneyValue.class);

        assertThat(onePlusOnePointTwo.getValue()).isEqualTo(2.2);
        assertThat(onePlusOnePointTwo).isInstanceOf(GuiDecimalValue.class);
    }

    @Test
    public void testNumericMinus() {
        GuiValue<?> twoMinusOne = two.min(one);
        GuiValue<?> twoMinusOnePointFive = two.min(onePointFive);
        GuiValue<?> twoMinusOneFifty = two.min(oneFifty);
        GuiValue<?> twoMinusOnePointTwo = (GuiValue<?>) two.min(onePointTwo);

        assertThat(twoMinusOne.getValue()).isEqualTo(1);
        assertThat(twoMinusOne).isInstanceOf(GuiIntegerValue.class);

        assertThat(twoMinusOnePointFive.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOnePointFive).isInstanceOf(GuiDecimalValue.class);

        assertThat(twoMinusOneFifty.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOneFifty).isInstanceOf(GuiMoneyValue.class);

        assertThat(twoMinusOnePointTwo.getValue()).isEqualTo(0.8);
        assertThat(twoMinusOnePointTwo).isInstanceOf(GuiDecimalValue.class);
    }

    @Test
    public void testNumericProduct() {
        GuiValue<?> result = two.prod(onePointTwo);
        assertThat(result).isInstanceOf(GuiDecimalValue.class);

        GuiNumericalValue<?> result2;

        result2 = (GuiNumericalValue<?>) result.prod(two);

        assertThat(result2).isInstanceOf(GuiDecimalValue.class);
        assertThat(result2.getValue()).isEqualTo(4.8);
    }


    @Test
    public void testDateValue() {
        assertThat(date.compareTo(dateTwo)).isNegative();
        assertThat(date.compareTo(date)).isZero();
        assertThat(date.equals(dateTwo)).isEqualTo(false);
    }


    @Test
    public void testDivision() {
        assertThat(one.div(two)).isEqualTo(zero);
        assertThat(one.div(twoPointZero).getValue()).isEqualTo(zeroPointFive.getValue());
        assertThat(onePointTwo.div(onePointTwo).getValue()).isEqualTo(onePointZero.getValue());
    }

    @Test
    public void testGreaterThan() {
        assertThat((Boolean) one.gT(two).getValue()).isFalse();
        assertThat((Boolean) two.gT(one).getValue()).isTrue();
        assertThat((Boolean) one.gT(one).getValue()).isFalse();

        assertThat((Boolean) zeroPointFive.gT(onePointFive).getValue()).isFalse();
        assertThat((Boolean) onePointFive.gT(zeroPointFive).getValue()).isTrue();
        assertThat((Boolean) zeroPointFive.gT(zeroPointFive).getValue()).isFalse();

        assertThat((Boolean) oneFifty.gT(twoFifty).getValue()).isFalse();
        assertThat((Boolean) twoFifty.gT(oneFifty).getValue()).isTrue();
        assertThat((Boolean) oneFifty.gT(oneFifty).getValue()).isFalse();

        assertThat((Boolean) date.gT(dateTwo).getValue()).isFalse();
        assertThat((Boolean) dateTwo.gT(date).getValue()).isTrue();
        assertThat((Boolean) date.gT(date).getValue()).isFalse();
    }

    @Test
    public void testLessThan() {
        assertThat((Boolean) one.lT(two).getValue()).isTrue();
        assertThat((Boolean) two.lT(one).getValue()).isFalse();
        assertThat((Boolean) one.lT(one).getValue()).isFalse();

        assertThat((Boolean) zeroPointFive.lT(onePointFive).getValue()).isTrue();
        assertThat((Boolean) onePointFive.lT(zeroPointFive).getValue()).isFalse();
        assertThat((Boolean) zeroPointFive.lT(zeroPointFive).getValue()).isFalse();

        assertThat((Boolean) oneFifty.lT(twoFifty).getValue()).isTrue();
        assertThat((Boolean) twoFifty.lT(oneFifty).getValue()).isFalse();
        assertThat((Boolean) oneFifty.lT(oneFifty).getValue()).isFalse();

        assertThat((Boolean) date.lT(dateTwo).getValue()).isTrue();
        assertThat((Boolean) dateTwo.lT(date).getValue()).isFalse();
        assertThat((Boolean) date.lT(date).getValue()).isFalse();
    }

    @Test
    public void testGreaterThanOrEqual() {
        assertThat((Boolean) one.gTEq(two).getValue()).isFalse();
        assertThat((Boolean) two.gTEq(one).getValue()).isTrue();
        assertThat((Boolean) one.gTEq(one).getValue()).isTrue();

        assertThat((Boolean) zeroPointFive.gTEq(onePointFive).getValue()).isFalse();
        assertThat((Boolean) onePointFive.gTEq(zeroPointFive).getValue()).isTrue();
        assertThat((Boolean) zeroPointFive.gTEq(zeroPointFive).getValue()).isTrue();

        assertThat((Boolean) oneFifty.gTEq(twoFifty).getValue()).isFalse();
        assertThat((Boolean) twoFifty.gTEq(oneFifty).getValue()).isTrue();
        assertThat((Boolean) oneFifty.gTEq(oneFifty).getValue()).isTrue();

        assertThat((Boolean) date.gTEq(dateTwo).getValue()).isFalse();
        assertThat((Boolean) dateTwo.gTEq(date).getValue()).isTrue();
        assertThat((Boolean) date.gTEq(date).getValue()).isTrue();
    }

    @Test
    public void testLessThanOrEqual() {
        assertThat((Boolean) one.lTEq(two).getValue()).isTrue();
        assertThat((Boolean) two.lTEq(one).getValue()).isFalse();
        assertThat((Boolean) one.lTEq(one).getValue()).isTrue();

        assertThat((Boolean) zeroPointFive.lTEq(onePointFive).getValue()).isTrue();
        assertThat((Boolean) onePointFive.lTEq(zeroPointFive).getValue()).isFalse();
        assertThat((Boolean) zeroPointFive.lTEq(zeroPointFive).getValue()).isTrue();

        assertThat((Boolean) oneFifty.lTEq(twoFifty).getValue()).isTrue();
        assertThat((Boolean) twoFifty.lTEq(oneFifty).getValue()).isFalse();
        assertThat((Boolean) oneFifty.lTEq(oneFifty).getValue()).isTrue();

        assertThat((Boolean) date.lTEq(dateTwo).getValue()).isTrue();
        assertThat((Boolean) dateTwo.lTEq(date).getValue()).isFalse();
        assertThat((Boolean) date.lTEq(date).getValue()).isTrue();
    }

    @Test
    public void testUndefined(){
        assertThat(one.plus(undefined).isDefined()).isFalse();
        assertThat(one.min(undefined).isDefined()).isFalse();
        assertThat(one.div(undefined).isDefined()).isFalse();
        assertThat(one.prod(undefined).isDefined()).isFalse();
        assertThat(date.lT(undefined).isDefined()).isFalse();
        assertThat(date.gT(undefined).isDefined()).isFalse();
        assertThat(date.gTEq(undefined).isDefined()).isFalse();
        assertThat(date.lTEq(undefined).isDefined()).isFalse();
        assertThat(boolTrue.and(undefined).isDefined()).isFalse();
        assertThat(boolTrue.or(undefined).isDefined()).isFalse();
    }

    @Test
    public void cantCompareDifferentTypes() {
//        assertThatThrownBy(() -> one.gT(onePointFive))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare integer with decimal");
//
//        assertThatThrownBy(() -> one.gT(oneFifty))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare integer with money");
//
//        assertThatThrownBy(() -> one.gT(date))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare integer with date");
//
//        assertThatThrownBy(() -> zeroPointFive.gT(oneFifty))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare decimal with money");
//
//        assertThatThrownBy(() -> zeroPointFive.gT(date))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare decimal with date");
//
//        assertThatThrownBy(() -> oneFifty.gT(date))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Cannot compare money with date");
    }

    @Test
    public void testNeg() {
        assertThat(one.neg().getValue()).isEqualTo(-1);
    }
}
