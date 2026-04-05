package com.kshrd.pp_group_02_spring_mini_project.exception;


public class AlreadyExistsException extends RuntimeException {
        public AlreadyExistsException(String message){
            super(message);
        }
}
