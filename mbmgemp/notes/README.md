# Notes

## Sending Messages to Users

The functions `prfmsg` and [`outprfge`](../GEMAIN.C#L2546) are the core mechanisms for sending messages to the player.

The `prfmsg` and `outprfge` functions work together to handle the game's messaging system:

1. `prfmsg` Function:
- Used to format and prepare messages that will be sent to players
- Takes a message ID/type and optional parameters to format the message
- Messages are stored in predefined templates (like SCAN01, PHSRUP, KILLPNTS, etc.)
- Formats the message but doesn't actually send it - stores it in a buffer

2. `outprfge` Function:
- Actually sends/outputs the message that was prepared by `prfmsg`
- Takes two main parameters:
  - `filter`: Determines who should receive the message (ALWAYS, FILTER)
  - `usrnum`: The user number/ID of the recipient
- Handles different message delivery scenarios:
  - `ALWAYS`: Message is always sent to the specified user
  - `FILTER`: Message is only sent if the user's message filter settings allow it

3. Typical Usage Pattern:
```c
prfmsg(MESSAGE_TYPE, ...parameters);  // Prepare the message
outprfge(ALWAYS/FILTER, usrnum);      // Send it to user
```

4. Message Broadcasting:
- The system can broadcast messages to multiple users through functions like `outwar` and `outsect`
- These functions use `outprfge` internally to send messages to relevant players

5. Message Filtering:
- When `FILTER` is used, messages are only sent if the user's settings allow it
- This is checked through `warusroff(shpno)->options[MSG_FILTER]`

This two-step process (prepare with `prfmsg`, send with `outprfge`) allows for flexible message handling and proper formatting before delivery to players. It's particularly useful for game events that need to notify multiple players or need to be filtered based on user preferences.

**************************************************************************
** Routes messages to players in the game based on message class and state.
** 
** Parameters:
**   class: Message class type (ALWAYS or FILTER)
**   shpno: Ship/user number to send message to
**
** Behavior:
** - If ship number valid and user in game:
**   - ALWAYS: Outputs message directly via outprf()
**   - FILTER + filtering enabled: Clears message via clrprf()
**   - Otherwise: Outputs via outprf()
** - If invalid ship/not in game: Clears message
**
** Used for sending combat results, scan reports, status updates,
** command responses, error messages, and menu displays to players.
**************************************************************************/