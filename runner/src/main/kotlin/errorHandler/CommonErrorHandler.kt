import errorHandler.ErrorHandler

class CommonErrorHandler : ErrorHandler {
    override fun reportError(message: String?) {
        throw Exception(message)
    }
}
