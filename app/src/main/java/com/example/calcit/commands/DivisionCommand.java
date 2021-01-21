package com.example.calcit.commands;

public class DivisionCommand implements OperationCommand {
    private Double operand1, operand2,result;
    public DivisionCommand(Double operand1, Double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Double getOperand1() {
        return operand1;
    }

    public void setOperand1(Double operand1) {
        this.operand1 = operand1;
    }

    public Double getOperand2() {
        return operand2;
    }

    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    public Double getResult() {
        return result;
    }


    @Override
    public Double execute() {
        if(operand1 == null || operand2 == null){
            throw new IllegalArgumentException("One operand is missed");
        }
        if(operand2 == 0){
            throw new IllegalArgumentException("Mathematical error.. No division by Zero");
        }
        result = operand1 / operand2;
        return result;
    }
    @Override
    public Double unexecute() {
        return operand1;
    }
}
