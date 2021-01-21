package com.example.calcit.commands;

/**
 * this is the parent of All mathematical operations
 *
*
* */
public interface OperationCommand {
    Integer execute();
    Integer unexecute();
}
