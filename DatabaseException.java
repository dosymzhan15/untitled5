class DatabaseException extends RuntimeException {
    public DatabaseException(String message) { super(message); }
}

class EmptyExamException extends Exception {
    public EmptyExamException(String message) { super(message); }
}