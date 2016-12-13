

function xy = lotkavoltraPcaudatum (x,t)
r=.7;
k =200;
xy = r*x(1)*(1-(x(1)/k));
endfunction
