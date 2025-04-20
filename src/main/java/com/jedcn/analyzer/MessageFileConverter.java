package com.jedcn.analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFileConverter {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MessageFileConverter <filename>");
            System.exit(1);
        }
        
        String filename = args[0];
        List<ConfigEntry> entries = parseMessageFile(filename);
        
        // Generate and print Java class
        String javaCode = generateJavaClass(entries, "GalacticEmpireConfig");
        System.out.println(javaCode);
    }
    
    private static List<ConfigEntry> parseMessageFile(String filename) {
        List<ConfigEntry> entries = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            ConfigEntry currentEntry = null;
            StringBuilder commentBuilder = new StringBuilder();
            
            // Pattern to match entries like: KEY {default} description
            Pattern entryPattern = Pattern.compile("^([A-Z0-9]+)\\s+\\{([^}]*)\\}\\s*(.*)$");
            
            while ((line = reader.readLine()) != null) {
                Matcher matcher = entryPattern.matcher(line);
                
                if (matcher.find()) {
                    // If we were processing a previous entry, add it to the list
                    if (currentEntry != null) {
                        currentEntry.comment = commentBuilder.toString().trim();
                        entries.add(currentEntry);
                    }
                    
                    // Create new entry
                    currentEntry = new ConfigEntry();
                    currentEntry.key = matcher.group(1);
                    currentEntry.defaultValue = matcher.group(2);
                    currentEntry.description = matcher.group(3).trim();
                    
                    // Reset comment builder for the new entry
                    commentBuilder = new StringBuilder();
                } else if (currentEntry != null) {
                    // Add to comment for current entry
                    if (commentBuilder.length() > 0) {
                        commentBuilder.append("\n");
                    }
                    commentBuilder.append(line);
                } else {
                    // Before first entry, could be file header
                    // We don't need to capture this for the Java output
                }
            }
            
            // Add the last entry if there is one
            if (currentEntry != null) {
                currentEntry.comment = commentBuilder.toString().trim();
                entries.add(currentEntry);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        return entries;
    }
    
    private static String generateJavaClass(List<ConfigEntry> entries, String className) {
        StringBuilder sb = new StringBuilder();
        
        // Class declaration
        sb.append("public class ").append(className).append(" {\n");
        sb.append("    \n");
        
        // Add each entry as a constant
        for (ConfigEntry entry : entries) {
            // Add the comment as JavaDoc
            if (!entry.comment.isEmpty()) {
                sb.append("    /**\n");
                for (String commentLine : entry.comment.split("\n")) {
                    sb.append("     * ").append(commentLine).append("\n");
                }
                sb.append("     * ").append(entry.key).append(" {").append(entry.defaultValue).append("}");
                if (!entry.description.isEmpty()) {
                    sb.append(" ").append(entry.description);
                }
                sb.append("\n");
                sb.append("     */\n");
            } else {
                sb.append("    /**\n");
                sb.append("     * ").append(entry.key).append(" {").append(entry.defaultValue).append("}");
                if (!entry.description.isEmpty()) {
                    sb.append(" ").append(entry.description);
                }
                sb.append("\n");
                sb.append("     */\n");
            }
            
            // Add the constant definition
            sb.append("    public static final String ").append(entry.key).append(" = ");
            
            // Format the default value correctly (escape quotes, etc.)
            sb.append("\"").append(escapeJavaString(entry.defaultValue)).append("\";\n");
            
            // Add the description as a separate constant if it exists
            if (!entry.description.isEmpty()) {
                sb.append("    public static final String ").append(entry.key).append("_DESCRIPTION = ");
                sb.append("\"").append(escapeJavaString(entry.description)).append("\";\n");
            }
            
            sb.append("\n");
        }
        
        // Close the class
        sb.append("}\n");
        
        return sb.toString();
    }
    
    private static String escapeJavaString(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    
    private static class ConfigEntry {
        String key;
        String defaultValue = "";
        String description = "";
        String comment = "";
    }
}