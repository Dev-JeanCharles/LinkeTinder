package org.jean.linketinder.Exceptions

class HandleException {

    static handleException(String message, Exception e) {
        println("$message: ${e.message}")
    }
}
