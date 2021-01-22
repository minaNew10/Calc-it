package com.example.calcit.commands;

/**
 * This class creates the addition mathematical operation
 * it implements {@link OperationCommand}
 * */
public class AdditionCommand implements OperationCommand {
    private Double operand1, operand2, result;

    /**
     * Constructor of {@link AdditionCommand}
     * @param operand1 the first operand of the operation
     * @param operand2 the second operand of the operation
     */
    public AdditionCommand(Double operand1, Double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * @return:  operand1 the first operand of the operation
     * */
    public Double getOperand1() {
        return operand1;
    }

    /**
     * sets the first operand
     * @Param: operand1 the first operand of the operation*/
    public void setOperand1(Double operand1) {
        this.operand1 = operand1;
    }
    /**
     * @return:  operand2 the second operand of the operation
     * */
    public Double getOperand2() {
        return operand2;
    }

    /**
     * sets the second operand
     * @Param: operand2 the second operand of the operation*/
    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    /**
     * @return:  result the result of the operation
     * if returns null then the operation is not implemented yet
     * */
    public Double getResult() {
        return result;
    }
    /**
     * Executes addition operation and returns the result
     * @return result of the addition operation
     * @throws IllegalArgumentException if one of the operands is missed
     * */
    @Override
    public Double execute() {
        if (operand1 == null || operand2 == null) {
            throw new IllegalArgumentException("One operand is missed");
        }
        result = operand1 + operand2;
        return result;
    }
    /**
     * Reverse the operation and returns the first operand to be used for undo functionality
     * @return operand1 as a result of reversing the operation */
    @Override
    public Double unexecute() {
        return operand1;
    }
}
