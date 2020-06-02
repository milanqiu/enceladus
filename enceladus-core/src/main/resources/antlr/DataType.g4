grammar DataType;

@parser::header {
import net.milanqiu.enceladus.antlr.AltNumberStoredParserRuleContext;
}

options {
    language = Java;
    contextSuperClass = AltNumberStoredParserRuleContext;
}

WS: [ \t]+ -> skip;

fragment DIGIT: [0-9];
fragment LETTER: [a-zA-Z];
fragment UNDERLINE: [_];

IDENTIFIER: (LETTER|UNDERLINE) (DIGIT|LETTER|UNDERLINE)*;
NONNEGATIVE_INTEGER: DIGIT+;

dataType: IDENTIFIER ('(' paramList ')')?;
paramList: param (',' param)*;
param: NONNEGATIVE_INTEGER | dataType | ('{' propertyList '}');
propertyList: property (',' property)*;
property: '"' IDENTIFIER '":' dataType;