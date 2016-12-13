function xy = lotkavoltracompetition (x,t)
r=.8;
a=2.4;
k=550;
r1=.7;
a1=.38;
k1=200;
xy = [r*x(1)*(1-((x(1)+a*x(2))/k)),r1*x(2)*(1-((x(2)+a1*x(1))/k1))];

endfunction
