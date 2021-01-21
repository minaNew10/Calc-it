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
    private enum Operator {ADD, SUB, DIV, MUL}
    private MainViewModel mViewModel;
    private Integer firstOperand,secOperand;
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
        mCalculator = new Calculator();
        addNumbersClickListeners();
        addOperationsClickListeners();
        return binding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
    private void addNumbersClickListeners(){
        binding.button1.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"1");
                else
                    binding.textView.setText("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"2");
                else
                    binding.textView.setText("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"3");
                else
                    binding.textView.setText("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"4");
                else
                    binding.textView.setText("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"5");
                else
                    binding.textView.setText("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"6");
                else
                    binding.textView.setText("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"7");
                else
                    binding.textView.setText("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"8");
                else
                    binding.textView.setText("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            String currtext;
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty())
                    currtext = binding.textView.getText().toString();
                if(currtext != null)
                    binding.textView.setText(currtext+"9");
                else
                    binding.textView.setText("9");
            }
        });;
    }

    private void addOperationsClickListeners() {
        binding.buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    //if the mOperator is null then it is the first operation and first operand is null also
                    if(mOperator == null) {
                        mOperator = Operator.ADD;
                        firstOperand = Integer.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                    }
                    //else it is the sec operand then do the operation and change the operator by the current one for the next
                    //operation in the sequence
                    else {
                        doOperation(mOperator);
                        mOperator = Operator.ADD;
                        binding.textView2.append(" " + "+");
                    }
                }else {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG);
                }
            }
        });
        binding.buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.SUB;
                        firstOperand = Integer.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                    }
                    else {
                       doOperation(mOperator);
                       mOperator = Operator.SUB;
                       binding.textView2.append(" " + "-");
                    }
                }else {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG);
                }
            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.MUL;
                        firstOperand = Integer.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                    }
                    else {
                        doOperation(mOperator);
                        mOperator = Operator.MUL;
                        binding.textView2.append(" " + "*");
                    }
                }else {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG);
                }
            }
        });
        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.textView.getText().toString().isEmpty()){
                    if(mOperator == null) {
                        mOperator = Operator.DIV;
                        firstOperand = Integer.valueOf(binding.textView.getText().toString());
                        binding.textView.setText("");
                    }
                    else {
                        doOperation(mOperator);
                        mOperator = Operator.DIV;
                        binding.textView2.append(" " + "/");
                    }
                }else {
                    Toast.makeText(getActivity(),"Please enter a number first",Toast.LENGTH_LONG);
                }
            }
        });

    }

    private void doOperation(Operator operator) {
        switch (operator){
            case ADD:
                secOperand = Integer.valueOf(binding.textView.getText().toString());
                AdditionCommand additionCommand = new AdditionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(additionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case SUB:
                secOperand = Integer.valueOf(binding.textView.getText().toString());
                SubtractionCommand subtractionCommand = new SubtractionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(subtractionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case MUL:
                secOperand = Integer.valueOf(binding.textView.getText().toString());
                MultiplicationCommand multiplicationCommand = new MultiplicationCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(multiplicationCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;
            case DIV:
                secOperand = Integer.valueOf(binding.textView.getText().toString());
                DivisionCommand divisionCommand = new DivisionCommand(firstOperand,secOperand);
                firstOperand = mCalculator.execute(divisionCommand);
                secOperand = null;
                binding.textView.setText("");
                binding.textView2.setText(firstOperand.toString());
                break;

        }
    }

}
