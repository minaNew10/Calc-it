package com.example.calcit.invoker;

import com.example.calcit.commands.OperationCommand;

import java.util.Stack;

public class Calculator {
    CommandManager mCommandManager;

    public Calculator() {
        mCommandManager = new CommandManager();
    }


    public Double execute(OperationCommand command){
        mCommandManager.addOperation(command);
        return command.execute();
    }

    public Double undo(){
        OperationCommand command = mCommandManager.undo();
        if(command != null)
            return command.unexecute();
        else
            return null;
    }

    public Double redo(){
        OperationCommand command = mCommandManager.redo();
        if(command != null)
            return command.execute();
        else
            return null;
    }
    public void clearAllOperations(){
        mCommandManager.clearAllOperations();
    }

    private class CommandManager {
        private Stack<OperationCommand> historyList;
        private Stack<OperationCommand> redoList;
        CommandManager() {
            historyList = new Stack<>();
            redoList = new Stack<>();
        }

        public void addOperation(OperationCommand operation){
            historyList.push(operation);
        }

        public OperationCommand undo(){
            OperationCommand lastCommand = null;
            if(!historyList.empty()) {
                lastCommand = historyList.pop();
                redoList.push(lastCommand);
            }
            return lastCommand;
        }

        public OperationCommand redo(){
            OperationCommand lastUndo = null;
            if(!redoList.empty()) {
                redoList.pop();
                historyList.push(lastUndo);
            }
            return lastUndo;
        }

        public void clearAllOperations() {
            historyList.clear();
            redoList.clear();
        }
    }
}
