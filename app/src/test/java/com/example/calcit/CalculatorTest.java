package com.example.calcit;

import com.example.calcit.commands.AdditionCommand;
import com.example.calcit.commands.DivisionCommand;
import com.example.calcit.commands.MultiplicationCommand;
import com.example.calcit.commands.SubtractionCommand;
import com.example.calcit.invoker.Calculator;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


/**
 * JUnit4 unit tests for the calculator logic.
 * These are local unit tests; no device needed.
 */
@RunWith(JUnit4.class)
public class CalculatorTest {
    private Calculator mCalculator;

    /**
     * Sets up the environment for testing.
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Tests for simple addition.
     */
    @Test
    public void addTwoNumbersThenReturnsTheSum() {
        //arrange
        Double operand1 = 4.0;
        Double operand2 = 6.0;
        AdditionCommand additionCommand = new AdditionCommand(operand1,operand2);
        //act
        Double resultAdd = mCalculator.execute(additionCommand);
        //assert
        assertThat(resultAdd, is(equalTo(10d)));
    }
    /**
     * Tests for addition with a negative operand.
     */
    @Test
    public void addOneNegativeNumberThenReturnsTheSumPositive() {
        //arrange
        Double operand1 = -4.0;
        Double operand2 = 9.0;
        AdditionCommand additionCommand = new AdditionCommand(operand1,operand2);
        //act
        Double resultAdd = mCalculator.execute(additionCommand);
        //assert
        assertThat(resultAdd, is(equalTo(5d)));
    }

    /**
     * Tests for floating-point addition.
     */
    @Test
    public void addTwoNumbersFloats() {
        //arrange
        Double operand1 = 1.111d;
        Double operand2 = 1.111d;
        AdditionCommand additionCommand = new AdditionCommand(operand1,operand2);
        //act
        Double resultAdd = mCalculator.execute(additionCommand);
        //assert
        assertThat(resultAdd, is(equalTo(2.222)));
    }
    /**
     * Tests for simple subtraction with a negative result.
     */
    @Test
    public void subWorksWithNegativeResult() {
        //arrange
        Double operand1 = 1d;
        Double operand2 = 17d;
        SubtractionCommand subtractionCommand = new SubtractionCommand(operand1,operand2);
        //act
        Double resultSub = mCalculator.execute(subtractionCommand);
        //assert
        assertThat(resultSub, is(equalTo(-16d)));
    }
    /**
     * Tests for simple division.
     */
    @Test
    public void divTwoNumbers() {
        //arrange
        Double operand1 = 32d;
        Double operand2 = 2d;
        DivisionCommand divisionCommand = new DivisionCommand(operand1,operand2);
        //act
        Double resultDiv = mCalculator.execute(divisionCommand);
        //assert
        assertThat(resultDiv, is(equalTo(16d)));
    }
    /**
     * Tests for  division. by zero
     */
    @Test(expected = IllegalArgumentException.class)
    public void divByZeroThenThrowsException() {
        //arrange
        Double operand1 = 32d;
        Double operand2 = 0d;
        DivisionCommand divisionCommand = new DivisionCommand(operand1,operand2);
        mCalculator.execute(divisionCommand);
    }
    /**
     * Tests for simple multiplication.
     */
    @Test
    public void mulTwoNumbers() {
        //arrange
        Double operand1 = 32d;
        Double operand2 = 2d;
        MultiplicationCommand multiplicationCommand = new MultiplicationCommand(operand1,operand2);
        //act
        Double resultMul = mCalculator.execute(multiplicationCommand);
        //assert
        assertThat(resultMul, is(equalTo(64d)));
    }
}
