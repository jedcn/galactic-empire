# MBMGEMP - Galactic Empire Main Game Module

This directory contains the core game source code for Galactic Empire, a space exploration and conquest game for Worldgroup BBS systems.

## Files

### Source Code
- [**GEMAIN.C**](GEMAIN.C) - Main game module entry point
- [**GECMDS.C**](GECMDS.C) - Implementation of game commands
- [**GECYBS.C**](GECYBS.C) - Cyborg/Cybertron enemy ships functionality
- [**GEDROIDS.C**](GEDROIDS.C) - Droid functionality implementation
- [**GEFUNCS.C**](GEFUNCS.C) - Utility functions
- [**GELIB.C**](GELIB.C) - Library functions
- [**GEPLANET.C**](GEPLANET.C) - Planet management functionality
- [**MBMGEGRF.C**](MBMGEGRF.C) - Graphics functionality
- [**SECURE.C**](SECURE.C) - Security and authentication features

### Headers
- [**GEMAIN.H**](GEMAIN.H) - Main header file
- [**GEGLOBAL.H**](GEGLOBAL.H) - Global definitions and data structures
- [**GEPROTO.H**](GEPROTO.H) - Function prototypes
- [**SECURE.H**](SECURE.H) - Security function declarations

### Data Files
- [**MBMGEMAL.DAT**](MBMGEMAL.DAT)/[**NEW**](MBMGEMAL.NEW) - Alliance data files
- [**MBMGEPLT.DAT**](MBMGEPLT.DAT)/[**NEW**](MBMGEPLT.NEW) - Planet data files
- [**MBMGESHP.DAT**](MBMGESHP.DAT)/[**NEW**](MBMGESHP.NEW) - Ship data files
- [**MBMGETEA.DAT**](MBMGETEA.DAT)/[**NEW**](MBMGETEA.NEW) - Team data files
- [**MBMGEUSR.DAT**](MBMGEUSR.DAT)/[**NEW**](MBMGEUSR.NEW) - User data files

### Message & Help Files
- [**MBMGEHLP.MSG**](MBMGEHLP.MSG) - Help message file
- [**MBMGEMSG.MSG**](MBMGEMSG.MSG) - Game message file
- [**MBMGESHP.MSG**](MBMGESHP.MSG) - Ship-related messages
- [**MBMGEMNU.TXT**](MBMGEMNU.TXT) - Menu text file

### Build Files
- [**MBMGEMP.LNK**](MBMGEMP.LNK) - Linker file
- [**MBMGEMP.MAK**](MBMGEMP.MAK) - Make file
- [**MBMGEMP.MDF**](MBMGEMP.MDF) - Module definition file

### Documentation
- [**MBMGEMP.DOC**](MBMGEMP.DOC) - Comprehensive game documentation
- [**GEINST.DOC**](GEINST.DOC) - Installation instructions
- [**GEREADME.DOC**](GEREADME.DOC) - General readme information

## Subdirectories

- [**GE/**](GE/) - Contains additional files organized into various categories:
  - [**DOCS/**](GE/DOCS/) - Documentation files
  - [**INSTALL/**](GE/INSTALL/) - Installation scripts
  - [**MSG/**](GE/MSG/) - Message files
  - [**REL/**](GE/REL/) - Release files
  - [**REL2/**](GE/REL2/) - Secondary release files
  - [**VIR60/**](GE/VIR60/) - Files for specific platform version

## Game Features

The game allows players to:
- Navigate the galaxy
- Colonize planets
- Engage in battles with other players and NPCs
- Research technologies
- Form alliances
- Build vast empires