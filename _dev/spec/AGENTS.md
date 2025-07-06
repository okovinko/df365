# This file concerns spec-related scope only

## HARD RULES  (MUST pass → fail if any violated):
- all properties should be marked as required & have no default values in all response schemas
- all properties should be marked as requited & have no default values in all PUT request schemas
- existing POST request properties' default values should be preserved
- if POST request property has a default value, it should be set as NOT REQUIRED
- the specification should be valid

