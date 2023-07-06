package util

import errorHandler.ErrorHandler

class CommonErrorHandler : ErrorHandler {
    private val errors: MutableList<String> = ArrayList()
    override fun reportError(message: String?) {
        if (message != null) errors.add(message)
    }

    fun getErrors(): List<String> {
        return errors
    }
}
