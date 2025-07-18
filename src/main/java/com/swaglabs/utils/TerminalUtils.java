package com.swaglabs.utils;

public class TerminalUtils {

    public static void executionCommand(String... command)
    {
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.inheritIO();
            Process process = builder.start();
            process.waitFor();
            LogsUtil.info("Command executed successfully");
        }
        catch (Exception e)
        {
            LogsUtil.error("Failed to execute command: " + e.getMessage());
        }
    }
}
