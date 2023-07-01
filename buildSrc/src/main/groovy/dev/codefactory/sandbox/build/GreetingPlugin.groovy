package dev.codefactory.sandbox.build

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

/**
 * https://docs.gradle.org/current/userguide/custom_plugins.html
 */
class GreetingPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.getTasks().register('greeting', Greeting)
    }

    static class Greeting extends DefaultTask {
        @TaskAction
        def greet() {
            println 'Hello from GreetingPlugin'
        }
    }
}