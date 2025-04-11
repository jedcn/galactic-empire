# MBMGEMAP - Planet Database Extract Utility

This directory contains a tool to extract planet data from the game's database and create a map file (MBMGEPLT.MAP).

## Files

- [**MBMGEMAP.C**](MBMGEMAP.C) - Main source code for the map extraction utility
- [**MBMGEMAP.H**](MBMGEMAP.H) - Header file containing data structures and definitions
- [**MBMGEMAP.HP**](MBMGEMAP.HP) - Additional header file for platform-specific definitions
- [**MBMGEMAP.LNK**](MBMGEMAP.LNK) - Linker file for building the executable
- [**MBMGEMAP.DOC**](MBMGEMAP.DOC) - Documentation explaining the purpose and operation of the tool
- [**GECLEAN.BAT**](GECLEAN.BAT) - Batch file for automation and cleaning operations
- [**README.NOW**](README.NOW) - Original readme file with usage instructions

## Purpose

This utility is intended to be run during nightly maintenance to update the map file. It allows users to download and visualize the game map. The map file contains information that allows users to render graphical starmaps of the current game state.