

class PrintscriptRunnerTest {
    private val sourceCode = """
            let a : string = "hello world!";
            let b : string = "hello world!";
            b = "5" + "5";
    """.trimIndent()
    private val sourceString =
        "[let a : string = \"hello world!\", let b : string = \"hello world!\", b = \"5\" + \"5\"]" // TODO replace when interpreter is ready

    // TODO: ESTE TEST NO ANDA @CHONA
   /* @Test
    fun `should run a simple program`() {
        // Create a temporary file
        val sourceFile = File.createTempFile("test", ".kt")

        try {
            // Write the source code to the file
            sourceFile.writeText(sourceCode)

            fun foo(input: String) {
                println("message: $input")
            }

            val ast = runBlocking {
                v1Runner.runExecution(sourceFile, ::foo)
            }

            assertEquals(sourceString, ast)
        } finally {
            // Delete the temporary file
            sourceFile.delete()
        }
    }*/

//    @Test
//    fun `should format a simple program`() { CONFIG FILE IS NOT WORKING
//        val sourceCode = """
//            let a : string = "hello world!";
//            let b : string = "hello world!";
//            b = "5" + "5";
//        """.trimIndent()
//
//        // Create a temporary file
//        val sourceFile = File.createTempFile("test", ".kt")
//        val configFile = File("config.json")
//
//        try {
//            // Write the source code to the file
//            sourceFile.writeText(sourceCode)
//
//            val ast = v1Runner.runFormatting(sourceFile, configFile) // TODO add args
//
//            assertEquals(sourceString, sourceString) // TODO fix
//        } finally {
//            // Delete the temporary file
//            sourceFile.delete()
//        }
//    }
}
