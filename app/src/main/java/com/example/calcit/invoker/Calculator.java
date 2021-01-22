package com.example.calcit.invoker;

import com.example.calcit.commands.OperationCommand;

import java.util.Stack;
/**
 * This class is the executor of all mathematical operations
 * to keep its' orders to implement the undo and redo functionality
 * it uses {@link CommandManager} to organize the operations
 * */

public class Calculator {
    CommandManager mCommandManager;

    /**
     * Constructor of {@link Calculator} class
     * */
    public Calculator() {
        mCommandManager = new CommandManager();
    }


   /**
    * Adds the operation to the stack through {@link CommandManager} without
    * knowing about the detail of the operation or the way of keeping track of it's order
    * @param command the operation to be saved and executed
    * @return the result of the operation
    * */
    public Double execute(OperationCommand command){
        mCommandManager.addOperation(command);
        return command.execute();
    }
    /**
     * Undo the last operation
     * @return OperationCommand if the stack is not empty or null if it is */

    public Double undo(){
        OperationCommand command = mCommandManager.undo();
        if(command != null)
            return command.unexecute();
        else
            return null;
    }

    /**
     * redo the previous operation
     * @return OperationCommand if the stack is not empty or null if it is */
    public Double redo(){
        OperationCommand command = mCommandManager.redo();
        if(command != null)
            return command.execute();
        else
            return null;
    }
    /**
     * Clears All the history of operations in {@link CommandManager}*/
    public void clearAllOperations(){
        mCommandManager.clearAllOperations();
    }

    /**
     * This class is the manager of all mathematical operations
     * to keep its' orders to implement the undo and redo functionality
     * it uses stacks to organize the operations
     * */
    private class CommandManager {
        private Stack<OperationCommand> historyList;
        private Stack<OperationCommand> redoList;
        CommandManager() {
            historyList = new Stack<>();
            redoList = new Stack<>();
        }
        /**
         * Adds the operation to the history list
         * @param operation is the operation to be added
         * */

        public void addOperation(OperationCommand operation){
            historyList.push(operation);
        }

        /**
         * Undo the operation if the history list is not empty and adds it to the redo list
         * @return OperationCommand if the historyList is not empty or null if it is
         * */
        public OperationCommand undo(){
            OperationCommand lastCommand = null;
            if(!historyList.empty()) {
                lastCommand = historyList.pop();
                redoList.push(lastCommand);
            }
            return lastCommand;
        }

        /**
         * Redo the operation if the Redo list is not empty and adds it to the undo list
         * @return OperationCommand if the RedoList is not empty or null if it is
         * */
        public OperationCommand redo(){
            OperationCommand lastUndo = null;
            if(!redoList.empty()) {
                redoList.pop();
                historyList.push(lastUndo);
            }
            return lastUndo;
        }

        /**
         * Clears all the history of the operations*/
        public void clearAllOperations() {
            historyList.clear();
            redoList.clear();
        }
    }
}
