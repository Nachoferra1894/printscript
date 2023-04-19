package interfaces

interface LinterI {
    fun getLinteredCodeCorrection(node: ASTNode): String
}