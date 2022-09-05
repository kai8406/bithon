grammar FilterExpression;


prog
   : expression
   ;

expression
  : binaryExpression
  | expression logicOperator expression
  | '(' expression ')'
  ;

binaryExpression
  : unaryExpression comparisonOperator unaryExpression
  ;

unaryExpression
  : VARIABLE
  | STRING_LITERAL | NUMBER_LITERAL
  ;

logicOperator
  : AND | OR
  ;

AND
  : [aA][nN][dD]
  ;

OR
  : [oO][rR]
  ;

comparisonOperator
    : '='
    | '>'
    | '<'
    | '<='
    | '>='
    | '<>'
    | '!='
    ;

VARIABLE
  : [a-zA-Z_][a-zA-Z_0-9]*
  ;

NUMBER_LITERAL
 : [0-9]+('.'?[0-9]+)?
 ;

STRING_LITERAL
 : SQUOTA_STRING;

fragment SQUOTA_STRING
  : '\'' ('\\'. | '\'\'' | ~('\'' | '\\'))* '\'';

WS: [ \n\t\r]+ -> skip;