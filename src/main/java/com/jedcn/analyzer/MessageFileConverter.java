package com.jedcn.analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageFileConverter {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MessageFileConverter <filename>");
            System.exit(1);
        }
        
        String filename = args[0];
        Map<String, ConfigEntry> configMap = parseMessageFile(filename);
        
        // Convert to JSON and print
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(configMap);
        System.out.println(json);
    }
    
    private static Map<String, ConfigEntry> parseMessageFile(String filename) {
        Map<String, ConfigEntry> configMap = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentKey = null;
            String currentDefault = null;
            String currentDescription = null;
            StringBuilder commentBuilder = new StringBuilder();
            
            // Pattern to match entries like: KEY {default} description
            Pattern entryPattern = Pattern.compile("^([A-Z0-9]+)\\s+\\{([^}]*)\\}\\s*(.*)$");
            
            while ((line = reader.readLine()) != null) {
                Matcher matcher = entryPattern.matcher(line);
                
                if (matcher.find()) {
                    // If we were processing a previous entry, add it to the map
                    if (currentKey != null) {
                        ConfigEntry entry = new ConfigEntry();
                        entry.comment = commentBuilder.toString().trim();
                        entry.defaultValue = currentDefault;
                        entry.description = currentDescription;
                        configMap.put(currentKey, entry);
                        
                        // Reset for new entry
                        commentBuilder = new StringBuilder();
                    }
                    
                    // Extract new entry information
                    currentKey = matcher.group(1);
                    currentDefault = matcher.group(2);
                    currentDescription = matcher.group(3).trim();
                } else if (currentKey != null) {
                    // Add to comment for current entry
                    if (commentBuilder.length() > 0) {
                        commentBuilder.append("\n");
                    }
                    commentBuilder.append(line);
                }
            }
            
            // Add the last entry if there is one
            if (currentKey != null) {
                ConfigEntry entry = new ConfigEntry();
                entry.comment = commentBuilder.toString().trim();
                entry.defaultValue = currentDefault;
                entry.description = currentDescription;
                configMap.put(currentKey, entry);
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        return configMap;
    }
    
    // Class to hold the configuration entry values
    private static class ConfigEntry {
        private String comment = "";
        private String defaultValue = "";
        private String description = "";
        
        // Gson uses these field names in the JSON output
        public String getComment() {
            return comment;
        }
        
        public String getDefault() {
            return defaultValue;
        }
        
        public String getDescription() {
            return description;
        }
    }
}