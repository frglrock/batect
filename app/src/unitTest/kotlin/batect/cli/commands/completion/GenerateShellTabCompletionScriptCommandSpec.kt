/*
   Copyright 2017-2020 Charles Korn.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package batect.cli.commands.completion

import batect.cli.CommandLineOptions
import batect.cli.CommandLineOptionsParser
import batect.cli.options.OptionDefinition
import batect.cli.options.OptionParser
import batect.os.HostEnvironmentVariables
import batect.telemetry.AttributeValue
import batect.telemetry.TelemetrySessionBuilder
import batect.testutils.createForEachTest
import batect.testutils.equalTo
import batect.testutils.given
import batect.testutils.runForEachTest
import batect.testutils.withMessage
import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.hasElement
import com.natpryce.hamkrest.throws
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

// This class is tested primarily by the tests in the app/src/completionTest directory.
object GenerateShellTabCompletionScriptCommandSpec : Spek({
    describe("a 'generate shell tab completion script' command") {
        val commandLineOptions = CommandLineOptions(generateShellTabCompletionScript = KnownShell.Fish)

        val option1 by createForEachTest { createMockOption() }
        val option2 by createForEachTest { createMockOption() }
        val parser by createForEachTest {
            mock<OptionParser> {
                on { getOptions() } doReturn setOf(option1, option2)
            }
        }
        val commandLineOptionsParser by createForEachTest {
            mock<CommandLineOptionsParser> {
                on { optionParser } doReturn parser
            }
        }

        val outputStream by createForEachTest { ByteArrayOutputStream() }
        val lineGenerator by createForEachTest {
            mock<FishShellTabCompletionLineGenerator> {
                on { generate(option1, "batect-1.2.3") } doReturn "completion-line-option-1"
                on { generate(option2, "batect-1.2.3") } doReturn "completion-line-option-2"
            }
        }

        val telemetrySessionBuilder by createForEachTest { mock<TelemetrySessionBuilder>() }

        given("the 'BATECT_COMPLETION_PROXY_REGISTER_AS' environment variable is set") {
            val environmentVariables by createForEachTest {
                HostEnvironmentVariables(
                    "BATECT_COMPLETION_PROXY_REGISTER_AS" to "batect-1.2.3",
                    "BATECT_COMPLETION_PROXY_VERSION" to "4.5.6"
                )
            }

            val command by createForEachTest { GenerateShellTabCompletionScriptCommand(commandLineOptions, commandLineOptionsParser, lineGenerator, PrintStream(outputStream), environmentVariables, telemetrySessionBuilder) }
            val exitCode by runForEachTest { command.run() }

            it("returns a zero exit code") {
                assertThat(exitCode, equalTo(0))
            }

            it("emits completion lines for each of the registered options") {
                assertThat(outputStream.toString().lines(), hasElement("completion-line-option-1") and hasElement("completion-line-option-2"))
            }

            it("records an event in telemetry with the shell name and proxy completion script version") {
                verify(telemetrySessionBuilder).addEvent(
                    "GeneratedShellTabCompletionScript",
                    mapOf(
                        "shell" to AttributeValue("Fish"),
                        "proxyCompletionScriptVersion" to AttributeValue("4.5.6")
                    )
                )
            }
        }

        given("the 'BATECT_COMPLETION_PROXY_REGISTER_AS' environment variable is not set") {
            val environmentVariables by createForEachTest { HostEnvironmentVariables() }
            val command by createForEachTest { GenerateShellTabCompletionScriptCommand(commandLineOptions, commandLineOptionsParser, lineGenerator, PrintStream(outputStream), environmentVariables, telemetrySessionBuilder) }

            it("throws an appropriate exception") {
                assertThat({ command.run() }, throws(withMessage("'BATECT_COMPLETION_PROXY_REGISTER_AS' environment variable not set.")))
            }
        }
    }
})

private fun createMockOption(): OptionDefinition = mock {
    on { showInHelp } doReturn true
}
