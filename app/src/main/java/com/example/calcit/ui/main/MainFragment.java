package com.example.calcit.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.calcit.commands.AdditionCommand;
import com.example.calcit.commands.DivisionCommand;
import com.example.calcit.commands.MultiplicationCommand;
import com.example.calcit.commands.OperationCommand;
import com.example.calcit.commands.SubtractionCommand;
import com.example.calcit.databinding.MainFragmentBinding;
import com.example.calcit.invoker.Calculator;

public class MainFragment extends Fragment {
    Toast mToast;
    private enum Operator {ADD, SUB, DIV, MUL;

        @NonNull
        @Override
        public String toString() {
            String operator = null;
            switch (this){
                case DIV:
                    operator = "/";
                    break;
                case MUL:
                    operator = "*";
                    break;
                case ADD:
                    operator = "+";
                    break;
                case SUB:
                    operator = "-";
                    break;

            }
            return operator;
        }
    }
    private Double firstOperand,secOperand;
    private OperationCommand currOperation;
    private Calculator mCalculator;
    private Operator mOperator;
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    MainFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(getLayoutInflater());
        mToast = Toast.makeText(getActivity(),"",Toast.LENGTH_LONG);
        mCalculator = new Calculator();
        addNumbersClickListeners();
        addOperationsClickListeners();
        addUndoRedoFunctions();
        return binding.getRoot();
    }

    /**
     * Adds functionality to all Number buttons
     **/
    private void addNumbersClickListeners(){
        View.OnClickListener listener = view -> {
            String num = ((Button)view).getText().toString();
            binding.textView.append(num);
        };
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.btnZero.setOnClickListener(listener);
        binding.btnDec.setOnClickListener(listener);
    }

    /**
     * Adds functionality to all Operations  buttons
     **/
    private void addOperationsClickListeners() {
        binding.buttonSum.setOnClickListener(view -> {
            handleMathematicalOperatorsButtons(Operator.ADD);
        });
        binding.buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMathematicalOperatorsButtons(Operator.SUB);
            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMathematicalOperatorsButtons(Operator.MUL);
            }
        });
        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMathematicalOperatorsButtons(Operator.DIV);
            }
        });
        binding.btnEqual.setOnClickListener(view -> {

            if(firstOperand != null && !binding.textView.toString().isEmpty()){
                secOperand = Double.valueOf(binding.textView.getText().toString());
                doOperation(mOperator);
                resetOperations();
            }

        });
        binding.btnClear.setOnClickListener(view -> {
            resetOperations();
        });
        binding.buttonBack.setOnClickListener(view -> {
            handleBackspace();
        });
    }

    /**
     * Handles user clicks on the mathematical operators buttons
     * keeps track of
     * firstOperand , secOperand, and mOperator
    */
    private void handleMathematicalOperatorsButtons(Operator operator) {
        //checks if the textview is not empty then we need to check whether this number is first or sec operands
        if (!binding.textView.getText().toString().isEmpty()) {
            //if the mOperator is null then it is the first operation and first operand is null also
            if (mOperator == null) {
                mOperator = operator;
                firstOperand = Double.valueOf(binding.textView.getText().toString());
                //move the number to the result textview
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString() + " " + operator.toString());
            }
            //else (if the operator is not null)then the number displayed
            // is the sec operand then do the operation and change the operator by the current
            //operation in the sequence
            else {
                doOperation(mOperator);
                //we need to change the operator here because the previous operation may be different so
                //we changes it waiting for the second operand
                mOperator = operator;
                binding.textView2.append(" " + operator.toString());
            }
            //if the operator is null and there is no number written in the text view
            //then prompt the user to enter a number
        } else if (mOperator == null) {
            Toast.makeText(getActivity(), "Please enter a number first", Toast.LENGTH_LONG).show();
        } else {
            //if there is no number in the text view and the user typed the operator again then
            //change the operator in the result text view
            changeLastOperator(operator.toString());
            mOperator = operator;
        }
    }
    /**
     * Execute the operation when the second operand is ready
     * set the value of firstOperand to the result of the operation
     * in case if the user wand to complete the sequence and set the j
     * secOperand to null to indicates
     * */
    private void doOperation(Operator operator) {
        secOperand = Double.valueOf(binding.textView.getText().toString());
        switch (operator){
            case ADD:
                AdditionCommand additionCommand = new AdditionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(additionCommand);
                break;
            case SUB:
                SubtractionCommand subtractionCommand = new SubtractionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(subtractionCommand);
                break;
            case MUL:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                MultiplicationCommand multiplicationCommand = new MultiplicationCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(multiplicationCommand);

                break;
            case DIV:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                DivisionCommand divisionCommand = new DivisionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(divisionCommand);
                break;

        }
        secOperand = null;
        binding.textView.setText("");
        binding.textView2.setText(firstOperand.toString());
    }
    /**
     * Adds functionality to Undo and redo buttons
     **/
    private void addUndoRedoFunctions() {
        binding.btnUndo.setOnClickListener(view -> {
            Double lastResult = mCalculator.undo();
            //checks if there is operation to undo
            if(lastResult == null) {
                mToast.setText("No operation to undo");
                mToast.show();
            }else {
                //if there is previous operation then set the first operand to
                //it's reversed result and display it
                firstOperand = lastResult;
                binding.textView2.setText(lastResult.toString());
            }
        });
        binding.btnRedo.setOnClickListener(view -> {
            Double redoResult = mCalculator.redo();
            if(redoResult == null){
                mToast.setText("No operation to redo");
                mToast.show();
            }else {
                firstOperand = redoResult;
                binding.textView2.setText(redoResult.toString());
            }
        });
    }

    /**
     * Resets all the values to begin another sequence of operations
     * */
    private void resetOperations() {
        binding.textView2.setText("");
        binding.textView.setText("");
        firstOperand = null;
        secOperand = null;
        mOperator = null;
        mCalculator.clearAllOperations();
    }

    /**
     * Change the last operator if the user clicks the operator button twice
     * */
    private void changeLastOperator(String operator) {
        String curr = binding.textView2.getText().toString();
        String after;
        if(!curr.isEmpty()) {
            after = curr.substring(0, curr.length() - 1);
            binding.textView2.setText(after);
            binding.textView2.append(operator);
        }
    }



    /**
     * Handle the back space to remove the last written character
     * */
    private void handleBackspace() {
        String curr = binding.textView.getText().toString();
        String after;
        if(!curr.isEmpty()) {
            after = curr.substring(0, curr.length() - 1);
            binding.textView.setText(after);
        }
    }



}
