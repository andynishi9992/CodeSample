x = 0:1:25;
y = [2,1,10,10,11,21,56,104,137,165,194,217,199,201,182,192,179,190,206,209,196,195,234,210,210,180];

plot(x,y);
title('Exercise 1 plot P.caudatum')
xlabel('days')
ylabel('Poplation density')
hold on

y = lsode("lotkavoltraPcaudatum",2,x);

plot(x,y,'r');
