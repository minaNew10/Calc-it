package com.example.calcit.invoker;

import com.example.calcit.commands.OperationCommand;

import java.util.Stack;

public class Calculator {
    CommandManager mCommandManager;

    public Calculator() {
        mCommandManager = new CommandManager();
    }


    public Integer execute(OperationCommand command){
        mCommandManager.addOperation(command);
        return command.execute();
    }

    public Integer undo(){
        OperationCommand command = mCommandManager.undo();
        return command.unexecute();
    }

    public Integer redo(){
        OperationCommand command = mCommandManager.redo();
        return command.execute();
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
            OperationCommand lastCommand = historyList.pop();
            redoList.push(lastCommand);
            return lastCommand;
        }

        public OperationCommand redo(){
            OperationCommand lastUndo = redoList.pop();
            historyList.push(lastUndo);
            return lastUndo;
        }
    }
}
