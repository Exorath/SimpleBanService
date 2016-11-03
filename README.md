# SimpleBanService
The SBS offers an endpoint that replies whether or not a user is banned. The implemented database is Azure Table Storage.

##Endpoints
###/players/{uuid} [GET]:
####Gets whether or not this uuid is banned

**Arguments**:
- uuid (string): The players uuid

**Response**: 
```json
{"banned": true, "reason": "Hacking", "expiry": 1478190151447}
```
- banned (boolean)[OPTIONAL]: Whether or not this player is banned. When it is not returned, some kind of error occurred.
- reason (string)[OPTIONAL]: The reason why this player was banned. Only provided when the player is banned and a reason was provided.
- expiry (number)[OPTIONAL]: When this ban will expire in millis since UNIX epoch. Only provided when the player is banned and an expiry was provided.

###/players/{uuid} [PUT]:
####Updates the ban record of a player.
**Body**:
```json
{"banned": true, "reason" : "Racism"}
```

**Arguments**:
- banned (boolean): Whether or not this player must be banned (Setting this to false will unban the player)
- reason (string) [OPTIONAL]: The reason for the ban, never required, only used when banned=*true*
- expiry (number) [OPTIONAL]: The expiry in Unix notation of the ban, never required, only used when banned=*true*

**Response**: 
```json
{"success": true}
```
- success (boolean): Whether or not the record was updated successfully.
- err (string)[OPTIONAL]: Error message only responded when the put was not successful.
##Environment
AZURE_STORAGE_CONNECTION_STRING	DefaultEndpointsProtocol=https;AccountName={ACCOUNT_NAME};AccountKey={ACCOUNT_KEY}
TABLE_NAME	{TABLE_NAME}
PORT	{PORT}
