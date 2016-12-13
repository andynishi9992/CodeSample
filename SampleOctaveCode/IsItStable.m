function X = IsItStable (x, y)

%the quilibrium points are (0,0) , (550,0) , (0,220) , (795.15,-102.27)
J = [.8 - 1.6*x/550 - 1.92*y/550,-1.92*x/550; -.266*y/200, .7 - 1.4*y/200-.266*x/200]


X = eig(J)
endfunction
