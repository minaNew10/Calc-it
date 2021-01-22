package com.example.calcit.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calcit.R;
import com.example.calcit.commands.AdditionCommand;
import com.example.calcit.commands.DivisionCommand;
import com.example.calcit.commands.MultiplicationCommand;
import com.example.calcit.commands.OperationCommand;
import com.example.calcit.commands.SubtractionCommand;
import com.example.calcit.databinding.MainFragmentBinding;
import com.example.calcit.invoker.Calculator;

public class MainFragment extends Fragment {
    Toast mToast;
    private enum Operator {ADD, SUB, DIV, MUL}
    private MainViewModel mViewModel;
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

    private void addUndoRedoFunctions() {
        binding.btnUndo.setOnClickListener(view -> {
            Double lastResult = mCalculator.undo();
            if(lastResult == null) {
                mToast.setText("No operation to undo");
                mToast.show();
            }else {
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
    private void addNumbersClickListeners(){
        binding.button1.setOnClickListener(view -> binding.textView.append("1"));
        binding.button2.setOnClickListener(view -> binding.textView.append("2"));
        binding.button3.setOnClickListener(view -> binding.textView.append("3"));
        binding.button4.setOnClickListener(view -> binding.textView.append("4"));
        binding.button5.setOnClickListener(view -> binding.textView.append("5"));
        binding.button6.setOnClickListener(view -> binding.textView.append("6"));
        binding.button7.setOnClickListener(view -> binding.textView.append("7"));
        binding.button8.setOnClickListener(view -> binding.textView.append("8"));
        binding.button9.setOnClickListener(view -> binding.textView.append("9"));
        binding.btnZero.setOnClickListener(view -> binding.textView.append("0"));
        binding.btnDec.setOnClickListener(view -> binding.textView.append("."));
    }

    private void addOperationsClickListeners() {
        binding.buttonSum.setOnClickListener(view -> {
            if(!binding.textView.getText().toString().isEmpty()){
                //if the mOperator is null then it is the first operation and first operand is null also
                if(mOperator == null) {
                    mOperator = Operator.ADD;
                    firstOperand = Double.valueOf(binding.textView.getText().toString());
                    binding.textView.setText("");
                    binding.textView2.setText(firstOperand.toString() + " +");
                }
                //else it is the sec operand then do the operation and change the operator by the current one for the next
                //operation in the sequence
                else {
                    doOperation(mOperator);
                    mOperator = Operator.ADD;
                    binding.textView2.append(" " + "+");
                }
            }else if(mOperator == null){
                Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG).show();
            }else {
                changeLastOperator("+");
                mOperator = Operator.ADD;
            }
        });
        binding.buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.SUB;
                        firstOperand = Double.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                        binding.textView2.setText(firstOperand.toString() + " -");
                    }
                    else {
                       doOperation(mOperator);
                       mOperator = Operator.SUB;
                       binding.textView2.append(" " + "-");
                    }
                }else if(mOperator == null) {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG).show();
                }else {
                    changeLastOperator("-");
                    mOperator = Operator.SUB;
                }
            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.MUL;
                        firstOperand = Double.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                        binding.textView2.setText(firstOperand.toString() + " *");
                    }
                    else {
                        doOperation(mOperator);
                        mOperator = Operator.MUL;
                        binding.textView2.append(" " + "*");
                    }
                }else if(mOperator == null) {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG).show();
                }else {
                    changeLastOperator("*");
                    mOperator = Operator.MUL;
                }
            }
        });
        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.DIV;
                        firstOperand = Double.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                        binding.textView2.setText(firstOperand.toString() + " /");
                    }
                    else {
                        doOperation(mOperator);
                        mOperator = Operator.DIV;
                        binding.textView2.append(" " + "/");
                    }
                }else if(mOperator == null) {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG).show();
                }else {
                    changeLastOperator("/");
                    mOperator = Operator.DIV;
                }
            }
        });
        binding.btnEqual.setOnClickListener(view -> {
            
        });
        binding.btnClear.setOnClickListener(view -> {
            binding.textView2.setText("");
            binding.textView.setText("");
            firstOperand = null;
            secOperand = null;
            mOperator = null;
            mCalculator.clearAllOperations();
        });
        binding.buttonBack.setOnClickListener(view -> {
            handleBackspace();
        });
    }

    private void changeLastOperator(String operator) {
        String curr = binding.textView2.getText().toString();
        String after;
        if(!curr.isEmpty()) {
            after = curr.substring(0, curr.length() - 1);
            binding.textView2.setText(after);
            binding.textView2.append(operator);
        }
    }

    private void handleBackspace() {
        String curr = binding.textView.getText().toString();
        String after;
        if(!curr.isEmpty()) {
            after = curr.substring(0, curr.length() - 1);
            binding.textView.setText(after);
        }
    }

    private void doOperation(Operator operator) {
        switch (operator){
            case ADD:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                AdditionCommand additionCommand = new AdditionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(additionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case SUB:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                SubtractionCommand subtractionCommand = new SubtractionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(subtractionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case MUL:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                MultiplicationCommand multiplicationCommand = new MultiplicationCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(multiplicationCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case DIV:
                secOperand = Double.valueOf(binding.textView.getText().toString());
                DivisionCommand divisionCommand = new DivisionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(divisionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;

        }
    }

}
