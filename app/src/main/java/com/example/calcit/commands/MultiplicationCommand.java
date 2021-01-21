package com.example.calcit.commands;

class MultiplicationCommand implements OperationCommand {
    Integer operand1, operand2,result;
    public MultiplicationCommand(Integer operand1, Integer num2) {
        this.operand1 = operand1;
        this.operand2 = num2;
    }

    public Integer getOperand1() {
        return operand1;
    }

    public void setOperand1(Integer operand1) {
        this.operand1 = operand1;
    }

    public Integer getOperand2() {
        return operand2;
    }

    public void setOperand2(Integer operand2) {
        this.operand2 = operand2;
    }

    public Integer getResult() {
        return result;
    }


    @Override
    public Integer execute() {
        if(operand1 == null || operand2 == null){
            throw new IllegalArgumentException("One operand is missed");
        }
        result = operand1 * operand2;
        return result;
    }
    @Override
    public Integer unexecute() {
        return operand1;
    }
}
