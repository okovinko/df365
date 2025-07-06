# This file concerns spec-related scope only

## HARD RULES  (MUST pass → fail if any violated):
- all properties should be marked as required & have no default values in all response schemas
- all properties should be marked as requited & have no default values in all PUT request schemas
- all properties should either have default value or be required in all POST request schemas, never both
- overall check - in all the methods' requests & responses - all properties should either be required or have default value, never both
- POST request properties' default values should be preserved after fixes implementation
- if POST request property has a default value, it should be set as NOT REQUIRED
- all current task **REQUIREMENTS** should be fulfilled
- the specification should be valid
  - every method should include optional `Correlation-Id` header with pattern `^[0-9A-Za-z]{8}$`

## COMMENTING
- you should add comments that reflecti the change reason
