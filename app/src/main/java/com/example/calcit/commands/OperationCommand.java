package com.example.calcit.commands;

/**
 * This is the parent of All mathematical operations to help in
 * undo and redo functionality
* */
public interface OperationCommand {
    Double execute();
    Double unexecute();
}
