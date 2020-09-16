# AST-transformations-task

## Bracket Insertion

Command line application for inserting brackets into a string.\
Brackets will be inserted between every two characters of the string
(the brackets between the middle pair can be excluded using the flag)\
Brackets in symmetrical positions are paired. Opening brackets for the first half and closing brackets for the other.

Example: `1234 -> (1(23)4)`

## Build
Build an executable with `installDist` gradle task\
Or use `run` task  with `--args` key.

For example\
`./gradlew installDist` and `./build/install/BracketInsertion/bin/BracketInsertion -w 1234`\
`./gradlew run --args="-w 1234"`\
In both cases, the output should be `(1(23)4)`

## Usage
`./BracketInsertion [-w] [-c] [-b BRACKETS] [STRING]`\
`-w, --wrap` -- place brackets around the line\
`-c, --center` -- insert empty brackets in the middle of a string if the length is even\
`-b BRACKETS, --brackets BRACKETS` -- sequence of brackets to insert. Any combination of {, [, ( separated by spaces. "(" by default\
`STRING` -- string to modify. Also can be passed after application started

## Examples 
`./BracketInsertion 123456`\
`output: 1(2(34)6)5`

`./BracketInsertion -w -c 1234`\
`output: (1(2()3)4)`

`./BracketInsertion -w -c 1234`\
`output: (1(2()3)4)`

`./BracketInsertion -w -b="{ [" 12345`\
`output: {1[2{}3]4}`

`./BracketInsertion -w -c`\
`1234`\
`output: (1(2()3)4)`


